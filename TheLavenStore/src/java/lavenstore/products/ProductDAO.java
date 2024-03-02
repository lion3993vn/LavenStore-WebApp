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
 * @author Pham Hieu
 */
public class ProductDAO {
    
    private static final String GET_ALL_PRODUCTS = "SELECT * FROM Product";
    
    private static final String GET_PRODUCT_BY_ID = "SELECT * FROM Product WHERE ID = ?";
    
    public List<ProductDTO> getAllProduct() throws SQLException{
        Connection conn = null;
        List<ProductDTO> list = new ArrayList<>();
        PreparedStatement psm = null;
        ResultSet rs = null;
        try{
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_ALL_PRODUCTS);
            rs = psm.executeQuery();
            while(rs.next()){
                int productID = rs.getInt("ID");
                String productName = rs.getString("Name");
                int cateID = rs.getInt("CateID");
                int quantity = rs.getInt("Quantity");
                int price = rs.getInt("Price");
                float rating = rs.getFloat("Rating");
                String description = rs.getString("Description");
                String image = rs.getString("Image");
                list.add(new ProductDTO(productID, productName, cateID, quantity, price, rating, description, image));
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
    
    public ProductDTO getProductByID(int id) throws SQLException{
        Connection conn = null;
        ProductDTO product = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try{
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_PRODUCT_BY_ID);
            psm.setInt(1, id);
            rs = psm.executeQuery();
            if(rs.next()){
                int productID = rs.getInt("ID");
                String productName = rs.getString("Name");
                int cateID = rs.getInt("CateID");
                int quantity = rs.getInt("Quantity");
                int price = rs.getInt("Price");
                float rating = rs.getFloat("Rating");
                String description = rs.getString("Description");
                String image = rs.getString("Image");
                product = new ProductDTO(productID, productName, cateID, quantity, price, rating, description, image);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (rs != null) rs.close();
            if (psm != null) psm.close();
            if (conn != null) conn.close();
        }
        return product;
    }
}
