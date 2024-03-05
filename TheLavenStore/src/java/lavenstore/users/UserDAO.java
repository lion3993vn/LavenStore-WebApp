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
import javax.naming.NamingException;
import lavenstore.utils.DBUtils;

/**
 *
 * @author Pham Hieu
 */
public class UserDAO {

    private static final String GET_ONE_USER = "SELECT * FROM USERS WHERE ID = ?";
    private static final String UPDATE_USER = "UPDATE [dbo].[Users] SET [Username] = ? ,[Password] = ? ,[Email] = ? ,[Role] = ? ,[Fullname] = ? ,[PhoneNumber] = ? ,[Address] = ? WHERE id = ?";

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
            if (rs.next()) {
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
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public boolean update(UserDTO newUser) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_USER);
                ptm.setString(1, newUser.getUserName());
                ptm.setString(2, newUser.getPassword());
                ptm.setString(3, newUser.getEmail());
                ptm.setString(4, "user");
                ptm.setString(5, newUser.getFullName());
                ptm.setString(6, newUser.getPhoneNumber());
                ptm.setString(7, newUser.getAddress());
                ptm.setInt(8, newUser.getID());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
}
