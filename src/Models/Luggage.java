package Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author Jaïr Zijp
 */

public class Luggage {
    
    private String labelNumber;
    private String type;
    private String brand;
    private String color;
    private String specialFeatures;
    private String owner;
    private String status;
    private String location;
    private String lostLocation;
    private String foundDate;
    private String lostDate;
    private String flightId;
    private int customerId;
    private double compesation;

   
    public Luggage() {}
    
    public boolean saveLuggage() {
        
        DB database = new DB();
        
        String queryStep2 = String.format("INSERT INTO luggage (label_number, type, brand, special_features, owner, status, location, lost_location, found_date, lost_date, compesation, customer_ID, color)" +
                " VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                getLabelNumber(), getType(), getBrand(), getSpecialFeatures(), getOwner(), getStatus(), getLocation(), getLostLocation(), getFoundDate(), getLostDate(), getCompesation(), getCustomerId(), getColor());
        
        database.executeUpdateQuery(queryStep2);
        
        return true;
    }
    
    public String getLabelNumber() {
        return labelNumber;
    }

    public void setLabelNumber(String labelNumber) {
        this.labelNumber = labelNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLostLocation() {
        return lostLocation;
    }

    public void setLostLocation(String lostLocation) {
        this.lostLocation = lostLocation;
    }

    public String getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(String foundDate) {
        this.foundDate = foundDate;
    }

    public String getLostDate() {
        return lostDate;
    }

    public void setLostDate(String lostDate) {
        this.lostDate = lostDate;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public double getCompesation() {
        return compesation;
    }

    public void setCompesation(double compesation) {
        this.compesation = compesation;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
}