/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warehouse.food.app;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author yoooo
 */
public class MenuController implements Initializable {

    @FXML
    private Label lblErrBreakfast;
    @FXML
    private Label lblErrLunch;
    @FXML
    private Label lblErrDinner;
    @FXML
    private JFXTextField txtBreakfast;
    @FXML
    private JFXTextField txtLunch;
    @FXML
    private JFXTextField txtDinner;
    @FXML
    private TableView<Menu> tableMenu;
    @FXML
    private TableColumn<Menu, String> colBreakfast;
    @FXML
    private TableColumn<Menu, String> colLunch;
    @FXML
    private TableColumn<Menu, String> colDinner;
    @FXML
    private Label lblErrBreakfastUpdate;
    @FXML
    private Label lblErrLunchUpdate;
    @FXML
    private Label lblErrDinnerUpdate;
    @FXML
    private JFXTextField txtBreakfastUpdate;
    @FXML
    private JFXTextField txtLunchUpdate;
    @FXML
    private JFXTextField txtDinnerUpdate;
    @FXML
    private Label lblErrDate;
    @FXML
    private Label lblErrDateUpdate;
    @FXML
    private JFXDatePicker txtDate;
    @FXML
    private TableColumn<Menu, String> colDate;
    @FXML
    private JFXDatePicker txtDateUpdate;
    ObservableList<Menu> obs = FXCollections.observableArrayList();
    Database db = new Database();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            viewMenu();
        } catch (SQLException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void viewMenu() throws SQLException {
        tableMenu.getItems().clear();

        db.getMenu(obs);
        colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colBreakfast.setCellValueFactory(new PropertyValueFactory<>("Breakfast"));
        colLunch.setCellValueFactory(new PropertyValueFactory<>("Lunch"));
        colDinner.setCellValueFactory(new PropertyValueFactory<>("Dinner"));
        tableMenu.setItems(obs);
    }

    @FXML
    private void addMenu(ActionEvent event) throws SQLException {

        //if the input is valid then it will add it to the database
        if (addValidate()) {
            String date = txtDate.getValue().toString();
            String breakfast = txtBreakfast.getText();
            String lunch = txtLunch.getText();
            String dinner = txtDinner.getText();
            db.insertMenu(date, breakfast, lunch, dinner);
            txtDate.setValue(null);
            txtBreakfast.setText("");
            txtLunch.setText("");
            txtDinner.setText("");
            viewMenu();
        }

    }

    //----------- add validation -----------
    private boolean addValidate() throws SQLException {

        boolean validate = true;
        String date;
        if (txtDate.getValue() == null) {
            date = null;
        } else {
            date = txtDate.getValue().toString();
        }

        String breakfast = txtBreakfast.getText();
        String lunch = txtLunch.getText();
        String dinner = txtDinner.getText();

        if (date == null) {
            lblErrDate.setText("Pick a Date");
            validate = false;
        } else if (db.isExsist(date)) {
            lblErrDate.setText("Date already exsist");
            validate = false;
        } else {
            lblErrDate.setText("");
        }
        if (breakfast.equals("")) {
            lblErrBreakfast.setText("Enter Breakfast");
            validate = false;
        } else {
            lblErrBreakfast.setText("");
        }
        if (lunch.equals("")) {
            lblErrLunch.setText("Enter Lunch");
            validate = false;
        } else {
            lblErrLunch.setText("");
        }
        if (dinner.equals("")) {
            lblErrDinner.setText("Enter Dinner");
            validate = false;
        } else {
            lblErrDinner.setText("");
        }

        return validate;

    }

    @FXML
    private void deleteMenu(ActionEvent event) throws SQLException {
        
        if(deleteValidate()){
            String date = txtDateUpdate.getValue().toString();
            db.deleteMenu(date);
            deleteUpdateValue();
            viewMenu();
        }
    }

    @FXML
    private void updateMenu(ActionEvent event) throws SQLException {
        
        // if the input is valid then it will update it in the database
        if (updateValidate()) {
            String date = txtDateUpdate.getValue().toString();
            String breakfast = txtBreakfastUpdate.getText();
            String lunch = txtLunchUpdate.getText();
            String dinner = txtDinnerUpdate.getText();
            db.updateMenu(date, breakfast, lunch, dinner);
            txtDateUpdate.setValue(null);
            txtBreakfastUpdate.setText("");
            txtLunchUpdate.setText("");
            txtDinnerUpdate.setText("");
            viewMenu();
        }
    }

    //----------- update validation -----------
    private boolean updateValidate() throws SQLException {
        boolean validate = true;
        String date;
        if (txtDateUpdate.getValue() == null) {
            date = null;
        } else {
            date = txtDateUpdate.getValue().toString();
        }

        String breakfast = txtBreakfastUpdate.getText();
        String lunch = txtLunchUpdate.getText();
        String dinner = txtDinnerUpdate.getText();

        if (date == null) {
            lblErrDateUpdate.setText("Pick a Date");
            validate = false;
        } else if (!db.isExsist(date)) {
            lblErrDateUpdate.setText("Date not exsist");
            validate = false;
        } else {
            lblErrDateUpdate.setText("");
        }
        if (breakfast.equals("")) {
            lblErrBreakfastUpdate.setText("Enter Breakfast");
            validate = false;
        } else {
            lblErrBreakfastUpdate.setText("");
        }
        if (lunch.equals("")) {
            lblErrLunchUpdate.setText("Enter Lunch");
            validate = false;
        } else {
            lblErrLunchUpdate.setText("");
        }
        if (dinner.equals("")) {
            lblErrDinnerUpdate.setText("Enter Dinner");
            validate = false;
        } else {
            lblErrDinnerUpdate.setText("");
        }

        return validate;
    }
    
    //----------- delete validation -----------
    private boolean deleteValidate() throws SQLException {
        boolean validate = true;
        String date;
        if (txtDateUpdate.getValue() == null) {
            date = null;
        } else {
            date = txtDateUpdate.getValue().toString();
        }

        if (date == null) {
            lblErrDateUpdate.setText("Pick a Date");
            validate = false;
        } else if (!db.isExsist(date)) {
            lblErrDateUpdate.setText("Date not exsist");
            validate = false;
        } else {
            lblErrDateUpdate.setText("");
        }

        return validate;
    }
    @FXML
    private void selectMenu(MouseEvent event) throws ParseException {
        // delete previues error
        deleteUpdateErrors();
        
        //convert String to LocalDate
        String sDate = tableMenu.getSelectionModel().getSelectedItem().date;
        LocalDate localDate = LocalDate.parse(sDate);
        txtDateUpdate.setValue(localDate);

        String breakfast = tableMenu.getSelectionModel().getSelectedItem().breakfast;
        txtBreakfastUpdate.setText(breakfast);

        String lunch = tableMenu.getSelectionModel().getSelectedItem().lunch;
        txtLunchUpdate.setText(lunch);

        String dinner = tableMenu.getSelectionModel().getSelectedItem().dinner;
        txtDinnerUpdate.setText(dinner);

    }

    public void deleteUpdateErrors() {
        lblErrDateUpdate.setText("");
        lblErrBreakfastUpdate.setText("");
        lblErrLunchUpdate.setText("");
        lblErrDinnerUpdate.setText("");
    }
    public void deleteUpdateValue() {
        txtDateUpdate.setValue(null);
        txtBreakfastUpdate.setText("");
        txtLunchUpdate.setText("");
        txtDinnerUpdate.setText("");
    }
    

}
