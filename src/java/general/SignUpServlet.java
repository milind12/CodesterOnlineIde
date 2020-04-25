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
import java.security.SecureRandom;
import java.util.Random;
import Email_G.*;

/**
 *
 * @author Milind Ghiya
 */
public class SignUpServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            Connection con = null;
            Statement stmt = null;
            String DB_URL = servletConfig.getServletContext().getInitParameter("DB_URL");
            String DB_DRIVER = servletConfig.getServletContext().getInitParameter("DB_DRIVER");
            String DB_USER = servletConfig.getServletContext().getInitParameter("DB_USER");
            String DB_PASSWORD = servletConfig.getServletContext().getInitParameter("DB_PASSWORD");
            Class.forName(DB_DRIVER);

            if (request.getParameter("collegeaddress") == null) {

                con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                stmt = con.createStatement();
                String sql = "select *  from user where username='" + request.getParameter("username") + "'";
                int x = 0;
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    rs.close();
                    stmt.close();
                    con.close();
                    HttpSession s = request.getSession(false);
                    s.setAttribute("message", "Username is repeated.Please choose some other username");
                    s.setAttribute("name", request.getParameter("name"));
                    //s.setAttribute("password", request.getParameter("password"));
                    s.setAttribute("yoj", request.getParameter("yoj"));
                    s.setAttribute("collegename", request.getParameter("collegename"));
                    s.setAttribute("email", request.getParameter("email"));
                    request.getRequestDispatcher("signup.jsp").forward(request, response);
                } else if (request.getParameter("username").length() <= 4) {
                    rs.close();
                    stmt.close();
                    con.close();
                    HttpSession s = request.getSession(false);
                    s.setAttribute("message", "Username must be of more than 4 characters.");
                    s.setAttribute("name", request.getParameter("name"));
                    //s.setAttribute("password", request.getParameter("password"));
                    s.setAttribute("yoj", request.getParameter("yoj"));
                    s.setAttribute("collegename", request.getParameter("collegename"));
                    s.setAttribute("email", request.getParameter("email"));
                    request.getRequestDispatcher("signup.jsp").forward(request, response);

                } else {

                    rs.close();
                    stmt.close();
                    con.close();
                    try {
                        User u = new Student();
                        u.setName(request.getParameter("name"));
                        u.setEmail(request.getParameter("email"));
                        u.setCollege(request.getParameter("collegename"));
                        //SecureRandom random = new SecureRandom();
                        char[] R_chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
                        StringBuilder sb = new StringBuilder();
                        Random random = new Random();
                        for (int i = 0; i < 20; i++) {
                            char c = R_chars[random.nextInt(R_chars.length)];
                            sb.append(c);
                        }
                        String password = sb.toString();
                        request.setAttribute("password", password);
                        SendEmail sm = new SendEmail(request.getParameter("email"), "password", "Your Password At CodeSter is " + password);
                        u.setPassword(password);
                        u.setUsername(request.getParameter("username"));
                        u.setRole("student");
                        Student s = (Student) u;

                        u.register(u, servletConfig);

                        s.setYoj(new Integer(request.getParameter("yoj")));
                        s.save(s, servletConfig);

                        HttpSession hs = request.getSession(false);
                        hs.setAttribute("message", "Password Successfully Send Check your Email..");

                        request.getRequestDispatcher("signup.jsp").forward(request, response);
                    } catch (Exception e) {
                        out.println(x + " " + e);
                    }
                }
            } else {

                con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                stmt = con.createStatement();
                String sql = "select *  from college where collegename='" + request.getParameter("collegename") + "'";
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    rs.close();
                    stmt.close();
                    con.close();
                    HttpSession s = request.getSession(false);
                    //s.setAttribute("role", rs.);
                    s.setAttribute("message", "Collegename is already registered");
                    request.getRequestDispatcher("signup2.jsp").forward(request, response);
                } else {
                    College c = new College();
                    c.setCollegename(request.getParameter("collegename"));
                    c.setCollegeaddress(request.getParameter("collegeaddress"));
                    c.save(servletConfig);
                }

                rs.close();
                stmt.close();
                con.close();

                User u = new ContestHost();
                u.setName(request.getParameter("name"));

                u.setPassword(request.getParameter("password"));
                char[] R_chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
                StringBuilder sb = new StringBuilder();
                Random random = new Random();
                for (int i = 0; i < 20; i++) {
                    char c = R_chars[random.nextInt(R_chars.length)];
                    sb.append(c);
                }
                String password = sb.toString();
                request.setAttribute("password", password);
                SendEmail sm = new SendEmail(request.getParameter("email"), "password", "Your Password At CodeSter is " + password);
                u.setPassword(password);
                //u.setUsername(request.getParameter("username"));
                u.setRole("contesthost");
                u.setEmail(request.getParameter("email"));
                u.setCollege(request.getParameter("collegename"));
                u.register(u, servletConfig);

                ContestHost ch = (ContestHost) u;

                ch.setPhone(request.getParameter("phone"));
                ch.save(servletConfig);
                HttpSession s = request.getSession(false);
                s.setAttribute("message", "Password Successfully Send Check your Email..");
                request.getRequestDispatcher("signup2.jsp").forward(request, response);

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

            response.getWriter().println("probem");
        } catch (SQLException ex) {
            response.getWriter().println("probem");
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
            Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, ex);
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
