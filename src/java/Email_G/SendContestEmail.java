/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Email_G;

import general.Contest;
import general.ContestResult;
import general.Problem;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
public class SendContestEmail extends HttpServlet {

    ServletConfig servletConfig = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.servletConfig = config;

    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            Contest c = Contest.getContest(new Integer(request.getParameter("contestid")), servletConfig);
            c.setProblems(Problem.getQuestionsOfContest(c, servletConfig));

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
            Statement stmt = null;
            Connection con = null;
            String DB_URL = getServletContext().getInitParameter("DB_URL");
            String DB_DRIVER = getServletContext().getInitParameter("DB_DRIVER");
            String DB_USER = getServletContext().getInitParameter("DB_USER");
            String DB_PASSWORD = getServletContext().getInitParameter("DB_PASSWORD");
            ContestResult cr = null;
            ArrayList<ContestResult> contestresults = new ArrayList<ContestResult>();
            try {
                Class.forName(DB_DRIVER);
                con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                stmt = con.createStatement();

                String sql = "SELECT u.name as name,u.username as username,u.emailid as emailid,sum(table1.maxscore) as score,count(table1.col1) as ca from (SELECT userid,problemid,max(score) as maxscore,(case when result =\"Correct Answer\" THEN result ELSE null END ) as col1,count(case when result =\"Correct Answer\" THEN result ELSE null END ) as correctanswers FROM `submission` where problemid in (select problemid from problem where contestid=" + c.getContestId() + ") group by userid,problemid) table1 join user u on table1.userid=u.userid group by table1.userid ORDER BY `ca` DESC  limit 10";
                ResultSet rs = stmt.executeQuery(sql);
                int i = 0;
                while (rs.next()) {
                    cr = new ContestResult();
//out.println(rs.getString("name"));
                    i++;
                    SendEmail sm = new SendEmail(rs.getString("emailid"), "", "Congratulations From team of Codester! Result for " + c.getName() + "" + " " + "\nYour Rank in the contest is " + i);
                    cr.setName(rs.getString("name"));
                    cr.setEmailid(rs.getString("emailid"));
                    cr.setUsername(rs.getString("username"));
                    cr.setScore(rs.getInt("score"));
                    cr.setCorrectAns(rs.getInt("ca"));
                    cr.setMaxscore(maxscore);
                    contestresults.add(cr);

                }

                sql = "Update contest set EmailSend=1 where contestid=" + c.getContestId();
                stmt.executeUpdate(sql);

                rs.close();
                stmt.close();
                con.close();
                response.sendRedirect("contestresults.jsp?contestid=" + c.getContestId());
            } catch (Exception e) {
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SendContestEmail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SendContestEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SendContestEmail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SendContestEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
