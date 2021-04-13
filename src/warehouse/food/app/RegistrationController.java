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
    private JFXTextField txtEmail;
    @FXML
    private JFXPasswordField txtPass;
    @FXML
    private JFXPasswordField txtPassCon;
    @FXML
    private JFXButton btnSign;
    @FXML
    private Label lblRed;
    @FXML
    private Label lblGreen;

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

        Database u = new Database();
        u.registerInsert(txtFirst.getText(), txtLast.getText(), txtPhone.getText(), txtEmail.getText(), txtPass.getText());

//        if (txtFirst.getText().isEmpty() && txtLast.getText().isEmpty()
//                && txtPhone.getText().isEmpty() && txtEmail.getText().isEmpty()
//                && txtPass.getText().isEmpty() && txtPassCon.getText().isEmpty()) {
//
//            lblRed.setText("You must fill the fields!");
//            lblGreen.setText(null);
//
//        } else if (txtFirst.getText().isEmpty()) {
//            lblRed.setText("whrite the first name!");
//            lblGreen.setText("");
//
//        } else if (txtLast.getText().isEmpty()) {
//            lblRed.setText("whrite the last name!");
//            lblGreen.setText("");
//
//        } else if (txtPhone.getText().isEmpty()) {
//            lblRed.setText("whrite the phone!");
//            lblGreen.setText("");
//
//        } else if (txtEmail.getText().isEmpty()) {
//            lblRed.setText("whrite the email!");
//            lblGreen.setText("");
//
//        } else if (txtPass.getText().isEmpty()) {
//            lblRed.setText("whrite the password!");
//            lblGreen.setText("");
//
//        } else if (txtPassCon.getText().isEmpty()) {
//            lblRed.setText("whrite the password conform!");
//            lblGreen.setText("");
//
//        } else if (!txtPass.getText().equals(txtPassCon.getText())) {   // عشان اشوف اذا كانت كلمه السر متطابقو او لا
//            lblRed.setText("password does not match :(");
//            lblGreen.setText(null);
//            
//        }

    }
}
