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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lavenstore.utils.DBUtils;

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
    private static final String GET_USER_BY_USERID = "SELECT * FROM Users WHERE id = ?";
    private static final String DELETE_USER_BY_USERID = "DELETE FROM Users WHERE id = ?";
    private static final String GET_LIST_USER_BY_KEYWORD = "SELECT * FROM users where username like ?";
    private static final String GET_PAGE_NUMBER = "SELECT count(*) FROM Users";
    private static final String GET_PAGING_USER = "SELECT * FROM users ORDER BY id OFFSET ? ROWS FETCH FIRST 8 ROWS ONLY";
    private static final String GET_LIST_USER_BY_KEYWORD_PAGING = "SELECT * FROM users where username like ? ORDER BY id OFFSET ? ROWS FETCH FIRST 8 ROWS ONLY";
    private static final String GET_PAGE_NUMBER_WITH_SEARCH = "SELECT count(*) FROM Users where username like ?";
    private static final String LOGIN = "SELECT * FROM Users WHERE Username = ? AND [Password] = ?";
    private static final String GET_USER_BY_USERNAME = "SELECT * FROM Users WHERE Username = ?";
    private static final String GET_USER_BY_EMAIL = "SELECT * FROM Users WHERE Email = ?";
    private static final String ADD_USER = "INSERT INTO [Users] ([Username],[Password],[Email],[Role],[Fullname],[PhoneNumber],[Address]) VALUES (?,?,?,?,?,?,?)";
    private static final String GET_ALL_USER = "SELECT * FROM Users";
    private static final String UPDATE_PASSWORD = "UPDATE Users SET [Password] = ? WHERE ID = ?";
    private static final String GET_ALL_USERID_USERNAME = "SELECT ID, UserName FROM Users";
    private static final String GET_ONE_USERID_USERNAME = "SELECT ID, UserName FROM Users WHERE ID = ?";
    private static final String GET_USER_BY_ID = "SELECT * FROM Users WHERE ID = ?";

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
                ptm.setString(4, newUser.getRole());
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

    public UserDTO getUserByUserID(int userID) throws SQLException {
        UserDTO user = null;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(GET_USER_BY_USERID);
            stmt.setInt(1, userID);
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

    public boolean deleteUserByID(int userID) throws SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(DELETE_USER_BY_USERID);
            stmt.setInt(1, userID);
            check = stmt.executeUpdate() > 0 ? true : false;
        } catch (Exception ex) {
            System.out.println("Error in servlet. Details:" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }

    public List<UserDTO> getUserByKeyword(String keyword) throws SQLException {
        List<UserDTO> listUser = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(GET_LIST_USER_BY_KEYWORD);
            ptm.setString(1, "%" + keyword + "%");
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

    public int getPageNumber() throws SQLException {
        UserDTO user = null;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int pageNumber = 0;
        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(GET_PAGE_NUMBER);
            rs = stmt.executeQuery();
            if (rs.next()) {
                pageNumber = rs.getInt(1);
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
        return pageNumber;
    }

    public List<UserDTO> getPagingUser(int index) throws SQLException {
        List<UserDTO> listUser = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(GET_PAGING_USER);
            ptm.setInt(1, (index - 1) * 8);
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

    public List<UserDTO> getUserByKeywordPaging(String keyword, int index) throws SQLException {
        List<UserDTO> listUser = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(GET_LIST_USER_BY_KEYWORD_PAGING);
            ptm.setString(1, "%" + keyword + "%");
            ptm.setInt(2, (index - 1) * 8);
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

    public int getPageNumberWithSearch(String keyword) throws SQLException {
        UserDTO user = null;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int pageNumber = 0;
        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(GET_PAGE_NUMBER_WITH_SEARCH);
            stmt.setString(1, "%" + keyword + "%");
            rs = stmt.executeQuery();
            if (rs.next()) {
                pageNumber = rs.getInt(1);
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
        return pageNumber;
    }
    
    public UserDTO login(String username, String password) throws SQLException {
        UserDTO user = null;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(LOGIN);
            stmt.setString(1, username);
            stmt.setString(2, password);
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

    ;
    
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

    public boolean insert(UserDTO newUser) throws SQLException, ClassNotFoundException, NamingException {
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
                ptm.setString(4, "user");
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
                listUser.add(new UserDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
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

    public boolean updatePassword(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_PASSWORD);
                ptm.setString(1, user.getPassword());
                ptm.setInt(2, user.getID());
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
    
    public UserDTO getOneUserTwoInfo(int id) throws SQLException{
        Connection conn = null;
        UserDTO user = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try{
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_ONE_USERID_USERNAME);
            psm.setInt(1, id);
            rs = psm.executeQuery();
            if(rs.next()){
                int userID = rs.getInt("ID");
                String userName = rs.getString("Username");
                user = new UserDTO(userID, userName, null, null, null, null, null, null);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (rs != null) rs.close();
            if (psm != null) psm.close();
            if (conn != null) conn.close();
        }
        return user;
    }
    
    public UserDTO getUserByID(int id) throws SQLException{
        Connection conn = null;
        UserDTO user = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try{
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_USER_BY_ID);
            psm.setInt(1, id);
            rs = psm.executeQuery();
            if(rs.next()){
                int userID = rs.getInt("ID");
                String userName = rs.getString("Username");
                String password = rs.getString("Password");
                String email = rs.getString("Email");
                String role = rs.getString("Role");
                String fullname = rs.getString("FullName");
                String phoneNumber = rs.getString("phoneNumber");
                String address = rs.getString("Address");
                user = new UserDTO(id, userName, password, email, role, fullname, phoneNumber, address);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (rs != null) rs.close();
            if (psm != null) psm.close();
            if (conn != null) conn.close();
        }
        return user;
    }
}
