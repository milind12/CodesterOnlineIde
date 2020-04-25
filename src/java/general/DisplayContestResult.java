/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
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
public class DisplayContestResult extends SimpleTagSupport {

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

            int contestid = Integer.parseInt(request.getParameter("contestid"));

            Contest c = Contest.getContest(contestid, pc);
            c.setProblems(Problem.getQuestionsOfContest(c, pc));

            StringBuilder sb = new StringBuilder();
            int maxscore = 0;

            for (int i = 0; i < c.getProblems().size(); i++) {
                sb.append(c.getProblems().get(i).getProblemid());

                if (i != (c.getProblems().size() - 1)) {
                    sb.append(",");
                }

                maxscore += c.getProblems().get(i).getScore();
            }
            if (sb.toString().equals("")) {
                sb.append("-1");
            }
            hs.setAttribute("contest", c);
            Statement stmt = null;
            Connection con = null;
            String DB_URL = pc.getServletContext().getInitParameter("DB_URL");
            String DB_DRIVER = pc.getServletContext().getInitParameter("DB_DRIVER");
            String DB_USER = pc.getServletContext().getInitParameter("DB_USER");
            String DB_PASSWORD = pc.getServletContext().getInitParameter("DB_PASSWORD");
            ContestResult cr = null;
            ArrayList<ContestResult> contestresults = new ArrayList<ContestResult>();
            hs.setAttribute("check", sb.toString());
            try {
                Class.forName(DB_DRIVER);
                con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                stmt = con.createStatement();

                String sql = "select u.name as name,u.username as username,u.emailid as emailid,sum(table1.maxscore) as score,count(table1.col1) as ca from (select userid,problemid,max(score) as maxscore,(case when result =\"Correct Answer\" then result else null end ) as col1,count(case when result =\"Correct Answer\" then result else null end ) as correctanswers,max(stime) as maxstime from `submission` where problemid in (" + sb.toString() + ") group by userid,problemid) table1 join user u on table1.userid=u.userid group by table1.userid order by `ca` desc,maxstime";
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    cr = new ContestResult();

                    cr.setName(rs.getString("name"));
                    cr.setEmailid(rs.getString("emailid"));
                    cr.setUsername(rs.getString("username"));
                    cr.setScore(rs.getInt("score"));
                    cr.setCorrectAns(rs.getInt("ca"));
                    cr.setMaxscore(maxscore);
                    contestresults.add(cr);

                }
                hs.setAttribute("contestsetterid", c.getUserid());
                hs.setAttribute("contestresults", contestresults);
                hs.setAttribute("emailSend", c.getEmailSend());
            } catch (Exception e) {

            }

            // TODO: insert code to write html after writing the body content.
            // e.g.:
            //
            // out.println("    </blockquote>");
        } catch (java.io.IOException ex) {
            throw new JspException("Error in DisplayContestResult tag", ex);
        }
    }

}
