package com.example.kami.ModelTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.example.kami.Model.Board.AbstractBoard;
import com.example.kami.Model.Cell.AbstractCell;

import javafx.scene.paint.Color;

public class AbstractBoardAndCellTest {

    private static final String BOARD_TYPE = "TestBoard";
    private static final int BOARD_WIDTH = 5;
    private static final int BOARD_HEIGHT = 5;

    private AbstractBoard board;
    private ArrayList<ArrayList<AbstractCell>> cells;

    @Before
    public void setUp() {
        // Create a test board with dummy cells
        cells = new ArrayList<ArrayList<AbstractCell>>();
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            ArrayList<AbstractCell> row = new ArrayList<AbstractCell>();
            for (int j = 0; j < BOARD_WIDTH; j++) {
                row.add(new AbstractCell() {
                });
            }
            cells.add(row);
        }
        board = new AbstractBoard(BOARD_TYPE, cells) {
            @Override
            public void init() {
                // Do nothing
            }
        };
    }

    @Test
    public void testGetBoardType() {
        assertEquals(BOARD_TYPE, board.getBoardType());
    }

    @Test
    public void testGetCells() {
        assertEquals(cells, board.getCells());
    }

    @Test
    public void testGetCell() {
        AbstractCell cell = board.getCell(0, 0);
        assertNotNull(cell);
        assertEquals(0, cell.getRow());
        assertEquals(0, cell.getColumn());
    }

    @Test
    public void testMassColoring() {
        // Create a test string color matrix
        ArrayList<ArrayList<String>> stringColors = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            ArrayList<String> row = new ArrayList<String>();
            for (int j = 0; j < BOARD_WIDTH; j++) {
                row.add("#ff0000"); // Red
            }
            stringColors.add(row);
        }
        // Color the cells
        board.massColoring(stringColors);
        // Check that the colors have been set correctly
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                assertEquals(Color.web("#ff0000"), cells.get(i).get(j).getColor());
            }
        }
    }

    @Test
    public void testGetStringColors() {
        // Set some colors manually
        cells.get(0).get(0).setColor(Color.RED);  // Set color at (0, 0) to red
        cells.get(1).get(1).setColor(Color.GREEN);
        cells.get(2).get(2).setColor(Color.BLUE);

        // Color the cells
        ArrayList<ArrayList<String>> stringColors = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            ArrayList<String> row = new ArrayList<String>();
            for (int j = 0; j < BOARD_WIDTH; j++) {
                row.add("#000000"); // Black
            }
            stringColors.add(row);
        }
        board.massColoring(stringColors);

        // Get the string colors
        stringColors = board.getStringColors();
        // Check that the string colors are correct
        assertEquals("0x000000ff", stringColors.get(0).get(0));
        assertEquals("0x000000ff", stringColors.get(1).get(1));
        assertEquals("0x000000ff", stringColors.get(2).get(2));
    }

    @Test
    public void testAbstractCell() {
        AbstractCell cell = new AbstractCell() {
        };
        // Test setting and getting color
        cell.setColor(Color.RED);
        assertEquals(Color.RED, cell.getColor());
        // Test setting and getting row and column
        cell.setRow(1);
        cell.setColumn(2);
        assertEquals(1, cell.getRow());
        assertEquals(2, cell.getColumn());
    }

    @Test
    public void testAbstractBoard() {
        // Test getting the board type
        ArrayList<ArrayList<AbstractCell>> cells = new ArrayList<>();
        ArrayList<AbstractCell> row = new ArrayList<>();
        AbstractCell cell = new AbstractCell() {
            public void init() {
                // Do nothing
            }
        };
        row.add(cell);
        cells.add(row);

        AbstractBoard board = new AbstractBoard(BOARD_TYPE, cells) {
            @Override
            public void init() {
                // Do nothing
            }
        };
        assertEquals(BOARD_TYPE, board.getBoardType());

        // Test getting the cells
        assertEquals(cells, board.getCells());

        // Test getting a specific cell
        AbstractCell expectedCell = cells.get(0).get(0);
        AbstractCell actualCell = board.getCell(0, 0);
        assertNotNull(actualCell);
        assertEquals(expectedCell, actualCell);

        // Test mass coloring
        ArrayList<ArrayList<String>> colorStrings = new ArrayList<>();
        ArrayList<String> colorStringRow = new ArrayList<>();
        colorStringRow.add("#000000"); // black
        colorStrings.add(colorStringRow);
        board.massColoring(colorStrings);

        // Test getting string colors
        ArrayList<ArrayList<String>> stringColors = board.getStringColors();
        assertEquals(cells.size(), stringColors.size());
        for (int i = 0; i < cells.size(); i++) {
            assertEquals(cells.get(i).size(), stringColors.get(i).size());
            for (int j = 0; j < cells.get(i).size(); j++) {
                assertEquals(cells.get(i).get(j).getColor().toString(), stringColors.get(i).get(j));
            }
        }
    }

}

