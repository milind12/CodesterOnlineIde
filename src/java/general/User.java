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
public class User {

    protected int userID;
    protected String college;
    protected String password;

    protected String email;

    protected String role;

    protected String name;

    protected String username;
    protected String reg;

    public String getreg() {
        return college;
    }

    /**
     * Set the value of college
     *
     * @param college new value of college
     */
    public void setreg(String reg) {
        this.reg = reg;
    }

    public String getCollege() {
        return college;
    }

    /**
     * Set the value of college
     *
     * @param college new value of college
     */
    public void setCollege(String college) {
        this.college = college;
    }

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void register(User u, ServletConfig servletConfig) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        Connection con = null;
        String DB_URL = servletConfig.getServletContext().getInitParameter("DB_URL");
        String DB_DRIVER = servletConfig.getServletContext().getInitParameter("DB_DRIVER");
        String DB_USER = servletConfig.getServletContext().getInitParameter("DB_USER");
        String DB_PASSWORD = servletConfig.getServletContext().getInitParameter("DB_PASSWORD");
        Class.forName(DB_DRIVER);
        con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        stmt = con.createStatement();
        String sql = null;
        sql = "INSERT INTO user (name,username,password,role,emailid,collegename) VALUES('" + u.getName() + "','" + u.getUsername() + "','" + u.getPassword() + "','" + u.getRole() + "','" + u.getEmail() + "','" + u.getCollege() + "')";
        stmt.executeUpdate(sql);

        stmt.close();
        con.close();

    }
}
