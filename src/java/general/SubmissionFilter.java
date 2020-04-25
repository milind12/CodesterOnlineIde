/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Stack;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Milind Ghiya
 */
public class SubmissionFilter implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured.
    private FilterConfig filterConfig = null;

    public SubmissionFilter() {
    }

    private void doBeforeProcessing(ServletRequest req, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("SubmissionFilter:DoBeforeProcessing");
        }

        HttpServletRequest request = (HttpServletRequest) req;

        HttpSession hs = request.getSession(false);
        User u = (User) hs.getAttribute("user");
        Contest contest = ((Contest) hs.getAttribute("contest"));
        String savesubmission = filterConfig.getServletContext().getInitParameter("initialpath") + u.getUsername() + File.separator + contest.getCollegename() + File.separator + contest.getName() + File.separator + contest.getProblems().get(Integer.parseInt(hs.getAttribute("problemnum").toString()) - 1).getProblemid();
        File submission = new File(savesubmission);
        if (!submission.exists()) {
            submission.mkdirs();
        }
        Integer n = 0;
        if (submission.listFiles() != null) {
            n = submission.listFiles().length;
        }

        File[] lof = submission.listFiles();
        Arrays.sort(lof, new FileComparator());
        StringBuilder reader = null;
        BufferedReader br = null;
        CodeFile c = null;
        String temp = new String();
        BasicFileAttributes attrs = null;
        Path p = null;
        ArrayList<String> content = new ArrayList<String>();
        ArrayList al;
        al = new ArrayList<CodeFile>();
        Date d = null;
        for (int i = n - 1; i >= 0; i--) {
            reader = new StringBuilder();
            c = new CodeFile();
            c.setName(lof[i].getName());
            if (lof[i].getName().endsWith(".java")) {
                c.setLang("JAVA");
            } else if (lof[i].getName().endsWith(".c")) {
                c.setLang("C");
            } else if (lof[i].getName().endsWith(".cpp")) {
                c.setLang("C++");
            }
            br = new BufferedReader(new FileReader(savesubmission + File.separator + lof[i].getName()));

            while (true) {

                temp = br.readLine();
                if (temp == null) {
                    break;
                }
                temp = temp.replace("<", "&lt");
                temp = temp.replace(">", "&gt");
                reader.append(temp);
                reader.append("<br>");
            }

            content.add(reader.toString());

            p = Paths.get(savesubmission + File.separator + lof[i].getName());
            attrs = Files.readAttributes(p, BasicFileAttributes.class);
            attrs.creationTime().toMillis();
            d = new Date(attrs.creationTime().toMillis());
            SimpleDateFormat sdf = new SimpleDateFormat(" MMM dd,yy  hh:mm a");
            c.setMillis(attrs.creationTime().toMillis());
            c.setSize(new Long(attrs.size()).toString());
            c.setDate(sdf.format(d));
            sdf = new SimpleDateFormat("HH:mm");
            c.setTime(sdf.format(d));
            al.add(c);

        }
        if (br != null) {
            br.close();
        }

        hs.setAttribute("content", content);
        hs.setAttribute("listOfSubmissions", al);

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
            String sql = "select * from submission where userid=" + u.getUserID() + " and problemid=" + contest.getProblems().get(Integer.parseInt(hs.getAttribute("problemnum").toString()) - 1).getProblemid();
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<String> s = new ArrayList<String>();
            while (rs.next()) {
                s.add(rs.getString("result"));
            }
            rs.close();
            stmt.close();
            con.close();
            hs.setAttribute("resultlist", s);
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
            log("SubmissionFilter:DoAfterProcessing");
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
            log("SubmissionFilter:doFilter()");
        }

        doBeforeProcessing(request, response);

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
                log("SubmissionFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("SubmissionFilter()");
        }
        StringBuffer sb = new StringBuffer("SubmissionFilter(");
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

class FileComparator implements Comparator<File> {

    @Override
    public int compare(File o1, File o2) {

        return new Long(o1.lastModified()).compareTo(o2.lastModified());

    }

}
