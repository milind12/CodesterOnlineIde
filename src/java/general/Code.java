/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.FilterConfig;
import javax.servlet.ServletConfig;

/**
 *
 * @author Milind Ghiya
 */
abstract public class Code {

    public List<String> l = new ArrayList<String>();
    protected String filename;

    private String submissionPath;

    /**
     * Get the value of submissionPath
     *
     * @return the value of submissionPath
     */
    public String getSubmissionPath() {
        return submissionPath;
    }

    /**
     * Set the value of submissionPath
     *
     * @param submissionPath new value of submissionPath
     */
    public void setSubmissionPath(String submissionPath) {
        this.submissionPath = submissionPath;
    }

    /**
     * Get the value of pname
     *
     * @return the value of pname
     */
    public String print(ServletConfig servletconfig) {
        return (String) (servletconfig.getServletContext().getInitParameter("initialpath"));
    }

    public String getFilename() {
        return filename;
    }

    /**
     * Set the value of pname
     *
     * @param pname new value of pname
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }
    private static final String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    protected String cmd;
    protected String savePath;
    protected String loc;
    protected String src;
    private String note;

    /**
     * Get the value of note
     *
     * @return the value of note
     */
    public String getNote() {
        return note;
    }

    public void setDefault(ServletConfig servletConfig) {

    }

    public void setDefault(ServletConfig servletConfig, String username) {

    }

    public void setDefault(FilterConfig fc, String username) {

    }

    /**
     * Set the value of note
     *
     * @param note new value of note
     */
    public void setNote(String note) {
        this.note = note;
    }

    private int id;

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(int id) {
        this.id = id;
    }

    Date dos;
    private String language;

    /**
     * Get the value of language
     *
     * @return the value of language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Set the value of language
     *
     * @param language new value of language
     */
    public void setLanguage(String language) {
        this.language = language;
    }
    protected String input;

    /**
     * Get the value of input
     *
     * @return the value of input
     */
    public String getInput() {
        return input;
    }

    /**
     * Set the value of input
     *
     * @param input new value of input
     */
    public void setInput(String input) {
        this.input = input;
    }

    protected String output;

    /**
     * Get the value of output
     *
     * @return the value of output
     */
    public String getOutput() {
        return output;
    }

    /**
     * Set the value of output
     *
     * @param output new value of output
     */
    public void setOutput(String output) {
        this.output = output;
    }

    abstract public String getSavePath();

    abstract public String getCmd();

    abstract public String getSrc();

    /**
     * Get the value of dos
     *
     * @return the value of dos
     */
    public Date getDos() {
        return dos;
    }

    /**
     * Set the value of dos
     *
     * @param dos new value of dos
     */
    public void setDos(Date dos) {
        this.dos = dos;
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
