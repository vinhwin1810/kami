package com.example.kami.Model.Cell;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import java.util.HashMap;
import java.util.Map;



public abstract class AbstractCell extends Polygon {
    private Color color;
    private int row;
    private int column;
    private Map<Integer, Color> colorDecoder = new HashMap<>();

    public AbstractCell(){
        this.initColorDecoder();
    }

    public void setColor(Color color) {

        this.color = color;
        this.setFill(color);

    }
    public void setColor(int color){
        this.color = getPaintColor(color);
        setFill(this.color);
    }
    private void initColorDecoder(){
        colorDecoder = new HashMap<>();
        colorDecoder.put(1, Color.PURPLE);
        colorDecoder.put(2, Color.ORANGE);
        colorDecoder.put(3, Color.RED);
        colorDecoder.put(4, Color.WHITE);
    }


    public Color getColor() {
        return this.color;
    }

    public void setRow(int row){
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    public int getRow(){
        return this.row;
    }



    public int getColumn() {
        return this.column;
    }
    private Color getPaintColor(Integer color){
        if(colorDecoder.containsKey(color)){
            return colorDecoder.get(color);
        }
        return Color.WHITE;
    }

}
