package com.example.kami.Controller;

import com.example.kami.Model.Board.AbstractBoard;
import com.example.kami.Model.Board.BoardFactory;
import com.example.kami.Model.Cell.AbstractCell;
import com.example.kami.Model.Level;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.shape.Rectangle;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class CustomPageController extends Pane {

    private ObjectProperty<Color> currentColor;

    private String levelCollectionName = "Level";
    private MongoDatabase db;
    private ArrayList<ArrayList<Integer>> hints;

    private MongoCollection<Document> levelCollection;

    private ArrayList<ArrayList<AbstractCell>> cells;

    private boolean addHint;

    private AbstractBoard board;

    private Map<Color, Integer> colorDecoder = new HashMap<>();

    private Level level;

    public CustomPageController(ObjectProperty<Color> currentColor, MongoDatabase db, String boardType) {
        cells = new ArrayList<>();
        this.currentColor = currentColor;
        this.db = db;
        this.levelCollection = db.getCollection(levelCollectionName);
        this.board = BoardFactory.createBoard(boardType);
        this.cells = board.getCells();
        this.addHint = false;
        this.hints = new ArrayList<ArrayList<Integer>>();
        this.initColorDecoder();

        draw();
    }

    public CustomPageController(ObjectProperty<Color> currentColor, MongoDatabase db, Level level) {
        this.level = level;
        this.currentColor = currentColor;
        this.db = db;
        this.levelCollection = db.getCollection(levelCollectionName);
        this.board = level.getBoard();
        this.cells = board.getCells();
        this.addHint = false;
        this.hints = new ArrayList<ArrayList<Integer>>();
        this.initColorDecoder();

        draw();
    }
    private void initColorDecoder(){
        colorDecoder = new HashMap<>();
        colorDecoder.put(Color.PURPLE,1);
        colorDecoder.put(Color.ORANGE,2);
        colorDecoder.put(Color.RED,3);
        colorDecoder.put(Color.WHITE,4);
    }

    private Integer getColor(Color color){
        if(colorDecoder.containsKey(color)){
            return colorDecoder.get(color);
        }
        return 4;
    }

    public void draw() {
        setUp();
        for (int i = 0; i < cells.size(); i++) {
            for (int j = 0; j < cells.get(i).size(); j++) {
                AbstractCell cell = board.getCell(i,j);
                final int finalI = i; // Declare i as final
                final int finalJ = j; // Declare j as final
                cell.setOnMouseClicked(e -> {
                    if(!addHint){
                        cell.setColor(currentColor.get());
                    }else{
                        cell.setStroke(currentColor.get());
                        ArrayList<Integer> hintCell = new ArrayList<Integer>();
                        hintCell.add(finalI); // Use finalI instead of i
                        hintCell.add(finalJ); // Use finalJ instead of j
                        hintCell.add(getColor(currentColor.getValue()));
                        hints.add(hintCell);
                    }

                });
                this.getChildren().add(cell);
            }
        }
    }


    private void setUp() {
        this.setPrefSize(500, 500);
        this.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-padding: 0; -fx-border-style: solid;");
        this.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        Rectangle clip = new Rectangle(0, 0, this.getPrefWidth(), this.getPrefHeight());
        this.setClip(clip);
        this.setRotate(90);
    }


    //get currentColor
    public ObjectProperty<Color> getCurrentColor() {
        return currentColor;
    }

    public void updateCellColor(Color color, AbstractCell cell) {
        cell.setColor(color);
    }

    public void updateCurrentColor(Color currentColor) {
        this.currentColor = new SimpleObjectProperty<Color>(currentColor);
    }

    public void saveLevel(String numberOfMoves, String levelName) {
        int numberOfMove =Integer.parseInt(numberOfMoves);
        ArrayList<ArrayList<String>> listStringCells = board.getStringColors();
        try {
                InsertOneResult result = levelCollection.insertOne(new Document()
                        .append("_id", new ObjectId())
                        .append("boardType", board.getBoardType())
                        .append("levelName", levelName)
                        .append("numberOfMoves", numberOfMove)
                        .append("hint", hints)
                        .append("Board", listStringCells)

                );
                System.out.println("Success! Inserted level id: " + result.getInsertedId());
        } catch (MongoException me){
            System.err.println("Unable to insert level: " + me);
        }
    }

    public void editLevel(String numberOfMoves, String levelName) {
        int numberOfMove = Integer.parseInt(numberOfMoves);
        ArrayList<ArrayList<String>> listStringCells = board.getStringColors();
        try {
            ObjectId objectId = new ObjectId(level.getLevelId());
            Document query = new Document("_id", objectId);
            Document update = new Document("$set", new Document()
                    .append("levelName", levelName)
                    .append("numberOfMoves", numberOfMove)
                    .append("hint", hints)
                    .append("Board", listStringCells));
            levelCollection.updateOne(query, update);
            System.out.println("Successfully updated "+ level.getLevelId());
        } catch (MongoException me) {
            System.err.println("Unable to insert level: " + me);
        }
    }

    public void setHint(){
        if(this.addHint){
            this.addHint = false;
        }else{
            this.addHint = true;
        }
    }
}