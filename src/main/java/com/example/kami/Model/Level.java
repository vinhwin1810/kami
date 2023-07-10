package com.example.kami.Model;

import com.example.kami.Model.Board.AbstractBoard;
import com.example.kami.Model.Board.BoardFactory;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;

public class Level{
    private String levelId;
    private String name;
    int hintCounter;
    int numHints;
    private String boardType;
    private ArrayList<ArrayList<String>> stringCellColors;
    private IntegerProperty observableMoves;
    private ArrayList<ArrayList<Integer>> hint;

    private AbstractBoard board;


    //private StringProperty winner = new SimpleStringProperty("");
    public Level(String name, int numberOfMoves, ArrayList<ArrayList<String>> stringCellColors, String boardType, String levelId){
        setName(name);
        setObservableMoves(numberOfMoves);
        setStringCellColors(stringCellColors);
        setBoardType(boardType);
        setLevelId(levelId);
        setBoard(boardType);

        board.massColoring(stringCellColors);
    }

    public Level(String name, int numberOfMoves, ArrayList<ArrayList<String>> stringCellColors, String boardType, ArrayList<ArrayList<Integer>> hint, String levelId) {
        setName(name);
        setObservableMoves(numberOfMoves);
        setStringCellColors(stringCellColors);
        setBoardType(boardType);
        setLevelId(levelId);
        setBoard(boardType);

        board.massColoring(stringCellColors);
        this.hint = hint;
        this.numHints = hint.size();
        this.hintCounter = 0;


    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getLevelId() {
        return this.levelId;
    }

    public void setBoard(String boardType) {
        this.board = BoardFactory.createBoard(boardType);
    }

    public AbstractBoard getBoard() {
        return this.board;
    }
    public void setStringCellColors(ArrayList<ArrayList<String>> stringCellColors) {
        this.stringCellColors = stringCellColors;
    }

    public ArrayList<ArrayList<String>> getStringCellColors() {
        return stringCellColors;
    }

    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }

    public String getBoardType() {
        return this.boardType;
    }


    public void setObservableMoves(int moves) {
        this.observableMoves = new SimpleIntegerProperty(moves);
    }


    public IntegerProperty getObservableMoves() { return this.observableMoves;}


    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public ArrayList<Integer> getHint(){
        ArrayList<Integer> res = new ArrayList<>();
        if(this.hintCounter < numHints) {
            res = hint.get(hintCounter);
            hintCounter += 1;
            return res;

        }else{
            return res;
        }


    }

    public void hintDecrement(){
        this.hintCounter--;
    }
}



