/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Luggage;
import Models.Customer;
import Models.Flight;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
    private TextField FlightIdField;
    @FXML
    private TextField AirportField;
    @FXML
    private TextField DestinationField;
    @FXML
    private DatePicker DateField;
    @FXML
    private TextField TimeField;
    
    
    /* Step 3. */
    @FXML
    private Label ValidationLabel;
    @FXML
    private AnchorPane Step3;
    @FXML
    private TextField LabelNumberField;
    @FXML
    private TextField TypeField;
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
        Flight flight = new Flight();
        
        
        /* Step 1. */
        customer.setName(NameField.getText().toString());
        customer.setEmail(EmailField.getText().toString());
        customer.setAddress(AddressField.getText().toString());
        customer.setCity(CityField.getText().toString());
        customer.setCountry(CountryField.getText().toString());
        customer.setZipcode(ZipcodeField.getText().toString());
        customer.setPhoneNumber(PhoneNumberField.getText().toString());
        
        /* Step 2 */
        flight.setFlightId(FlightIdField.getText().toString());
        flight.setAirport(AirportField.getText().toString());
        flight.setDate(DateField.getValue().toString());
        flight.setDestination(DestinationField.getText().toString());
        flight.setTime(TimeField.getText().toString());
        
        
        /* Step 2. */
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
        luggage.setFlightId(FlightIdField.getText().toString());
        luggage.setOwner(NameField.getText().toString());
   
         //values can not be null
        if(!luggage.isValid()){
           ValidationLabel.setText("Fill in all fields");
           System.out.println("False");
            
           return false;
       }
        
        flight.saveFlight();
        customer.saveCustomer();
        String addedCustomerId = customer.getLastId();
        System.out.printf("Customer ID: " + addedCustomerId);
        
        luggage.setCustomerId(Integer.parseInt(addedCustomerId));
        
        luggage.saveLuggage();
        
        Main.GoToScreen("LostAndFound.fxml");
        return true;
        
    }
    
    @FXML
    private void nextStep(ActionEvent event) throws IOException {
        Step1.setVisible(false);
        Step2.setVisible(true);
        
    }
    
    @FXML
    private void nextStep2(ActionEvent event) throws IOException {
        Step1.setVisible(false);
        Step2.setVisible(false);
        Step3.setVisible(true);
        
    }
    
    @FXML
    private void previousStep(ActionEvent event) throws IOException {
        Step1.setVisible(true);
        Step2.setVisible(false);
    }
    
    @FXML
    private void previousStep2(ActionEvent event) throws IOException {
        Step1.setVisible(false);
        Step2.setVisible(true);
        Step3.setVisible(false);
    }
    
}
