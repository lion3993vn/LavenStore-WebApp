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
import javax.naming.NamingException;
import lavenstore.utils.DBUtils;

/**
 *
 * @author Pham Hieu
 */
public class UserDAO {

    private static final String GET_ONE_USER = "SELECT * FROM USERS WHERE ID = ?";
    private static final String UPDATE_USER = "UPDATE [dbo].[Users] SET [Username] = ? ,[Password] = ? ,[Email] = ? ,[Role] = ? ,[Fullname] = ? ,[PhoneNumber] = ? ,[Address] = ? WHERE id = ?";
    private static final String GET_ALL_USER = "SELECT * FROM users";
    private static final String GET_USER_BY_USERNAME = "SELECT * FROM Users WHERE Username = ?";
    private static final String GET_USER_BY_EMAIL = "SELECT * FROM Users WHERE Email = ?";
    private static final String ADD_USER = "INSERT INTO [Users] ([Username],[Password],[Email],[Role],[Fullname],[PhoneNumber],[Address]) VALUES (?,?,?,?,?,?,?)";

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

    public List<UserDTO> getAllUser() throws SQLException {
        List<UserDTO> listUser = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(GET_ALL_USER);
            rs = ptm.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("ID");
                String userName = rs.getString("userName");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String role = rs.getString("role");
                String fullName = rs.getString("fullName");
                String phoneNumber = rs.getString("phoneNumber");
                String address = rs.getString("address");
                listUser.add(new UserDTO(ID, userName, password, email, role, fullName, phoneNumber, address));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listUser;
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

    public UserDTO getUserByUsername(String username) throws SQLException {
        UserDTO user = null;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(GET_USER_BY_USERNAME);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (rs.next()) {
                user = new UserDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
            }
            con.close();
        } catch (Exception ex) {
            System.out.println("Error in servlet. Details:" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return user;
    }

    public UserDTO getUserByEmail(String email) throws SQLException {
        UserDTO user = null;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(GET_USER_BY_EMAIL);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            if (rs.next()) {
                user = new UserDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
            }
        } catch (Exception ex) {
            System.out.println("Error in servlet. Details:" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return user;
    }

    public boolean insert(UserDTO newUser) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_USER);
                ptm.setString(1, newUser.getUserName());
                ptm.setString(2, newUser.getPassword());
                ptm.setString(3, newUser.getEmail());
                ptm.setString(4, newUser.getRole());
                ptm.setString(5, newUser.getFullName());
                ptm.setString(6, newUser.getPhoneNumber());
                ptm.setString(7, newUser.getAddress());
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
