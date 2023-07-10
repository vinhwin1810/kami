package com.example.kami.Controller;

import com.example.kami.History.Command;
import com.example.kami.History.History;
import com.example.kami.Model.Board.AbstractBoard;
import com.example.kami.Model.Board.BoardFactory;
import javafx.animation.Animation;

import com.example.kami.Model.Cell.AbstractCell;
import com.example.kami.Model.Cell.TriangleCell;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;

import javafx.util.Duration;

import java.time.*;
import java.lang.Thread;

import java.util.ArrayList;

import com.example.kami.Model.Level;
import javafx.beans.property.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class GamePageController extends Pane {

    private Level level;

    private IntegerProperty currentMoves;
    private StringProperty winner;
    private ArrayList<ArrayList<AbstractCell>> cells;
    public ObjectProperty<Color> currentColor;
    private AbstractBoard board;

    private History history;



    public GamePageController( Level level, ObjectProperty<Color> currentColor) {
        this.currentColor = currentColor;
        this.board = level.getBoard();
        this.cells = board.getCells();
        this.level = level;
        this.winner = new SimpleStringProperty("");
        this.currentMoves = level.getObservableMoves();
        this.history = new History();

        draw();
    }

    public void draw() {

        setup();
        for (int i = 0; i < cells.size(); i++) {
            for (int j = 0; j < cells.get(i).size(); j++) {
                AbstractCell cell = board.getCell(i,j);
                cell.setOnMouseClicked(e -> {

                    if (this.winner.get() == "" && currentMoves.get() > 0) {
                        floodFill(currentColor.get(), cell.getColor(), cell);
                    }

                    if (this.isWon()) {
                        this.winner.set("won");
                    } else if (currentMoves.get() <= 0 ) {
                        this.winner.set("lost");
                    }

                });
                this.getChildren().add(cell);
            }
        }
    }

    private void setup() {
        this.setPrefSize(500, 500);
        this.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-padding: 0; -fx-border-style: solid;");
        this.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        Rectangle clip = new Rectangle(0, 0, this.getPrefWidth(), this.getPrefHeight());
        this.setClip(clip);
        this.setRotate(90);
    }

    public void updateCurrentColor(Color currentColor) {
        this.currentColor = new SimpleObjectProperty<>(currentColor);
    }

    public IntegerProperty getCurrentMoves() { return this.currentMoves; }
    public StringProperty getWinner() {return this.winner; }

    public void floodFill(Color colorToPaint, Color originColor, AbstractCell cell) {

        int row = cell.getRow();
        int column =cell.getColumn();
        if (cells.get(row).get(column).getColor().equals(colorToPaint)) return;
        currentMoves.set(currentMoves.get() - 1);

        history.addCommand(new Command());
        history.setColorForLatestCommand(cell.getColor().toString());

        fill(colorToPaint, originColor, column, row);
    }

    private void fill(Color colorToPaint, Color originColor, int column, int row) {
        if (row >= cells.size() || column >= cells.get(0).size() || row < 0 || column < 0) {
            return;
        }
        if (!cells.get(row).get(column).getColor().equals(originColor) || cells.get(row).get(column).getColor().equals(colorToPaint)) {
            return;
        }

        AbstractCell cell = cells.get(row).get(column);
        cell.setColor(colorToPaint);

        history.addToLatestCommand(cell);

        switch (level.getBoardType().toLowerCase()) {
            case "triangle":
                TriangleCell tri =  (TriangleCell) cells.get(row).get(column);
                if (tri.getOrientation() == "up") {
                    fill(colorToPaint, originColor, column - 1, row);
                    fill(colorToPaint, originColor, column + 1, row);
                    fill(colorToPaint, originColor, column, row + 1);
                } else if (tri.getOrientation() == "down") {
                    fill(colorToPaint, originColor, column - 1, row);
                    fill(colorToPaint, originColor, column + 1, row);
                    fill(colorToPaint, originColor, column, row - 1);
                }
            case "rectangle":
                fill(colorToPaint, originColor, column - 1, row);
                fill(colorToPaint, originColor, column + 1, row);
                fill(colorToPaint, originColor, column, row + 1);
                fill(colorToPaint, originColor, column, row - 1);
        }
    }
    public boolean isWon(){
        Color color = board.getCell(0,0).getColor();
        for (int i = 0; i < cells.size(); i++){
            for(AbstractCell cell: cells.get(i)){
                if (!(cell.getColor().equals(color))){
                    return false;
                }
            }
        }
        return true;
    }

    public String getHint(){
        ArrayList<Integer> cellCor = level.getHint();
        if(cellCor.size() > 0){
            AbstractCell cell = board.getCell(cellCor.get(0),cellCor.get(1));
            Integer colorToChange = cellCor.get(2);
            Color curColor = cell.getColor();
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.2), event -> {
                if(cell.getColor().equals(curColor)){
                    cell.setColor(colorToChange);
                }else{
                    cell.setColor(curColor);
                }

            }));

            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();

            Timeline stopTimeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
                timeline.stop();
            }));
            stopTimeline.play();
            return "good hint";
        }else{
            return "no hint";
        }

    }

    public void undo () {
        if (!history.isEmpty()) {
            history.undo();
            currentMoves.set(currentMoves.get() + 1);
            level.hintDecrement();
            this.winner.set("");
        }
    }

    public void reset() {
       while (!history.isEmpty()) {
           undo();
       }
    }
}
