package Controllers;

import Controllers.Main;
import Models.Luggage;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class LostAndFound implements Initializable{
    
    @Override
    public void initialize(URL url, ResourceBundle rb){  
        
        try {
            getLuggage();
        } catch (SQLException ex) {
            Logger.getLogger(LostAndFound.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    public void goToNew(ActionEvent event) throws IOException {
        Main.GoToScreen("RegisterLuggage.fxml");
    }
    
    @FXML
    public static void getLuggage() throws SQLException {
        
        Luggage luggage = new Luggage();
        
        ResultSet resultLuggage = luggage.getLuggage();
        
        System.out.println("Baggage: " + resultLuggage);
        
        ResultSetMetaData luggageMD = resultLuggage.getMetaData();
        int columnsNumber = luggageMD.getColumnCount();
        
        while (resultLuggage.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = resultLuggage.getString(i);
                System.out.print(columnValue);
            }
            System.out.println("");
        }
        
     
        
        
    }

    
}
