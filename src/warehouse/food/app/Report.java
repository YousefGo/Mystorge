/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warehouse.food.app;

/**
 *
 * @author mobai
 */
public class Report {

    private String date;
    private String opreation;
    private Double opreationQuenrtity;
    private int itemID;
    String phoneNumber;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOpreation() {
        return opreation;
    }

    public void setOpreation(String opreation) {
        this.opreation = opreation;
    }

    public double getOpreationQuenrtity() {
        return opreationQuenrtity;
    }

    public void setOpreationQuenrtity(double opreationQuenrtity) {
        this.opreationQuenrtity = opreationQuenrtity;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Report(String date, String opreation, double opreationQuenrtity, int itemID, String username) {
        this.date = date;
        this.opreation = opreation;
        this.opreationQuenrtity = opreationQuenrtity;
        this.itemID = itemID;
        this.username = username;
    }
    private String username;
}
