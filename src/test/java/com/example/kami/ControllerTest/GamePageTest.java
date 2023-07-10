package com.example.kami.ControllerTest;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ArrayList;

import com.example.kami.Model.Level;
import com.example.kami.Model.Cell.AbstractCell;
import com.example.kami.Model.Board.AbstractBoard;
import com.example.kami.Controller.GamePageController;
import javafx.beans.property.ObjectProperty;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
public class GamePageTest {

    @Mock
    private Level levelMock;
    @Mock
    private ObjectProperty<Color> currentColorMock;

    private GamePageController gamePageController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ArrayList<ArrayList<AbstractCell>> cellsMock = new ArrayList<>();
        AbstractBoard boardMock = mock(AbstractBoard.class);
        when(boardMock.getCells()).thenReturn(cellsMock);
        when(levelMock.getBoard()).thenReturn(boardMock);
        gamePageController = new GamePageController(levelMock, currentColorMock);
    }

    @Test
    public void testUpdateCurrentColor() {
        Color newColor = Color.BLUE;
        ObjectProperty<Color> newColorProperty = new SimpleObjectProperty<>(newColor);
        gamePageController.updateCurrentColor(newColor);

        assertEquals(newColorProperty.get(), gamePageController.currentColor.get());
    }

//    @Test
//    public void testFloodFill() {
//        Color colorToPaint = Color.BLUE;
//        Color originColor = Color.RED;
//
//        // create a 3x3 board filled with cells of the origin color
//        ArrayList<ArrayList<AbstractCell>> cellsMock = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            ArrayList<AbstractCell> rowMock = new ArrayList<>();
//            for (int j = 0; j < 3; j++) {
//                AbstractCell cellMock = mock(AbstractCell.class);
//                when(cellMock.getRow()).thenReturn(i);
//                when(cellMock.getColumn()).thenReturn(j);
//                when(cellMock.getColor()).thenReturn(originColor);
//                rowMock.add(cellMock);
//            }
//            cellsMock.add(rowMock);
//        }
//
//        // create a mock board with the cells
//        AbstractBoard boardMock = mock(AbstractBoard.class);
//        when(boardMock.getCells()).thenReturn(cellsMock);
//
//        // set the cell to use for the flood fill operation
//        AbstractCell cellMock = cellsMock.get(1).get(2);
//
//        // perform the flood fill operation
//        gamePageController.floodFill(colorToPaint, originColor, cellMock);
//
//        // verify that the getColor method was called twice and the set color is correct
//        verify(cellMock, times(2)).getColor();
//        assertEquals(colorToPaint, cellMock.getColor());
//    }
//
//
//    @Test
//    public void testIsWon() {
//        AbstractCell cellMock = mock(AbstractCell.class);
//        when(cellMock.getColor()).thenReturn(Color.RED);
//
//        // Create a 3x3 board filled with red cells
//        ArrayList<ArrayList<AbstractCell>> cellsMock = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            ArrayList<AbstractCell> rowMock = new ArrayList<>();
//            for (int j = 0; j < 3; j++) {
//                rowMock.add(cellMock);
//            }
//            cellsMock.add(rowMock);
//        }
//
//        AbstractBoard boardMock = mock(AbstractBoard.class);
//        when(boardMock.getCells()).thenReturn(cellsMock);
//        when(boardMock.getCell(0, 0)).thenReturn(cellMock);
//
//        when(levelMock.getBoard()).thenReturn(boardMock);
//
//        boolean isWon = gamePageController.isWon();
//        assertEquals(true, isWon);
//    }
//
//    @Test
//    public void testGetHint() {
//        ArrayList<Integer> hintMock = new ArrayList<>();
//        hintMock.add(1);
//        hintMock.add(2);
//        hintMock.add(3);
//
//        when(levelMock.getHint()).thenReturn(hintMock);
//
//        String hint = gamePageController.getHint();
//        assertEquals("Hint: Click on cell (1, 2) to change color to 3", hint);
//    }
}
