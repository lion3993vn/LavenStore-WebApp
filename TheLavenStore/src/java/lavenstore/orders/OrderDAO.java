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
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    private static final String GET_ONE_ORDER = "SELECT * FROM [Order] WHERE ID = ?";
    private static final String UPDATE_ORDER = "UPDATE [Order]"
            + "SET   [Location] = ?"
            + "      ,[PhoneNumber] = ?"
            + "      ,[Status] = ?"
            + "      ,[Note] = ?"
            + " WHERE ID = ?";
    private static final String REQUIRE_SEARCH_CODE = "OrderCode LIKE ?";
    private static final String REQUIRE_SEARCH_STATUS = "Status = ?";
    private static final String INSERT_ORDER = "INSERT INTO [dbo].[Order]"
            + "           ([UserID],[Date],[PaymentDate],[PaymentMethod],"
            + "		   [Location],[PhoneNumber],[Price],[Status],[OrderCode],[Note])"
            + "            VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String GET_ORDER_BY_ORDER_CODE = "SELECT * FROM [Order] WHERE OrderCode = ?";

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
                Timestamp date = rs.getTimestamp("Date");
                Timestamp paymentDate = rs.getTimestamp("PaymentDate");
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
                Timestamp date = rs.getTimestamp("Date");
                Timestamp paymentDate = rs.getTimestamp("PaymentDate");
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
        if (oStatus.equals("NONE")) {
            oStatus = "";
        }
        try {
            conn = DBUtils.getConnection();
            if (!oCode.equals("") && !oStatus.equals("")) {
                psm = conn.prepareStatement(GET_ALL_ORDER + " WHERE " + REQUIRE_SEARCH_CODE + " AND " + REQUIRE_SEARCH_STATUS);
                System.out.println(GET_ALL_ORDER + " WHERE " + REQUIRE_SEARCH_CODE + " AND " + REQUIRE_SEARCH_STATUS);
                psm.setString(1, oCode + "%");
                psm.setString(2, oStatus);
            } else if (!oCode.equals("") && oStatus.equals("")) {
                psm = conn.prepareStatement(GET_ALL_ORDER + " WHERE " + REQUIRE_SEARCH_CODE);
                psm.setString(1, oCode + "%");
            } else if (oCode.equals("") && !oStatus.equals("")) {
                psm = conn.prepareStatement(GET_ALL_ORDER + " WHERE " + REQUIRE_SEARCH_STATUS);
                psm.setString(1, oStatus);
            }
            rs = psm.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("ID");
                int userID = rs.getInt("UserID");
                Timestamp date = rs.getTimestamp("Date");
                Timestamp paymentDate = rs.getTimestamp("PaymentDate");
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

    public int insertOrder(OrderDTO o) throws SQLException {
        Connection conn = null;
        OrderDTO order = null;
        PreparedStatement ptm = null;
        ResultSet generatedKeys = null;
        boolean check = false;
        int lastInsertID = 0;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);

            ptm.setInt(1, o.getUserID());
            ptm.setTimestamp(2, o.getDate());
            ptm.setTimestamp(3, o.getPaymentDate());
            ptm.setString(4, o.getPaymentMethod());
            ptm.setString(5, o.getLocation());
            ptm.setString(6, o.getPhoneNumber());
            ptm.setInt(7, o.getAmount());
            ptm.setString(8, o.getStatus());
            ptm.setString(9, o.getOrderCode());
            ptm.setString(10, o.getNote());

            int rowsAffected = ptm.executeUpdate();
            if (rowsAffected > 0) {
                generatedKeys = ptm.getGeneratedKeys();
                if (generatedKeys.next()) {
                    lastInsertID = generatedKeys.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (generatedKeys != null) {
                generatedKeys.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return lastInsertID;
    }

    public OrderDTO getOrderByOrderCode(String orderCode) throws SQLException {
        Connection conn = null;
        OrderDTO order = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_ORDER_BY_ORDER_CODE);
            psm.setString(1, orderCode);
            rs = psm.executeQuery();
            if (rs.next()) {
                int ID = rs.getInt("ID");
                int userID = rs.getInt("UserID");
                Timestamp date = rs.getTimestamp("Date");
                Timestamp paymentDate = rs.getTimestamp("PaymentDate");
                String paymentMethod = rs.getString("PaymentMethod");
                String location = rs.getString("Location");
                String phoneNumber = rs.getString("PhoneNumber");
                int price = rs.getInt("Price");
                String status = rs.getString("Status");
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
