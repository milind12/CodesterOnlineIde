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
public class AddContest extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            //Insert contest details to contest table codester database
            Connection con = null;
            Statement stmt = null;
            String DB_URL = servletConfig.getServletContext().getInitParameter("DB_URL");
            String DB_DRIVER = servletConfig.getServletContext().getInitParameter("DB_DRIVER");
            String DB_USER = servletConfig.getServletContext().getInitParameter("DB_USER");
            String DB_PASSWORD = servletConfig.getServletContext().getInitParameter("DB_PASSWORD");
            Class.forName(DB_DRIVER);
            try {
                con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                stmt = con.createStatement();
                String sql = null;
                int x = 0;
                HttpSession s = request.getSession(false);
                /*
                 ResultSet rs = stmt.executeQuery(sql);
                 if (rs.next()) {
                 rs.close();
                 stmt.close();
                 con.close();
                 s.setAttribute("message", "Contest name is repeated.Please choose some other contest name");
                 response.sendRedirect("newcontest.jsp");
                 }

                 sql = "SELECT COUNT(*) AS MAXCONTEST FROM contest";
                 rs = stmt.executeQuery(sql);
                 rs.next();
                 int i = rs.getInt("MAXCONTEST");
                 rs.close();

                 */
                sql = "INSERT INTO contest (name,userid,collegename,start,end) VALUES('" + request.getParameter("name") + "','" + ((User) s.getAttribute("user")).getUserID() + "','" + ((User) s.getAttribute("user")).getCollege() + "','" + request.getParameter("startdate") + " " + request.getParameter("starttime") + "','" + request.getParameter("enddate") + " " + request.getParameter("endtime") + "')";
                stmt.executeUpdate(sql);
                s.setAttribute("message", request.getParameter("name") + " contest added.You can view contest details and add questions now");

                response.sendRedirect("newcontest.jsp");
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddContest.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddContest.class.getName()).log(Level.SEVERE, null, ex);
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
