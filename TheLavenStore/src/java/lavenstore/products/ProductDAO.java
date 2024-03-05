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

public class ProductDAO {

    private static final String GET_LIST_PRODUCT = "select ID, [Name], CateID, Quantity, Price, Rating, [Description], [Image]  from Product";
    private static final String GET_RELEASE_PRODUCT = "select top 3 ID, [Name], CateID, Quantity, Price, Rating, [Description], [Image]  from Product order by id desc";
    private static final String GET_DELETE_PRODUCT = "delete from Product where ID = ?";
    private static final String GET_LIST_PRODUCT_BY_CATEID = "SELECT * FROM Product WHERE CateID = ?";
    private static final String GET_PRODUCT_BY_ID = "SELECT * FROM Product WHERE ID = ?";
    private static final String GET_LIST_PRODUCT_BY_SEARCH = "SELECT * FROM Product WHERE CateID = ? and [name] like ?";
    private static String GET_LIST_PRODUCT_SORT_BY_PRICE = "select * from Product WHERE CateID = ? order by Price ?";

    public List<ProductDTO> getListProduct() throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            pst = conn.prepareStatement(GET_LIST_PRODUCT);
            rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new ProductDTO(rs.getInt("ID"), rs.getString("Name"), rs.getInt("CateID"), rs.getInt("Quantity"), rs.getInt("Price"), rs.getFloat("Rating"), rs.getString("Description"), rs.getString("Image")));
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

        return list;
    }

    public void deleteProduct(String id) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            pst = conn.prepareStatement(GET_DELETE_PRODUCT);
            pst.setString(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ProductDTO> getNewReleaseProduct() throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            pst = conn.prepareStatement(GET_RELEASE_PRODUCT);
            rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new ProductDTO(rs.getInt("ID"), rs.getString("Name"), rs.getInt("CateID"), rs.getInt("Quantity"), rs.getInt("Price"), rs.getFloat("Rating"), rs.getString("Description"), rs.getString("Image")));
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

        return list;
    }

    public List<ProductDTO> getListByCateId(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            pst = conn.prepareStatement(GET_LIST_PRODUCT_BY_CATEID);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new ProductDTO(rs.getInt("ID"), rs.getString("Name"), rs.getInt("CateID"), rs.getInt("Quantity"), rs.getInt("Price"), rs.getFloat("Rating"), rs.getString("Description"), rs.getString("Image")));
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
        return list;
    }

    public ProductDTO getProductByID(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            pst = conn.prepareStatement(GET_PRODUCT_BY_ID);
            pst.setString(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                return (new ProductDTO(rs.getInt("ID"), rs.getString("Name"), rs.getInt("CateID"), rs.getInt("Quantity"), rs.getInt("Price"), rs.getFloat("Rating"), rs.getString("Description"), rs.getString("Image")));
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
        return null;
    }

    public List<ProductDTO> getListBySearch(String search) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            pst = conn.prepareStatement(GET_LIST_PRODUCT_BY_SEARCH);

            pst.setString(1, "%" + search + "%");
            rs = pst.executeQuery();
            while (rs.next()) {

                list.add(new ProductDTO(rs.getInt("ID"), rs.getString("Name"), rs.getInt("CateID"), rs.getInt("Quantity"), rs.getInt("Price"), rs.getFloat("Rating"), rs.getString("Description"), rs.getString("Image")));
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
        return list;
    }

    public List<ProductDTO> listSortByPrice(int id, String sortCol) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            pst = conn.prepareStatement(GET_LIST_PRODUCT_SORT_BY_PRICE);
            pst.setInt(1, id);
            pst.setString(2, sortCol);
            rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new ProductDTO(rs.getInt("ID"), rs.getString("Name"), rs.getInt("CateID"), rs.getInt("Quantity"), rs.getInt("Price"), rs.getFloat("Rating"), rs.getString("Description"), rs.getString("Image")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
