/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;
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
public class CompileRun extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    ServletConfig servletConfig = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.servletConfig = config;

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, InterruptedException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            User u = null;
            String nextPage = null;
            Code c = null;
            HttpSession hs = request.getSession(false);
            boolean bool = (hs.getAttribute("user") == null);
            if (bool) {
                nextPage = "newcode.jsp";
            } else {
                u = (User) hs.getAttribute("user");
                nextPage = "newusercode.jsp";
            }
            if (request.getParameter("s").toString().equals("1")) {
                c = new C();

            } else if (request.getParameter("s").toString().equals("2")) {
                c = new Cpp();

            } else if (request.getParameter("s").toString().equals("0")) {
                c = new Java();

            }

            if (bool) {
                c.setDefault(servletConfig);
            } else {
                c.setDefault(servletConfig, u.getUsername());
            }

            hs.setAttribute("code", request.getParameter("tA"));
            hs.setAttribute("lang", request.getParameter("s").toString());
            hs.setAttribute("input", request.getParameter("tA2"));
            File f = new File(c.getSrc());
            if (f.exists() && !f.isDirectory()) {

                f.delete();
                // do something
            }
            File f2 = new File(c.getInput());
            File f3 = new File(c.getOutput());
            f3.createNewFile();
            /* TODO output your page here. You may use following sample code. */

            FileWriter writer = new FileWriter(c.getSrc(), true);
            writer.write(request.getParameter("tA"));

            writer.close();
            if (!bool) {

                writer = new FileWriter(c.getSubmissionPath(), true);
                writer.write(request.getParameter("tA"));

                writer.close();
            }
            FileWriter writer2 = new FileWriter(c.getInput(), true);
            writer2.write(request.getParameter("tA2"));

            writer2.close();

            String line;
            StringBuilder compileTimeError = new StringBuilder();
            int flag = 0;

            //         out.println(c.getCmd() + " " + c.getSavePath() + " " + c.getLoc() + " " + new File(c.getSrc()).exists() + " " + getServletContext().getRealPath("/"));
            Process p = Runtime.getRuntime().exec(c.getCmd() + " " + c.getSavePath() + " " + c.getLoc());
//out.println("cmd /c cd "+c.getPath()+" & "+c.getCmd() +" "+c.getSavePath()+" "+c.getLoc());
            BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));

            BufferedReader bre = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            while ((line = bri.readLine()) != null) {
                out.println(line);

            }
            bri.close();

            while ((line = bre.readLine()) != null) {
                out.println(line);
                compileTimeError.append(line + "\n");
                flag = 1;
            }
            bre.close();
            p.waitFor();
            int flag2 = 0;
            if (flag == 0) {
                ProcessBuilder pb = new ProcessBuilder(c.l);
                pb.redirectInput(f2);

                pb.redirectOutput(f3);

                pb.redirectError(f3);
                p = pb.start();

                p.waitFor(5, TimeUnit.SECONDS);
                StringBuilder sb = new StringBuilder();
                if (p.isAlive()) {

//         ProcessBuilder pb2=new ProcessBuilder("cmd.exe","/c taskkill /F /FI \"Memusage lt 40000\" /IM "+c.pname);
//         Process p2=pb2.start();
//        try {
//            p2.waitFor();
//        } catch (InterruptedException ex) {
//               }
//
                    sb.append("time limit exceeded");
                    sb.append("\n");

                    p.destroyForcibly();

                }

                BufferedReader reader = new BufferedReader(new FileReader(c.getOutput()));
                //BufferedReader br = new InputStreamReader(new FileInputStream(txtFilePath));

                while ((line = reader.readLine()) != null) {
                    sb.append(line);

                    sb.append('\n');

                }

                reader.close();

                out.println(sb.toString());
                hs.setAttribute("output", sb);

                request.getRequestDispatcher(nextPage).forward(request, response);
            } else {
                hs.setAttribute("output", "Compilation unsuccessful\n" + compileTimeError.toString().replace(c.getSrc(), "line "));
                request.getRequestDispatcher(nextPage).forward(request, response);

            }

        }
//
//    }
//     if(flag2==1){out.println("time limit exceeded");}
//
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
        } catch (InterruptedException ex) {
            Logger.getLogger(CompileRun.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (InterruptedException ex) {
            Logger.getLogger(CompileRun.class.getName()).log(Level.SEVERE, null, ex);
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
