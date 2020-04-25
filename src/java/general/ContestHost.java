/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletConfig;

/**
 *
 * @author Milind Ghiya
 */
public class ContestHost extends User {

    protected String phone;

    /**
     * Get the value of phone
     *
     * @return the value of phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set the value of phone
     *
     * @param phone new value of phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void save(ServletConfig servletConfig) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        Connection con = null;
        String DB_URL = servletConfig.getServletContext().getInitParameter("DB_URL");
        String DB_DRIVER = servletConfig.getServletContext().getInitParameter("DB_DRIVER");
        String DB_USER = servletConfig.getServletContext().getInitParameter("DB_USER");
        String DB_PASSWORD = servletConfig.getServletContext().getInitParameter("DB_PASSWORD");
        Class.forName(DB_DRIVER);
        con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        stmt = con.createStatement();

        String sql = "select * from user order by userid desc limit 1";
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();

        sql = "INSERT INTO contesthost  VALUES (" + rs.getInt("userid") + ",'" + this.getPhone() + "')";

        int i = stmt.executeUpdate(sql);
        rs.close();
        stmt.close();
        con.close();

    }

}
