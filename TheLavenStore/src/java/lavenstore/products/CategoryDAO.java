/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavenstore.products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lavenstore.utils.DBUtils;

/**
 *
 * @author camsa
 */
public class CategoryDAO {

    private static final String GET_LIST_CATEGORY = "SELECT * FROM Category";
    private static final String GET_CATE_NAME_BY_CATEID = "SELECT CateName FROM Category WHERE CateID = ?";
    private static final String GET_CATE_BY_CATEID = "SELECT * FROM Category WHERE CateID = ?";

    public List<CategoryDTO> getListCategory() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CategoryDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            pst = conn.prepareStatement(GET_LIST_CATEGORY);
            rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new CategoryDTO(rs.getInt("CateID"), rs.getString("CateName")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getCateNameByCateID(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String value = null;
        
        try {
            conn = DBUtils.getConnection();
            pst = conn.prepareStatement(GET_CATE_NAME_BY_CATEID);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                value = rs.getString("cateName");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return value;
    }
    
    public CategoryDTO getCateByCateID(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        CategoryDTO cate = null;
        try {
            conn = DBUtils.getConnection();
            pst = conn.prepareStatement(GET_CATE_BY_CATEID);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                cate = new CategoryDTO();
                cate.setCateID(rs.getInt("CateID"));
                cate.setCateName(rs.getString("CateName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return cate;
    }
}
