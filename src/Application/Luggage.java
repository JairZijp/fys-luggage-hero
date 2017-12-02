package Application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Luggage {
    
    private String label_number;
    private String type;
    private String brand;
    private String color;
    private String special_features;
    private String owner;
    private String status;
    private String location;
    private String lost_location;
    private String found_date;
    private String lost_date;
    private double compesation;
    
    public Luggage(String label_number, String type, String brand, 
            String color, String special_features, String owner, 
            String status, String location, String lost_location, 
            String found_date, double compesation) {
        
        
    }

    public String getLabel_number() {
        return label_number;
    }

    public void setLabel_number(String label_number) {
        this.label_number = label_number;
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

    public String getSpecial_features() {
        return special_features;
    }

    public void setSpecial_features(String special_features) {
        this.special_features = special_features;
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

    public String getLost_location() {
        return lost_location;
    }

    public void setLost_location(String lost_location) {
        this.lost_location = lost_location;
    }

    public String getFound_date() {
        return found_date;
    }

    public void setFound_date(String found_date) {
        this.found_date = found_date;
    }

    public String getLost_date() {
        return lost_date;
    }

    public void setLost_date(String lost_date) {
        this.lost_date = lost_date;
    }

    public double getCompesation() {
        return compesation;
    }

    public void setCompesation(double compesation) {
        this.compesation = compesation;
    }
    
    
    
    
}
