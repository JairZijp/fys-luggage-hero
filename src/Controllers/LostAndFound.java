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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class LostAndFound implements Initializable {

    @FXML
    private TableView<Luggage> LostAndFoundTableView;

    private ObservableList<Luggage> luggageList = FXCollections.observableArrayList();

    @FXML
    private TableColumn ID;
    @FXML
    private TableColumn flightId;
    @FXML
    private TableColumn owner;
    @FXML
    private TableColumn date;
    @FXML
    private TableColumn status;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Luggage initLuggage = new Luggage();

        try {
            ResultSet foundLuggage;
            foundLuggage = initLuggage.getLuggage();
            while (foundLuggage.next()) {
                Luggage luggage = new Luggage();
                luggage.setFlightId(foundLuggage.getString("flight_id"));
                luggage.setOwner(foundLuggage.getString("owner"));
                luggage.setFoundDate(foundLuggage.getString("found_date"));
                luggage.setStatus(foundLuggage.getString("status"));

                luggageList.add(luggage);
            }
            LostAndFoundTableView.setItems(luggageList);
            for (int cnr = 0; cnr < LostAndFoundTableView.getColumns().size(); cnr++) {
                TableColumn tc = (TableColumn) LostAndFoundTableView.getColumns().get(cnr);
                String propertyName = tc.getId();
                if (propertyName != null && !propertyName.isEmpty()) {
                    tc.setCellValueFactory(new PropertyValueFactory<Luggage, String>(propertyName));
                }
            }
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

        ResultSetMetaData luggageMD = resultLuggage.getMetaData();
        int columnsNumber = luggageMD.getColumnCount();

        while (resultLuggage.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                String columnValue = resultLuggage.getString(i);
            }
        }
    }
}
