/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Email_G;

import general.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author USER
 */
public class OneTimePServlet extends HttpServlet {

     ServletConfig servletConfig = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.servletConfig = config;}
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
            
            String pas=request.getParameter("password");
            String cpas=request.getParameter("confirmpassword");
            
            if(pas.equals(cpas)){
                
                Statement  stmt = null;
            //HttpSession s = null;
            Connection con = null;
            String DB_URL = servletConfig.getServletContext().getInitParameter("DB_URL");
            String DB_DRIVER = servletConfig.getServletContext().getInitParameter("DB_DRIVER");
            String DB_USER = servletConfig.getServletContext().getInitParameter("DB_USER");
            String DB_PASSWORD = servletConfig.getServletContext().getInitParameter("DB_PASSWORD");
            try {
                Class.forName(DB_DRIVER);
                con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                   stmt =  con.createStatement();
                   HttpSession hs = request.getSession(false);
                User u = (User) hs.getAttribute("user");
               // ResultSet rs = null;
                String sql = "Update user set reg = '0' , password = '"+request.getParameter("password")+"' where username = '"+ u.getUsername()+"'" ;
                 
                stmt.executeUpdate(sql);
                    //response.sendRedirect("home.jsp");
                   response.sendRedirect("home.jsp");
                    
                
                
                
                } catch (Exception e) {
                out.println(e);
                request.getRequestDispatcher("OneTimePassword.jsp").forward(request, response);
            }
            
            
            }else{
                request.getRequestDispatcher("OneTimePassword.jsp").forward(request, response);
            }
            /* TODO output your page here. You may use following sample code. */
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
          
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
