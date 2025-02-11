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
import java.util.ArrayList;
import java.util.List;
import lavenstore.utils.DBUtils;

/**
 *
 * @author Pham Hieu
 */
public class OrderDAO {

    private static final String GET_ALL_ORDER = "SELECT * FROM [Order] ORDER BY ID DESC OFFSET ? ROWS FETCH FIRST 8 ROWS ONLY";
    private static final String GET_ALL_ORDER1 = "SELECT * FROM [Order]";
    private static final String GET_PAGE_ALL_ORDER = "SELECT COUNT(*) FROM [Order]";
    private static final String GET_ALL_ORDER_BY_USERID = "SELECT * FROM [Order] WHERE [UserID] = ? ORDER BY ID DESC OFFSET ? ROWS FETCH FIRST 3 ROWS ONLY";
    private static final String GET_PAGE_ORDER_BY_USERID = "SELECT COUNT(*) FROM [Order] WHERE [UserID] = ?";
    private static final String GET_ONE_ORDER = "SELECT * FROM [Order] WHERE ID = ?";
    private static final String UPDATE_ORDER = "UPDATE [Order]"
            + "SET   [Location] = ?"
            + "      ,[PhoneNumber] = ?"
            + "      ,[Status] = ?"
            + "      ,[Note] = ?"
            + "      ,[PaymentDate] = ?"
            + " WHERE ID = ?";
    private static final String REQUIRE_SEARCH_CODE = "OrderCode LIKE ?";
    private static final String REQUIRE_SEARCH_STATUS = "Status = ?";
    private static final String OFFSET = "ORDER BY ID DESC OFFSET ? ROWS FETCH FIRST 8 ROWS ONLY";
    private static final String INSERT_ORDER = "INSERT INTO [dbo].[Order]"
            + "           ([UserID],[Date],[PaymentDate],[PaymentMethod],"
            + "		   [Location],[PhoneNumber],[Price],[Status],[OrderCode],[Note])"
            + "            VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String GET_ORDER_BY_ORDER_CODE = "SELECT * FROM [Order] WHERE OrderCode = ?";
    private static final String GET_TOTAL_REVENUE_DAILY = "SELECT SUM(Price) AS TotalRevenue "
            + "FROM [dbo].[Order] "
            + "WHERE CONVERT(date, [Date]) = CONVERT(date, GETDATE()) "
            + "AND [Status] = 'COMPLETED'";
    private static final String GET_TOTAL_REVENUE_MONTHLY = "SELECT SUM(Price) AS TotalRevenue "
            + "FROM [dbo].[Order] "
            + "WHERE MONTH([Date]) = MONTH(GETDATE()) AND YEAR([Date]) = YEAR(GETDATE()) "
            + "AND [Status] = 'COMPLETED'";
    private static final String GET_TOTAL_SOLD_PRODUCT_DAILY = "SELECT SUM(od.Quantity) AS TotalSoldProduct "
            + "FROM [dbo].[OrderDetail] od "
            + "INNER JOIN [dbo].[Order] o ON od.OrderID = o.ID "
            + "WHERE CONVERT(date, o.[Date]) = CONVERT(date, GETDATE()) "
            + "AND o.[Status] = 'COMPLETED'";
    private static final String GET_TOTAL_SOLD_PRODUCT_MONTHLY = "SELECT SUM(od.Quantity) AS TotalSoldProduct "
            + "FROM [dbo].[OrderDetail] od "
            + "INNER JOIN [dbo].[Order] o ON od.OrderID = o.ID "
            + "WHERE MONTH(o.[Date]) = MONTH(GETDATE()) AND YEAR(o.[Date]) = YEAR(GETDATE()) "
            + "AND o.[Status] = 'COMPLETED'";
    private static final String GET_TOTAL_ORDER_DAILY = "SELECT COUNT(*) AS TotalOrders "
            + "FROM [dbo].[Order] "
            + "WHERE CONVERT(date, [Date]) = CONVERT(date, GETDATE()) "
            + "AND [Status] IN ('PENDING', 'COMPLETED') ";
    private static final String GET_TOTAL_ORDER_MONTHLY = "SELECT COUNT(ID) AS NumberOfOrders "
            + "FROM [dbo].[Order] "
            + "WHERE MONTH([Date]) = MONTH(GETDATE()) AND YEAR([Date]) = YEAR(GETDATE()) "
            + "AND [Status] IN ('PENDING', 'COMPLETED')";
    private static final String GET_TOTAL_ORDER_PENDING_DAILY = "SELECT COUNT(*)"
            + "FROM [dbo].[Order]"
            + "WHERE CONVERT(date, [Date]) = CONVERT(date, GETDATE()) AND Status = 'PENDING'";

    public List<OrderDTO> getAllOrder(int index) throws SQLException {
        Connection conn = null;
        List<OrderDTO> list = new ArrayList<>();
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_ALL_ORDER);
            psm.setInt(1, (index - 1) * 8);
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

    public int getPageAllOrder() throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_PAGE_ALL_ORDER);
            rs = psm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
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
        return count;
    }

    public int getAllOrderByUserID(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_PAGE_ORDER_BY_USERID);
            psm.setInt(1, id);
            rs = psm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
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
        return count;
    }

    public List<OrderDTO> getOrderByUserID(int id, int index) throws SQLException {
        Connection conn = null;
        List<OrderDTO> list = new ArrayList<>();
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_ALL_ORDER_BY_USERID);
            psm.setInt(1, id);
            psm.setInt(2, (index - 1) * 3);
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
            psm.setTimestamp(5, o.getPaymentDate());
            psm.setInt(6, o.getID());
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

    public List<OrderDTO> searchOrder(String oCode, String oStatus, int index) throws SQLException {
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
                psm = conn.prepareStatement(GET_ALL_ORDER1 + " WHERE " + REQUIRE_SEARCH_CODE + " AND " + REQUIRE_SEARCH_STATUS + " " + OFFSET);
                psm.setString(1, oCode + "%");
                psm.setString(2, oStatus);
                psm.setInt(3, (index - 1) * 8);
            } else if (!oCode.equals("") && oStatus.equals("")) {
                psm = conn.prepareStatement(GET_ALL_ORDER1 + " WHERE " + REQUIRE_SEARCH_CODE + " " + OFFSET);
                psm.setString(1, oCode + "%");
                psm.setInt(2, (index - 1) * 8);
            } else if (oCode.equals("") && !oStatus.equals("")) {
                psm = conn.prepareStatement(GET_ALL_ORDER1 + " WHERE " + REQUIRE_SEARCH_STATUS + " " + OFFSET);
                psm.setString(1, oStatus);
                psm.setInt(2, (index - 1) * 8);
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

    public int getNumPageWithSearchOrder(String oCode, String oStatus) throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            if (!oCode.equals("") && !oStatus.equals("")) {
                psm = conn.prepareStatement(GET_PAGE_ALL_ORDER + " WHERE " + REQUIRE_SEARCH_CODE + " AND " + REQUIRE_SEARCH_STATUS);
                psm.setString(1, oCode + "%");
                psm.setString(2, oStatus);
            } else if (!oCode.equals("") && oStatus.equals("")) {
                psm = conn.prepareStatement(GET_PAGE_ALL_ORDER + " WHERE " + REQUIRE_SEARCH_CODE);
                psm.setString(1, oCode + "%");
            } else if (oCode.equals("") && !oStatus.equals("")) {
                psm = conn.prepareStatement(GET_PAGE_ALL_ORDER + " WHERE " + REQUIRE_SEARCH_STATUS);
                psm.setString(1, oStatus);
            }
            rs = psm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
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
        return count;
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

    public int getTotalRevenueDaily() throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_TOTAL_REVENUE_DAILY);
            rs = psm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
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
        return count;
    }

    public int getTotalRevenueMonthly() throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_TOTAL_REVENUE_MONTHLY);
            rs = psm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
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
        return count;
    }

    public int getTotalSoldProductDaily() throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_TOTAL_SOLD_PRODUCT_DAILY);
            rs = psm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
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
        return count;
    }

    public int getTotalSoldProductMonthly() throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_TOTAL_SOLD_PRODUCT_MONTHLY);
            rs = psm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
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
        return count;
    }

    public int getTotalOrderDaily() throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_TOTAL_ORDER_DAILY);
            rs = psm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
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
        return count;
    }

    public int getTotalOrderMonthly() throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_TOTAL_ORDER_MONTHLY);
            rs = psm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
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
        return count;
    }

    public int getTotalOrderPenDingDaily() throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_TOTAL_ORDER_PENDING_DAILY);
            rs = psm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
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
        return count;
    }
}
