package com.example.kami.View;

import com.mongodb.client.MongoDatabase;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChooseBoardTypeView {
    private Scene chooseBoardTypeScene;

    public void init(Stage primaryStage, MongoDatabase db) {

        MongoDatabase finalDb = db;


        Button button1 = new Button();
        button1.setText("Triangle Board");
        button1.setStyle("-fx-background-color: #5F9EA0;-fx-pref-width: 150; -fx-text-fill: white;");
        button1.setOnAction(e -> {
            CustomPageView customPageView = new CustomPageView();
            customPageView.init(primaryStage, finalDb, "triangle");
            primaryStage.setScene(customPageView.getScene());
        });

        Button button2 = new Button();
        button2.setText("Rectangle Board");
        button2.setStyle("-fx-background-color: #5F9EA0;-fx-pref-width: 150; -fx-text-fill: white;");
        button2.setOnAction(e -> {
            CustomPageView customPageView = new CustomPageView();
            customPageView.init(primaryStage, finalDb, "rectangle");
            primaryStage.setScene(customPageView.getScene());
        });


        VBox vBox = new VBox(button1, button2);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.setStyle("-fx-background-image: url('kami.png'); " + "-fx-background-color: blue"+
                "-fx-background-size: cover;" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-position: center center;");
        StackPane layout = new StackPane();
        layout.getChildren().add(vBox);

        chooseBoardTypeScene = new Scene(layout, 450, 400);
    }
    public Scene getScene() {
        return this.chooseBoardTypeScene;
    }
}
