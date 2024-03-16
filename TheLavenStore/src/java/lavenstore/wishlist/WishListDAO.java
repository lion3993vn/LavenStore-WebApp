/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavenstore.wishlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import lavenstore.utils.DBUtils;

/**
 *
 * @author Pham Hieu
 */
public class WishListDAO {

    private static final String IS_WISHLIST = "SELECT * FROM Wishlist WHERE UserID = ? AND ProductID =?";
    private static final String REMOVE_WISHLIST = "DELETE FROM WishList WHERE UserID = ? AND ProductID = ?";
    private static final String ADD_WISHLIST = "INSERT INTO [dbo].[Wishlist]"
            + "           ([UserID]"
            + "           ,[ProductID])"
            + "     VALUES"
            + "           (?,?)";
    private static final String GET_WISHLIST_BY_USER_ID = "SELECT * FROM Wishlist WHERE UserID = ? ORDER BY [ID] OFFSET ? ROWS FETCH FIRST 12 ROWS ONLY";
    private static final String GET_TOTAL_PAGE_WISHLIST = "SELECT COUNT(*) FROM Wishlist WHERE UserID = ?";

    public boolean isWishList(int pid, int uid) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        WishListDTO wish = null;
        try {
            conn = DBUtils.getConnection();
            pst = conn.prepareStatement(IS_WISHLIST);
            pst.setInt(1, uid);
            pst.setInt(2, pid);
            rs = pst.executeQuery();
            if (rs.next()) {
                wish = new WishListDTO();
                wish.setID(rs.getInt("ID"));
                wish.setUserID(rs.getInt("UserID"));
                wish.setProductID(rs.getInt("ProductID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wish != null ? true : false;
    }

    public boolean removeWishList(int pid, int uid) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            pst = conn.prepareStatement(REMOVE_WISHLIST);
            pst.setInt(1, uid);
            pst.setInt(2, pid);
            check = pst.executeUpdate() > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean addWishList(int pid, int uid) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            pst = conn.prepareStatement(ADD_WISHLIST);
            pst.setInt(1, uid);
            pst.setInt(2, pid);
            check = pst.executeUpdate() > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
    
    public List<WishListDTO> getWishListByUserID(int uid, int index) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<WishListDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            pst = conn.prepareStatement(GET_WISHLIST_BY_USER_ID);
            pst.setInt(1, uid);
            pst.setInt(2, (index-1)*12);
            rs = pst.executeQuery();
            while (rs.next()) {
                WishListDTO wish = new WishListDTO();
                wish.setID(rs.getInt("ID"));
                wish.setUserID(rs.getInt("UserID"));
                wish.setProductID(rs.getInt("ProductID"));
                list.add(wish);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public int getPageWishListByUserID(int uid) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            pst = conn.prepareStatement(GET_TOTAL_PAGE_WISHLIST);
            pst.setInt(1, uid);
            rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
