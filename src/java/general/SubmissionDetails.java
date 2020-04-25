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
public class SubmissionDetails extends SimpleTagSupport {

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
            HttpServletRequest request = (HttpServletRequest) pc.getRequest();
            HttpSession hs = request.getSession(false);

            Contest c = (Contest) hs.getAttribute("contest");

            Statement stmt = null;
            Connection con = null;
            String DB_URL = pc.getServletContext().getInitParameter("DB_URL");
            String DB_DRIVER = pc.getServletContext().getInitParameter("DB_DRIVER");
            String DB_USER = pc.getServletContext().getInitParameter("DB_USER");
            String DB_PASSWORD = pc.getServletContext().getInitParameter("DB_PASSWORD");
            try {
                Class.forName(DB_DRIVER);
                con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                stmt = con.createStatement();
                String sql = "select * from `submission` where problemid in (select problemid from problem where contestid=" + c.getContestId() + ") and userid in (select userid from user where username='" + request.getParameter("username") + "')";
                ResultSet rs = stmt.executeQuery(sql);
                Submission s = null;
                ArrayList<Submission> subs = new ArrayList<Submission>();
                while (rs.next()) {
                    s = new Submission();
                    s.setUserid(rs.getInt("userid"));
                    s.setProblemid(rs.getInt("problemid"));
                    s.setResult(rs.getString("result"));
                    s.setIp(rs.getString("ip"));
                    s.setScore(rs.getInt("score"));
                    s.setStime(rs.getTimestamp("stime"));
                    subs.add(s);
                }
                hs.setAttribute("subs", subs);

            } catch (Exception e) {
            }
            // TODO: insert code to write html after writing the body content.
            // e.g.:
            //
            // out.println("    </blockquote>");
        } catch (java.io.IOException ex) {
            throw new JspException("Error in SubmissionDetails tag", ex);
        }
    }

}
