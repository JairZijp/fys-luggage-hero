package Controllers;

import java.io.IOException; 
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class Menu{

    @FXML
    public void GoToLaf(ActionEvent event) throws IOException {
        Main.GoToScreen("LostAndFound.fxml");
    }

    @FXML
    public void GoToStats(ActionEvent event) throws IOException {
        Main.GoToScreen("Statistics.fxml");
    }

    @FXML
    public void GoToUsers(ActionEvent event) throws IOException {
        Main.GoToScreen("Users.fxml");
    }

    @FXML
    public void GoToImport(ActionEvent event) throws IOException {
        Main.GoToScreen("ImportExcel.fxml");
    }


}
