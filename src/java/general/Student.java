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
public class Student extends User {

    /**
     * Get the value of emailid
     *
     * @return the value of emailid
     */
    protected int yoj;

    /**
     * Get the value of yoj
     *
     * @return the value of yoj
     */
    public int getYoj() {
        return yoj;
    }

    /**
     * Set the value of yoj
     *
     * @param yoj new value of yoj
     */
    public void setYoj(int yoj) {
        this.yoj = yoj;
    }

    /**
     * Get the value of college
     *
     * @return the value of college
     */
    /**
     * Set the value of college
     *
     * @param college new value of college
     */
    public void save(Student s, ServletConfig servletConfig) throws ClassNotFoundException, SQLException {
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

        sql = "INSERT INTO student  VALUES (" + rs.getInt("userid") + ",'" + s.getYoj() + "')";
        int i = stmt.executeUpdate(sql);
        rs.close();
        stmt.close();
        con.close();

    }
}
