package com.example.kami.View;

import com.example.kami.Model.Level;
import com.mongodb.client.MongoDatabase;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Pos;



public class HomePageView {
    private Scene homePageScene;

    public void init(Stage primaryStage, MongoDatabase db) {
        MongoDatabase finalDb = db;
        primaryStage.setTitle("Home Page");
        Button button1 = new Button();
        button1.setText("Choose Level");
        button1.setStyle("-fx-background-color: #5F9EA0;-fx-pref-width: 150; -fx-text-fill: white;");
        button1.setOnAction(event -> {
            LevelPageView levelPage = new LevelPageView();
            levelPage.init(primaryStage, finalDb);
            primaryStage.setScene(levelPage.getScene());
        });

        Button button2 = new Button();
        button2.setText("Custom A Level");
        button2.setStyle("-fx-background-color: #Ae1253;-fx-pref-width: 150; -fx-text-fill: white;");
        button2.setOnAction(event -> {
//            CustomPageView customPage = new CustomPageView();
//            customPage.init(primaryStage, finalDb);
//            primaryStage.setScene(customPage.getScene());

            ChooseBoardTypeView boardTypeView = new ChooseBoardTypeView();
            boardTypeView.init(primaryStage, finalDb);
            primaryStage.setScene(boardTypeView.getScene());
        });

        VBox vBox = new VBox(button1, button2);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.setStyle("-fx-background-image: url('kami.png'); " + "-fx-background-color: blue;"+
                "-fx-background-size: cover;" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-position: center center;");

        StackPane layout = new StackPane();
        layout.getChildren().add(vBox);

        homePageScene= new Scene(layout, 450, 400);

    }

    public Scene getScene() {
        return this.homePageScene;
    }
}
