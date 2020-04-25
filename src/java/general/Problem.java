/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.FilterConfig;
import javax.servlet.ServletConfig;
import javax.servlet.jsp.PageContext;

/**
 *
 * @author Milind Ghiya
 */
public class Problem {

    private int problemid;

    private int contestid;

    private int userid;

    private String name;

    private String detail;

    private String inputformat;

    private String constraint;

    private String outputformat;

    private String sampleinput;

    private String editorial;

    private String sampleoutput;

    private ArrayList<TestCase> testCases;

    private int score;

    /**
     * Get the value of score
     *
     * @return the value of score
     */
    public int getScore() {
        return score;
    }

    /**
     * Set the value of score
     *
     * @param score new value of score
     */
    public void setScore(int score) {
        this.score = score;
    }

    public void setTestCases(ArrayList<TestCase> testCases) {
        this.testCases = testCases;

    }

    public ArrayList<TestCase> getTestCases() {
        return this.testCases;
    }

    /**
     * Get the value of sampleoutput
     *
     * @return the value of sampleoutput
     */
    public String getSampleoutput() {
        return sampleoutput;
    }

    /**
     * Set the value of sampleoutput
     *
     * @param sampleoutput new value of sampleoutput
     */
    public void setSampleoutput(String sampleoutput) {
        this.sampleoutput = sampleoutput;
    }

    /**
     * Get the value of editorial
     *
     * @return the value of editorial
     */
    public String getEditorial() {
        return editorial;
    }

    /**
     * Set the value of editorial
     *
     * @param editorial new value of editorial
     */
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    /**
     * Get the value of sampleinput
     *
     * @return the value of sampleinput
     */
    public String getSampleinput() {
        return sampleinput;
    }

    /**
     * Set the value of sampleinput
     *
     * @param sampleinput new value of sampleinput
     */
    public void setSampleinput(String sampleinput) {
        this.sampleinput = sampleinput;
    }

    /**
     * Get the value of outputformat
     *
     * @return the value of outputformat
     */
    public String getOutputformat() {
        return outputformat;
    }

    /**
     * Set the value of outputformat
     *
     * @param outputformat new value of outputformat
     */
    public void setOutputformat(String outputformat) {
        this.outputformat = outputformat;
    }

    /**
     * Get the value of constraint
     *
     * @return the value of constraint
     */
    public String getConstraint() {
        return constraint;
    }

    /**
     * Set the value of constraint
     *
     * @param constraint new value of constraint
     */
    public void setConstraint(String constraint) {
        this.constraint = constraint;
    }

    /**
     * Get the value of inputformat
     *
     * @return the value of inputformat
     */
    public String getInputformat() {
        return inputformat;
    }

    /**
     * Set the value of inputformat
     *
     * @param inputformat new value of inputformat
     */
    public void setInputformat(String inputformat) {
        this.inputformat = inputformat;
    }

    /**
     * Get the value of detail
     *
     * @return the value of detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * Set the value of detail
     *
     * @param detail new value of detail
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the value of userid
     *
     * @return the value of userid
     */
    public int getUserid() {
        return userid;
    }

    /**
     * Set the value of userid
     *
     * @param userid new value of userid
     */
    public void setUserid(int userid) {
        this.userid = userid;
    }

    /**
     * Get the value of contestid
     *
     * @return the value of contestid
     */
    public int getContestid() {
        return contestid;
    }

    /**
     * Set the value of contestid
     *
     * @param contestid new value of contestid
     */
    public void setContestid(int contestid) {
        this.contestid = contestid;
    }

    /**
     * Get the value of problemid
     *
     * @return the value of problemid
     */
    public int getProblemid() {
        return problemid;
    }

    /**
     * Set the value of problemid
     *
     * @param problemid new value of problemid
     */
    public void setProblemid(int problemid) {
        this.problemid = problemid;
    }

    public String saveProblem(Problem p, ServletConfig servletConfig) {
        try {
            Statement stmt = null;
            Connection con = null;
            String DB_URL = servletConfig.getServletContext().getInitParameter("DB_URL");
            String DB_DRIVER = servletConfig.getServletContext().getInitParameter("DB_DRIVER");
            String DB_USER = servletConfig.getServletContext().getInitParameter("DB_USER");
            String DB_PASSWORD = servletConfig.getServletContext().getInitParameter("DB_PASSWORD");
            Class.forName(DB_DRIVER);
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            stmt = con.createStatement();

            String sql = "insert into problem values ('" + p.getProblemid() + "','" + p.getContestid() + "','" + p.getUserid() + "','" + p.getName().replace("'", "''") + "','" + p.getDetail().replace("'", "''") + "','" + p.getInputformat().replace("'", "''") + "','" + p.getConstraint().replace("'", "''") + "','" + p.getOutputformat().replace("'", "''") + "','" + p.getSampleinput().replace("'", "''") + "','" + p.getSampleoutput().replace("'", "''") + "','" + p.getEditorial().replace("'", "''") + "','" + p.getScore() + "')";
            stmt.executeUpdate(sql);
            stmt.close();
            con.close();
            return "yes";
        } catch (Exception e) {
            return e.toString();
        }
    }

    public static ArrayList<Problem> getQuestionsOfContest(Contest c, PageContext pc) {
        ArrayList<Problem> al = new ArrayList<Problem>();
        Connection con = null;
        Problem p = null;
        Statement stmt = null;
        String DB_URL = pc.getServletContext().getInitParameter("DB_URL");
        String DB_DRIVER = pc.getServletContext().getInitParameter("DB_DRIVER");
        String DB_USER = pc.getServletContext().getInitParameter("DB_USER");
        String DB_PASSWORD = pc.getServletContext().getInitParameter("DB_PASSWORD");
        try {
            Class.forName(DB_DRIVER);
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            stmt = con.createStatement();
            String sql = "select * from problem where contestid=" + c.getContestId();
            ResultSet rs = stmt.executeQuery(sql);
            TestCase t = null;
            String tcp = c.getCollegename() + File.separator + c.getName() + File.separator;
            while (rs.next()) {
                p = new Problem();
                p.setUserid(rs.getInt("userid"));
                p.setContestid(rs.getInt("contestid"));
                p.setProblemid(rs.getInt("problemid"));
                p.setName(replaceme(rs.getString("name")));
                p.setScore(rs.getInt("score"));
                p.setInputformat(replaceme(rs.getString("inputformat")));
                p.setOutputformat(replaceme(rs.getString("outputformat")));
                p.setConstraint(replaceme(rs.getString("constraint")));
                p.setSampleinput((rs.getString("sampleinput")));
                p.setSampleoutput((rs.getString("sampleoutput")));
                p.setEditorial(replaceme(rs.getString("editorial")));
                p.setDetail(replaceme(rs.getString("detail")));

                al.add(p);
            }
        } catch (Exception e) {
        }

        return al;
    }

    public static ArrayList<Problem> getQuestionsOfContest(Contest c, FilterConfig fc) {
        ArrayList<Problem> al = new ArrayList<Problem>();
        Connection con = null;
        Problem p = null;
        Statement stmt = null;
        String DB_URL = fc.getServletContext().getInitParameter("DB_URL");
        String DB_DRIVER = fc.getServletContext().getInitParameter("DB_DRIVER");
        String DB_USER = fc.getServletContext().getInitParameter("DB_USER");
        String DB_PASSWORD = fc.getServletContext().getInitParameter("DB_PASSWORD");
        try {
            Class.forName(DB_DRIVER);
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            stmt = con.createStatement();
            String sql = "select * from problem where contestid=" + c.getContestId();
            ResultSet rs = stmt.executeQuery(sql);
            TestCase t = null;

            String tcp = c.getCollegename() + File.separator + c.getName() + File.separator;
            while (rs.next()) {
                p = new Problem();
                p.setUserid(rs.getInt("userid"));
                p.setContestid(rs.getInt("contestid"));
                p.setProblemid(rs.getInt("problemid"));
                p.setScore(rs.getInt("score"));
                p.setName(replaceme(rs.getString("name")));
                p.setInputformat(replaceme(rs.getString("inputformat")));
                p.setOutputformat(replaceme(rs.getString("outputformat")));
                p.setConstraint(replaceme(rs.getString("constraint")));
                p.setSampleinput(replaceme(rs.getString("sampleinput")));
                p.setSampleoutput(replaceme(rs.getString("sampleoutput")));
                p.setEditorial(replaceme(rs.getString("editorial")));
                p.setDetail(replaceme(rs.getString("detail")));
                p.setTestCases(TestCase.getTestCaseOfProblem(p, c, fc));
                al.add(p);
            }
        } catch (Exception e) {

        }

        return al;
    }

    public static ArrayList<Problem> getQuestionsOfContest(Contest c, ServletConfig fc) {
        ArrayList<Problem> al = new ArrayList<Problem>();
        Connection con = null;
        Problem p = null;
        Statement stmt = null;
        String DB_URL = fc.getServletContext().getInitParameter("DB_URL");
        String DB_DRIVER = fc.getServletContext().getInitParameter("DB_DRIVER");
        String DB_USER = fc.getServletContext().getInitParameter("DB_USER");
        String DB_PASSWORD = fc.getServletContext().getInitParameter("DB_PASSWORD");
        try {
            Class.forName(DB_DRIVER);
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            stmt = con.createStatement();
            String sql = "select * from problem where contestid=" + c.getContestId();
            ResultSet rs = stmt.executeQuery(sql);
            TestCase t = null;

            String tcp = c.getCollegename() + File.separator + c.getName() + File.separator;
            while (rs.next()) {
                p = new Problem();
                p.setUserid(rs.getInt("userid"));
                p.setContestid(rs.getInt("contestid"));
                p.setProblemid(rs.getInt("problemid"));
                p.setScore(rs.getInt("score"));
                p.setName(replaceme(rs.getString("name")));
                p.setInputformat(replaceme(rs.getString("inputformat")));
                p.setOutputformat(replaceme(rs.getString("outputformat")));
                p.setConstraint(replaceme(rs.getString("constraint")));
                p.setSampleinput(replaceme(rs.getString("sampleinput")));
                p.setSampleoutput(replaceme(rs.getString("sampleoutput")));
                p.setEditorial(replaceme(rs.getString("editorial")));
                p.setDetail(replaceme(rs.getString("detail")));
                al.add(p);
            }
        } catch (Exception e) {

        }

        return al;
    }

    static private String replaceme(String s) {
        s = s.replace("<br>", "$%$");
        s = s.replace("<", "&lt");
        s = s.replace(">", "&gt");
        s = s.replace("$%$", "<br>");

        return s;
    }

    static public void UpdateProblem(int problemid, ServletConfig servletConfig, Problem p) {
        Statement stmt;
        Connection con;
        String DB_URL = servletConfig.getServletContext().getInitParameter("DB_URL");
        String DB_DRIVER = servletConfig.getServletContext().getInitParameter("DB_DRIVER");
        String DB_USER = servletConfig.getServletContext().getInitParameter("DB_USER");
        String DB_PASSWORD = servletConfig.getServletContext().getInitParameter("DB_PASSWORD");
        try {
            Class.forName(DB_DRIVER);
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            stmt = con.createStatement();
            String sql = "update problem set name='" + p.getName().replace("'", "''") + "',detail='" + p.getDetail().replace("'", "''") + "',inputformat='" + p.getInputformat().replace("'", "''") + "',`constraint`='" + p.getConstraint().replace("'", "''") + "',outputformat='" + p.getOutputformat().replace("'", "''") + "',sampleinput='" + p.getSampleinput().replace("'", "''") + "',sampleoutput='" + p.getSampleoutput().replace("'", "''") + "', editorial='" + p.getEditorial().replace("'", "''") + "',score='" + p.getScore() + "' where problemid=" + problemid;
            stmt.executeUpdate(sql);

            stmt.close();
            con.close();

        } catch (Exception e) {

        }

    }

    static public void DeleteProblem(int problemid, ServletConfig servletConfig, Contest c) {
        Statement stmt = null;
        Connection con = null;
        String DB_URL = servletConfig.getServletContext().getInitParameter("DB_URL");
        String DB_DRIVER = servletConfig.getServletContext().getInitParameter("DB_DRIVER");
        String DB_USER = servletConfig.getServletContext().getInitParameter("DB_USER");
        String DB_PASSWORD = servletConfig.getServletContext().getInitParameter("DB_PASSWORD");
        try {
            Class.forName(DB_DRIVER);
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            File f = new File(servletConfig.getServletContext().getInitParameter("initialpath") + c.getCollegename() + File.separator + c.getName());
            if (f.exists()) {
                f.delete();
            }
            stmt = con.createStatement();
            String sql = "delete from problem where problemid=" + problemid;
            stmt.executeUpdate(sql);
            stmt.close();
            con.close();
        } catch (Exception e) {
        }

    }
}
