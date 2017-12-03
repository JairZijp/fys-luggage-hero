package Application;

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
        //use this variable to check if user can be registered
        boolean passed = true;
        String validationErrors = "";
        
        //To String after every getText so it can be checked on being empty
        final String username = UserNameField.getText().toString();
        final String name = NameField.getText().toString();
        final String email = MailField.getText().toString();
        final String role = RoleField.getSelectionModel().getSelectedItem().toString();
        final String password = PasswordField.getText().toString();
        final String confirmPassword = ConfirmPasswordField.getText().toString();
        
        //values can not be null
        if(name == null || username == null || email == null || role == null || password == null){
            ValidationLabel.setText("Fill in all fields");
            return false;
        }
        
        //mail has to match regex
        Pattern ptr = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE);
        if(! ptr.matcher(email).matches()){
            ValidationLabel.setText("E-mail is invalid");
            return false;
        }
        
        //password and confirmpassword has to be same values
        if(password == null ? confirmPassword != null : !password.equals(confirmPassword)){
            ValidationLabel.setText("Passwords do not match.");
            return false;
        }
        
        if(!UserExists(username, email)){
            InsertUser(username, name, role, password, email);
        }else{
            ValidationLabel.setText("User already exists with this username/email");
            return false;
        }
                
        //Navigate back to the list
        Main.GoToScreen("Users.fxml");
        return true;
    }
    
    public static String HashPassword(String Password) throws NoSuchAlgorithmException{
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(Password.getBytes(), 0, Password.length());    
        return new BigInteger(1, m.digest()).toString(16);
    }
    
    private boolean UserExists(String Username, String Email) throws SQLException {
        boolean exists;
        DB Connection = new DB();
        //Simpele query om te kijken of er een record dubbel is, niet specifiek welke record.
        String sql = String.format("SELECT * FROM user WHERE `username` = '%s' OR `email` = '%s'", Username, Email);
        ResultSet queryResult = Connection.executeResultSetQuery(sql);
        
        exists = queryResult.first();
        
        Connection.close();
        
        return exists;
    }
    
    private void InsertUser(String Username, String Name, String Role, String Password, String Email) throws NoSuchAlgorithmException{
        //Make Connection                
        DB Connection = new DB(); 
        String sql = String.format("INSERT INTO user (username, name, role, password, email)" +
                "VALUES ('%s', '%s', '%s', '%s', '%s')",
                Username, Name, Role, HashPassword(Password), Email);
        
        //execute query and close connection     
        Connection.executeUpdateQuery(sql);
        Connection.close();
    }
}

//User Class structure(needs imporvement)
class User{
    private final String Username;
    private final String Name;    
    private final String Role;
    private final String Email;
    private final String Password;
    
    public User(String Username, String Name, String Role, String Email, String Password){
        this.Username = Username;
        this.Name = Name;
        this.Role = Role;
        this.Email = Email;
        this.Password = Password;        
    }
    
    public String Username(){
        return this.Username;
    }
    
    public String Name(){
        return this.Name;
    }
    
    public String Role(){
        return this.Role;
    }
    
    public String Email(){
        return this.Email;
    }
    
    public boolean isValid(){
        return this.Username != null &&
                this.Name != null &&                
                this.Role != null &&
                this.Email != null &&
                this.Password != null;
    }
}
