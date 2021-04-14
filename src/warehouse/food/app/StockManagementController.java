/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warehouse.food.app;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author yoooo
 */
public class StockManagementController implements Initializable {

    @FXML
    private TableView<Item> table;
    @FXML
    private TableColumn<Item, Integer> id;
    @FXML
    private TableColumn<Item, String> name;
    @FXML
    private TableColumn<Item, Double> UnitPrice;
    @FXML
    private TableColumn<Item, String> UnitType;
    @FXML
    private TableColumn<Item, Integer> Quantity;
    @FXML
    private VBox StockManagement;
    @FXML
    private Label lblErrOperation;
    @FXML
    private JFXComboBox<String> CBoxOperation;
    @FXML
    private Label lblAddByNumber;
    @FXML
    private JFXTextField txtFQuantity;

    ObservableList<Item> oblistStock = FXCollections.observableArrayList();

    ObservableList<String> oblistComboBox = FXCollections.observableArrayList("ADD", "Spend");
    @FXML
    private Label lblErrTableSellection;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        UnitType.setCellValueFactory(new PropertyValueFactory<>("unitType"));
        UnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        Quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        Database db = new Database();
        try {
            db.getItemsForStock(oblistStock);
        } catch (SQLException ex) {
            Logger.getLogger(StockManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(oblistStock);
        CBoxOperation.setItems(oblistComboBox);
    }

    @FXML
    private void DoOpreation(ActionEvent event) throws SQLException {
        Item selectedItem = table.getSelectionModel().getSelectedItem();
        String opreation = CBoxOperation.getValue();
        String amount = txtFQuantity.getText();

        Database db = new Database();

        boolean valid = true;

        if (opreation == null) {
            lblErrOperation.setText("Please Select an Operation");
            valid = false;
        } else {
            lblErrOperation.setText("");
        }

        if (!Pattern.matches("[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)", amount)) {
            lblAddByNumber.setText("Please Enter a valid number");
            valid = false;
        } else {
            lblAddByNumber.setText("");
        }

        if (selectedItem == null) {
            lblErrTableSellection.setText("Please Select an Item From the table");
            valid = false;
        } else {
            lblErrTableSellection.setText("");
        }

        if (valid) {
            double amountd = Double.parseDouble(amount);
            int idd = selectedItem.getID();

            if (opreation.equals("ADD")) {
                db.updateQyt(idd, amountd, "ADD");
            } else if (opreation.equals("Spend")) {
                if (db.getItemQuantity(idd) - amountd < 0) {
                    lblErrTableSellection.setText("The Quantity to Spent is more than the Exist Quantity");
                } else {
                    db.spendQyt(idd, amountd, "Spend");
                }
            }
            db.getItemsForStock(oblistStock);
            table.setItems(oblistStock);
        }
    }

}
