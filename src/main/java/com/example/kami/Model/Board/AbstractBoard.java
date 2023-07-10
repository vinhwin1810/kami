package com.example.kami.Model.Board;

import com.example.kami.Model.Cell.AbstractCell;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public abstract class AbstractBoard {
    public String boardType;
    ArrayList<ArrayList<AbstractCell>> cells;

    public int DEFAULT_WIDTH = 500;
    public int DEFAULT_HEIGHT = 500;

    public AbstractBoard(String boardType, ArrayList<ArrayList<AbstractCell>> cells) {
        this.boardType = boardType;
        this.cells = cells;
    }

    public void massColoring(ArrayList<ArrayList<String>> stringColor) {
        for (int i = 0; i < cells.size(); i ++) {
            for(int j = 0; j < cells.get(i).size(); j++) {
                AbstractCell cell = this.getCell(i,j);
                cell.setColor(Color.web(stringColor.get(i).get(j)));
            }
        }
    }

    public ArrayList<ArrayList<AbstractCell>> getCells() {
        return cells;
    }

    public AbstractCell getCell(int row, int column) {
        return cells.get(row).get(column);
    }

    public  ArrayList<ArrayList<String>> getStringColors() {
        ArrayList<ArrayList<String>> stringColors = new ArrayList<>();
        for (int i = 0; i < cells.size(); i ++) {
            ArrayList<String> row = new ArrayList<>();
            for(int j = 0; j < cells.get(i).size(); j++) {
                row.add(this.getCell(i,j).getColor().toString());
            }
            stringColors.add(row);
        }
        return stringColors;
    }

    public  String getBoardType() {
        return this.boardType;
    };


    public abstract void init();

}
