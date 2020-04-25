/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Milind Ghiya
 */
@WebServlet("/UploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 10MB
        maxRequestSize = 1024 * 1024 * 100)   // 50MB

public class UploadTestCase extends HttpServlet {

    ServletConfig servletConfig = null;

    @Override
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
            User user = (User) request.getSession().getAttribute("user");

            // creates the save directory if it does not exists
            try {
                HttpSession hs = request.getSession(false);

                String path = getServletContext().getInitParameter("initialpath") + ((Contest) hs.getAttribute("contest")).getCollegename() + File.separator + ((Contest) hs.getAttribute("contest")).getName() + File.separator + hs.getAttribute("problemid").toString();

                File fileSaveDir = new File(path);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdirs();
                }
                File folder = new File(path);
                File[] listOfFiles = folder.listFiles();
                /*  int x = listOfFiles.length;
                 Integer n = 0;
                 if (x != 0) {
                 n = Integer.parseInt(listOfFiles[x - 1].getName().substring(6, listOfFiles[x - 1].getName().length() - 4));
                 n++;
                 } else {
                 n = 1;
                 }
                 */ int a[] = new int[100];
                int n = listOfFiles.length;

                String temp;
                for (int i = 0; i < n; i++) {
                    if (listOfFiles[i].getName().contains("input")) {
                        temp = listOfFiles[i].getName().replace("input", "");
                        temp = temp.replace(".txt", "");
                        a[Integer.parseInt(temp)] = 1;
                    } else {
                        temp = listOfFiles[i].getName().replace("output", "");
                        temp = temp.replace(".txt", "");
                        a[Integer.parseInt(temp)] = 1;
                    }
                }
                Integer m = 1;

                for (int i = 0; i < 100; i++) {

                    if (a[i] == 1) {
                        m = i + 1;
                    }

                }
                String path1 = path + File.separator + "input" + m.toString() + ".txt";
                String path2 = path + File.separator + "output" + m.toString() + ".txt";
                Part part = request.getPart("file1");

                String fileName = extractFileName(part);
                part.write(path1);

                part = request.getPart("file2");

                fileName = extractFileName(part);
                part.write(path2);

                response.sendRedirect("upload.jsp?problemid=" + hs.getAttribute("problemid").toString());

            } catch (Exception e) {
                out.println(e);

            }

        }
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
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
