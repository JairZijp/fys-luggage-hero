/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jaïr Zijp
 * 
 */
public class Customer {
   
    private String name;
    private String email;
    private String address;
    private String city;
    private String zipcode;
    private String country;
    private String phoneNumber;

    
    public boolean saveCustomer() throws SQLException {
        
        DB database = new DB();
        
        String queryStep1 = String.format("INSERT INTO customer (name, email, address, city, zipcode, country, phone)" +
                " VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                name, email, address, city, zipcode, country, phoneNumber);
                
        database.executeUpdateQuery(queryStep1);

        return true;
         
    }
    
    public String getLastId() {
        
        DB database = new DB();
        
        String queryLastId = String.format("SELECT ID FROM customer ORDER BY ID DESC LIMIT 1;");
        String queryResult = database.executeStringQuery(queryLastId);
         
        return queryResult;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
}
