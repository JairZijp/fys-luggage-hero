package Controllers;

import Models.DB;
import Models.User;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Inloggen {
    
    @FXML
    private TextField UsernameField;
    @FXML
    private PasswordField PasswordField;
    @FXML
    private Label ErrorLabel;
    
    @FXML
    private void Login(ActionEvent event) throws NoSuchAlgorithmException, IOException, SQLException{
        
        User user = new User();
        
        String username = UsernameField.getText().toString(),
                password = PasswordField.getText().toString(),
                hashedPassword = User.HashPassword(password);
        
        DB Connection = new DB();
        
        String sql = String.format("SELECT * FROM user WHERE `username` = '%s' AND `password` = '%s'",
                username, hashedPassword);
        
        ResultSet queryResult = Connection.executeResultSetQuery(sql);
        
        if(queryResult.first()){
           
            
            //set properties of current user
            user.setUsername(queryResult.getString("username"));
            user.setName(queryResult.getString("name"));
            user.setEmail(queryResult.getString("email"));
            user.setRole(queryResult.getString("role"));
            
            //set current user in main class
            Main.setCurrentUser(user);
            
            //Navigate back to old screen
            Main.GoToScreen("LostAndFound.fxml");
        }else{
            ErrorLabel.setText("Incorrect username/password");
        }
    }
}
