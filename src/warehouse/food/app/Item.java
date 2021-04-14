/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warehouse.food.app;

/**
 *
 * @author yoooo
 */
public class Item {
    int ID;
    String name;
    double unitPrice;
    String unitType;
    String category;
    int amountPerBox;
    int quantity;

    public Item(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Item(int ID, String name, double unitPrice, String unitType, int quantity) {
        this.ID = ID;
        this.name = name;
        this.unitPrice = unitPrice;
        this.unitType = unitType;
        this.quantity = quantity;
    }

    public Item(int ID, String name, double unitPrice, String unitType, String category, int amountPerBox) {
        this.ID = ID;
        this.name = name;
        this.unitPrice = unitPrice;
        this.unitType = unitType;
        this.category = category;
        this.amountPerBox = amountPerBox;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAmountPerBox() {
        return amountPerBox;
    }

    public void setAmountPerBox(int amountPerBox) {
        this.amountPerBox = amountPerBox;
    }
   
}
