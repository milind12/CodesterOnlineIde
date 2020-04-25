/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web_Services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 *
 * @author Darshan-dd
 */
@Path("collegename")
public class CollegeNameService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<college> getCollegename() {
        List<college> collegenamelist = new ArrayList<college>();
        String collegenameString = "select * from college";
        try {
            college col = null;
            Connection con = null;
            Statement stmt = null;
            con = DBConnection.createConnection(con);
            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(collegenameString);
            while (rs.next()) {
                col = new college();
                col.setCollegeid(rs.getInt("collegeid"));
                col.setCollegename(rs.getString("collegename"));
                col.setCollegeaddress(rs.getString("address"));
                collegenamelist.add(col);
            }
            stmt.close();
            con.close();

        } catch (Exception e) {
        }
        return collegenamelist;
    }
}
