package Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu {

    @FXML
    Stage PrimaryStage;
    @FXML
    Scene scene;

    @FXML
    public void GoToLaf(ActionEvent event) throws IOException {
        GoToScreen("LostAndFound.fxml");
    }

    @FXML
    public void GoToStats(ActionEvent event) throws IOException {
        GoToScreen("Statistics.fxml");
    }

    @FXML
    public void GoToUsers(ActionEvent event) throws IOException {
        GoToScreen("Users.fxml");
    }

    @FXML
    public void GoToImport(ActionEvent event) throws IOException {
        GoToScreen("ImportExcel.fxml");
    }

    public void GoToScreen(String name) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(name));
        PrimaryStage.setTitle("Hello World");
        PrimaryStage.setScene(new Scene(root, 999, 999));
        PrimaryStage.show();

//        Parent root = FXMLLoader.load(getClass().getResource(name));
//        Scene ding = new Scene(root, 400, 400);
//        stage.setScene(ding);
//        stage.show();
    }
}
