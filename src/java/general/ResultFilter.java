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
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Milind Ghiya
 */
public class ResultFilter implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured.
    private FilterConfig filterConfig = null;

    public ResultFilter() {
    }

    private void doBeforeProcessing(ServletRequest req, ServletResponse response)
            throws IOException, ServletException, InterruptedException {
        if (debug) {
            log("ResultFilter:DoBeforeProcessing");
        }

        float pos = 0;
        HttpServletRequest request = (HttpServletRequest) req;

        HttpSession hs = request.getSession(false);

        Code c = null;
        User u = (User) hs.getAttribute("user");
        Contest contest = ((Contest) hs.getAttribute("contest"));

        if (contest.getEnddate().compareTo(new Date()) == -1) {
            ((HttpServletResponse) response).sendRedirect("home.jsp");
        }

        String savesubmission = filterConfig.getServletContext().getInitParameter("initialpath") + u.getUsername() + File.separator + contest.getCollegename() + File.separator + contest.getName() + File.separator + contest.getProblems().get(Integer.parseInt(hs.getAttribute("problemnum").toString()) - 1).getProblemid();
        File submission = new File(savesubmission);
        if (!submission.exists()) {
            submission.mkdirs();
        }
        Integer n = submission.listFiles().length;

        n++;
        String extension = null;
        if (request.getParameter("s").toString().equals("1")) {
            c = new C();
            extension = ".c";
        } else if (request.getParameter("s").toString().equals("2")) {
            c = new Cpp();
            extension = ".cpp";
        } else if (request.getParameter("s").toString().equals("0")) {
            c = new Java();
            extension = ".java";
        }
        c.setDefault(filterConfig, u.getUsername());

        File f = new File(c.getSrc());
        if (f.exists() && !f.isDirectory()) {

            f.delete();
            // do something
        }

        FileWriter writer = new FileWriter(savesubmission + File.separator + "submission" + n.toString() + extension, true);
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

            hs.setAttribute("result", compileTimeError.toString());
            flag = 1;
            break;
        }
        //out.println(compileTimeError.toString());
        bre.close();
        int flag2 = 0;
        File input = null;
        ArrayList<TestCase> testcases = contest.getProblems().get(Integer.parseInt(hs.getAttribute("problemnum").toString()) - 1).getTestCases();
        Date d1 = null;
        Date d2 = null;
        ArrayList<String> resultOfTestCases = new ArrayList<String>();
        ArrayList<Long> timetaken = new ArrayList<Long>();
        int flag5 = 0;
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
                d1 = new Date();

                p = pb.start();

                p.waitFor(3, TimeUnit.SECONDS);
                if (p.isAlive()) {

                    sb.append("Time Limit Exceeded");
                    p.destroyForcibly();
                    hs.setAttribute("result", sb.toString());

                    break;
                }
                d2 = new Date();

                timetaken.add(d2.getTime() - d1.getTime());
                reader1 = new BufferedReader(new FileReader(testcases.get(i).getOutput()));
                reader2 = new BufferedReader(new FileReader(c.getOutput()));

                while (true) {
                    line1 = reader1.readLine();

                    line2 = reader2.readLine();
                    if (line1 == null && line2 == null) {
                        ++pos;
                        reader1.close();
                        reader2.close();
                        break;
                    } else if ((line1 == null && line2 != null) || (line1 != null && line2 == null)) {
                        reader1.close();
                        reader2.close();
                        flag5 = 1;
                        areEqual = false;
                        break;

                    } else if (!line1.equals(line2)) {
                        reader1.close();
                        reader2.close();
                        flag5 = 1;
                        areEqual = false;
                        break;
                    }
                }
                if (areEqual == false) {
                    resultOfTestCases.add("Wrong Answer");
                } else {
                    resultOfTestCases.add("Correct Answer");
                }
            }
            if (!sb.toString().equals("Time Limit Exceeded")) {
                if (flag5 == 1) {
                    hs.setAttribute("result", "Wrong Answer");;
                } else {
                    hs.setAttribute("result", "Correct Answer");
                }
            } else {

            }
        }
        hs.setAttribute("resultoftc", resultOfTestCases);
        hs.setAttribute("timetaken", timetaken);

        Statement stmt = null;
        Connection con = null;
        String DB_URL = filterConfig.getServletContext().getInitParameter("DB_URL");
        String DB_DRIVER = filterConfig.getServletContext().getInitParameter("DB_DRIVER");
        String DB_USER = filterConfig.getServletContext().getInitParameter("DB_USER");
        String DB_PASSWORD = filterConfig.getServletContext().getInitParameter("DB_PASSWORD");
        try {
            Class.forName(DB_DRIVER);
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            stmt = con.createStatement();
            String sql = "insert into submission (problemid,userid,score,result,ip,stime) values('" + contest.getProblems().get(Integer.parseInt(hs.getAttribute("problemnum").toString()) - 1).getProblemid() + "','" + u.getUserID() + "','" + pos / testcases.size() * contest.getProblems().get(Integer.parseInt(hs.getAttribute("problemnum").toString()) - 1).getScore() + "','" + hs.getAttribute("result").toString() + "','" + req.getRemoteAddr() + "','" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + "')";

            stmt.executeUpdate(sql);
            stmt.close();
            con.close();
        } catch (Exception e) {

        }

// Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
	/*
         for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
         String name = (String)en.nextElement();
         String values[] = request.getParameterValues(name);
         int n = values.length;
         StringBuffer buf = new StringBuffer();
         buf.append(name);
         buf.append("=");
         for(int i=0; i < n; i++) {
         buf.append(values[i]);
         if (i < n-1)
         buf.append(",");
         }
         log(buf.toString());
         }
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("ResultFilter:DoAfterProcessing");
        }

	// Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed.
	/*
         for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
         String name = (String)en.nextElement();
         Object value = request.getAttribute(name);
         log("attribute: " + name + "=" + value.toString());

         }
         */
        // For example, a filter might append something to the response.
	/*
         PrintWriter respOut = new PrintWriter(response.getWriter());
         respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        if (debug) {
            log("ResultFilter:doFilter()");
        }

        try {
            doBeforeProcessing(request, response);
        } catch (InterruptedException ex) {
            Logger.getLogger(ResultFilter.class.getName()).log(Level.SEVERE, null, ex);
        }

        Throwable problem = null;
        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }

        doAfterProcessing(request, response);

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("ResultFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("ResultFilter()");
        }
        StringBuffer sb = new StringBuffer("ResultFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
