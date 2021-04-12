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
    //--------------calculatoe page--------------------------------------------

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
                + "There is "+AmountPerBox+" "+ UnitType +" "+itemName+" in each box\n"
                + "Totla price = " + totalPrice + "\n";
        st.close();
        con.close();

        return resoultText;
    }

    public void insertUser(String username, String password, String emial, String phone, String address) throws SQLException {
        String sqlCommand
                = "INSERT INTO USERS(Username, Password, Email, Phone, Address) "
                + "VALUES('" + username + "','" + password + "','" + emial + "','" + phone + "','" + address + "');";

        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sqlCommand);
        st.close();
        con.close();
        System.out.println("the user added");
    }

    public void createRoom(String roomNumber, double roomRate) throws SQLException {
        String sqlCommand
                = "INSERT INTO ROOMS(RoomNumber, RoomRate)"
                + "VALUES('" + roomNumber + "'," + roomRate + ");";

        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sqlCommand);
        st.close();
        con.close();
        System.out.println("the room added");
    }

    public boolean login(String username, String password) throws SQLException {
        boolean valid;
        String sqlCommand = ""
                + "SELECT Username, Password "
                + "FROM USERS "
                + "WHERE Username = '" + username + "' ";

        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sqlCommand);
        if (rs.next()) {
            String name = rs.getString("Username");
            String pass = rs.getString("Password");
            valid = username.equals(name) && password.equals(pass);
        } else {
            valid = false;
        }
        st.close();
        con.close();
        return valid;

//        while (rs.next()) {
//            String name = rs.getString("username");
//            String pass = rs.getString("password");
//
//            if (username.equals(name) && password.equals(pass)) {
//                st.close();
//                con.close();
//                return true;
//            }
//        }
//        st.close();
//        con.close();
//        return false;
    }

    public void reservationRoomsList(ObservableList oblist) throws SQLException {
        String sqlCommand = ""
                + "SELECT RoomNumber, RoomRate, Availability "
                + "FROM ROOMS;";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sqlCommand);

        while (rs.next()) {
            String number = rs.getString("RoomNumber");
            String rate = rs.getString("RoomRate");
            String avilability = rs.getString("Availability");
            if (avilability.equals("Avilable")) {
                //   Room room = new Room(number, rate, avilability);
                //    oblist.add(room);
            }
        }
        System.out.println("closed");
        st.close();
        con.close();
    }

    public void adminRoomsList(ObservableList oblist) throws SQLException {
        String sqlCommand = ""
                + "SELECT RoomNumber, RoomRate, Availability "
                + "FROM ROOMS;";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sqlCommand);

        while (rs.next()) {
            String number = rs.getString("RoomNumber");
            String rate = rs.getString("RoomRate");
            String avilability = rs.getString("Availability");

            //     Room room = new Room(number, rate, avilability);
            //     oblist.add(room);
        }
        st.close();
        con.close();
    }

    public void checkIn(String number, String checkIn, String checkOut) throws SQLException {
        String sqlCommand = ""
                + "INSERT INTO RESERVATION(ROOMNUMBER, CHECKIN, CHECKOUT) "
                + "VALUES('" + number + "', '" + checkIn + "', '" + checkOut + "');";

        String sqlCommand2 = ""
                + "UPDATE ROOMS "
                + "SET Availability = 'Busy'"
                + "WHERE RoomNumber = '" + number + "';";

        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sqlCommand);
        st.executeUpdate(sqlCommand2);
        st.close();
        con.close();
        System.out.println("the reservation added added");
    }
}
