/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Luggage;
import Models.Customer;
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
 * @author Jaïr Zijp
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
    private TextField ColorField;
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
        
        Luggage luggage = new Luggage();
        Customer customer = new Customer();
        
        
        /* Step 1. */
        customer.setName(NameField.getText().toString());
        customer.setEmail(EmailField.getText().toString());
        customer.setAddress(AddressField.getText().toString());
        customer.setCity(CityField.getText().toString());
        customer.setCountry(CountryField.getText().toString());
        customer.setZipcode(ZipcodeField.getText().toString());
        customer.setPhoneNumber(PhoneNumberField.getText().toString());
        
        customer.saveCustomer();
        String addedCustomerId = customer.getLastId();
        System.out.printf("Customer ID: " + addedCustomerId);
        
        
        /* Step 2. */
        luggage.setCustomerId(Integer.parseInt(addedCustomerId));
        luggage.setLabelNumber(LabelNumberField.getText().toString());
        luggage.setType(TypeField.getText().toString());
        luggage.setBrand(BrandField.getText().toString());
        luggage.setLocation(LocationField.getText().toString());
        luggage.setFoundDate(FoundDateField.getValue().toString());
        luggage.setColor(ColorField.getText().toString());
        luggage.setLostDate(LostDateField.getValue().toString());
        luggage.setStatus(StatusField.getText().toString());
        luggage.setLostLocation(LostLocationField.getText().toString());
        luggage.setCompesation(Double.parseDouble(CompesationField.getText()));
        luggage.setSpecialFeatures(SpecialFeaturesField.getText().toString());
        luggage.setFlightId(FlightIDField.getText().toString());
        luggage.setOwner(NameField.getText().toString());
   
        luggage.saveLuggage();
        
        return true;
        
    }
    
    @FXML
    private void nextStep(ActionEvent event) throws IOException {
        Step1.setVisible(false);
        Step2.setVisible(true);
        
    }
    
}
