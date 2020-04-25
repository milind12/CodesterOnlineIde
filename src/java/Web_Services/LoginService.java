/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web_Services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.logging.*;
/**
 *
 * @author Milind Ghiya
 */
@Path("login")
public class LoginService {
    
    @GET
    @Path("/{usnm}/{pwd}")
    @Produces("application/json")
    public User login(@PathParam("usnm") String usnm, @PathParam("pwd") String pwd) {
        User u = new User();
        try {
            Connection con = null;
            Statement stmt = null;
            con = DBConnection.createConnection(con);

            ResultSet rs = null;

            String sql = "SELECT *  FROM user where emailid='" + usnm + "' and password='" + pwd + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                u.setUserID(rs.getInt("userid"));
                u.setEmail(rs.getString("emailid"));
                u.setCollege(rs.getString("collegename"));
                u.setPassword(rs.getString("password"));
                u.setName(rs.getString("name"));
                u.setUsername(rs.getString("username"));
                u.setRole(rs.getString("role"));
            }
        } catch (Exception e) {
              //response.sendRedirect("error.jsp");
          Logger logger = Logger.getLogger("IN LOGINSERVICES");
          logger.info("errorrrrrrrrr" + e);
        }
        return u;
    }

}
