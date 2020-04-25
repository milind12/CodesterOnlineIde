/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Web_Services;

import com.sun.javafx.collections.NonIterableChange;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.eclipse.persistence.internal.helper.SimpleDatabaseType;
import java.util.Date;
import java.util.logging.Logger;

/**
 *
 * @author Milind Ghiya
 */

@Path("contest")
public class ContestService {

    @GET
    @Path("/{cn}")
    @Produces("application/json")
    public List<Contest> getContests(@PathParam("cn") String collegename) {
        List<Contest> l = new ArrayList<Contest>();

        try {
            Contest c = null;
            Connection con = null;
            Statement stmt = null;
            con = DBConnection.createConnection(con);

            String sql = "Select * from contest where collegename='" + collegename + "'";

            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                c = new Contest();
                c.setCollegename(rs.getString("collegename"));
                c.setUserid(rs.getInt("userid"));
                c.setContestId(rs.getInt("contestid"));
                c.setName(rs.getString("name"));

                c.setStartdate(new Date(rs.getTimestamp("start").getTime()));
                c.setEnddate(new Date(rs.getTimestamp("end").getTime()));
                l.add(c);
            }
            stmt.close();
            con.close();

        } catch (Exception e) {
        }
        return l;

    }

    @GET
    @Produces("application/json")
    public List<Contest> getAllContests() {
        List<Contest> l = new ArrayList<Contest>();
        
        try {

            Contest c = null;
            Connection con = null;
            Statement stmt = null;
            con = DBConnection.createConnection(con);

            String sql = "Select * from contest";

            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                c = new Contest();
                c.setCollegename(rs.getString("collegename"));
                c.setUserid(rs.getInt("userid"));
                c.setContestId(rs.getInt("contestid"));
                c.setName(rs.getString("name"));

                c.setStartdate(new Date(rs.getTimestamp("start").getTime()));
                c.setEnddate(new Date(rs.getTimestamp("end").getTime()));
                l.add(c);
            }
            stmt.close();
            con.close();

        } catch (Exception e) {
                  Logger logger = Logger.getLogger("IN CONTESTSERVICES");
                    logger.info("errorrrrrrrrr" + e);
    
        }
        return l;

    }
}
