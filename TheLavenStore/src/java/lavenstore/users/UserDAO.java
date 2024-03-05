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
import lavenstore.utils.DBUtils;

/**
 *
 * @author Pham Hieu
 */
public class UserDAO {

    private static final String GET_ONE_USER = "SELECT * FROM USERS WHERE ID = ?";

    public UserDTO getOneUser(int ID) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(GET_ONE_USER);
            ps.setInt(1, ID);
            rs = ps.executeQuery();
            if (rs.next()){
                String userName = rs.getString("userName");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String role = rs.getString("role");
                String fullName = rs.getString("fullName");
                String phoneNumber = rs.getString("phoneNumber");
                String address = rs.getString("address");
                user = new UserDTO(ID, userName, password, email, role, fullName, phoneNumber, address);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        return user;
    }   
}
