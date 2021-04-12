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
import javafx.scene.control.TableView;

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
    private TableView<?> tblItems;
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

    ObservableList<String> oblistCat = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Database db = new Database();
        try {
            db.getCatNames(oblistCat);
        } catch (SQLException ex) {
            Logger.getLogger(CalculatorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cBoxtxtFitemCatUpdate.setItems(oblistCat);
        cBoxtxtFitemCatAdd1.setItems(oblistCat);
        // TODO
    }

    @FXML
    private void btnAddAction(ActionEvent event) {
        Database db = new Database();
        
        String name = txtFitemNameAdd.getText();
        String unitPrice = txtFitemUnitPriceAdd.getText();
        String unitType = txtFitemUnitTypeAdd.getText();
        String categoryID = cBoxtxtFitemCatAdd1.getValue();
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
        
        if (categoryID == null) {
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
    
    }

    @FXML
    private void btnUpdateAction(ActionEvent event) {
//         Connect con = new Connect();
//        Student selectedItem = tblStudents.getSelectionModel().getSelectedItem();
//        txtnam.setText(selectedItem.getName());
//        txtemail.setText(selectedItem.getEmail());
//        id = con.getId(selectedItem.getEmail());
//        System.out.println("id="+id);
//        update1.setVisible(true);
    }

    @FXML
    private void btnDeleteAction(ActionEvent event) {

//        Connect con = new Connect();
//        int des = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?", "Delete Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//        if (des == 0) {
//            Student selectedItem = tblStudents.getSelectionModel().getSelectedItem();
//            con.DeleteStudent(selectedItem.getEmail());
//            tblStudents.refresh();
//            tblStudents.getItems().clear();
//            tblStudents.setItems(oblist);
//            con.StudentData(oblist);
//            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
//            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
//            tblStudents.setItems(oblist);
//        }
    }

    @FXML
    private void btnSaveAction(ActionEvent event) {
//        Connect con = new Connect();
//         con.updateStudents(txtnam.getText(), txtemail.getText(), id);
//         tblStudents.getItems().clear();
//            tblStudents.setItems(oblist);
//            con.StudentData(oblist);
//            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
//            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
//            tblStudents.setItems(oblist);
//            update1.setVisible(false);
    }

}
