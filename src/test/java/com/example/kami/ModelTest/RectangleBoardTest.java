package com.example.kami.ModelTest;
import static org.junit.Assert.*;

import org.junit.Test;

import com.example.kami.Model.Cell.AbstractCell;
import com.example.kami.Model.Cell.RectangleCell;
import com.example.kami.Model.Board.RectangleBoard;
import javafx.scene.paint.Color;

public class RectangleBoardTest {

    private static final int DEFAULT_SIZE = 25;

    private static class TestRectangleBoard extends RectangleBoard {

        private boolean initCalled = false;

        public TestRectangleBoard() {
            super();
        }

        @Override
        public void init() {
            super.init();
            initCalled = true;
        }

        public boolean isInitCalled() {
            return initCalled;
        }

    }

    @Test
    public void testInit() {
        TestRectangleBoard board = new TestRectangleBoard();
        assertTrue(board.isInitCalled());

        int numCols = (int) board.DEFAULT_WIDTH / DEFAULT_SIZE;
        int numRows = (int) board.DEFAULT_HEIGHT / DEFAULT_SIZE;
        assertEquals(numCols, board.getColumns());
        assertEquals(numRows, board.getRows());

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                AbstractCell cell = board.getCell(row, col);
                assertNotNull(cell);
                assertTrue(cell instanceof RectangleCell);
                assertEquals(row, cell.getRow());
                assertEquals(col, cell.getColumn());
                assertEquals(Color.WHITE, cell.getColor());
                assertEquals(Color.BLACK, cell.getStroke());
                double startX = col * DEFAULT_SIZE;
                double startY = row * DEFAULT_SIZE;
                double endX = startX + DEFAULT_SIZE;
                double endY = startY + DEFAULT_SIZE;
                double[] points = cell.getPoints().stream().mapToDouble(d -> d).toArray();
                assertArrayEquals(new double[]{startX, startY, endX, startY, endX, endY, startX, endY}, points, 0.0);
            }
        }
    }

}

