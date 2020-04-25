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
import java.util.ArrayList;
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
public class ResultServlet extends HttpServlet {

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
            throws ServletException, IOException, InterruptedException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession hs = request.getSession(false);
            Code c = null;
            User u = (User) hs.getAttribute("user");
            Contest contest = ((Contest) hs.getAttribute("contest"));
            String savesubmission = getServletContext().getInitParameter("initialpath") + u.getUsername() + File.separator + contest.getCollegename() + File.separator + contest.getName() + File.separator + contest.getProblems().get(Integer.parseInt(hs.getAttribute("problemnum").toString()) - 1).getProblemid();
            File submission = new File(savesubmission);
            if (!submission.exists()) {
                submission.mkdirs();
            }
            Integer n = submission.listFiles().length;

            n++;

            if (request.getParameter("s").toString().equals("1")) {
                c = new C();

            } else if (request.getParameter("s").toString().equals("2")) {
                c = new Cpp();

            } else if (request.getParameter("s").toString().equals("0")) {
                c = new Java();

            }
            c.setDefault(servletConfig, u.getUsername());

            File f = new File(c.getSrc());
            if (f.exists() && !f.isDirectory()) {

                f.delete();
                // do something
            }

            FileWriter writer = new FileWriter(savesubmission + File.separator + "submission" + n.toString() + ".txt", true);
            writer.write(request.getParameter("tA"));

            writer.close();
            writer = new FileWriter(c.getSrc(), true);
            writer.write(request.getParameter("tA"));

            writer.close();

            File f3 = new File(c.getOutput());

            String line;
            StringBuilder compileTimeError = new StringBuilder();
            int flag = 0;
            Process p = Runtime.getRuntime().exec(c.getCmd() + " " + c.getSavePath() + " " + c.getLoc());

            BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));

            BufferedReader bre = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            while ((line = bri.readLine()) != null) {

            }
            bri.close();
            while ((line = bre.readLine()) != null) {
                compileTimeError.append("Compile Time Error");

                flag = 1;
                break;
            }
            //out.println(compileTimeError.toString());
            bre.close();
            int flag2 = 0;
            File input = null;
            ArrayList<TestCase> testcases = contest.getProblems().get(Integer.parseInt(hs.getAttribute("problemnum").toString()) - 1).getTestCases();
            if (flag == 0) {
                ProcessBuilder pb = new ProcessBuilder(c.l);
                StringBuilder sb = new StringBuilder();
                BufferedReader reader1 = null;

                BufferedReader reader2 = null;
                boolean areEqual = true;
                f3.createNewFile();

                int lineNum = 1;
                String line1 = null;

                String line2 = null;
                for (int i = 0; i < testcases.size(); i++) {
                    input = new File(testcases.get(i).getInput());
                    pb.redirectInput(input);
                    pb.redirectOutput(f3);
                    pb.redirectError(f3);
                    p = pb.start();

                    p.waitFor(3, TimeUnit.SECONDS);
                    if (p.isAlive()) {

                        sb.append("time limit exceeded");
                        p.destroyForcibly();

                        break;
                    }

                    reader1 = new BufferedReader(new FileReader(testcases.get(i).getOutput()));
                    reader2 = new BufferedReader(new FileReader(c.getOutput()));

                    while (true) {
                        line1 = reader1.readLine();

                        line2 = reader2.readLine();
                        if (line1 == null && line2 == null) {
                            reader1.close();
                            reader2.close();
                            break;
                        } else if ((line1 == null && line2 != null) || (line1 != null && line2 == null)) {
                            reader1.close();
                            reader2.close();

                            areEqual = false;
                            break;

                        } else if (!line1.equals(line2)) {
                            reader1.close();
                            reader2.close();

                            areEqual = false;
                            break;
                        }
                    }
                    if (areEqual == false) {
                        sb.append("Wrong Answer");
                        break;
                    }
                }
                if (!areEqual) {
                    out.println(sb.toString());
                } else {
                    out.println("correct answer");
                }
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
        } catch (InterruptedException ex) {
            Logger.getLogger(ResultServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ResultServlet.class.getName()).log(Level.SEVERE, null, ex);
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
