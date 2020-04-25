/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web_Services;

import java.sql.Connection;
import java.sql.Statement;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Milind Ghiya
 */

@Path("register")
public class RegisterService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public IsSuccess register(User u) {
        IsSuccess is = new IsSuccess();
        try {
            Connection con = null;
            Statement stmt = null;
            con = DBConnection.createConnection(con);
            String sql = "INSERT INTO user (name,username,password,role,emailid,collegename,reg) VALUES('" + u.getName() + "','" + u.getUsername() + "','" + u.getPassword() + "','" + u.getRole() + "','" + u.getEmail() + "','" + u.getCollege() + "',0)";
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            con.close();
        } catch (Exception e) {
            is.setValue(false);
            is.setMessage(e.toString());
            return is;
        }
        is.setValue(true);
        is.setMessage("registration successful");
        return is;
    }

}
