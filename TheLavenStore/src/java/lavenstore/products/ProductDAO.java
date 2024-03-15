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

    private static final String GET_LIST_PRODUCT = "SELECT * FROM Product";
    private static final String GET_PAGE_LIST_PRODUCT = "SELECT COUNT(*) FROM Product";
    private static final String GET_BEST_SELLER = "SELECT TOP 8 ProductID"
            + " FROM [dbo].[OrderDetail]"
            + " GROUP BY ProductID"
            + " ORDER BY SUM(Quantity) DESC";
    private static final String GET_RELEASE_PRODUCT = "select top 3 * from Product order by id desc";
    private static final String DELETE_PRODUCT = "delete from Product where ID = ?";
    private static final String REQUIRE_SEARCH = "Name LIKE ?";
    private static final String REQUIRE_SORT = " ORDER BY Price ";
    private static final String GET_LIST_PRODUCT_BY_CATEID = "SELECT * FROM Product WHERE CateID = ?";
    private static final String GET_PAGE_LIST_PRODUCT_BY_CATEID = "SELECT COUNT(*) FROM Product WHERE CateID = ?";
    private static final String GET_PRODUCT_BY_ID = "SELECT * FROM Product WHERE ID = ?";
    private static final String GET_LIST_PRODUCT_BY_SEARCH = "SELECT * FROM Product WHERE CateID = ? and [name] like ?";
    private static final String GET_LIST_PRODUCT_SORT_BY_PRICE = "select * from Product WHERE CateID = ? ORDER BY Price ?";
    private static final String OFFSET = " OFFSET ? ROWS FETCH FIRST 12 ROWS ONLY";
    private static final String OFFSET_WITH_ORDER_BY = " ORDER BY [ID] OFFSET ? ROWS FETCH FIRST 12 ROWS ONLY";
    private static final String GET_RELATED_PRODUCT = "SELECT TOP 4 * from Product WHERE CateID = ? AND [ID] <> ?";
    private static final String ADD_PRODUCT = "INSERT INTO [dbo].[Product] ([Name] ,[CateID] ,[Quantity] ,[Price] ,[Rating] ,[Description] ,[Image]) VALUES (?,?,?,?,?,?,?)";
    private static final String UPDATE_PRODUCT = "UPDATE [dbo].[Product] SET [Name] = ? ,[CateID] = ? ,[Quantity] = ? ,[Price] = ? ,[Rating] = ? ,[Description] = ?,[Image] = ? WHERE id = ?";

    public List<ProductDTO> getListProduct(int index) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            pst = conn.prepareStatement(GET_LIST_PRODUCT + OFFSET_WITH_ORDER_BY);
            pst.setInt(1, (index - 1) * 12);
            rs = pst.executeQuery();
            while (rs.next()) {
                ProductDTO product = new ProductDTO();
                product.setID(rs.getInt("ID"));
                product.setCateID(rs.getInt("CateID"));
                product.setName(rs.getString("Name"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setPrice(rs.getInt("Price"));
                product.setRate(rs.getFloat("Rating"));
                product.setDescription(rs.getString("Description"));
                product.setImage(rs.getString("Image"));
                list.add(product);
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

    public int getPageListProduct() throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            pst = conn.prepareStatement(GET_PAGE_LIST_PRODUCT);
            rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
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

        return count;
    }

    public boolean deleteProduct(int productID) throws SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(DELETE_PRODUCT);
            stmt.setInt(1, productID);
            check = stmt.executeUpdate() > 0 ? true : false;
        } catch (Exception ex) {
            System.out.println("Error in servlet. Details:" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
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
                ProductDTO product = new ProductDTO();
                product.setID(rs.getInt("ID"));
                product.setCateID(rs.getInt("CateID"));
                product.setName(rs.getString("Name"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setPrice(rs.getInt("Price"));
                product.setRate(rs.getFloat("Rating"));
                product.setDescription(rs.getString("Description"));
                product.setImage(rs.getString("Image"));
                list.add(product);
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

    public List<ProductDTO> getListByCateId(int id, int index) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            pst = conn.prepareStatement(GET_LIST_PRODUCT_BY_CATEID + OFFSET_WITH_ORDER_BY);
            pst.setInt(1, id);
            pst.setInt(2, (index - 1) * 12);
            rs = pst.executeQuery();
            while (rs.next()) {
                ProductDTO product = new ProductDTO();
                product.setID(rs.getInt("ID"));
                product.setCateID(rs.getInt("CateID"));
                product.setName(rs.getString("Name"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setPrice(rs.getInt("Price"));
                product.setRate(rs.getFloat("Rating"));
                product.setDescription(rs.getString("Description"));
                product.setImage(rs.getString("Image"));
                list.add(product);
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

    public int getPageListByCateId(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            pst = conn.prepareStatement(GET_LIST_PRODUCT_BY_CATEID);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
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
        return count;
    }

    public List<ProductDTO> getListRequireByCateId(int id, String search, String sort, int index) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            if (id == 0) {
                if (search != null && sort == null) {
                    pst = conn.prepareStatement(GET_LIST_PRODUCT + " WHERE " + REQUIRE_SEARCH + OFFSET_WITH_ORDER_BY);
                    pst.setString(1, "%" + search + "%");
                    pst.setInt(2, (index - 1) * 12);
                } else if (search == null && sort != null) {
                    pst = conn.prepareStatement(GET_LIST_PRODUCT + REQUIRE_SORT + sort + OFFSET);
                    pst.setInt(1, (index - 1) * 12);
                } else if (search != null && sort != null) {
                    pst = conn.prepareStatement(GET_LIST_PRODUCT + " WHERE " + REQUIRE_SEARCH + REQUIRE_SORT + sort + OFFSET);
                    pst.setString(1, "%" + search + "%");
                    pst.setInt(2, (index - 1) * 12);
                }
            } else if (id != 0) {
                if (search != null && sort == null) {
                    pst = conn.prepareStatement(GET_LIST_PRODUCT_BY_CATEID + " AND " + REQUIRE_SEARCH + OFFSET_WITH_ORDER_BY);
                    pst.setInt(1, id);
                    pst.setString(2, "%" + search + "%");
                    pst.setInt(3, (index - 1) * 12);
                } else if (search == null && sort != null) {
                    pst = conn.prepareStatement(GET_LIST_PRODUCT_BY_CATEID + REQUIRE_SORT + sort + OFFSET);
                    pst.setInt(1, id);
                    pst.setInt(2, (index - 1) * 12);
                } else if (search != null && sort != null) {
                    pst = conn.prepareStatement(GET_LIST_PRODUCT_BY_CATEID + " AND " + REQUIRE_SEARCH + REQUIRE_SORT + sort + OFFSET);
                    pst.setInt(1, id);
                    pst.setString(2, "%" + search + "%");
                    pst.setInt(3, (index - 1) * 12);
                }
            }
            rs = pst.executeQuery();
            while (rs.next()) {
                ProductDTO product = new ProductDTO();
                product.setID(rs.getInt("ID"));
                product.setCateID(rs.getInt("CateID"));
                product.setName(rs.getString("Name"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setPrice(rs.getInt("Price"));
                product.setRate(rs.getFloat("Rating"));
                product.setDescription(rs.getString("Description"));
                product.setImage(rs.getString("Image"));
                list.add(product);
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

    public int getPageListRequireByCateId(int id, String search, String sort) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            if (id == 0) {
                if (search != null && sort == null) {
                    pst = conn.prepareStatement(GET_PAGE_LIST_PRODUCT + " WHERE " + REQUIRE_SEARCH);
                    pst.setString(1, "%" + search + "%");
                } else if (search == null && sort != null) {
                    pst = conn.prepareStatement(GET_PAGE_LIST_PRODUCT + REQUIRE_SORT + sort);
                } else if (search != null && sort != null) {
                    pst = conn.prepareStatement(GET_PAGE_LIST_PRODUCT + " WHERE " + REQUIRE_SEARCH + REQUIRE_SORT + sort);
                    pst.setString(1, "%" + search + "%");
                }
            } else if (id != 0) {
                if (search != null && sort == null) {
                    pst = conn.prepareStatement(GET_PAGE_LIST_PRODUCT_BY_CATEID + " AND " + REQUIRE_SEARCH);
                    pst.setInt(1, id);
                    pst.setString(2, "%" + search + "%");
                } else if (search == null && sort != null) {
                    pst = conn.prepareStatement(GET_PAGE_LIST_PRODUCT_BY_CATEID + REQUIRE_SORT + sort);
                    pst.setInt(1, id);
                } else if (search != null && sort != null) {
                    pst = conn.prepareStatement(GET_PAGE_LIST_PRODUCT_BY_CATEID + " AND " + REQUIRE_SEARCH + REQUIRE_SORT + sort);
                    pst.setInt(1, id);
                    pst.setString(2, "%" + search + "%");
                }
            }
            rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
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
        return count;
    }

    public ProductDTO getProductByID(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ProductDTO product = null;
        try {
            conn = DBUtils.getConnection();
            pst = conn.prepareStatement(GET_PRODUCT_BY_ID);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                product = new ProductDTO();
                product.setID(rs.getInt("ID"));
                product.setCateID(rs.getInt("CateID"));
                product.setName(rs.getString("Name"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setPrice(rs.getInt("Price"));
                product.setRate(rs.getFloat("Rating"));
                product.setDescription(rs.getString("Description"));
                product.setImage(rs.getString("Image"));
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
        return product;
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
                ProductDTO product = new ProductDTO();
                product.setID(rs.getInt("ID"));
                product.setCateID(rs.getInt("CateID"));
                product.setName(rs.getString("Name"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setPrice(rs.getInt("Price"));
                product.setRate(rs.getFloat("Rating"));
                product.setDescription(rs.getString("Description"));
                product.setImage(rs.getString("Image"));
                list.add(product);
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
                ProductDTO product = new ProductDTO();
                product.setID(rs.getInt("ID"));
                product.setCateID(rs.getInt("CateID"));
                product.setName(rs.getString("Name"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setPrice(rs.getInt("Price"));
                product.setRate(rs.getFloat("Rating"));
                product.setDescription(rs.getString("Description"));
                product.setImage(rs.getString("Image"));
                list.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public int[] getBestSeller() throws SQLException {
        Connection conn = null;
        int[] a = new int[8];
        int check = 0;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_BEST_SELLER);
            rs = psm.executeQuery();
            while (rs.next()) {
                a[check] = rs.getInt("ProductID");
                check++;
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
        return a;
    }

    public List<ProductDTO> getRelatedProduct(int id, int cateid) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            pst = conn.prepareStatement(GET_RELATED_PRODUCT);
            pst.setInt(1, cateid);
            pst.setInt(2, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                ProductDTO product = new ProductDTO();
                product.setID(rs.getInt("ID"));
                product.setCateID(rs.getInt("CateID"));
                product.setName(rs.getString("Name"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setPrice(rs.getInt("Price"));
                product.setRate(rs.getFloat("Rating"));
                product.setDescription(rs.getString("Description"));
                product.setImage(rs.getString("Image"));
                list.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public boolean insert(ProductDTO newProduct) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_PRODUCT);
                ptm.setString(1, newProduct.getName());
                ptm.setInt(2, newProduct.getCateID());
                ptm.setInt(3, newProduct.getQuantity());
                ptm.setInt(4, newProduct.getPrice());
                ptm.setFloat(5, newProduct.getRate());
                ptm.setString(6, newProduct.getDescription());
                ptm.setString(7, newProduct.getImage());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean update(ProductDTO newProduct) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_PRODUCT);
                ptm.setString(1, newProduct.getName());
                ptm.setInt(2, newProduct.getCateID());
                ptm.setInt(3, newProduct.getQuantity());
                ptm.setInt(4, newProduct.getPrice());
                ptm.setFloat(5, newProduct.getRate());
                ptm.setString(6, newProduct.getDescription());
                ptm.setString(7, newProduct.getImage());
                ptm.setInt(8, newProduct.getID());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public List<ProductDTO> getListWithSearch(String keyword, int cateID, int index) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            if (!keyword.isEmpty() && cateID == 0) {
                pst = conn.prepareStatement(GET_LIST_PRODUCT + " where " + REQUIRE_SEARCH + " ORDER BY ID " + OFFSET);
                pst.setString(1, "%" + keyword + "%");
                pst.setInt(2, index);
            } else if (keyword.isEmpty() && cateID != 0) {
                pst = conn.prepareStatement(GET_LIST_PRODUCT + " where " + " CateID = ?" + " ORDER BY ID " + OFFSET);
                pst.setInt(1, cateID);
                pst.setInt(2, index);
            } else if (!keyword.isEmpty() && cateID != 0) {
                pst = conn.prepareStatement(GET_LIST_PRODUCT + " where " + REQUIRE_SEARCH + " AND CateID = ?" + " ORDER BY ID " + OFFSET);
                pst.setString(1, "%" + keyword + "%");
                pst.setInt(2, cateID);
                pst.setInt(3, index);
            }

            rs = pst.executeQuery();
            while (rs.next()) {
                ProductDTO product = new ProductDTO();
                product.setID(rs.getInt("ID"));
                product.setCateID(rs.getInt("CateID"));
                product.setName(rs.getString("Name"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setPrice(rs.getInt("Price"));
                product.setRate(rs.getFloat("Rating"));
                product.setDescription(rs.getString("Description"));
                product.setImage(rs.getString("Image"));
                list.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public int getTotalWithSearch(String keyword, int cateID) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int totalPage = 0;
        try {
            conn = DBUtils.getConnection();
            if (!keyword.isEmpty() && cateID == 0) {
                pst = conn.prepareStatement(GET_PAGE_LIST_PRODUCT + " where " + REQUIRE_SEARCH);
                pst.setString(1, "%" + keyword + "%");

            } else if (keyword.isEmpty() && cateID != 0) {
                pst = conn.prepareStatement(GET_PAGE_LIST_PRODUCT + " where " + " CateID = ?");
                pst.setInt(1, cateID);

            } else if (!keyword.isEmpty() && cateID != 0) {
                pst = conn.prepareStatement(GET_PAGE_LIST_PRODUCT + " where " + REQUIRE_SEARCH + " AND CateID = ?");
                pst.setString(1, "%" + keyword + "%");
                pst.setInt(2, cateID);
            }

            rs = pst.executeQuery();
            if (rs.next()) {
                totalPage = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return totalPage;
    }
}
