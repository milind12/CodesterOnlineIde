/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Milind Ghiya
 */
public class DisplayContest extends SimpleTagSupport {

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

            PageContext pc = (PageContext) getJspContext();
            User u = (User) pc.getAttribute("user", PageContext.SESSION_SCOPE);
            Date today = new Date();
            Connection con = null;
            Statement stmt = null;
            String DB_URL = pc.getServletContext().getInitParameter("DB_URL");
            String DB_DRIVER = pc.getServletContext().getInitParameter("DB_DRIVER");
            String DB_USER = pc.getServletContext().getInitParameter("DB_USER");
            String DB_PASSWORD = pc.getServletContext().getInitParameter("DB_PASSWORD");
            Class.forName(DB_DRIVER);
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            stmt = con.createStatement();
            String sql = "select * from contest where userid=" + u.getUserID();
            ResultSet rs = stmt.executeQuery(sql);
            Contest c = null;
            ArrayList<Contest> upcoming = new ArrayList<Contest>();
            ArrayList<Contest> ended = new ArrayList<Contest>();
            ArrayList<Contest> ongoing = new ArrayList<Contest>();
            while (rs.next()) {
                c = new Contest();
                c.setCollegename(u.getCollege());
                c.setUserid(u.getUserID());
                c.setContestId(rs.getInt("contestid"));
                c.setName(rs.getString("name"));

                c.setStart(rs.getTimestamp("start"));
                c.setStartdate(new Date(c.getStart().getTime()));
                c.setEnd(rs.getTimestamp("end"));
                c.setEnddate(new Date(c.getEnd().getTime()));

                if (today.compareTo(c.getStartdate()) == 1 && today.compareTo(c.getEnddate()) == -1) {
                    ongoing.add(c);
                } else if (today.compareTo(c.getStartdate()) == -1 && today.compareTo(c.getEnddate()) == -1) {
                    upcoming.add(c);
                } else {
                    ended.add(c);
                }
            }
            Collections.sort(ongoing, new SortContestByDate());
            Collections.sort(upcoming, new SortContestByDate());
            Collections.sort(ended, new SortContestByDate());

            pc.getRequest().setAttribute("ongoing", ongoing);
            pc.getRequest().setAttribute("ended", ended);
            pc.getRequest().setAttribute("upcoming", upcoming);
        } catch (Exception ex) {
            throw new JspException(ex.toString());
        }
    }

}

class SortContestByDate implements Comparator<Contest> {

    @Override
    public int compare(Contest c2, Contest c1) {

        return c1.getStart().compareTo(c2.getStart());

    }
}
