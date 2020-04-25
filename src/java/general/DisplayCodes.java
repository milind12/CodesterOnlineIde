/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Milind Ghiya
 */
public class DisplayCodes extends SimpleTagSupport {

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
            User user = (User) getJspContext().getAttribute("user", PageContext.SESSION_SCOPE);
            PageContext pc = (PageContext) getJspContext();

            File dir = new File((String) pc.getServletContext().getInitParameter("initialpath") + user.getUsername() + File.separator + "code");
            dir.mkdirs();
            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }
            Date d = null;
            BasicFileAttributes attrs = null;
            Path p = null;
            CodeFile c = null;
            // if request is not of type search

            if (pc.getRequest().getParameter("search") == null) {
                ArrayList al;
                al = new ArrayList<CodeFile>();
                // path defined in servlet context
                String path = (String) pc.getServletContext().getInitParameter("initialpath");
                //path upto code folder
                path = path + user.getUsername() + File.separator + "code";
                File folder = new File(path);

                // get list of files in directory
                File[] listOfFiles = folder.listFiles();

                StringBuilder list = new StringBuilder();
                list.append("[");
                for (int i = 0; i < listOfFiles.length; i++) {
                    c = new CodeFile();
                    if (i != 0) {
                        list.append(",");
                    }

                    c.setName(listOfFiles[i].getName());
                    if (listOfFiles[i].getName().endsWith(".java")) {
                        c.setLang("JAVA");
                    } else if (listOfFiles[i].getName().endsWith(".c")) {
                        c.setLang("C");
                    } else if (listOfFiles[i].getName().endsWith(".cpp")) {
                        c.setLang("C++");
                    }

                    list.append("\"" + listOfFiles[i].getName() + "\"");
                    p = Paths.get(path + File.separator + listOfFiles[i].getName());
                    attrs = Files.readAttributes(p, BasicFileAttributes.class);
                    attrs.creationTime().toMillis();
                    d = new Date(attrs.creationTime().toMillis());
                    SimpleDateFormat sdf = new SimpleDateFormat(" MMM dd, yyyy ");
                    c.setMillis(attrs.creationTime().toMillis());
                    c.setSize(new Long(attrs.size()).toString());
                    c.setDate(sdf.format(d));
                    sdf = new SimpleDateFormat("HH:mm");
                    c.setTime(sdf.format(d));
                    al.add(c);

                }
                list.append("]");
                if (al.size() == 0) {
                    pc.getRequest().setAttribute("noitem", "No code saved");
                }
                pc.getRequest().setAttribute("list", list.toString());
                pc.getRequest().setAttribute("al", al);
            } else {

                ArrayList al;

//                al = new ArrayList<String>();
//
//                String path = (String) pc.getServletContext().getInitParameter("initialpath");
//                File folder = new File(path + user.getUsername() + File.separator + "code");
//                File[] listOfFiles = folder.listFiles();
//                StringBuilder list = new StringBuilder();
//                list.append("[");
//
//                for (int i = 0; i < listOfFiles.length; i++) {
//
//                    if (i != 0) {
//                        list.append(",");
//                    }
//
//                    if (listOfFiles[i].getName().toLowerCase().contains(pc.getRequest().getParameter("search").toLowerCase())) {
//                        al.add(listOfFiles[i].getName());
//                    }
//
//                    list.append("\"" + listOfFiles[i].getName() + "\"");
//
//                }
//
//                list.append("]");
//                if (al.size() == 0) {
//                    pc.getRequest().setAttribute("noitem", "No match found");
//                }
//                pc.getRequest().setAttribute("list", list.toString());
//                pc.getRequest().setAttribute("al", al);
//                pc.getRequest().setAttribute("search", pc.getRequest().getParameter("search"));
// TODO: insert code to write html after writing the body content.
                // e.g.:
                //
                // out.println("    </blockquote>");
                al = new ArrayList<CodeFile>();
                // path defined in servlet context
                String path = (String) pc.getServletContext().getInitParameter("initialpath");
                //path upto code folder
                path = path + user.getUsername() + File.separator + "code";
                File folder = new File(path);

                // get list of files in directory
                File[] listOfFiles = folder.listFiles();

                StringBuilder list = new StringBuilder();
                list.append("[");
                for (int i = 0; i < listOfFiles.length; i++) {

                    if (i != 0) {
                        list.append(",");
                    }

                    if (listOfFiles[i].getName().toLowerCase().contains(pc.getRequest().getParameter("search").toLowerCase())) {
                        c = new CodeFile();
                        c.setName(listOfFiles[i].getName());
                        p = Paths.get(path + File.separator + listOfFiles[i].getName());

                        if (listOfFiles[i].getName().endsWith(".java")) {
                            c.setLang("JAVA");
                        } else if (listOfFiles[i].getName().endsWith(".c")) {
                            c.setLang("C");
                        } else if (listOfFiles[i].getName().endsWith(".cpp")) {
                            c.setLang("C++");
                        }

                        attrs = Files.readAttributes(p, BasicFileAttributes.class);
                        attrs.creationTime().toMillis();
                        d = new Date(attrs.creationTime().toMillis());
                        SimpleDateFormat sdf = new SimpleDateFormat(" MMM dd, yyyy ");
                        c.setMillis(attrs.creationTime().toMillis());
                        c.setSize(new Long(attrs.size()).toString());
                        c.setDate(sdf.format(d));
                        sdf = new SimpleDateFormat("HH:mm");
                        c.setTime(sdf.format(d));
                        al.add(c);
                    }
                    list.append("\"" + listOfFiles[i].getName() + "\"");

                }
                list.append("]");
                if (al.size() == 0) {
                    pc.getRequest().setAttribute("noitem", "No code saved");
                }
                pc.getRequest().setAttribute("list", list.toString());
                pc.getRequest().setAttribute("al", al);
                pc.getRequest().setAttribute("search", pc.getRequest().getParameter("search"));
            }
        } catch (java.io.IOException ex) {
            throw new JspException("Error in DisplayCodes tag", ex);
        }
    }

}
