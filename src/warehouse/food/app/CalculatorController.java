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
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author yoooo
 */
public class CalculatorController implements Initializable {

    @FXML
    private JFXTextField txtFPeopleNumber;
    @FXML
    private Label lblErrPeopleNumber;
    @FXML
    private JFXComboBox<String> cBoxItemName;
    @FXML
    private Label lblErrItemSelect;
    @FXML
    private JFXTextField txtFConsumption;
    @FXML
    private Label lblErrConsumption;
    @FXML
    private JFXButton btnCalculate;
    @FXML
    private Text lblTotal;

    ObservableList<String> oblist = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Database db = new Database();
        try {
            db.getItesmsNames(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(CalculatorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cBoxItemName.setItems(oblist);
        // TODO
    }

    @FXML
    private void btnCalculateAction(ActionEvent event) throws SQLException {
        Database db = new Database();
        String peopelNumber = txtFPeopleNumber.getText();
        String consumption = txtFConsumption.getText();
        String itemName = cBoxItemName.getValue();
        boolean valid = true;

        if (!Pattern.matches("[0-9]+", peopelNumber)) {
            lblErrPeopleNumber.setText("Please Enter an Integer only");
            valid = false;
        } else {
            lblErrPeopleNumber.setText("");
        }

        if (!Pattern.matches("[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)", consumption)) {
            lblErrConsumption.setText("Please Enter a valid number only");
            valid = false;
        } else {
            lblErrConsumption.setText("");
        }

        if (itemName == null) {
            lblErrItemSelect.setText("Please Select an Item");
            valid = false;
        } else {
            lblErrItemSelect.setText("");
        }

        if (valid) {
            int pNumber = Integer.parseInt(txtFPeopleNumber.getText());
            double consumptionP = Double.parseDouble(txtFConsumption.getText());
            
            String resultText = db.totoalConsumptoin(pNumber, itemName, consumptionP);
            
            lblTotal.setText(resultText);
        }

    }

}
