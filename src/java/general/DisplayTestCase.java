/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.io.File;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Milind Ghiya
 */
public class DisplayTestCase extends SimpleTagSupport {

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();

        try {
            // TODO: insert code to write html before writing the body content.
            // e.g.:
            //
            // out.println("<strong>" + attribute_1 + "</strong>");
            // out.println("    <blockquote>");

            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }

            PageContext pc = (PageContext) getJspContext();
            HttpServletRequest request = (HttpServletRequest) pc.getRequest();
            HttpSession hs = request.getSession(false);
            String path = pc.getServletContext().getInitParameter("initialpath") + ((Contest) hs.getAttribute("contest")).getCollegename() + File.separator + ((Contest) hs.getAttribute("contest")).getName() + File.separator + hs.getAttribute("problemid").toString();
            File folder = new File(path);

            if (!folder.exists()) {
                folder.mkdirs();
            }
            hs.setAttribute("testcasepath", path);
            File[] listOfFiles = folder.listFiles();
            int a[] = new int[100];
            ArrayList<TestCase> al = new ArrayList<TestCase>();
            TestCase t = null;
            int n = listOfFiles.length;
            String temp;
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

                /*
                 t.setInput(listOfFiles[i].getName());

                 t.setOutput(listOfFiles[i + n].getName());
                 temp = listOfFiles[i].getName().replace("input", "");
                 temp = temp.replace(".txt", "");
                 t.setId(Integer.parseInt(temp));
                 */
            }
            for (int i = 0; i < 100; i++) {

                if (a[i] == 1) {
                    t = new TestCase();
                    t.setInput("input" + i + ".txt");

                    t.setOutput("output" + i + ".txt");
                    t.setId(i);
                    al.add(t);
                }

            }
            hs.setAttribute("testcases", al);
            hs.setAttribute("testcasecount", listOfFiles.length / 2);
            // TODO: insert code to write html after writing the body content.
            // e.g.:
            //
            // out.println("    </blockquote>");
        } catch (java.io.IOException ex) {
            throw new JspException("Error in DisplayTestCase tag", ex);

        }
    }

}
