package com.example.kami.View;

import com.example.kami.Controller.CustomPageController;

import com.example.kami.Model.Level;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CustomPageView {
    private Scene customScene;
    private static ObjectProperty<Color> currentColor = new SimpleObjectProperty<>(Color.PURPLE);
    CustomPageController customPageController;

    public void init(Stage primaryStage, MongoDatabase db, String boardType) {
        MongoDatabase finalDb = db;
        primaryStage.setTitle("Custom Level");
        Button backButton = new Button("Back");
        backButton.setStyle(" -fx-pref-width: 80px; -fx-pref-height: 110px;-fx-border-color: black; -fx-border-width: 1px;-fx-background-color: lightgray; -fx-padding: 5 10; -fx-background-radius: 5; -fx-font-weight: bold;");
        customPageController = new CustomPageController(currentColor, db, boardType);

        Button purpleBtn = new Button();
        purpleBtn.setStyle("-fx-pref-width: 100; -fx-pref-height: 100; -fx-background-radius: 0;-fx-background-color: purple;");
        purpleBtn.setOnAction(e -> currentColor.set(Color.PURPLE));

        Button orangeBtn = new Button();
        orangeBtn.setStyle("-fx-pref-width: 100; -fx-pref-height: 100; -fx-background-radius: 0;-fx-background-color: orange;");
        orangeBtn.setOnAction(e -> currentColor.set(Color.ORANGE));


        Button redBtn = new Button();
        redBtn.setStyle("-fx-pref-width: 100; -fx-pref-height: 100; -fx-background-radius: 0;-fx-background-color: red;");
        redBtn.setOnAction(e -> currentColor.set(Color.RED));

        Button whiteBtn = new Button();
        whiteBtn.setStyle("-fx-pref-width: 100; -fx-pref-height: 100; -fx-background-radius: 0; -fx-background-color: white;");
        whiteBtn.setOnAction(e -> currentColor.set(Color.WHITE));
        currentColor.addListener((observable,oldValue,newValue) -> {
            customPageController.updateCurrentColor(newValue);
        });
        Button addHint = new Button("Add Hint");
        addHint.setStyle("-fx-pref-width: 160px; -fx-pref-height: 110px;-fx-border-color: black; -fx-border-width: 1px;-fx-background-color: lightgray; -fx-padding: 5 10; -fx-background-radius: 5; -fx-font-weight: bold;");

        addHint.setOnAction(e -> {
            customPageController.setHint();
        });


        backButton.setOnAction(e -> {
            HomePageView homePage = new HomePageView();
            homePage.init(primaryStage, finalDb);
            primaryStage.setScene(homePage.getScene());
        });

        //Color Panel
        HBox colorPanel = new HBox(purpleBtn, orangeBtn, whiteBtn, redBtn);

        // Control Panel
        HBox controlPanel = new HBox();

        TextField numMoves = new TextField();
        numMoves.setStyle("-fx-alignment: center; -fx-pref-width: 80px; -fx-pref-height: 110px;-fx-border-color: black; -fx-border-width: 1px;-fx-background-color: white; -fx-padding: 5 10; -fx-background-radius: 5; -fx-font-weight: bold;");
        numMoves.setPromptText("Moves: ");
        numMoves.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                numMoves.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        TextField levelname = new TextField();
        levelname.setStyle("-fx-alignment: center; -fx-pref-width: 80px; -fx-pref-height: 110px;-fx-border-color: black; -fx-border-width: 1px;-fx-background-color: white; -fx-padding: 5 10; -fx-background-radius: 5; -fx-font-weight: bold;");
        levelname.setPromptText("Level: ");

        controlPanel.getChildren().add(levelname);
        controlPanel.getChildren().add(numMoves);


        Button createButton = new Button("Save");
        createButton.setStyle("-fx-pref-width: 80px; -fx-pref-height: 110px;-fx-border-color: black; -fx-border-width: 1px;-fx-background-color: lightgray; -fx-padding: 5 10; -fx-background-radius: 5; -fx-font-weight: bold;");
        createButton.setOnAction(e -> {
            customPageController.saveLevel(numMoves.getText(), levelname.getText());
            HomePageView homePageView = new HomePageView();
            homePageView.init(primaryStage, finalDb);
            primaryStage.setScene(homePageView.getScene());
        });

        GridPane buttonGrid = new GridPane();
        buttonGrid.setAlignment(Pos.CENTER);

        buttonGrid.add(numMoves, 0, 0);
        buttonGrid.add(levelname, 1, 0);
        buttonGrid.add( backButton,0, 1);
        buttonGrid.add(createButton, 1, 1);
        buttonGrid.add(addHint, 0, 2, 2, 1);
        //inline styling for buttonGrid
        buttonGrid.setStyle("-fx-border-color: black; -fx-border-width: 1px;-fx-padding: 2px 0px;");

        HBox buttonAndColorBox = new HBox();

        buttonAndColorBox.setAlignment(Pos.CENTER_LEFT);
        colorPanel.setAlignment(Pos.CENTER_LEFT);
        buttonAndColorBox.getChildren().addAll(buttonGrid, colorPanel);

        VBox root = new VBox(customPageController, buttonAndColorBox);
        StackPane layout = new StackPane();
        layout.getChildren().add(root);
        customScene= new Scene(layout, 500, 600);
    }

    public void init(Stage primaryStage, MongoDatabase db, Level level) {
        MongoDatabase finalDb = db;
        primaryStage.setTitle("Custom Level");
        Button backButton = new Button("Back");
        backButton.setStyle(" -fx-pref-width: 80px; -fx-pref-height: 110px;-fx-border-color: black; -fx-border-width: 1px;-fx-background-color: lightgray; -fx-padding: 5 10; -fx-background-radius: 5; -fx-font-weight: bold;");
        customPageController = new CustomPageController(currentColor, db, level);

        Button purpleBtn = new Button();
        purpleBtn.setStyle("-fx-pref-width: 115; -fx-pref-height: 100; -fx-background-radius: 0;-fx-background-color: purple;");
        purpleBtn.setOnAction(e -> currentColor.set(Color.PURPLE));

        Button orangeBtn = new Button();
        orangeBtn.setStyle("-fx-pref-width: 115; -fx-pref-height: 100; -fx-background-radius: 0;-fx-background-color: orange;");
        orangeBtn.setOnAction(e -> currentColor.set(Color.ORANGE));

        Button redBtn = new Button();
        redBtn.setStyle("-fx-pref-width: 115; -fx-pref-height: 100; -fx-background-radius: 0;-fx-background-color: red;");
        redBtn.setOnAction(e -> currentColor.set(Color.RED));

        currentColor.addListener((observable,oldValue,newValue) -> {
            customPageController.updateCurrentColor(newValue);
        });

        Button addHint = new Button("Add Hint");
        addHint.setStyle("-fx-pref-width: 160px; -fx-pref-height: 110px;-fx-border-color: black; -fx-border-width: 1px;-fx-background-color: lightgray; -fx-padding: 5 10; -fx-background-radius: 5; -fx-font-weight: bold;");

        addHint.setOnAction(e -> {
            customPageController.setHint();
        });

        backButton.setOnAction(e -> {
            HomePageView homePage = new HomePageView();
            homePage.init(primaryStage, finalDb);
            primaryStage.setScene(homePage.getScene());
        });

        //Color Panel
        HBox colorPanel = new HBox(purpleBtn, orangeBtn, redBtn);

        // Control Panel
        HBox controlPanel = new HBox();

        TextField numMoves = new TextField();
        numMoves.setStyle("-fx-alignment: center; -fx-pref-width: 80px; -fx-pref-height: 110px;-fx-border-color: black; -fx-border-width: 1px;-fx-background-color: white; -fx-padding: 5 10; -fx-background-radius: 5; -fx-font-weight: bold;");
        //numMoves.setPromptText("Moves: ");
        numMoves.setText(Integer.toString(level.getObservableMoves().get()));
        numMoves.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                numMoves.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        TextField levelname = new TextField();
        levelname.setStyle("-fx-alignment: center; -fx-pref-width: 80px; -fx-pref-height: 110px;-fx-border-color: black; -fx-border-width: 1px;-fx-background-color: white; -fx-padding: 5 10; -fx-background-radius: 5; -fx-font-weight: bold;");
        //levelname.setPromptText("Level: ");
        levelname.setText(level.getName());


        controlPanel.getChildren().add(levelname);
        controlPanel.getChildren().add(numMoves);

        Button editButton = new Button("Edit");
        editButton.setStyle("-fx-pref-width: 80px; -fx-pref-height: 110px;-fx-border-color: black; -fx-border-width: 1px;-fx-background-color: lightgray; -fx-padding: 5 10; -fx-background-radius: 5; -fx-font-weight: bold;");
        editButton.setOnAction(e -> {
            customPageController.editLevel(numMoves.getText(), levelname.getText());
            HomePageView homePageView = new HomePageView();
            homePageView.init(primaryStage, finalDb);
            primaryStage.setScene(homePageView.getScene());
        });

        GridPane buttonGrid = new GridPane();
        buttonGrid.setAlignment(Pos.CENTER);

        buttonGrid.add(numMoves, 0, 0);
        buttonGrid.add(levelname, 1, 0);
        buttonGrid.add( backButton,0, 1);
        buttonGrid.add(editButton, 1, 1);
        buttonGrid.add(addHint, 0, 2, 2, 1);
        //inline styling for buttonGrid
        buttonGrid.setStyle("-fx-border-color: black; -fx-border-width: 1px;-fx-padding: 2px 0px;");

        HBox buttonAndColorBox = new HBox();

        buttonAndColorBox.setAlignment(Pos.CENTER_LEFT);
        colorPanel.setAlignment(Pos.CENTER_LEFT);
        buttonAndColorBox.getChildren().addAll(buttonGrid, colorPanel);

        VBox root = new VBox(customPageController, buttonAndColorBox);
        StackPane layout = new StackPane();
        layout.getChildren().add(root);
        customScene= new Scene(layout, 500, 600);
    }


    public Scene getScene() {
        return customScene;
    }
}
