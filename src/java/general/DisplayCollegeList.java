/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Milind Ghiya
 */
public class DisplayCollegeList extends SimpleTagSupport {

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();

        // TODO: insert code to write html before writing the body content.
        // e.g.:
        //
        // out.println("<strong>" + attribute_1 + "</strong>");
        // out.println("    <blockquote>");
        JspFragment f = getJspBody();
        if (f != null) {
            f.invoke(out);
        }

        Connection con = null;
        Statement stmt = null;
        PageContext pc = (PageContext) getJspContext();

        int count = 0;

        String DB_URL = pc.getServletContext().getInitParameter("DB_URL");
        String DB_DRIVER = pc.getServletContext().getInitParameter("DB_DRIVER");
        String DB_USER = pc.getServletContext().getInitParameter("DB_USER");
        String DB_PASSWORD = pc.getServletContext().getInitParameter("DB_PASSWORD");
        ArrayList<String> al = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException ex) {
        }
        try {
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException ex) {
        }
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DisplayCollegeList.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "SELECT *  FROM college";
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
        }

        al = new ArrayList<String>();
        ArrayList<Integer> al2 = new ArrayList<Integer>();
        Date d = new Date();

        int i = new Integer(d.toString().substring(d.toString().length() - 4, d.toString().length()));

        int j = i;
        i = i - 4;
        for (; i <= j; i++) {
            al2.add(i);

        }

        try {
            while (rs.next()) {
                al.add(rs.getString("collegename"));

                // TODO: insert code to write html after writing the body content.
                // e.g.:
                //
                // out.println("    </blockquote>");
            }

            pc.getRequest().setAttribute("al", al);
            pc.getRequest().setAttribute("al2", al2);
            stmt.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(DisplayCollegeList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
