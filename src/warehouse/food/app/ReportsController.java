/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warehouse.food.app;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author yoooo
 */
public class ReportsController implements Initializable {

    @FXML
    private TableColumn<?, ?> date;
    @FXML
    private TableView<Report> table;
    @FXML
    private TableColumn<Report, String> Opreation;
    @FXML
    private TableColumn<Report, Double> OpreationQuerntytiy;
    @FXML
    private TableColumn<Report, Integer> itemID;
    @FXML
    private TableColumn<Report, String> Username;
    
    ObservableList<Report> oblistReport = FXCollections.observableArrayList();
    ObservableList<String> oblistDate = FXCollections.observableArrayList();

    @FXML
    private JFXComboBox<String> Search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        Opreation.setCellValueFactory(new PropertyValueFactory<>("Opreation"));
        OpreationQuerntytiy.setCellValueFactory(new PropertyValueFactory<>("opreationQuenrtity"));
        itemID.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        Username.setCellValueFactory(new PropertyValueFactory<>("Username"));
        Database db = new Database();
        try {
            db.getItemsForReport(oblistReport);
            db.getItemsDateReports(oblistDate);
        } catch (SQLException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(oblistReport);
        Search.setItems(oblistDate);
    }

    @FXML
    private void DoFind(ActionEvent event) throws SQLException {
        Database db = new Database();
        String date = Search.getValue();
        db.getSpecficItemDate(oblistReport,date);
    }

}
