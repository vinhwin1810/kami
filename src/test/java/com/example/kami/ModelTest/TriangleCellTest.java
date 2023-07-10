package com.example.kami.ModelTest;

import com.example.kami.Model.Cell.TriangleCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TriangleCellTest {

    @Test
    public void setPointsShouldAddPointsToList() {
        double firstX = 0.0;
        double firstY = 0.0;
        double secondX = 1.0;
        double secondY = 0.0;
        double thirdX = 0.5;
        double thirdY = 1.0;
        String orientation = "up";
        Color color = Color.RED;

        TriangleCell cell = new TriangleCell();

        ObservableList<Double> expectedPoints = FXCollections.observableArrayList(
                firstX, firstY,
                secondX, secondY,
                thirdX, thirdY
        );

        cell.setPoints(firstX, firstY, secondX, secondY, thirdX, thirdY, orientation);
        cell.setColor(color);

        assertEquals(expectedPoints.size(), cell.getPoints().size());
        for (int i = 0; i < expectedPoints.size(); i++) {
            assertEquals(expectedPoints.get(i), cell.getPoints().get(i), 0.0);
        }

        assertEquals(orientation, cell.getOrientation());
        assertEquals(color, cell.getColor());
    }
    @Test
    public void testGetOrientation() {
        TriangleCell cell = new TriangleCell();
        cell.setPoints(0, 0, 1, 1, 2, 0, "up");
        assertEquals("up", cell.getOrientation());
    }

    @Test
    public void testSetColor() {
        TriangleCell cell = new TriangleCell();
        cell.setPoints(0, 0, 1, 1, 2, 0, "up");
        cell.setColor(Color.RED);
        assertEquals(Color.RED, cell.getColor());
    }

    @Test
    public void testSetPoints() {
        TriangleCell cell = new TriangleCell();
        cell.setPoints(0, 0, 1, 1, 2, 0, "up");
        ObservableList<Double> points = cell.getPoints();
        double[] expectedPoints = {0.0, 0.0, 1.0, 1.0, 2.0, 0.0};
        for (int i = 0; i < expectedPoints.length; i++) {
            assertEquals(expectedPoints[i], points.get(i), 0);
        }
    }

}
