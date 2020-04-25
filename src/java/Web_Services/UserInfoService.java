/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web_Services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.ws.rs.Path;
import java.util.*;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 *
 * @author Darshan-dd
 */

@Path("userinfo")
public class UserInfoService {

    @GET
    @Produces("application/json")
    public List<UserInfo> userdata() {
        List<UserInfo> userinfolist = new ArrayList<UserInfo>();
        try {
            UserInfo userInfo=null;
            Connection con = null;
            Statement stmt = null;
            con = DBConnection.createConnection(con);

            String sql = "select DISTINCT user.userid,user.name,user.username,user.password,user.role,user.emailid,user.collegename from user,student,submission,contesthost";

            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                userInfo = new UserInfo();
                
                userInfo.setUserid(rs.getInt("userid"));
                
                userInfo.setCollegename(rs.getString("collegename"));
                
                userInfo.setName(rs.getString("name"));
                
                userInfo.setUsername(rs.getString("username"));
                
                userInfo.setRole(rs.getString("role"));
                
                userInfo.setEmail(rs.getString("emailid"));
                
               // userInfo.setYearofjoin(rs.getInt("yearofjoining"));
                
                //userInfo.setScore(rs.getInt("score"));
                
                //userInfo.setPhoneno(rs.getLong("phone"));
                
                userinfolist.add(userInfo);
            }
            
        stmt.close();
        con.close();

        } catch (Exception e) {
       
            System.err.println("pppppppppp"+e);
        }

        return userinfolist;
    }

}
