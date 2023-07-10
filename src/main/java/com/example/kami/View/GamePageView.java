package com.example.kami.View;
import com.example.kami.Controller.GamePageController;
import com.example.kami.Model.Cell.AbstractCell;

import com.example.kami.Model.Level;
import com.mongodb.client.MongoDatabase;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Popup;
import javafx.stage.Stage;

import javafx.geometry.Pos;



public class GamePageView{

    private static ObjectProperty<Color> currentColor = new SimpleObjectProperty<>(Color.PURPLE);

    private static IntegerProperty currentMovesLeft;
    private static StringProperty winner;
    private static int movesLeft = 5;
    private static Label movesLeftLabel;

    public void init(Stage primaryStage, MongoDatabase db, Level level) {
        MongoDatabase finalDb = db;
        GamePageController gameController = new GamePageController(level, currentColor);

        // Apply inline CSS to the GridPane to center its contents

        currentMovesLeft = gameController.getCurrentMoves();
        winner = gameController.getWinner();

        //POP UP PERFECT WHEN WINNER IS FOUND
        winner.addListener((observable, oldValue, newValue) -> {
            if (newValue == "won") {
                Popup popup = new Popup();
                Label messageLabel = new Label("Perfect!");
                messageLabel.setStyle("-fx-font-size: 50px; -fx-font-weight: bold;");
                VBox messageBox = new VBox(messageLabel);
                messageBox.setAlignment(Pos.CENTER);
                messageBox.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 2px; -fx-padding: 10px;");
                popup.getContent().add(messageBox);
                popup.setAutoHide(true);

                popup.show(primaryStage);

            } else if(newValue == "lost") {
                Popup popup = new Popup();
                Label messageLabel = new Label("Loser!");
                messageLabel.setStyle("-fx-font-size: 50px; -fx-font-weight: bold;");
                VBox messageBox = new VBox(messageLabel);
                messageBox.setAlignment(Pos.CENTER);
                messageBox.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 2px; -fx-padding: 10px;");
                popup.getContent().add(messageBox);
                popup.setAutoHide(true);

                popup.show(primaryStage);
            }
        });

        Button purpleBtn = new Button();
        //inline styling for purple button
        purpleBtn.setStyle("-fx-pref-width: 100; -fx-pref-height: 100; -fx-background-radius: 0;-fx-background-color: purple;");
        purpleBtn.setOnAction(e -> currentColor.set(Color.PURPLE));

        Button orangeBtn = new Button();
        //inline styling for orange button
        orangeBtn.setStyle("-fx-pref-width: 100; -fx-pref-height: 100; -fx-background-radius: 0;-fx-background-color: orange;");
        orangeBtn.setOnAction(e -> currentColor.set(Color.ORANGE));

        Button redBtn = new Button();
        //inline styling for red button
        redBtn.setStyle("-fx-pref-width: 100; -fx-pref-height: 100; -fx-background-radius: 0;-fx-background-color: red;");
        redBtn.setOnAction(e -> currentColor.set(Color.RED));

        Button whiteBtn = new Button();
        whiteBtn.setStyle("-fx-pref-width: 100; -fx-pref-height: 100; -fx-background-radius: 0; -fx-background-color: white;");
        whiteBtn.setOnAction(e -> currentColor.set(Color.WHITE));

        currentColor.addListener((observable,oldValue,newValue) -> {
            gameController.updateCurrentColor(newValue);
        });
        HBox colorPanel = new HBox(purpleBtn, orangeBtn, whiteBtn, redBtn);

        movesLeftLabel = new Label();
        movesLeftLabel.textProperty().bind(currentMovesLeft.asString());
        // inline styling for moveLefts
        movesLeftLabel.setStyle("-fx-alignment: center; -fx-pref-width: 80px; -fx-pref-height: 110px;-fx-border-color: black; -fx-border-width: 1px;-fx-background-color: lightgray; -fx-padding: 5 10; -fx-background-radius: 5; -fx-font-weight: bold;");

        Button hintButton = new Button("Hint");
        hintButton.setStyle("-fx-pref-width: 160px; -fx-pref-height: 110px;-fx-border-color: black; -fx-border-width: 1px;-fx-background-color: lightgray; -fx-padding: 5 10; -fx-background-radius: 5; -fx-font-weight: bold;");
        hintButton.setOnAction(e ->{
            String hint = gameController.getHint();
            if(hint.equals("no hint")){
                Popup popup = new Popup();
                Label messageLabel = new Label("Out of Hints");
                messageLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
                VBox messageBox = new VBox(messageLabel);
                messageBox.setAlignment(Pos.CENTER);
                messageBox.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 2px; -fx-padding: 10px;");
                popup.getContent().add(messageBox);
                popup.setAutoHide(true);
                popup.show(primaryStage);
            }
        });

        Button undoButton = new Button("Undo");
        //iline styling for undo
        undoButton.setStyle("-fx-pref-width: 80px; -fx-pref-height: 110px;-fx-border-color: black; -fx-border-width: 1px;-fx-background-color: lightgray; -fx-padding: 5 10; -fx-background-radius: 5; -fx-font-weight: bold;");

        undoButton.setOnAction(e -> {
            gameController.undo();
        });

        Button resetButton = new Button("Reset");
        //inline styling for resetButton
        resetButton.setStyle("-fx-pref-width: 80px; -fx-pref-height: 110px;-fx-border-color: black; -fx-border-width: 1px;-fx-background-color: lightgray; -fx-padding: 5 10; -fx-background-radius: 5; -fx-font-weight: bold;");

        resetButton.setOnAction(e -> {
            gameController.reset();
        });


        Button backBtn = new Button("Back");
        //inline styling for backbutton
        backBtn.setStyle(" -fx-pref-width: 80px; -fx-pref-height: 110px;-fx-border-color: black; -fx-border-width: 1px;-fx-background-color: lightgray; -fx-padding: 5 10; -fx-background-radius: 5; -fx-font-weight: bold;");

        backBtn.setOnAction(e -> {
            LevelPageView levelPage = new LevelPageView();
            levelPage.init(primaryStage, finalDb);
            primaryStage.setScene(levelPage.getScene());
        });
        GridPane buttonGrid = new GridPane();
        buttonGrid.setAlignment(Pos.CENTER);

        buttonGrid.add(undoButton, 0, 0);
        buttonGrid.add(resetButton, 1, 0);
        buttonGrid.add(backBtn, 0, 1);
        buttonGrid.add(movesLeftLabel, 1, 1);
        buttonGrid.add(hintButton, 0, 2, 2, 1);
        //inline styling for buttonGrid
        buttonGrid.setStyle("-fx-border-color: black; -fx-border-width: 1px;-fx-border-color: black;-fx-padding: 2px 0px;");


        HBox buttonAndColorBox = new HBox();

        buttonAndColorBox.setAlignment(Pos.CENTER_LEFT);
        colorPanel.setAlignment(Pos.CENTER_LEFT);
        buttonAndColorBox.getChildren().addAll(buttonGrid, colorPanel);


        VBox root = new VBox(gameController, buttonAndColorBox);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 500, 600);
        primaryStage.setTitle("Game Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}