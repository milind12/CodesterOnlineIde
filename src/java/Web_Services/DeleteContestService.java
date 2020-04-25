/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web_Services;

import java.sql.Connection;
import java.sql.Statement;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Milind Ghiya
 */
@Path("deletecontest")
public class DeleteContestService {

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public IsSuccess deleteContest(@PathParam("id") int id) {
        IsSuccess is = new IsSuccess();

        try {
            Connection con = null;
            Statement stmt = null;
            con = DBConnection.createConnection(con);
            String sql = "delete from contest where contestid=" + id;
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
        is.setMessage("deletion successful");

        return is;
    }

}
