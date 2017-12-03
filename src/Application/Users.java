/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author Simon
 */
public class Users implements Initializable{
    
    public void initialize(URL url, ResourceBundle rb){
       DB Connection = new DB();
       //Simpele query om te kijken of er een record dubbel is, niet specifiek welke record.
       String sql = String.format("SELECT `ID`, `username`, `name`, `role`, `email` FROM user");
       ResultSet queryResult = Connection.executeResultSetQuery(sql);
       
       Connection.close();
    }

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
