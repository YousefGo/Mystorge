/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warehouse.food.app;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author yoooo
 */
public class RegistrationController implements Initializable {

    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXTextField txtFirst;
    @FXML
    private JFXTextField txtLast;
    @FXML
    private JFXTextField txtPhone;
    @FXML
    private JFXPasswordField txtPass;
    @FXML
    private JFXButton btnSign;
    @FXML
    private Label lblGreen;
    @FXML
    private Label lblFLnameErr;
    @FXML
    private Label lblPhoneNumber;
    @FXML
    private Label lblPassword;
    @FXML
    private Label lblSuccess;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnLoginAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void btnSignAction(ActionEvent event) throws SQLException {

        Database db = new Database();
        
        String firstName = txtFirst.getText();
        String lastName = txtLast.getText();
        String phone = txtPhone.getText();
        String pass = txtPass.getText();

        boolean valid = true;

        if (firstName.equals("") || lastName.equals("")) {
            lblFLnameErr.setText("First Name, Last Name are Mandatory");
            valid = false;
        } else {
            lblFLnameErr.setText("");
        }

        if (phone.equals("")) {
            lblPhoneNumber.setText("This Feild is mandatory");
            valid = false;
        } else if (!Pattern.matches("05{1}[0-9]{8}", phone)) {
            lblPhoneNumber.setText("Phone Number Must be like 0512345678");
            valid = false;
        } else if (db.register(phone)) {
            lblPhoneNumber.setText("Phone Number is Already Exists");
            valid = false;
        } else {
            lblPhoneNumber.setText("");
        }

        if (pass.equals("")) {
            lblPassword.setText("This Feild is mandatory");
            valid = false;
        }else {
            lblPassword.setText("");
        }
        
        if(valid) {
            db.registerInsert(txtFirst.getText(), txtLast.getText(), txtPhone.getText(), txtPass.getText());
            lblSuccess.setText("User Successfully Registered");
        }else {
            lblSuccess.setText("");
        }
    }
}
