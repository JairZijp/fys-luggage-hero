package Controllers;

import Controllers.Main;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LostAndFound {
    
    
    @FXML
    public void goToNew(ActionEvent event) throws IOException {
        Main.GoToScreen("RegisterLuggage.fxml");
    }
    
}
