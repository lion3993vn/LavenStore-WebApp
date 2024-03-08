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
        PreparedStatement psm = null;
        boolean check = false;
        int orderID = 0;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(INSERT_ORDER);

            psm.setInt(1, o.getUserID());
            psm.setTimestamp(2, o.getDate());
            psm.setTimestamp(3, o.getPaymentDate());
            psm.setString(4, o.getPaymentMethod());
            psm.setString(5, o.getLocation());
            psm.setString(6, o.getPhoneNumber());
            psm.setInt(7, o.getAmount());
            psm.setString(8, o.getStatus());
            psm.setString(9, o.getOrderCode());
            psm.setString(10, o.getNote());

            int row = psm.executeUpdate();

            if (row > 0) {
                // Lấy giá trị tự động tăng được tạo ra
                try (ResultSet generatedKeys = psm.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        orderID = generatedKeys.getInt(1);
                    }
                }
            }
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
        return orderID;
    }

    public OrderDTO getOrderByOrderCode(String orderCode) throws SQLException {
        Connection conn = null;
        OrderDTO order = null;
        PreparedStatement psm = null;
        boolean check = false;
        int orderID = 0;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_ORDER_BY_ORDER_CODE);

            int row = psm.executeUpdate();

            if (row > 0) {
                // Lấy giá trị tự động tăng được tạo ra
                try (ResultSet generatedKeys = psm.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        orderID = generatedKeys.getInt(1);
                    }
                }
            }
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
        return orderID;
    }
}
