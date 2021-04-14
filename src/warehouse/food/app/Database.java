/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warehouse.food.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.collections.ObservableList;

/**
 *
 * @author Yousef Abdullah Abdulwahab
 */
public class Database {

    private Connection connect() throws SQLException {
        Connection con;
        String url = "jdbc:sqlite:MyStorage.db";
        con = DriverManager.getConnection(url);
        System.out.println("Connection Establish");
        return con;

    }
    //--------------calculator page--------------------------------------------

    public void getItesmsNames(ObservableList oblist) throws SQLException {
        oblist.clear();
        String sqlCommand = ""
                + "SELECT Name "
                + "FROM ITEMS;";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sqlCommand);

        while (rs.next()) {
            String ItemName = rs.getString("Name");
            oblist.add(ItemName);
        }
        st.close();
        con.close();
    }

    public String totoalConsumptoin(int pNumber, String itemName, double cPerPeroson) throws SQLException {
        String resoultText = "";

        double totalAmount = pNumber * cPerPeroson;

        String sqlCommand = ""
                + "SELECT AmountPerBox, UnitType, UnitPrice "
                + "FROM ITEMS "
                + "WHERE Name ='" + itemName + "';";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sqlCommand);

        double UnitPrice = rs.getDouble("UnitPrice");
        String UnitType = rs.getString("UnitType");
        int AmountPerBox = rs.getInt("AmountPerBox");
        double totalAmountPerBox = totalAmount / AmountPerBox;
        double totalPrice = totalAmount * UnitPrice;

        resoultText = "Total Amount of consumption = " + totalAmount + " " + UnitType + "\n"
                + "Total Number of Boxes = " + totalAmountPerBox + "\n"
                + "There is " + AmountPerBox + " " + UnitType + " " + itemName + " in each box\n"
                + "Totla price = " + totalPrice + "\n";
        st.close();
        con.close();

        return resoultText;
    }

    //--------------End of calculator page--------------------------------------------
    //--------------FoodTypes page--------------------------------------------
    public void insertItem(String Name, double UnitPrice, String UnitType, String CatName, double AmountPerBox) throws SQLException {
        String sqlCommand
                = "INSERT INTO ITEMS( Name, UnitPrice, UnitType, CatName, AmountPerBox) "
                + "VALUES('" + Name + "'," + UnitPrice + ",'" + UnitType + "','" + CatName + "'," + AmountPerBox + ");";

        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sqlCommand);
        st.close();
        con.close();
        System.out.println("the Item added Successfully");
    }

    public void deleteItme(int ID) throws SQLException {
        String sql = "DELETE FROM ITEMS WHERE ID = " + ID + ";";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);

        st.close();
        con.close();

    }

    public int getCatIDId(String CatName) throws SQLException {
        String sql = "SELECT ID FROM CATEGORY WHERE Name='" + CatName + "';";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        int id = rs.getInt("id");
        st.close();
        con.close();
        return id;

    }

    public String getCatName(int CatID) throws SQLException {
        String sql = "SELECT Name FROM CATEGORY WHERE Name=" + CatID + ";";
        Connection con = connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        String name = rs.getString("Name");
        st.close();
        con.close();
        return name;

    }

    public void updateItem(int ID, String Name, double UnitPrice, String UnitType, double AmountPerBox, String catName) throws SQLException {
        String sql = "UPDATE ITEMS set Name = '" + Name + "', UnitPrice=" + UnitPrice + " , UnitType='" + UnitType + "', AmountPerBox=" + AmountPerBox + " , catName='" + catName + "' "
                + "WHERE ID=" + ID + ";";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();

    }

    public void getAllItems(ObservableList<Item> oblist) throws SQLException {
        oblist.clear();
        String sqlCommand = ""
                + "SELECT ID, Name, UnitPrice, UnitType, CatName, AmountPerBox "
                + "FROM ITEMS;";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sqlCommand);

        while (rs.next()) {
            int ID = rs.getInt("ID");
            String Name = rs.getString("Name");
            double UnitPrice = rs.getDouble("UnitPrice");
            String UnitType = rs.getString("UnitType");
            String CatName = rs.getString("CatName");
            int AmountPerBox = rs.getInt("AmountPerBox");

            Item item = new Item(ID, Name, UnitPrice, UnitType, CatName, AmountPerBox);

            oblist.add(item);
        }
        st.close();
        con.close();
    }

    public void getCatNames(ObservableList oblist) throws SQLException {
        oblist.clear();
        String sqlCommand = ""
                + "SELECT Name "
                + "FROM CATEGORY;";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sqlCommand);

        while (rs.next()) {
            String ItemName = rs.getString("Name");
            oblist.add(ItemName);
        }
        st.close();
        con.close();
    }

    //--------------End of FoodTypes page--------------------------------------------
    //-------------- Login page --------------------------------------------
    public boolean login(String phone, String password) throws SQLException {

        String sql = "SELECT Phone, Password FROM USERS";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        boolean check = false;

        while (rs.next()) {
            String e = rs.getString("phone");
            String p = rs.getString("password");
            if (phone.equals(e) && password.equals(p)) {
                check = true;
                break;
            }
        }
        st.close();
        con.close();
        return check;
    }

    //--------------End of Login page--------------------------------------------
    //-------------- Register page --------------------------------------------
    public void registerInsert(String FirstName, String LastName, String phone, String password) throws SQLException {

        String sql = "INSERT INTO USERS(Fname, Lname, Phone, Password) VALUES('" + FirstName + "', '" + LastName + "', '" + phone + "', '" + password + "')";
        Connection con = this.connect();

        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();
        System.out.println("The user added :)");

    }

    public boolean register(String phone) throws SQLException {

        String sql = "SELECT Phone FROM USERS "
                + "WHERE PHONE = '" + phone + "';";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        boolean check = false;

        if (rs.next()) {
            check = true;
        }
        st.close();
        con.close();
        return check;

    }

    public String getUser(String phone) throws SQLException {
        String sql = "SELECT Phone, fname, lname FROM USERS "
                + "WHERE PHONE = '" + phone + "';";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        String fname = rs.getString("fname");
        String lname = rs.getString("lname");

        String user = fname + " " + lname;

        st.close();
        con.close();
        return user;
    }

    //----------------------Stock page -----------------//
    public void getItemsForStock(ObservableList<Item> oblist) throws SQLException {
        oblist.clear();
        String sqlCommand = ""
                + "SELECT ID, Name, UnitPrice, UnitType,Quantity "
                + "FROM ITEMS ORDER BY Quantity desc ;";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sqlCommand);

        while (rs.next()) {
            int ID = rs.getInt("ID");
            String Name = rs.getString("Name");
            double UnitPrice = rs.getDouble("UnitPrice");
            String UnitType = rs.getString("UnitType");
            int quantity = rs.getInt("Quantity");

            Item item = new Item(ID, Name, UnitPrice, UnitType, quantity);

            oblist.add(item);
        }
        st.close();
        con.close();
    }

    public void getNamesForItmes(ObservableList oblist) throws SQLException {

        String sqlCommand = ""
                + "SELECT Name "
                + "FROM ITEMS;";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sqlCommand);

        while (rs.next()) {

            String Name = rs.getString("Name");

            Item item = new Item(Name);
            oblist.add(item);
        }
        st.close();
        con.close();
    }

    public void updateQyt(int ID, double qyt, String ops) throws SQLException {
        Connection con = this.connect();

        String getValue = "SELECT Quantity FROM ITEMS WHERE ID =" + ID;

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(getValue);
        String qyti = rs.getString("Quantity");
        double qytdd = Double.parseDouble(qyti);
        double res = qytdd + qyt;
        String sql = "UPDATE ITEMS set Quantity = " + res
                + " WHERE ID=" + ID + ";";

        st.executeUpdate(sql);
        sql = "INSERT INTO STOCK(date,Operation,OperationQuantity,ItemID,phoneNumber) values" + "("
                + "DATE()"
                + ",'" + ops + "',"
                + qyt
                + ","
                + ID + ","
                + "'" + User.getPhone() + "')";
        st.executeUpdate(sql);

        st.close();
        con.close();

    }

    public void spendQyt(int ID, double qyt, String ops) throws SQLException {
        Connection con = this.connect();

        String getValue = "SELECT Quantity FROM ITEMS WHERE ID =" + ID;

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(getValue);
        String qyti = rs.getString("Quantity");
        double qytdd = Double.parseDouble(qyti);
        double res = qytdd - qyt;
        String sql = "UPDATE ITEMS set Quantity = " + res
                + " WHERE ID=" + ID + ";";
        st.executeUpdate(sql);

        st.executeUpdate(sql);
        sql = "INSERT INTO STOCK(date,Operation,OperationQuantity,ItemID,phoneNumber) values" + "("
                + "DATE()"
                + ",'" + ops + "',"
                + qyt
                + ","
                + ID + ","
                + "'" + User.getPhone() + "')";
        st.executeUpdate(sql);
        st.close();
        con.close();

    }

    public double getItemQuantity(double ID) throws SQLException {
        Connection con = this.connect();

        String getValue = "SELECT Quantity FROM ITEMS WHERE ID =" + ID;

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(getValue);
        double qyti = rs.getDouble("Quantity");

        st.close();
        con.close();
        return qyti;

    }
    //----------------------End Stock page -----------------//

//------------------------ report page ---------------------------------//
    public void getItemsForReport(ObservableList<Report> oblist) throws SQLException {
        oblist.clear();
        String sqlCommand = ""
                + "SELECT * "
                + "FROM STOCK ORDER BY Date  ;";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sqlCommand);

        while (rs.next()) {
            String Date = rs.getString("Date");
            String Operation = rs.getString("Operation");

            double OperationQuantity = rs.getDouble("OperationQuantity");
            int ItemID = rs.getInt("ItemID");

            String Username = rs.getString("phoneNumber");

            Report item = new Report(Date, Operation, OperationQuantity, ItemID, Username);

            oblist.add(item);
        }
        st.close();
        con.close();
    }

    //------------- find --------------------------------------
    public void getItemsDateReports(ObservableList oblist) throws SQLException {
        oblist.clear();
        String sqlCommand = ""
                + "SELECT Date "
                + "FROM STOCK;";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sqlCommand);

        while (rs.next()) {
            String date = rs.getString("Date");
            oblist.add(date);
        }

        st.close();
        con.close();
    }

    public void getSpecficItemDate(ObservableList oblist, String dateit) throws SQLException {
        oblist.clear();
        String sqlCommand = ""
                + "SELECT * "
                + "FROM STOCK where Date like '%" + dateit + "%'";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sqlCommand);

        while (rs.next()) {
            String Date = rs.getString("Date");
            String Operation = rs.getString("Operation");

            double OperationQuantity = rs.getDouble("OperationQuantity");
            int ItemID = rs.getInt("ItemID");

            String Username = rs.getString("Username");

            Report item = new Report(Date, Operation, OperationQuantity, ItemID, Username);

            oblist.add(item);
        }

        st.close();
        con.close();
    }

//------------------------ End report page ---------------------------------//
    //-------------- Menu page--------------------------------------------
    public void insertMenu(String date, String breakfast, String lunch, String dinner) throws SQLException {
        String sqlCommand
                = "INSERT INTO MENU( Date, Breakfast, Lunch, Dinner) "
                + "VALUES('" + date + "','" + breakfast + "','" + lunch + "','" + dinner + "');";

        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sqlCommand);
        st.close();
        con.close();
        System.out.println("the Menu added Successfully");
    }

    public boolean isExsist(String date) throws SQLException {
        boolean exsist = false;
        String sqlCommand = "SELECT * FROM MENU WHERE Date = '" + date + "';";

        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sqlCommand);
        if (rs.next()) {
            exsist = true;
        }
        st.close();
        con.close();
        return exsist;

    }

    public void updateMenu(String date, String breakfast, String lunch, String dinner) throws SQLException {
        String sqlCommand
                = "UPDATE MENU SET Breakfast = '" + breakfast + "', Lunch = '" + lunch + "',"
                + " Dinner = '" + dinner + "' WHERE Date = '" + date + "';";

        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sqlCommand);
        st.close();
        con.close();
        System.out.println("the Menu updated Successfully");
    }

    public void deleteMenu(String date) throws SQLException {

        String sqlCommand = "DELETE FROM MENU WHERE Date = '" + date + "';";

        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sqlCommand);
        st.close();
        con.close();
        System.out.println("the Menu deleted Successfully");
    }

    public void getMenu(ObservableList oblist) throws SQLException {
        oblist.clear();
        String sqlCommand = ""
                + "SELECT * "
                + "FROM MENU;";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sqlCommand);

        while (rs.next()) {
            oblist.add(new Menu(rs.getString("Date"), rs.getString("Breakfast"), rs.getString("Lunch"), rs.getString("Dinner")));
        }
        st.close();
        con.close();
    }

    //--------------End of Menu page--------------------------------------------
    //-------------- Home page--------------------------------------------
    public int getNumberOfCategory() throws SQLException {
        int numberOfCategory = 0;
        String sqlCommand = "SELECT count(*) FROM ITEMS;";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sqlCommand);

        while (rs.next()) {
            numberOfCategory = rs.getInt("count(*)");
        }
        st.close();
        con.close();
        return numberOfCategory;
    }

    public int getNumberOfItemDetails() throws SQLException {
        int numberOfItemDetails = 0;
        String sqlCommand = "SELECT sum(Quantity) From ITEMS;";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sqlCommand);

        while (rs.next()) {
            numberOfItemDetails = rs.getInt("sum(Quantity)");
        }
        st.close();
        con.close();
        return numberOfItemDetails;
    }

    public int getNumberOfItems() throws SQLException {
        int numberOfItems = 0;
        String sqlCommand = "SELECT count(*) From ITEMS;";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sqlCommand);

        while (rs.next()) {
            numberOfItems = rs.getInt("count(*)");
        }
        st.close();
        con.close();
        return numberOfItems;
    }

    public int getNumberOfZeroStock() throws SQLException {
        int numberOfZeroStock = 0;

        String sqlCommand = "SELECT count(*)  AS ZeroStock FROM ITEMS WHERE Quantity = 0; ";

//        String sqlCommand = "SELECT (SELECT count(*) FROM CATEGORY) - "
//                + "(SELECT count (distinct CatName)  FROM ITEMS) AS ZeroStock ;";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sqlCommand);

        while (rs.next()) {
            numberOfZeroStock = rs.getInt("ZeroStock");
        }
        st.close();
        con.close();
        return numberOfZeroStock;
    }
    //--------------End of Home page--------------------------------------------
}
