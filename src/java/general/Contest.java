/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.FilterConfig;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.jsp.PageContext;

/**
 *
 * @author Milind Ghiya
 */
public class Contest {

    private int contestId;

    private String name;

    private int userid;

    private String collegename;

    private Timestamp start;
    private Date startdate;

    private Date enddate;

    private Timestamp end;

    private String startingDateString;

    private String endDateString;

    private ArrayList<Problem> problems;

    private String enddatetime;

    private String startdatetime;

    private int emailSend;

    /**
     * Get the value of emailSend
     *
     * @return the value of emailSend
     */
    public int getEmailSend() {
        return emailSend;
    }

    /**
     * Set the value of emailSend
     *
     * @param emailSend new value of emailSend
     */
    public void setEmailSend(int emailSend) {
        this.emailSend = emailSend;
    }

    /**
     * Get the value of startdatetime
     *
     * @return the value of startdatetime
     */
    public String getStartdatetime() {
        return startdatetime;
    }

    /**
     * Set the value of startdatetime
     *
     * @param startdatetime new value of startdatetime
     */
    public void setStartdatetime(String startdatetime) {
        this.startdatetime = startdatetime;
    }

    /**
     * Get the value of enddatetime
     *
     * @return the value of enddatetime
     */
    public String getEnddatetime() {
        return enddatetime;
    }

    /**
     * Set the value of enddatetime
     *
     * @param enddatetime new value of enddatetime
     */
    public void setEnddatetime(String enddatetime) {
        this.enddatetime = enddatetime;
    }

    public ArrayList<Problem> getProblems() {
        return this.problems;
    }

    public void setProblems(ArrayList<Problem> problems) {
        this.problems = problems;
    }

    /**
     * Get the value of endDateString
     *
     * @return the value of endDateString
     */
    public String getEndDateString() {
        return endDateString;
    }

    /**
     * Set the value of endDateString
     *
     * @param endDateString new value of endDateString
     */
    public void setEndDateString(String endDateString) {
        this.endDateString = endDateString;
    }

    /**
     * Get the value of startingDateString
     *
     * @return the value of startingDateString
     */
    public String getStartingDateString() {
        return startingDateString;
    }

    /**
     * Set the value of startingDateString
     *
     * @param startingDateString new value of startingDateString
     */
    public void setStartingDateString(String startingDateString) {

        this.startingDateString = startingDateString;
    }

    /**
     * Get the value of startdate
     *
     * @return the value of startdate
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * Get the value of enddate
     *
     * @return the value of enddate
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * Set the value of enddate
     *
     * @param enddate new value of enddate
     */
    public void setEnddate(Date enddate) {
        this.enddate = enddate;

        SimpleDateFormat sdf = new SimpleDateFormat(" dd MMM,yy HH:mm a");
        this.setEndDateString(sdf.format(enddate));
        sdf = new SimpleDateFormat("dd MMM,yy  HH:mm a");
        this.setStartdatetime(sdf.format(enddate));

    }

    /**
     * Set the value of startdate
     *
     * @param startdate new value of startdate
     */
    public void setStartdate(Date startdate) {

        this.startdate = startdate;
        SimpleDateFormat sdf = new SimpleDateFormat(" dd MMM,yy  HH:mm a");
        this.setStartingDateString(sdf.format(startdate));
        sdf = new SimpleDateFormat("dd MMM,yy  HH:mm a");
        this.setStartdatetime(sdf.format(startdate));
    }

    /**
     * Get the value of end
     *
     * @return the value of end
     */
    public Timestamp getEnd() {
        return end;
    }

    /**
     * Set the value of end
     *
     * @param end new value of end
     */
    public void setEnd(Timestamp end) {
        this.end = end;
    }

    /**
     * Get the value of start
     *
     * @return the value of start
     */
    public Timestamp getStart() {
        return start;
    }

    /**
     * Set the value of start
     *
     * @param start new value of start
     */
    public void setStart(Timestamp start) {
        this.start = start;
    }

    /**
     * Get the value of collegename
     *
     * @return the value of collegename
     */
    public String getCollegename() {
        return collegename;
    }

    /**
     * Set the value of collegename
     *
     * @param collegename new value of collegename
     */
    public void setCollegename(String collegename) {
        this.collegename = collegename;
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
     * Get the value of contestId
     *
     * @return the value of contestId
     */
    public int getContestId() {
        return contestId;
    }

    /**
     * Set the value of contestId
     *
     * @param contestId new value of contestId
     */
    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    static public Contest getContest(int contestId, PageContext pc, User u) {
        Contest c = new Contest();

        Connection con = null;
        Statement stmt = null;
        String DB_URL = pc.getServletContext().getInitParameter("DB_URL");
        String DB_DRIVER = pc.getServletContext().getInitParameter("DB_DRIVER");
        String DB_USER = pc.getServletContext().getInitParameter("DB_USER");
        String DB_PASSWORD = pc.getServletContext().getInitParameter("DB_PASSWORD");
        try {
            Class.forName(DB_DRIVER);
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            stmt = con.createStatement();
            String sql = "select * from contest where contestid=" + contestId + "  and userid=" + u.getUserID();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                c.setCollegename(u.getCollege());
                c.setUserid(u.getUserID());
                c.setContestId(rs.getInt("contestid"));
                c.setName(rs.getString("name"));
                c.setEmailSend(rs.getInt("EmailSend"));
                c.setStart(rs.getTimestamp("start"));
                c.setStartdate(new Date(c.getStart().getTime()));
                c.setEnd(rs.getTimestamp("end"));
                c.setEnddate(new Date(c.getEnd().getTime()));
            }
        } catch (Exception e) {
        }
        return c;
    }

    static public Contest getContest(int contestId, PageContext pc) {
        Contest c = new Contest();

        Connection con = null;
        Statement stmt = null;
        String DB_URL = pc.getServletContext().getInitParameter("DB_URL");
        String DB_DRIVER = pc.getServletContext().getInitParameter("DB_DRIVER");
        String DB_USER = pc.getServletContext().getInitParameter("DB_USER");
        String DB_PASSWORD = pc.getServletContext().getInitParameter("DB_PASSWORD");
        try {
            Class.forName(DB_DRIVER);
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            stmt = con.createStatement();
            String sql = "select * from contest where contestid=" + contestId;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                c.setCollegename(rs.getString("collegename"));
                c.setUserid(rs.getInt("userid"));
                c.setContestId(rs.getInt("contestid"));
                c.setName(rs.getString("name"));
                c.setEmailSend(rs.getInt("EmailSend"));
                c.setStart(rs.getTimestamp("start"));
                c.setStartdate(new Date(c.getStart().getTime()));
                c.setEnd(rs.getTimestamp("end"));
                c.setEnddate(new Date(c.getEnd().getTime()));
            }
        } catch (Exception e) {
        }
        return c;
    }

    static public Contest getContest(int contestId, ServletConfig pc) {
        Contest c = new Contest();

        Connection con = null;
        Statement stmt = null;
        String DB_URL = pc.getServletContext().getInitParameter("DB_URL");
        String DB_DRIVER = pc.getServletContext().getInitParameter("DB_DRIVER");
        String DB_USER = pc.getServletContext().getInitParameter("DB_USER");
        String DB_PASSWORD = pc.getServletContext().getInitParameter("DB_PASSWORD");
        try {
            Class.forName(DB_DRIVER);
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            stmt = con.createStatement();
            String sql = "select * from contest where contestid=" + contestId;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                c.setCollegename(rs.getString("collegename"));
                c.setUserid(rs.getInt("userid"));
                c.setContestId(rs.getInt("contestid"));
                c.setName(rs.getString("name"));
                c.setEmailSend(rs.getInt("EmailSend"));
                c.setStart(rs.getTimestamp("start"));
                c.setStartdate(new Date(c.getStart().getTime()));
                c.setEnd(rs.getTimestamp("end"));
                c.setEnddate(new Date(c.getEnd().getTime()));
            }
        } catch (Exception e) {
        }
        return c;
    }

    static public Contest getContest(int contestId, FilterConfig fc) throws SQLException, ClassNotFoundException {
        Contest c = new Contest();

        Connection con = null;
        Statement stmt = null;
        String DB_URL = fc.getServletContext().getInitParameter("DB_URL");
        String DB_DRIVER = fc.getServletContext().getInitParameter("DB_DRIVER");
        String DB_USER = fc.getServletContext().getInitParameter("DB_USER");
        String DB_PASSWORD = fc.getServletContext().getInitParameter("DB_PASSWORD");
        Class.forName(DB_DRIVER);
        con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        stmt = con.createStatement();
        String sql = "select * from contest where contestid=" + contestId;
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            c.setCollegename(rs.getString("collegename"));
            c.setUserid(rs.getInt("userid"));
            c.setContestId(rs.getInt("contestid"));
            c.setName(rs.getString("name"));
            c.setEmailSend(rs.getInt("EmailSend"));

            c.setStart(rs.getTimestamp("start"));
            c.setStartdate(new Date(c.getStart().getTime()));
            c.setEnd(rs.getTimestamp("end"));
            c.setEnddate(new Date(c.getEnd().getTime()));
        }

        return c;
    }

}
