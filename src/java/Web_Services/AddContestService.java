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
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("addcontest")
public class AddContestService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public IsSuccess add(Contest c) {
        IsSuccess is = new IsSuccess();
        try {
            Connection con = null;
            Statement stmt = null;
            con = DBConnection.createConnection(con);
			String sql = "insert into contest (name,userid,collegename,start,end,EmailSend) values('" + c.getName() + "'," + c.getUserid() + ",'" + c.getCollegename() + "','" + c.getStartdate() + "','" + c.getEnddate() + "',0)";
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
        is.setMessage("contest added successfully");
        return is;
    }

}
