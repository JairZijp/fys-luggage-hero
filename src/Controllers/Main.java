package Controllers;

import Models.DB;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Enumeration;
import java.io.IOException;

public class Main extends Application {

    private static Stage thePrimaryStage;
    private static int StageWidth = 1000;
    private static int StageHeight = 600;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Views/LostAndFound.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, StageWidth, StageHeight));
        primaryStage.show();

        thePrimaryStage = primaryStage;
    }

    public static void GoToScreen(String name) throws IOException {
        //Dit is een test..
        System.out.println("Opening" + name);
        Parent root = FXMLLoader.load(Main.class.getResource("../Views/" + name));
        thePrimaryStage.setTitle("Hello World");
        thePrimaryStage.setScene(new Scene(root, StageWidth, StageHeight));
        thePrimaryStage.show();
    }

    public static void main(String[] args) {
        DB database = new DB();
        launch(args);
          
    }
}
