/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Milind Ghiya
 */
public class Submission {

    private int submissionid;
    private int problemid;
    private int userid;
    private int score;
    private String result;
    private String ip;
    private Timestamp stime;
    private String formattedStime;

    public int getSubmissionid() {
        return submissionid;
    }

    public void setSubmissionid(int submissionid) {
        this.submissionid = submissionid;
    }

    public int getProblemid() {
        return problemid;
    }

    public void setProblemid(int problemid) {
        this.problemid = problemid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Timestamp getStime() {
        return stime;
    }

    public void setStime(Timestamp stime) {
        this.stime = stime;
        this.setFormattedStime(new SimpleDateFormat("dd MMM YY, hh:mm:ss").format(stime));
    }

    public String getFormattedStime() {
        return formattedStime;
    }

    public void setFormattedStime(String formattedStime) {
        this.formattedStime = formattedStime;
    }
}
