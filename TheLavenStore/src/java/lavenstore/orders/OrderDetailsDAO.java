/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavenstore.orders;

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
public class OrderDetailsDAO {

    private static final String GET_ORDER_DETAILS_BY_ID = "SELECT * FROM OrderDetail WHERE OrderID = ?";
    private static final String INSERT_ORDER_DETAILS = "INSERT INTO [dbo].[OrderDetail] ([OrderID],[ProductID],[Quantity],[Price]) VALUES (?,?,?,?)";

    public List<OrderDetailsDTO> getOrderDetailsByID(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;
        List<OrderDetailsDTO> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_ORDER_DETAILS_BY_ID);
            psm.setInt(1, id);
            rs = psm.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("ID");
                int orderCode = rs.getInt("OrderID");
                int productID = rs.getInt("ProductID");
                int quantity = rs.getInt("Quantity");
                int price = rs.getInt("Price");
                list.add(new OrderDetailsDTO(ID, orderCode, productID, quantity, price));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (psm != null) {
                psm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
    
    public boolean insertOrderDetails(ItemCart ic, int orderID) throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(INSERT_ORDER_DETAILS);
            psm.setInt(1, orderID);
            psm.setInt(2, ic.getProduct().getID());
            psm.setInt(3, ic.getQuantity());
            psm.setInt(4, ic.getPrice());
            check = psm.executeUpdate() > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (psm != null) {
                psm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
}
