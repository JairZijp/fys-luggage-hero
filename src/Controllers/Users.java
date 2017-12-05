/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.DB;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author Simon
 */
public class Users implements Initializable{
    @FXML
    private TableView UsersTable;
    @FXML
    private TableColumn<ObservableList<String>, String> TableID;
    @FXML
    private TableColumn<ObservableList<String>, String> TableUsername;
    @FXML
    private TableColumn<ObservableList<String>, String> TableName;
    @FXML
    private TableColumn<ObservableList<String>, String> TableRole;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
       DB Connection = new DB();
       //Simpele query om te kijken of er een record dubbel is, niet specifiek welke record.
       String sql = String.format("SELECT `ID`, `username`, `name`, `role`, `email` FROM user");
        try {
            ResultSet queryResult = Connection.executeResultSetQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
       
//       TableID.setCellValueFactory()
       
       
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
