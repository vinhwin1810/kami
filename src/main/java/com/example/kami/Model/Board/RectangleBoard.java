package com.example.kami.Model.Board;

import com.example.kami.Model.Cell.AbstractCell;
import com.example.kami.Model.Cell.RectangleCell;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class RectangleBoard extends AbstractBoard {
    private int DEFAULT_SIZE = 25;
    private int columns;
    private int rows;
    public RectangleBoard(){
        super("rectangle", new ArrayList<>());
        this.init();
    }
    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }
    @Override
    public void init() {
        int numCols = (int) this.DEFAULT_WIDTH / DEFAULT_SIZE;
        int numRows = (int) this.DEFAULT_HEIGHT / DEFAULT_SIZE;
        for (int row = 0; row < numRows; row++) {
            ArrayList<AbstractCell> cellRow = new ArrayList<>();
            for (int col = 0; col < numCols; col++) {
                double startX = col * DEFAULT_SIZE;
                double startY = row * DEFAULT_SIZE;
                RectangleCell rectangleCell = new RectangleCell();
                rectangleCell.setColor(Color.WHITE);
                rectangleCell.setStroke(Color.BLACK);
                rectangleCell.setPoints(
                        startX, startY,
                        startX + DEFAULT_SIZE, startY,
                        startX + DEFAULT_SIZE, startY + DEFAULT_SIZE,
                        startX, startY + DEFAULT_SIZE
                );
                rectangleCell.setRow(row);
                rectangleCell.setColumn(col);
                cellRow.add(rectangleCell);

            }
            cells.add(cellRow);
        }
    }



}
