/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warehouse.food.app;

import com.jfoenix.controls.JFXButton;
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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author yoooo
 */
public class FoodTypesController implements Initializable {

    @FXML
    private Label lblErrItemName;
    @FXML
    private JFXTextField txtFitemNameAdd;
    @FXML
    private Label lblErrItemUnitType;
    @FXML
    private JFXTextField txtFitemUnitTypeAdd;
    @FXML
    private Label lblErrItemUnitPrice;
    @FXML
    private JFXTextField txtFitemUnitPriceAdd;
    @FXML
    private Label lblErrCategory;
    @FXML
    private JFXComboBox<String> cBoxtxtFitemCatAdd1;
    @FXML
    private Label lblErrAmountPerBox;
    @FXML
    private JFXTextField txtFAmountPerBoxAdd1;
    @FXML
    private JFXButton btnAdd1;
    @FXML
    private TableView<Item> tblItems;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private Label lblID;
    @FXML
    private JFXTextField txtFitemNameUpdate;
    @FXML
    private JFXTextField txtFitemUnitTypeUpdate;
    @FXML
    private JFXTextField txtFitemUnitPriceUpdate;
    @FXML
    private JFXComboBox<String> cBoxtxtFitemCatUpdate;
    @FXML
    private JFXTextField txtFAmountPerBoxUpdate;
    @FXML
    private JFXButton btnSave;
    @FXML
    private TableColumn<Item, Integer> tblCID;
    @FXML
    private TableColumn<Item, String> tblCName;
    @FXML
    private TableColumn<Item, String> tblCUnitType;
    @FXML
    private TableColumn<Item, Double> tblCUnitPrice;
    @FXML
    private TableColumn<Item, String> tblCCategory;
    @FXML
    private TableColumn<Item, Integer> tblCAmountPerBox;

    ObservableList<String> oblistCat = FXCollections.observableArrayList();

    ObservableList<Item> oblistItems = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblCID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        tblCName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCUnitType.setCellValueFactory(new PropertyValueFactory<>("unitType"));
        tblCUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblCCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        tblCAmountPerBox.setCellValueFactory(new PropertyValueFactory<>("amountPerBox"));

        Database db = new Database();
        try {
            db.getAllItems(oblistItems);
            db.getCatNames(oblistCat);
        } catch (SQLException ex) {
            Logger.getLogger(CalculatorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tblItems.setItems(oblistItems);
        cBoxtxtFitemCatUpdate.setItems(oblistCat);
        cBoxtxtFitemCatAdd1.setItems(oblistCat);
        // TODO
    }

    @FXML
    private void btnAddAction(ActionEvent event) throws SQLException {
        Database db = new Database();

        String name = txtFitemNameAdd.getText();
        String unitPrice = txtFitemUnitPriceAdd.getText();
        String unitType = txtFitemUnitTypeAdd.getText();
        String catName = cBoxtxtFitemCatAdd1.getValue();
        String amountPerBox = txtFAmountPerBoxAdd1.getText();

        boolean valid = true;

        if (name.equals("")) {
            lblErrItemName.setText("Please Write a Name");
            valid = false;
        } else {
            lblErrItemName.setText("");
        }

        if (!Pattern.matches("[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)", unitPrice)) {
            lblErrItemUnitPrice.setText("Please Enter a valid number");
            valid = false;
        } else {
            lblErrItemUnitPrice.setText("");
        }

        if (unitType.equals("")) {
            lblErrItemUnitType.setText("Please Write a Type");
            valid = false;
        } else {
            lblErrItemUnitType.setText("");
        }

        if (catName == null) {
            lblErrCategory.setText("Please Select an Item");
            valid = false;
        } else {
            lblErrCategory.setText("");
        }

        if (!Pattern.matches("[0-9]+", amountPerBox)) {
            lblErrAmountPerBox.setText("Please Enter an Integer only");
            valid = false;
        } else {
            lblErrAmountPerBox.setText("");
        }

        if (valid) {
            name = name;
            double UnitPrice = Double.parseDouble(unitPrice);
            unitType = unitType;          
            catName = catName;
            double AmountPerBox = Double.parseDouble(amountPerBox);

            db.insertItem(name, UnitPrice, unitType, catName, AmountPerBox);
            db.getAllItems(oblistItems);
            tblItems.setItems(oblistItems);

        }

    }

    @FXML
    private void btnUpdateAction(ActionEvent event) {
        Database db = new Database();
        Item selectedItem = tblItems.getSelectionModel().getSelectedItem();

        txtFitemNameUpdate.setText(selectedItem.getName());
        txtFitemUnitPriceUpdate.setText(selectedItem.getUnitPrice() + "");
        txtFitemUnitTypeUpdate.setText(selectedItem.getUnitType());
        txtFAmountPerBoxUpdate.setText(selectedItem.getAmountPerBox() + "");
        cBoxtxtFitemCatUpdate.setValue(selectedItem.getCategory());
        lblID.setText(selectedItem.getID() + "");

    }

    @FXML
    private void btnDeleteAction(ActionEvent event) throws SQLException {

        Database db = new Database();
        int des = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?", "Delete Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (des == 0) {
            Item selectedItem = tblItems.getSelectionModel().getSelectedItem();
            db.deleteItme(selectedItem.getID());

            db.getAllItems(oblistItems);
            tblItems.setItems(oblistItems);
        }
    }

    @FXML
    private void btnSaveAction(ActionEvent event) throws SQLException {
        Database db = new Database();

        String name = txtFitemNameUpdate.getText();
        String unitPrice = txtFitemUnitPriceUpdate.getText();
        String unitType = txtFitemUnitTypeUpdate.getText();
        String catName = cBoxtxtFitemCatUpdate.getValue();
        String amountPerBox = txtFAmountPerBoxUpdate.getText();

        name = name;
        double UnitPrice = Double.parseDouble(unitPrice);
        unitType = unitType;
        catName = catName;
        int AmountPerBox = Integer.parseInt(amountPerBox);

        db.updateItem(Integer.parseInt(lblID.getText()), name, UnitPrice, unitType, AmountPerBox, catName);
        db.getAllItems(oblistItems);
        tblItems.setItems(oblistItems);
    }

}
