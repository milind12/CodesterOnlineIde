/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web_Services;

/**
 *
 * @author Milind Ghiya
 */
import java.sql.Connection;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("contestupdate")
public class ContestUpdateService {

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response Update(Contest c) {

        IsSuccess is = new IsSuccess();

        try {
            Connection con = null;
            Statement stmt = null;
            con = DBConnection.createConnection(con);
            String sql = "update contest set name='" + c.getName() + "',collegename='" + c.getCollegename() + "',start='" + c.getStartdate() + "',end='" + c.getEnddate() + "' where contestid=" + c.getContestId();
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            con.close();
            return Response.accepted(c).build();
        } catch (Exception e) {
            Logger.getLogger("hi").log(Level.SEVERE, null, e);
            return Response.serverError().build();
        }

    }
}
