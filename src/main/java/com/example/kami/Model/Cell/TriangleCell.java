package com.example.kami.Model.Cell;

import javafx.scene.paint.Color;

public class TriangleCell extends AbstractCell {
    private String orientation;

    public TriangleCell() {};

    public TriangleCell(double firstX, double firstY, double secondX, double secondY, double thirdX, double thirdY, Color color) {
        this.getPoints().addAll(
                firstX, firstY,
                secondX, secondY,
                thirdX, thirdY
        );
        this.setColor(color);
    }

    public void setPoints(double firstX, double firstY, double secondX, double secondY, double thirdX, double thirdY, String orientation) {
        this.getPoints().addAll(
                firstX, firstY,
                secondX, secondY,
                thirdX, thirdY
        );
        this.orientation = orientation;
    }

    public String getOrientation(){
        return this.orientation;
    }
}
