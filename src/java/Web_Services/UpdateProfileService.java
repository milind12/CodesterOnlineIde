 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web_Services;

import java.sql.Connection;
import java.sql.Statement;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Milind Ghiya
 */
@Path("update")
public class UpdateProfileService {
int k;
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public IsSuccess Update(User u) {
           
        IsSuccess is = new IsSuccess();

        try {
            Connection con = null;
            Statement stmt = null;
            con = DBConnection.createConnection(con);
            String sql = "Update user set name='" + u.getName() + "',username='" + u.getUsername() + "',password='" + u.getPassword() + "',role='" + u.getRole() + "',emailid='" + u.getEmail() + "',collegename='" + u.getCollege() + "' where userid=" + u.getUserID() + "";
            stmt = con.createStatement();
            k = stmt.executeUpdate(sql);
            
            System.err.println("kkkkkkkkkkkkkkkk"+k);
            //stmt.executeUpdate(sql);
            stmt.close();
            con.close();
        } catch (Exception e) {
            is.setValue(false);
            is.setMessage(e.toString());
            

        }
        is.setValue(true);
        is.setMessage("registration successful");
        return is;

    }

}
