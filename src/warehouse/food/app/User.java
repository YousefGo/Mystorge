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
public class User {

    static String user = "";
    static String phone;
    public User(String user) {
        this.phone = user;
    }

    public static String getUser() {
        return phone;
    }

    public static void setUser(String phone) {
        User.phone = phone;
    }

    User() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        User.phone = phone;
    }

}
