/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

/**
 *
 * @author Milind Ghiya
 */
public class ContestResult {

    private int maxscore;

    /**
     * Get the value of maxscore
     *
     * @return the value of maxscore
     */
    public int getMaxscore() {
        return this.maxscore;
    }

    /**
     * Set the value of maxscore
     *
     * @param maxscore new value of maxscore
     */
    public void setMaxscore(int maxscore) {
        this.maxscore = maxscore;
    }

    private String name;

    private String username;

    private String emailid;

    private int score;

    private int correctAns;

    /**
     * Get the value of correctAns
     *
     * @return the value of correctAns
     */
    public int getCorrectAns() {
        return correctAns;
    }

    /**
     * Set the value of correctAns
     *
     * @param correctAns new value of correctAns
     */
    public void setCorrectAns(int correctAns) {
        this.correctAns = correctAns;
    }

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

    /**
     * Get the value of emailid
     *
     * @return the value of emailid
     */
    public String getEmailid() {
        return emailid;
    }

    /**
     * Set the value of emailid
     *
     * @param emailid new value of emailid
     */
    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
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

}
