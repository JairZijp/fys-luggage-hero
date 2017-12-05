package Controllers;

import Controllers.Main;
import Models.User;
import Models.DB;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class UsersCreate implements Initializable {
    
    @FXML
    private TextField UserNameField;
    @FXML
    private TextField NameField;
    @FXML
    private TextField MailField;
    @FXML
    private ComboBox RoleField;
    @FXML
    private PasswordField PasswordField;
    @FXML
    private PasswordField ConfirmPasswordField;
    @FXML
    private Label ValidationLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){        
        List<String> list = new ArrayList<>();
        list.add("Manager");
        list.add("Servicedesk");
        list.add("Traveler");
        ObservableList obList = FXCollections.observableList(list);
        RoleField.getItems().clear();
        RoleField.setItems(obList);
    }    
    
    /**
     * Function to create/edit a user. After saving redirects back to Users.FXML
     * @throws IOException 
     */
    @FXML
    private boolean SaveUser(ActionEvent event) throws IOException, SQLException, NoSuchAlgorithmException{
        System.out.println("Starting to save..");
        //use this variable to check if user can be registered
        boolean passed = true;
        String validationErrors = "";
        
        //nieuwe user aanmaken
        User newUser = new User();
        
        //To String after every getText so it can be checked on being empty
        newUser.setUsername(UserNameField.getText().toString());
        newUser.setName(NameField.getText().toString());
        newUser.setRole(RoleField.getSelectionModel().getSelectedItem().toString());
        newUser.setEmail(MailField.getText().toString());
        newUser.setPassword(PasswordField.getText().toString());
        final String confirmPassword = ConfirmPasswordField.getText().toString();
        
        
        System.out.println("Validation...");
        //values can not be null
        if(!newUser.isValid()){
            ValidationLabel.setText("Fill in all fields");
            return false;
        }
        
        System.out.println("Validating Email...");
        //User needs valid Email
        if(!newUser.hasValidEmail()){
            ValidationLabel.setText("E-mail is invalid");
            return false;
        }
        
        System.out.println("Validating Passwords...");
        //password and confirmpassword has to be same values
        if(newUser.Password() == null ? confirmPassword != null : !newUser.Password().equals(User.HashPassword(confirmPassword))){
            ValidationLabel.setText("Passwords do not match.");
            return false;
        }
        
        if(!newUser.Exists()){
            newUser.Save();
        }else{
            ValidationLabel.setText("User already exists with this username/email");
            return false;
        }
                
        //Navigate back to the list
        Main.GoToScreen("Users.fxml");
        return true;
    }
}