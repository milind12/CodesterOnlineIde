/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Milind Ghiya
 */
public class SaveCode extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            try {
                String filename = request.getParameter("filename");
                ServletContext sc = getServletContext();
                HttpSession hs = request.getSession(false);
                User u = (User) hs.getAttribute("user");
                hs.setAttribute("code", request.getParameter("tA"));
                hs.setAttribute("lang", request.getParameter("s").toString());
                if (request.getParameter("s").toString().equals("1")) {
                    if (!filename.endsWith(".c")) {
                        filename = filename + ".c";
                    }
                } else if (request.getParameter("s").toString().equals("2")) {
                    if (!filename.endsWith(".cpp")) {
                        filename = filename + ".cpp";
                    }
                } else if (request.getParameter("s").toString().equals("0")) {
                    if (!filename.endsWith(".java")) {
                        filename = filename + ".java";
                    }
                }
                File directory;
                directory = new File(sc.getInitParameter("initialpath") + File.separator + u.getUsername() + File.separator + "code");
                directory.mkdirs();

                File f = new File(directory, filename);
                f.createNewFile();

                BufferedWriter bw = null;
                FileWriter fw = null;

                fw = new FileWriter(sc.getInitParameter("initialpath") + File.separator + u.getUsername() + File.separator + "code" + File.separator + filename);
                bw = new BufferedWriter(fw);
                bw.write((String) request.getParameter("tA"));
                bw.close();
                fw.close();
                hs.setAttribute("message", "Code saved successfully");
                request.getRequestDispatcher("newusercode.jsp").forward(request, response);

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
