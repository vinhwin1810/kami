package com.example.kami.Model.Board;

public class BoardFactory {
    public static AbstractBoard createBoard(String boardType) {
        switch (boardType.toLowerCase()) {
            case "triangle":
                return new TriangleBoard();
            case "rectangle":
                return new RectangleBoard();
            default:
                throw new IllegalArgumentException("Invalid Board Type: " + boardType);
        }
    }
}
