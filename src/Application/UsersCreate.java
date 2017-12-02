package Application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
    private boolean SaveUser(ActionEvent event) throws IOException, SQLException{
        //To String after every getText so it can be checked on being empty
        final String username = UserNameField.getText().toString();
        final String name = NameField.getText().toString();
        final String email = MailField.getText().toString();
        final String role = RoleField.getSelectionModel().getSelectedItem().toString();
        final String password = PasswordField.getText().toString();
        final String confirmPassword = ConfirmPasswordField.getText().toString();
        
        
        if(name == null || username == null || email == null){
            ValidationLabel.setText("Fill in all fields");
            return false;
        }
//        if(email != )
        if(password == null ? confirmPassword != null : !password.equals(confirmPassword)){
            ValidationLabel.setText("Passwords do not match.");
            return false;
        }
        
        //Make Connection
        DB dbconn = new DB();
                
        String sql = String.format("INSERT INTO user (username, name, role, password, email)" +
                "VALUES ('%s', '%s', '%s', '%s', '%s')",
                username, name, role, email, password);
        
        //execute query and close connection     
        dbconn.executeUpdateQuery(sql);
        dbconn.close();
        
        //Navigate back to the list
        Main.GoToScreen("Users.fxml");
        return true;
    }
    
    
    /**
     * Looks if a user is logged in and redirects to Inloggen.fxml
     * @throws java.io.IOException
     */
    public void Authenticate() throws IOException{
        
        
        //Redirect back to inloggen.fxml to get a user logged in
        Main.GoToScreen("Inloggen.fxml");
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
