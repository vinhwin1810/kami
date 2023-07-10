package com.example.kami.ModelTest;

import com.example.kami.Model.Cell.RectangleCell;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RectangleCellTest {

    private RectangleCell rectangleCell;

    @Before
    public void setUp() {
        rectangleCell = new RectangleCell();
    }

    @Test
    public void testSetPoints() {
        double[] expectedPoints = {1.0, 1.0, 2.0, 2.0, 3.0, 3.0, 4.0, 4.0};
        rectangleCell.setPoints(1, 1, 2, 2, 3, 3, 4, 4);
        ObservableList<Double> actualPoints = rectangleCell.getPoints();

        assertEquals(expectedPoints.length, actualPoints.size());

        for (int i = 0; i < expectedPoints.length; i++) {
            assertEquals(expectedPoints[i], actualPoints.get(i), 0.0);
        }
    }

    @Test
    public void testSetColor() {
        rectangleCell.setColor(Color.RED);
        assertEquals(Color.RED, rectangleCell.getColor());
    }

    @Test
    public void testSetRow() {
        rectangleCell.setRow(5);
        assertEquals(5, rectangleCell.getRow());
    }

    @Test
    public void testSetColumn() {
        rectangleCell.setColumn(3);
        assertEquals(3, rectangleCell.getColumn());
    }
}
