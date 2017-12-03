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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author jairz
 * FXML Controller RegisterLuggage
 */
public class RegisterLuggage {
        
    @FXML
    private AnchorPane Step1;
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
    
    /* Step 2. */
    @FXML
    private AnchorPane Step2;
    @FXML
    private TextField LabelNumberField;
    @FXML
    private TextField TypeField;
    @FXML
    private TextField FlightIDField;
    @FXML
    private TextField BrandField;
    @FXML
    private TextField LocationField;
    @FXML
    private DatePicker FoundDateField;
    @FXML
    private DatePicker LostDateField;
    @FXML
    private TextArea StatusField;
    @FXML
    private TextField CompesationField;
    @FXML
    private TextField SpecialFeaturesField;
    @FXML 
    private TextField LostLocationField;
    
    @FXML
    private boolean saveLuggage(ActionEvent event) throws IOException, SQLException {
        
        /* Step 1. */
        final String name = NameField.getText().toString();
        final String email = EmailField.getText().toString();
        final String city = CityField.getText().toString();
        final String address = AddressField.getText().toString();
        final String country = CountryField.getText().toString();
        final String phoneNumber = PhoneNumberField.getText().toString();
        final String zipcode = ZipcodeField.getText().toString();
        
        /* Step 2. */
        final String labelNumber = LabelNumberField.getText().toString();
        final String type = TypeField.getText().toString();
        final String flightId = FlightIDField.getText().toString();
        final String brand = BrandField.getText().toString();
        final String location = LocationField.getText().toString();
        final String foundDate = FoundDateField.getValue().toString();
        final String lostDate = LostDateField.getValue().toString();
        final String status = StatusField.getText().toString();
        final String compesation = CompesationField.getText().toString();
        final String specialFeatures = SpecialFeaturesField.getText().toString();
        final String lostLocation = LostLocationField.getText().toString();
        
        DB database = new DB();
        
        String queryStep1 = String.format("INSERT INTO customer (name, email, address, city, zipcode, country, phone)" +
                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                name, email, address, city, zipcode, country, phoneNumber);
        
        String queryStep2 = String.format("INSERT INTO luggage (label_number, type, brand, special_features, owner, status, location, lost_location, found_date, lost_date, compesation)" +
                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                labelNumber, type, brand, specialFeatures, name, status, location, lostLocation, foundDate, lostDate, compesation );
        
        database.executeUpdateQuery(queryStep1);
        database.executeUpdateQuery(queryStep2);
        
        return true;
    }
    
    @FXML
    private void nextStep(ActionEvent event) throws IOException {
        Step1.setVisible(false);
        Step2.setVisible(true);
        
    }
    
}
