package com.example.kami.Model.Cell;

public class RectangleCell extends AbstractCell {
    public RectangleCell(){}


    public void RectangleCell(double firstX, double firstY, double secondX, double secondY, double thirdX, double thirdY,
                          double forthX, double forthY) {
        this.getPoints().addAll(
                firstX, firstY,
                secondX, secondY,
                thirdX, thirdY,
                forthX, forthY
        );
    }

    public void setPoints(double firstX, double firstY, double secondX, double secondY, double thirdX, double thirdY,
                          double forthX, double forthY) {
        this.getPoints().addAll(
                firstX, firstY,
                secondX, secondY,
                thirdX, thirdY,
                forthX, forthY
        );

    }

}
