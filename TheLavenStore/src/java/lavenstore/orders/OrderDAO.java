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
    private static final String UPDATE_ORDER = "UPDATE [Order]"
            + "SET   [Location] = ?"
            + "      ,[PhoneNumber] = ?"
            + "      ,[Status] = ?"
            + "      ,[Note] = ?"
            + " WHERE ID = ?";
    private static final String REQUIRE_SEARCH_CODE = "OrderCode LIKE ?";
    private static final String REQUIRE_SEARCH_STATUS = "Status = ?";
    
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

    public OrderDTO getOrderByID(int id) throws SQLException {
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

    public boolean updateOrder(OrderDTO o) throws SQLException {
        Connection conn = null;
        OrderDTO order = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(UPDATE_ORDER);
            psm.setString(1, o.getLocation());
            psm.setString(2, o.getPhoneNumber());
            psm.setString(3, o.getStatus());
            psm.setString(4, o.getNote());
            psm.setInt(5, o.getID());
            check = psm.executeUpdate() > 0 ? true : false;
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
        return check;
    }
    
    public List<OrderDTO> searchOrder(String oCode, String oStatus) throws SQLException {
        Connection conn = null;
        List<OrderDTO> list = new ArrayList<>();
        PreparedStatement psm = null;
        ResultSet rs = null;
        if(oStatus.equals("NONE")){
            oStatus = "";
        }
        try {
            conn = DBUtils.getConnection();
            if(!oCode.equals("") && !oStatus.equals("")){
                psm = conn.prepareStatement(GET_ALL_ORDER + " WHERE " + REQUIRE_SEARCH_CODE + " AND " + REQUIRE_SEARCH_STATUS);
                System.out.println(GET_ALL_ORDER + " WHERE " + REQUIRE_SEARCH_CODE + " AND " + REQUIRE_SEARCH_STATUS);
                psm.setString(1, oCode+"%");
                psm.setString(2, oStatus);
            } else if(!oCode.equals("") && oStatus.equals("")){
                psm = conn.prepareStatement(GET_ALL_ORDER + " WHERE " + REQUIRE_SEARCH_CODE);
                psm.setString(1, oCode+"%");
            } else if(oCode.equals("") && !oStatus.equals("")){
                psm = conn.prepareStatement(GET_ALL_ORDER + " WHERE " + REQUIRE_SEARCH_STATUS);
                psm.setString(1, oStatus);
            }
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
}
