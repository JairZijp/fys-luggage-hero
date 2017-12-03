/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 *
 * @author jairz
 * FXML Controller RegisterLuggage
 */
public class RegisterLuggage {
        
    @FXML
    private TextField NameField;
    @FXML
    private TextField CityField;
    @FXML
    private TextField EmailField;
    @FXML
    private TextField AddressField;
    @FXML
    private TextField CountryField;
    @FXML
    private TextField ZipcodeField;
    @FXML
    private TextField PhoneNumberField;
    
    
    @FXML
    private boolean saveLuggage(ActionEvent event) throws IOException, SQLException {
        
        final String name = NameField.getText().toString();
        final String email = EmailField.getText().toString();
        final String city = CityField.getText().toString();
        final String address = AddressField.getText().toString();
        final String country = CountryField.getText().toString();
        final String phoneNumber = PhoneNumberField.getText().toString();
        final String zipcode = ZipcodeField.getText().toString();
        
        DB database = new DB();
        
        String query = String.format("INSERT INTO customer (name, email, address, city, zipcode, country, phone)" +
                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                name, email, address, city, zipcode, country, phoneNumber);
        
        database.executeUpdateQuery(query);
        
        return true;
    }
    
    @FXML
    private void nextStep() {
        
    }
    
}
