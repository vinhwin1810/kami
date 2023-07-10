package com.example.kami.View;

import com.example.kami.Controller.LevelPageController;

import com.example.kami.Model.Level;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.spreadsheet.Grid;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LevelPageView {

    private List<GridPane> levelButtons = new ArrayList<>();

    private Scene LevelPageScene;

    public void init(Stage primaryStage, MongoDatabase db) {
        MongoDatabase finalDb = db;
        primaryStage.setTitle("Level Select");

        LevelPageController levelPageController = new LevelPageController(finalDb);
        List<Level> listOfLevels = levelPageController.findAllLevel();

        for (Level level : listOfLevels) {
            GridPane levelGrid = new GridPane();
            levelGrid.setAlignment(Pos.CENTER);

            Button levelButton = new Button();
            levelButton.setText(level.getName());
            levelButton.setStyle("-fx-background-color: darkslateblue;-fx-pref-width: 100; -fx-text-fill: white;");
            levelButton.setOnAction(e -> {
                GamePageView gamePageView = new GamePageView();
                gamePageView.init(primaryStage, finalDb, level);
            });

            levelGrid.add(levelButton, 0,0);

            GridPane panelEditDelete = new GridPane();
            Button editButton = new Button();
            editButton.setText("edit");
            editButton.setStyle("-fx-background-color: gray;-fx-pref-width: 60; -fx-text-fill: white;");
            editButton.setOnAction(e -> {
                CustomPageView customPageView = new CustomPageView();
                customPageView.init(primaryStage, finalDb, level);
                primaryStage.setScene(customPageView.getScene());
            });

            panelEditDelete.add(editButton, 0,0);

            Button deleteButton = new Button();
            deleteButton.setText("delete");
            deleteButton.setStyle("-fx-background-color: red;-fx-pref-width: 60; -fx-text-fill: white;");
            deleteButton.setOnAction(e -> {
                levelPageController.delete(level.getLevelId());
                LevelPageView levelPage = new LevelPageView();
                levelPage.init(primaryStage, finalDb);
                primaryStage.setScene(levelPage.getScene());
            });
            panelEditDelete.add(deleteButton, 1,0);

            levelGrid.add(panelEditDelete, 1,0);
            levelGrid.setHgap(20);

            levelButtons.add(levelGrid);
        }

        Button backButton = new Button();
        backButton.setText("Back");
        backButton.setStyle("-fx-background-color: #Ae1253; -fx-pref-width: 150; -fx-text-fill: white;");

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HomePageView homePage = new HomePageView();
                homePage.init(primaryStage, finalDb);
                primaryStage.setScene(homePage.getScene());
            }
        });

        VBox levelButtonsBox = new VBox();
        levelButtonsBox.getChildren().addAll(levelButtons);

        levelButtonsBox.setAlignment(Pos.CENTER);
        levelButtonsBox.setSpacing(10);

        VBox vBox = new VBox();

        vBox.getChildren().add(levelButtonsBox);
        vBox.getChildren().add(backButton);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(30);
        vBox.setStyle("-fx-background-color: #5F9EA0;");

        this.LevelPageScene= new Scene(vBox, 500, 500);

        //primaryStage.setScene(menuScene);

    }
    public Scene getScene() {
        return this.LevelPageScene;
    }

}
