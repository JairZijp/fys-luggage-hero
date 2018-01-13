/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Luggage;
import java.net.URL;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;

/**
 *
 * @author jairz
 */
public class Statistics implements Initializable {

    @FXML
    private ComboBox AirportDropDown;

    @FXML
    private PieChart FoundLuggage;
    
    @FXML
    private PieChart AllLuggage;

    // defining the list
    private static List<String> airports = new ArrayList<>();

    private static void setAirports() {
        // add all the roles to the list
        airports.add("Schiphol");
        airports.add("Aviodrome");
        airports.add("Eindhoven");
    }

    public static List GetAirportList() {
        // return the roles in list format
        return airports;
    }

    public static ObservableList GetAirportObservableList() {
        // set all roles
        setAirports();

        // put roles in observable list
        ObservableList RoleList = FXCollections.observableList(airports);

        //return observablelist
        return RoleList;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList RolesList = GetAirportObservableList();
        AirportDropDown.getItems().clear();
        AirportDropDown.setItems(RolesList);

        try {
            Luggage lug = new Luggage();
            ResultSet lostLuggage = lug.getLuggage();

            /*
             * these methods are used to use 1 query instead of multiple.
             * the data will not be modified in the methods.
             */
            // use totalrecords to set title or something
            int totalRecords = CountRows(lostLuggage);
            int lostLuggageCount = CountByColumnValue(lostLuggage, "status", "lost");
            int foundLuggageCount = CountByColumnValue(lostLuggage, "status", "found");
            
             ObservableList<PieChart.Data> lostAndFoundData
                    = FXCollections.observableArrayList(
                            new PieChart.Data("Found", foundLuggageCount),
                            new PieChart.Data("Never found", lostLuggageCount));
            AllLuggage.setData(lostAndFoundData);

            // sum total compesation
            int compesation = SumByColumnName(lostLuggage, "compesation");

            float averageCompesation = compesation / totalRecords;
            
            
            //Found data
            int foundInThreeDays = luggageFoundInDayRange(lostLuggage, 3);
            int foundInTwentyOneDays = luggageFoundInDayRange(lostLuggage, 21, 3);
            int neverFound = totalRecords - foundInThreeDays + foundInTwentyOneDays;

            ObservableList<PieChart.Data> foundData
                    = FXCollections.observableArrayList(
                            new PieChart.Data("Found in 3 days", foundInThreeDays),
                            new PieChart.Data("Found in 21 days", foundInTwentyOneDays),
                            new PieChart.Data("Never found", neverFound));
            FoundLuggage.setData(foundData);

        } catch (SQLException | ParseException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Count the rows matching the value
     *
     * @param Data The data the rows need to be counted of
     * @param Column The column of the values to match
     * @param ValueToMatch The value to find in the ResultSet
     * @return
     * @throws SQLException
     */
    private int CountByColumnValue(ResultSet Data, String Column, String ValueToMatch) throws SQLException {
        // reset iterator before looping again
        // if this method is used multiple with the same data it won't find a next row
        // because it's already looped through
        Data.beforeFirst();
        int counter = 0;

        while (Data.next()) {
            String columnValue = Data.getString(Column);

            if (columnValue == null ? ValueToMatch == null : columnValue.equals(ValueToMatch)) {
                counter++;
            }
        }
        return counter;
    }

    private int SumByColumnName(ResultSet Data, String Column) throws SQLException {
        Data.beforeFirst();
        int total = 0;

        while (Data.next()) {
            total += Data.getInt(Column);
        }

        return total;
    }

    private int CountRows(ResultSet Data) throws SQLException {
        int counter = 0;
        Data.beforeFirst();
        while (Data.next()) {
            counter++;
        }
        return counter;
    }

    private int luggageFoundInDayRange(ResultSet Data, int DayRange) throws SQLException, SQLException, ParseException {
        return luggageFoundInDayRange(Data, DayRange, 0);
    }

    private int luggageFoundInDayRange(ResultSet Data, int DayRange, int RangeFrom) throws SQLException, ParseException {
        Data.beforeFirst();
        int counter = 0;

        while (Data.next()) {
            String dateFoundString = Data.getString("found_date").toString();

            if (dateFoundString != null && !dateFoundString.isEmpty()) {
                System.out.println(dateFoundString);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                Date dateFound = dateFormat.parse(dateFoundString);
                Date dateLost = dateFormat.parse(Data.getString("lost_date"));

                long difference = dateFound.getTime() - dateLost.getTime();
                // divide by hours * minutes * seconds * milliseconds (equal to 1 day) 
                int diffDays = (int) (difference / (24 * 60 * 60 * 1000));

                if (diffDays <= DayRange && diffDays > RangeFrom) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
