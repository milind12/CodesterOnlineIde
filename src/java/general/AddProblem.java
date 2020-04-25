/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Milind Ghiya
 */
public class AddProblem extends HttpServlet {

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
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try {
                HttpSession hs = request.getSession(false);
                /* TODO output your page here. You may use following sample code. */
                User u = (User) hs.getAttribute("user");

                if (hs.getAttribute("edit") == null) {
                    Statement stmt = null;
                    Connection con = null;
                    String DB_URL = getServletContext().getInitParameter("DB_URL");
                    String DB_DRIVER = getServletContext().getInitParameter("DB_DRIVER");
                    String DB_USER = getServletContext().getInitParameter("DB_USER");
                    String DB_PASSWORD = getServletContext().getInitParameter("DB_PASSWORD");
                    Class.forName(DB_DRIVER);
                    con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                    stmt = con.createStatement();
                    int userid = u.getUserID();
                    int contestid = Integer.parseInt(hs.getAttribute("contestid").toString()), problemid;
                    String sql = "select * from problem order by problemid desc limit 1";
                    ResultSet rs = stmt.executeQuery(sql);
                    rs.next();
                    int i = rs.getInt("problemid");

                    problemid = i + 1;

                    rs.close();

                    stmt.close();
                    con.close();

                    //Set and Save problem
                    Problem p = new Problem();
                    p.setUserid(userid);
                    p.setProblemid(problemid);
                    p.setContestid(contestid);
                    p.setConstraint(request.getParameter("constraint").replace("\n", "<br>"));
                    p.setDetail(request.getParameter("detail").replace("\n", "<br>"));
                    p.setName(request.getParameter("name").replace("\n", "<br>"));
                    p.setInputformat(request.getParameter("inputformat").replace("\n", "<br>"));
                    p.setEditorial(request.getParameter("editorial").replace("\n", "<br>"));
                    p.setOutputformat(request.getParameter("outputformat").replace("\n", "<br>"));
                    p.setSampleinput(request.getParameter("sampleinput").replace("\n", "<br>"));
                    p.setSampleoutput(request.getParameter("sampleoutput").replace("\n", "<br>"));
                    p.setScore(Integer.parseInt(request.getParameter("score")));
                    if (p.saveProblem(p, servletConfig).equals("yes")) {
                        hs.setAttribute("message", "Question added successfully");
                    } else {
                        hs.setAttribute("message", p.saveProblem(p, servletConfig));
                    }

                    response.sendRedirect("addProblem.jsp");
                } else {

                    Problem p = new Problem();
                    p.setConstraint(request.getParameter("constraint"));
                    p.setDetail(request.getParameter("detail"));
                    p.setName(request.getParameter("name"));
                    p.setInputformat(request.getParameter("inputformat"));
                    p.setEditorial(request.getParameter("editorial"));
                    p.setOutputformat(request.getParameter("outputformat"));
                    p.setSampleinput(request.getParameter("sampleinput"));
                    p.setSampleoutput(request.getParameter("sampleoutput"));
                    p.setScore(Integer.parseInt(request.getParameter("score")));

                    Problem.UpdateProblem((Integer) hs.getAttribute("problemid"), servletConfig, p);
                    hs.setAttribute("problemid", null);
                    hs.setAttribute("edit", null);
                    response.sendRedirect("addEditRemove.jsp?contestid=" + ((Contest) hs.getAttribute("contest")).getContestId());
                }

            } catch (Exception e) {
                out.println(e);
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
        } catch (SQLException ex) {
            Logger.getLogger(AddProblem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddProblem.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(AddProblem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddProblem.class.getName()).log(Level.SEVERE, null, ex);
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
