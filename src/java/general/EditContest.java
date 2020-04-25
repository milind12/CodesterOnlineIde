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
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Milind Ghiya
 */
public class EditContest extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            Connection con = null;
            Statement stmt = null;
            String DB_URL = getServletContext().getInitParameter("DB_URL");
            String DB_DRIVER = getServletContext().getInitParameter("DB_DRIVER");
            String DB_USER = getServletContext().getInitParameter("DB_USER");
            String DB_PASSWORD = getServletContext().getInitParameter("DB_PASSWORD");
            try {
                Class.forName(DB_DRIVER);
                con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                stmt = con.createStatement();
                String sql = "update contest set name='" + request.getParameter("name") + "', start='" + request.getParameter("startdate") + " " + request.getParameter("starttime") + "',end='" + request.getParameter("enddate") + " " + request.getParameter("endtime") + "' where contestid=" + request.getParameter("contestid");
                stmt.executeUpdate(sql);
            } catch (Exception e) {
                out.print(e);
                request.getSession(false).setAttribute("message", "something went wrong ...Try again later");
                response.sendRedirect("editcontest.jsp?contestid=" + request.getParameter("contestid"));
            }
            request.getSession(false).setAttribute("message", "Saved Successfully..");
            response.sendRedirect("editcontest.jsp?contestid=" + request.getParameter("contestid"));

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
        processRequest(request, response);
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
        processRequest(request, response);
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
