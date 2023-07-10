package com.example.kami.Controller;


import com.example.kami.Model.Level;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LevelPageController {
    private MongoCollection<Document> levelCollection;
    private String levelCollectionName = "Level";
    private MongoDatabase db;

    public LevelPageController(MongoDatabase db) {
        this.db = db;
        this.levelCollection = db.getCollection(levelCollectionName);
    }

    public List<Level> findAllLevel () {
        List<Level> allLevels = new ArrayList<>();
        MongoCursor<Document> mongoCursor = this.levelCollection.find().iterator();
        try {
            while (mongoCursor.hasNext()) {
                Document document = mongoCursor.next();
                if(document.size() == 5){

                    String levelId =  document.get("_id").toString();
                    int numberOfMoves = (int) document.get("numberOfMoves");
                    String levelName = (String) document.get("levelName");
                    String boardType = (String) document.get("boardType");
                    ArrayList<ArrayList<String>> board = (ArrayList<ArrayList<String>>) document.get("Board");
                    Level level = new Level(levelName, numberOfMoves, board, boardType, levelId);
                    allLevels.add(level);
                }else{
                    String levelId = document.get("_id").toString();
                    int numberOfMoves = (int) document.get("numberOfMoves");
                    String levelName = (String) document.get("levelName");
                    String boardType = (String) document.get("boardType");
                    ArrayList<ArrayList<Integer>> hint = (ArrayList<ArrayList<Integer>>) document.get("hint");
                    ArrayList<ArrayList<String>> board = (ArrayList<ArrayList<String>>) document.get("Board");
                    Level level = new Level(levelName, numberOfMoves, board, boardType,hint, levelId);
                    allLevels.add(level);
                }
            }
        } catch (MongoException e) {
            System.out.println("LevelController findAllLevel Error: " + e);
        }
        return allLevels;
    }

    public void delete (String levelId) {
        try {
            ObjectId objectId = new ObjectId(levelId);
            Document query = new Document("_id", objectId);
            levelCollection.deleteOne(query);
        } catch (MongoException e) {
            System.out.println("LevelController delete Error: " + e);
        }

    }

}
