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
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Milind Ghiya
 */
public class ShowProfile extends SimpleTagSupport {

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();

        try {
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
            HttpServletRequest hsr = (HttpServletRequest) pc.getRequest();
            HttpSession hs = hsr.getSession(false);
            String DB_URL = pc.getServletContext().getInitParameter("DB_URL");
            String DB_DRIVER = pc.getServletContext().getInitParameter("DB_DRIVER");
            String DB_USER = pc.getServletContext().getInitParameter("DB_USER");
            String DB_PASSWORD = pc.getServletContext().getInitParameter("DB_PASSWORD");

            try {
                Class.forName(DB_DRIVER);
            } catch (ClassNotFoundException ex) {
            }

            try {
                con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                stmt = con.createStatement();
            } catch (SQLException ex) {
            }

            User user = new User();
            try {
                String sql = "select *  from user where username='" + hsr.getParameter("username") + "'";
                ResultSet rs = null;
                rs = stmt.executeQuery(sql);
                rs.next();
                user.setUserID(rs.getInt("userid"));
                user.setEmail(rs.getString("emailid"));
                user.setCollege(rs.getString("collegename"));
                user.setName(rs.getString("name"));
                user.setRole(rs.getString("role"));
                user.setUsername(rs.getString("username"));
                sql = "select p.problemid,p.name,p.contestid from (SELECT distinct problemid FROM `submission` where userid=" + user.getUserID() + " and result='Correct Answer' order by stime desc) a join problem p on a.problemid=p.problemid";
                ArrayList<String> al1 = new ArrayList<String>();
                ArrayList<Integer> al2 = new ArrayList<Integer>();
                ArrayList<Integer> al3 = new ArrayList<Integer>();

                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    al1.add(rs.getString("name"));
                    al2.add(rs.getInt("contestid"));
                    al3.add(rs.getInt("problemid"));

                }
                hsr.setAttribute("otheruser", user);
                hs.setAttribute("al1", al1);
                hs.setAttribute("al2", al2);
                hs.setAttribute("al3", al3);
                hs.setAttribute(sql, al3);
            } catch (SQLException ex) {
            }

            // TODO: insert code to write html after writing the body content.
            // e.g.:
            //
            // out.println("    </blockquote>");
        } catch (java.io.IOException ex) {
            throw new JspException("Error in ShowProfile tag", ex);
        }
    }

}
