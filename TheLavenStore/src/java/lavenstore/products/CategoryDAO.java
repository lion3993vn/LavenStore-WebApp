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
    
    
}
