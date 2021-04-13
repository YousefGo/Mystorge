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

    public void updateItem(int ID, String Name, double UnitPrice, String UnitType,  double AmountPerBox, String catName) throws SQLException {
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

        String sql = "SELECT * FROM USERS";
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
    
    void registerInsert(String FirstName, String LastName, String phone, String email, String password) throws SQLException {

        String sql = "INSERT INTO USERS(Fname, Lname, Phone, Email, Password) VALUES('" + FirstName + "', '" + LastName + "', '" + phone + "', '" + email + "', '" + password +"')";
        Connection con = this.connect();

        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();
        System.out.println("The user added :)");

    }

    //--------------End of Register page--------------------------------------------
    
//    public void createRoom(String roomNumber, double roomRate) throws SQLException {
//        String sqlCommand
//                = "INSERT INTO ROOMS(RoomNumber, RoomRate)"
//                + "VALUES('" + roomNumber + "'," + roomRate + ");";
//
//        Connection con = this.connect();
//        Statement st = con.createStatement();
//        st.executeUpdate(sqlCommand);
//        st.close();
//        con.close();
//        System.out.println("the room added");
//    }
//
//    public boolean login(String username, String password) throws SQLException {
//        boolean valid;
//        String sqlCommand = ""
//                + "SELECT Username, Password "
//                + "FROM USERS "
//                + "WHERE Username = '" + username + "' ";
//
//        Connection con = this.connect();
//        Statement st = con.createStatement();
//        ResultSet rs = st.executeQuery(sqlCommand);
//        if (rs.next()) {
//            String name = rs.getString("Username");
//            String pass = rs.getString("Password");
//            valid = username.equals(name) && password.equals(pass);
//        } else {
//            valid = false;
//        }
//        st.close();
//        con.close();
//        return valid;
//
////        while (rs.next()) {
////            String name = rs.getString("username");
////            String pass = rs.getString("password");
////
////            if (username.equals(name) && password.equals(pass)) {
////                st.close();
////                con.close();
////                return true;
////            }
////        }
////        st.close();
////        con.close();
////        return false;
//    }
//
//    public void reservationRoomsList(ObservableList oblist) throws SQLException {
//        String sqlCommand = ""
//                + "SELECT RoomNumber, RoomRate, Availability "
//                + "FROM ROOMS;";
//        Connection con = this.connect();
//        Statement st = con.createStatement();
//        ResultSet rs = st.executeQuery(sqlCommand);
//
//        while (rs.next()) {
//            String number = rs.getString("RoomNumber");
//            String rate = rs.getString("RoomRate");
//            String avilability = rs.getString("Availability");
//            if (avilability.equals("Avilable")) {
//                //   Room room = new Room(number, rate, avilability);
//                //    oblist.add(room);
//            }
//        }
//        System.out.println("closed");
//        st.close();
//        con.close();
//    }
//
//    public void adminRoomsList(ObservableList oblist) throws SQLException {
//        String sqlCommand = ""
//                + "SELECT RoomNumber, RoomRate, Availability "
//                + "FROM ROOMS;";
//        Connection con = this.connect();
//        Statement st = con.createStatement();
//        ResultSet rs = st.executeQuery(sqlCommand);
//
//        while (rs.next()) {
//            String number = rs.getString("RoomNumber");
//            String rate = rs.getString("RoomRate");
//            String avilability = rs.getString("Availability");
//
//            //     Room room = new Room(number, rate, avilability);
//            //     oblist.add(room);
//        }
//        st.close();
//        con.close();
//    }
//
//    public void checkIn(String number, String checkIn, String checkOut) throws SQLException {
//        String sqlCommand = ""
//                + "INSERT INTO RESERVATION(ROOMNUMBER, CHECKIN, CHECKOUT) "
//                + "VALUES('" + number + "', '" + checkIn + "', '" + checkOut + "');";
//
//        String sqlCommand2 = ""
//                + "UPDATE ROOMS "
//                + "SET Availability = 'Busy'"
//                + "WHERE RoomNumber = '" + number + "';";
//
//        Connection con = this.connect();
//        Statement st = con.createStatement();
//        st.executeUpdate(sqlCommand);
//        st.executeUpdate(sqlCommand2);
//        st.close();
//        con.close();
//        System.out.println("the reservation added added");
//    }
}
