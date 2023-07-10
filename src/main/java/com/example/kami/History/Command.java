package com.example.kami.History;

import com.example.kami.Model.Cell.AbstractCell;
import javafx.beans.property.IntegerProperty;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Command {

    ArrayList<AbstractCell> cells;
    String originColor;

    public Command() {
        cells = new ArrayList<>();
    }

    public void addCell(AbstractCell cell) {
        cells.add(cell);
    }

    public void setOriginColor(String originColor) {
        this.originColor = originColor;
    }

    public String getOriginColor() {
        return originColor;
    }

    public ArrayList<AbstractCell> getCells() {
        return cells;
    }

}
