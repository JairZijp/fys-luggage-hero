/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *
 * @author Simon
 */
public class Users {
    /**
     * Redirects to the userscreate.fxml
     * @param event
     * @throws IOException 
     */
    @FXML
    private void GoToCreate(ActionEvent event) throws IOException{
        Main.GoToScreen("UsersCreate.fxml");
    }
}
