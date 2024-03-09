/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavenstore.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lavenstore.email.BillEmail;
import lavenstore.email.Email;
import lavenstore.orders.Cart;
import lavenstore.orders.ItemCart;
import lavenstore.orders.OrderDAO;
import lavenstore.orders.OrderDTO;
import lavenstore.orders.OrderDetailsDAO;
import lavenstore.orders.OrderDetailsDTO;
import lavenstore.payment.Config;
import lavenstore.products.ProductDAO;
import lavenstore.products.ProductDTO;
import lavenstore.users.UserDTO;

/**
 *
 * @author Pham Hieu
 */
@WebServlet(name = "PaymentController", urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "checkout";
    private static final String SUCCESS = "";
    private static final String PENDING = "payment-pending.jsp";
    private static final String EMAIL_SUBJECT = "[TheLavenStore] Electronic Invoice #";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url = ERROR;
        boolean error = false;
        try {

            //bat parameter
            String phoneNumber = request.getParameter("phoneNumber");
            String address = request.getParameter("address");
            String note = request.getParameter("note");
            String payment = request.getParameter("payment");

            //lay items trong cart
            ProductDAO pdao = new ProductDAO();
            List<ProductDTO> listProduct = pdao.getAllProduct();
            Cookie[] listCookies = request.getCookies();
            String valueCart = "";
            if (listCookies != null) {
                for (Cookie c : listCookies) {
                    if (c.getName().equals("cart")) {
                        valueCart += c.getValue();
                    }
                }
            }
            Cart cart = new Cart(valueCart, listProduct);
            List<ItemCart> listInCart = cart.getItems();

            //kiem tra so luong trong kho
            for (ItemCart iC : listInCart) {
                if (iC.getQuantity() > iC.getProduct().getQuantity()) {
                    request.setAttribute("error" + iC.getProduct().getID(), "Chỉ còn " + iC.getProduct().getQuantity() + " sản phẩm");
                    error = true;
                }
            }
            if (error) {
                request.setAttribute("error", 1);
                return;
            }

            //tao don hang
            HttpSession session = request.getSession(false);
            UserDTO user = (UserDTO) session.getAttribute("account");

            //tao moi doi tuong
            OrderDAO oDao = new OrderDAO();
            OrderDTO order = new OrderDTO();

            //tao ordercode
            String orderCode = Config.getRandomNumber(6);
            while (oDao.getOrderByOrderCode(orderCode) != null) {
                orderCode = Config.getRandomNumber(6);
            }

            //lay thoi gian hien tai
            long currentTimeMillis = System.currentTimeMillis();
            Timestamp currentTimestamp = new Timestamp(currentTimeMillis);

            //set thuoc tinh
            order.setUserID(user.getID());
            order.setDate(currentTimestamp);
            order.setLocation(address);
            order.setPhoneNumber(phoneNumber);
            order.setAmount(cart.getTotalInCart() + 25000);
            order.setOrderCode(orderCode);
            order.setNote(note);

            //payment check
            if (payment.equals("cod")) {
                
                //set thuoc tinh order cod
                order.setPaymentMethod("COD");
                order.setStatus("PENDING");
                
                //insert vao order
                int orderID = oDao.insertOrder(order);

                OrderDetailsDAO oDeDao = new OrderDetailsDAO();
                OrderDetailsDTO oDetails = new OrderDetailsDTO();

                //insert order details & remove quantity san pham khoi database
                int totalQuantity = 0;
                for (ItemCart i : listInCart) {
                    oDeDao.insertOrderDetails(i, orderID);
                    pdao.updateQuantity(i);
                    totalQuantity += i.getQuantity();
                }

                //xoa cart
                for (Cookie c : listCookies) {
                    if (c.getName().equals("cart")) {
                        c.setMaxAge(0);
                        response.addCookie(c);
                    }
                }
                
                //send email
                String contentMail = BillEmail.billEmail(user, order, totalQuantity);
                boolean email = Email.sendEmail(user.getEmail(), EMAIL_SUBJECT + orderCode, contentMail);
                if (!email) {
                    throw new Exception("Email send error");
                }

                url = PENDING;

                //set attribute
                request.setAttribute("order", order);
                request.setAttribute("totalQuantity", totalQuantity);
            } else if (payment.equals("vnpay")) {

            }
        } catch (Exception e) {
            log("Error at HomeController: " + e.toString());
            request.setAttribute("MESSAGE", "Somethings are error...");
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
