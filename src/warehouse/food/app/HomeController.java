/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warehouse.food.app;

import java.awt.Color;
import java.awt.Paint;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

/**
 * FXML Controller class
 *
 * @author yoooo
 */
public class HomeController implements Initializable {

    @FXML
    private Circle progressCircle;

    double radius;
    double circumference;

    Database db = new Database();

    @FXML
    private Text txtItems;
    @FXML
    private Text txtItemDetaiels;
    @FXML
    private Text txtZeroStockItems;
    @FXML
    private Text txtTotalStock;
    @FXML
    private Text txtSuppliers;

    /**
     * Initializes the controller class.
     */

    /*
    

    
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        // to rotate the shape by -90 degree
        progressCircle.getTransforms().add(new Rotate(-90));

        // will get the radius value of the circle
        radius = progressCircle.getRadius();
        System.out.println(radius);

        // circumference of a circle = 2πr
        circumference = radius * 2 * Math.PI;
        progressCircle.getStrokeDashArray().add(circumference);

        try {
            // getting information from database
            int numberOfCategory = db.getNumberOfCategory();
            int numberOfItems = db.getNumberOfItems();
            int numberOfItemDetails = db.getNumberOfItemDetails();
            int numberOfZeroStock = db.getNumberOfZeroStock();
            int numberOfSuppliers = numberOfCategory;

            // full stock is the maximum number of itemDetails can a stock reach
            int fullStock = 10000;
            // calculate percentage from 0% - 100%
            double percentage = 100 - (((double) numberOfItemDetails / (double) fullStock) * 100.0);

            // setting all the values
            if (percentage <= 0.0) {
                
                progressCircle.setStrokeWidth(percentage);
                Color a = new Color(244,244,244);
                progressCircle.setStroke(awtColorToJavaFX(a));
                //progressCirc;
            } else {
                setProgress(percentage);

            }
            txtTotalStock.setText((int) percentage + "%");
            txtItems.setText(numberOfCategory + "");
            txtItemDetaiels.setText(numberOfItemDetails + "");
            txtZeroStockItems.setText(numberOfZeroStock + "");
            txtSuppliers.setText(numberOfSuppliers + "");

        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setProgress(double percentage) {
        double result = circumference - (percentage / 100.0) * circumference;
        progressCircle.setStrokeDashOffset(result);

    }
    
    private javafx.scene.paint.Color awtColorToJavaFX(Color c) {
        return javafx.scene.paint.Color.rgb(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha() / 255.0);
    }

}
