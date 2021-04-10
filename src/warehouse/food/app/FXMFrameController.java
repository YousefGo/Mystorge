/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warehouse.food.app;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author yoooo
 */
public class FXMFrameController implements Initializable {

    @FXML
    private BorderPane MyBorderPane;
    @FXML
    private JFXButton btnHome;
    @FXML
    private JFXButton btnStock;
    @FXML
    private JFXButton btnReport;
    @FXML
    private JFXButton btnCalculator;
    @FXML
    private JFXButton btnMenu;
    @FXML
    private JFXButton btnAddItems;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        changeMenuColors(btnHome);
        loadUI("Home");
    }    

    @FXML
    private void btnHomeAction(ActionEvent event) {
        changeMenuColors(btnHome);
        loadUI("Home");
    }

    @FXML
    private void btnStockAction(ActionEvent event) {
        changeMenuColors(btnStock);
        loadUI("StockManagement");
    }

    @FXML
    private void btnReportAction(ActionEvent event) {
        changeMenuColors(btnReport);
        loadUI("Reports");
    }

    @FXML
    private void btnCalculatorAction(ActionEvent event) {
        changeMenuColors(btnCalculator);
        loadUI("Calculator");
    }

    @FXML
    private void btnMenuAction(ActionEvent event) {
        changeMenuColors(btnMenu);
        loadUI("Menu");
    }

    @FXML
    private void btnAddItemsAction(ActionEvent event) {
        changeMenuColors(btnAddItems);
        loadUI("FoodTypes");
    }
    
    private void loadUI(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMFrameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        MyBorderPane.setCenter(root);
    }

    private void changeMenuColors(JFXButton button) {
        btnHome.getStyleClass().remove("current");
        btnStock.getStyleClass().remove("current");
        btnReport.getStyleClass().remove("current");
        btnCalculator.getStyleClass().remove("current");
        btnMenu.getStyleClass().remove("current");
        btnAddItems.getStyleClass().remove("current");
        button.getStyleClass().add("current"); 
    }
    
}
