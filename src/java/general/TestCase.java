/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterConfig;
import javax.servlet.ServletConfig;

/**
 *
 * @author Milind Ghiya
 */
public class TestCase {

    private int id;

    private String input;

    private String output;

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

    public void delete(Integer id, String address) {
        File f = new File(address + File.separator + "input" + id + ".txt");
        f.delete();

        f = new File(address + File.separator + "output" + id + ".txt");
        f.delete();

    }

    public String getContent(String address, String filename) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(address + File.separator + filename);
        BufferedReader reader = new BufferedReader(fr);

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);

            sb.append("<br>");

        }
        reader.close();
        return sb.toString();
    }

    public static ArrayList<TestCase> getTestCaseOfProblem(Problem p, Contest c, FilterConfig fc) {
        ArrayList<TestCase> al = new ArrayList<TestCase>();
        TestCase t = null;
        StringBuilder path = new StringBuilder();
        path.append(fc.getServletContext().getInitParameter("initialpath"));
        path.append(File.separator);
        path.append(c.getCollegename());
        path.append(File.separator);
        path.append(c.getName());
        path.append(File.separator);
        path.append(new Integer(p.getProblemid()).toString());
        File folder = new File(path.toString());
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            int n = listOfFiles.length;
            StringBuilder sb = new StringBuilder(path.toString());
            sb.append(File.separator);
            /* for (int i = 0; i < n / 2; i++) {
             t = new TestCase();
             sb.append(listOfFiles[i].getName());
             t.setInput(sb.toString());
             sb = sb.replace(sb.toString().length() - listOfFiles[i].getName().length(), sb.toString().length(), "");
             sb.append(listOfFiles[i + n / 2].getName());
             t.setOutput(sb.toString());
             sb = sb.replace(sb.toString().length() - listOfFiles[i + n / 2].getName().length(), sb.toString().length(), "");
             al.add(t);
             }*/
            String temp;
            int a[] = new int[100];
            for (int i = 0; i < n; i++) {
                if (listOfFiles[i].getName().contains("input")) {
                    temp = listOfFiles[i].getName().replace("input", "");
                    temp = temp.replace(".txt", "");
                    a[Integer.parseInt(temp)] = 1;
                } else {
                    temp = listOfFiles[i].getName().replace("output", "");
                    temp = temp.replace(".txt", "");
                    a[Integer.parseInt(temp)] = 1;
                }

            }
            for (int i = 0; i < 100; i++) {

                if (a[i] == 1) {
                    t = new TestCase();
                    t.setInput(sb.toString() + "input" + i + ".txt");

                    t.setOutput(sb.toString() + "output" + i + ".txt");
                    t.setId(i);
                    al.add(t);
                }

            }

        }
        return al;

    }
}
