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
import java.util.Date;
import java.util.List;
import lavenstore.products.ProductDTO;
import lavenstore.utils.DBUtils;

/**
 *
 * @author Pham Hieu
 */
public class OrderDAO {

    private static final String GET_ALL_ORDER = "SELECT * FROM [Order]";
    private static final String GET_ONE_ORDER = "SELECT * FROM [Order] where ID = ?";

    public List<OrderDTO> getAllOrder() throws SQLException {
        Connection conn = null;
        List<OrderDTO> list = new ArrayList<>();
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_ALL_ORDER);
            rs = psm.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("ID");
                int userID = rs.getInt("UserID");
                Date date = rs.getDate("Date");
                Date paymentDate = rs.getDate("PaymentDate");
                String paymentMethod = rs.getString("PaymentMethod");
                String location = rs.getString("Location");
                String phoneNumber = rs.getString("PhoneNumber");
                int price = rs.getInt("Price");
                String status = rs.getString("Status");
                String orderCode = rs.getString("OrderCode");
                String note = rs.getString("Note");
                list.add(new OrderDTO(ID, userID, date, paymentDate, paymentMethod, location, phoneNumber, price, status, orderCode, note));
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

    public OrderDTO getOneOrder(int id) throws SQLException {
        Connection conn = null;
        OrderDTO order = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_ONE_ORDER);
            psm.setInt(1, id);
            rs = psm.executeQuery();
            if (rs.next()) {
                int ID = rs.getInt("ID");
                int userID = rs.getInt("UserID");
                Date date = rs.getDate("Date");
                Date paymentDate = rs.getDate("PaymentDate");
                String paymentMethod = rs.getString("PaymentMethod");
                String location = rs.getString("Location");
                String phoneNumber = rs.getString("PhoneNumber");
                int price = rs.getInt("Price");
                String status = rs.getString("Status");
                String orderCode = rs.getString("OrderCode");
                String note = rs.getString("Note");
                order = new OrderDTO(ID, userID, date, paymentDate, paymentMethod, location, phoneNumber, price, status, orderCode, note);
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
        return order;
    }
}
