package com.example.kami;

import com.example.kami.View.HomePageView;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application {
    String connection_URI;
    String databaseName = "Kami";
    public void start(Stage primaryStage) throws Exception {
        connection_URI = "mongodb+srv://csc260-w2023-project2:project2@kamidb.4qe2vr0.mongodb.net/";
        MongoClient mongoClient = MongoClients.create(connection_URI);
        MongoDatabase db = mongoClient.getDatabase(databaseName);

//
        HomePageView homePage = new HomePageView();
        homePage.init(primaryStage, db);

        primaryStage.setScene(homePage.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}