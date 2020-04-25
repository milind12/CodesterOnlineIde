   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import static general.Code.randomAlphaNumeric;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.servlet.FilterConfig;
import javax.servlet.ServletConfig;

/**
 *
 * @author Milind Ghiya
 */
public class Java extends Code {

    /**
     * Get the value of src
     *
     * @return the value of src
     */
    public String getSrc() {
        return this.src;
    }

    /**
     * Set the value of src
     *
     * @param src new value of src
     */
    public void setSrc(String src) {
        this.src = src;
    }

    /**
     * Get the value of savePath
     *
     * @return the value of savePath
     */
    public String getSavePath() {
        return savePath;
    }

    /**
     * Set the value of savePath
     *
     * @param savePath new value of savePath
     */
    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    /**
     * Get the value of cmd
     *
     * @return the value of cmd
     */
    public String getCmd() {
        return cmd;
    }

    /**
     * Set the value of cmd
     *
     * @param cmd new value of cmd
     */
    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    /**
     * Get the value of path
     *
     * @return the value of path
     */
    public void setDefault(ServletConfig servletConfig, String username) {
        String folder1 = randomAlphaNumeric(3);
        String folder = username + File.separator + folder1;
        File f = new File(servletConfig.getServletContext().getInitParameter("initialpath") + folder);
        if (!f.exists()) {
            f.mkdirs();
        }
        f = new File(servletConfig.getServletContext().getInitParameter("initialpath") + username + File.separator + "submissions");
        if (!f.exists()) {
            f.mkdirs();
        }
        this.setSubmissionPath(servletConfig.getServletContext().getInitParameter("initialpath") + username + File.separator + "submissions" + File.separator + randomAlphaNumeric(4) + ".java");

        this.savePath = "" + servletConfig.getServletContext().getInitParameter("initialpath") + folder + "";
        this.cmd = "javac -d";
        this.src = servletConfig.getServletContext().getInitParameter("initialpath") + folder + File.separator + "JavaApp.java";
        this.loc = "" + servletConfig.getServletContext().getInitParameter("initialpath") + folder + File.separator + "JavaApp.java";
        this.input = servletConfig.getServletContext().getInitParameter("initialpath") + folder + File.separator + "input.txt";
        this.output = servletConfig.getServletContext().getInitParameter("initialpath") + folder + File.separator + "output.txt";
        this.l.add("java");
        this.l.add("-cp");
        this.l.add(servletConfig.getServletContext().getInitParameter("initialpath") + folder + File.separator);
        this.l.add("JavaApp");
    }

    public void setDefault(FilterConfig servletConfig, String username) {
        String folder1 = randomAlphaNumeric(3);
        String folder = username + File.separator + folder1;
        File f = new File(servletConfig.getServletContext().getInitParameter("initialpath") + folder);
        if (!f.exists()) {
            f.mkdirs();
        }
        f = new File(servletConfig.getServletContext().getInitParameter("initialpath") + username + File.separator + "submissions");
        if (!f.exists()) {
            f.mkdirs();
        }
        this.setSubmissionPath(servletConfig.getServletContext().getInitParameter("initialpath") + username + File.separator + "submissions" + File.separator + randomAlphaNumeric(4) + ".java");

        this.savePath =  servletConfig.getServletContext().getInitParameter("initialpath") + folder ;
        this.cmd = "javac -d";
        this.src = servletConfig.getServletContext().getInitParameter("initialpath") + folder + File.separator + "JavaApp.java";
        this.loc = servletConfig.getServletContext().getInitParameter("initialpath") + folder + File.separator + "JavaApp.java";
        this.input = servletConfig.getServletContext().getInitParameter("initialpath") + folder + File.separator + "input.txt";
        this.output = servletConfig.getServletContext().getInitParameter("initialpath") + folder + File.separator + "output.txt";
        this.l.add("java");
        this.l.add("-cp");
        this.l.add(servletConfig.getServletContext().getInitParameter("initialpath") + folder + File.separator);
        this.l.add("JavaApp");
    }

    public void setDefault(ServletConfig servletConfig) {

        String folder = randomAlphaNumeric(3);
        File f = new File(servletConfig.getServletContext().getInitParameter("initialpath") + folder);
        if (!f.exists()) {
            f.mkdirs();
        }
        this.savePath = "" + servletConfig.getServletContext().getInitParameter("initialpath") + folder + "";
        this.cmd = "env -i javac -d";
        this.src = servletConfig.getServletContext().getInitParameter("initialpath") + folder + File.separator + "JavaApp.java";
        this.loc = "" + servletConfig.getServletContext().getInitParameter("initialpath") + folder + File.separator + "JavaApp.java";
        this.input = servletConfig.getServletContext().getInitParameter("initialpath") + folder + File.separator + "input.txt";
        this.output = servletConfig.getServletContext().getInitParameter("initialpath") + folder + File.separator + "output.txt";
        this.l.add("env");
        this.l.add("-i");
        this.l.add("java");
        this.l.add("-cp");
        this.l.add(servletConfig.getServletContext().getInitParameter("initialpath") + folder + File.separator);
        this.l.add("JavaApp");
    }

    public String getLoc() {
        return this.loc;
    }

    /**
     * Set the value of path
     *
     * @param path new value of path
     */
    public void setLoc(String loc) {
        this.loc = loc;
    }

}
