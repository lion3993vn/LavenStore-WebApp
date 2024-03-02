/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavenstore.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lavenstore.utils.DBUtils;

/**
 *
 * @author Pham Hieu
 */
public class UserDAO {
    private static final String GET_ALL_USERID_USERNAME = "SELECT ID, UserName FROM User";
    
    public List<UserDTO> getAllUserTwoInfo() throws SQLException{
        Connection conn = null;
        List<UserDTO> list = new ArrayList<>();
        PreparedStatement psm = null;
        ResultSet rs = null;
        try{
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_ALL_USERID_USERNAME);
            rs = psm.executeQuery();
            while(rs.next()){
                int userID = rs.getInt("ID");
                String userName = rs.getString("Username");
                list.add(new UserDTO(userID, userName, null, null, null, null, null, null));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (rs != null) rs.close();
            if (psm != null) psm.close();
            if (conn != null) conn.close();
        }
        return list;
    }
}
