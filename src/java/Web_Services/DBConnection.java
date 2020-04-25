/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web_Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Milind Ghiya
 */
public class DBConnection {

    static String DB_DRIVER = "com.mysql.jdbc.Driver";
//    static String DB_URL = "jdbc:mysql://127.8.86.140:3306/codester";
    //  static String DB_USER = "adminJRjZBmF";
    // static String DB_PASSWORD = "ltu_QnnZtdy";
    static String DB_URL = "jdbc:mysql://localhost/codester";
    static String DB_USER = "root";
    static String DB_PASSWORD = "root";

    public static Connection createConnection(Connection c) throws SQLException, ClassNotFoundException {
        Class.forName(DB_DRIVER);
        c = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        return c;
    }
}
