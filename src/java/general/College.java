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
public class College {

    protected int collegeid;
    protected String collegename;
    private String collegeaddress;

    /**
     * Get the value of collegeaddress
     *
     * @return the value of collegeaddress
     */
    public String getCollegeaddress() {
        return collegeaddress;
    }

    /**
     * Set the value of collegeaddress
     *
     * @param collegeaddress new value of collegeaddress
     */
    public void setCollegeaddress(String collegeaddress) {
        this.collegeaddress = collegeaddress;
    }

    /**
     * Get the value of collegename
     *
     * @return the value of collegename
     */
    public String getCollegename() {
        return collegename;
    }

    /**
     * Set the value of collegename
     *
     * @param collegename new value of collegename
     */
    public void setCollegename(String collegename) {
        this.collegename = collegename;
    }

    /**
     * Get the value of collegeid
     *
     * @return the value of collegeid
     */
    public int getCollegeid() {
        return collegeid;
    }

    /**
     * Set the value of collegeid
     *
     * @param collegeid new value of collegeid
     */
    public void setCollegeid(int collegeid) {
        this.collegeid = collegeid;
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

        String sql = "select count(*) as COLLEGENUM from college";
        ResultSet rs = stmt.executeQuery(sql);

        rs = stmt.executeQuery(sql);
        rs.next();
        int i = rs.getInt("COLLEGENUM");
        i++;
        sql = "INSERT INTO college  VALUES (" + i + ",'" + this.getCollegename() + "','" + this.getCollegeaddress() + "')";
        stmt.executeUpdate(sql);
        stmt.close();
        con.close();

    }

}
