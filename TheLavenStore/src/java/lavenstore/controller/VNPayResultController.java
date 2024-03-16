/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavenstore.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import lavenstore.payment.Config;
import lavenstore.products.ProductDAO;
import lavenstore.products.ProductDTO;
import lavenstore.users.UserDTO;
import lavenstore.utils.Utils;

/**
 *
 * @author Pham Hieu
 */
@WebServlet(name = "VNPayResultController", urlPatterns = {"/VNPayResultController"})
public class VNPayResultController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "payment-failure.jsp";
    private static final String SUCCESS = "payment-success.jsp";
    private static final String EMAIL_SUBJECT = "[TheLavenStore] Electronic Invoice #";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        //khoi tao gia tri tra ve vnpay
        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
            String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        String signValue = Config.hashAllFields(fields);

        try {
            HttpSession session = request.getSession(false);
            UserDTO user = (UserDTO) session.getAttribute("account");
            
            //lay san pham trong cookie
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

            //bat parameter
            String orderCode = request.getParameter("vnp_TxnRef");
            String amount = request.getParameter("vnp_Amount");
            String vnp_ResponseCode = request.getParameter("vnp_ResponseCode");
            String paymentDate = request.getParameter("vnp_PayDate");
            String vnp_TransactionStatus = request.getParameter("vnp_TransactionStatus");

            //lay order
            OrderDAO odao = new OrderDAO();
            OrderDTO order = odao.getOrderByOrderCode(orderCode);

            //lay total quantity
            int totalQuantity = 0;
            for (ItemCart i : listInCart) {
                totalQuantity += i.getQuantity();
            }

            if (signValue.equals(vnp_SecureHash)) {
                if ("00".equals(vnp_TransactionStatus)) {

                    //update order
                    Timestamp payment_date = Utils.convertStringToTimestamp(paymentDate);
                    order.setPaymentDate(payment_date);
                    System.out.println(payment_date);
                    order.setStatus("COMPLETED");
                    odao.updateOrder(order);

                    //xoa cart
                    for (Cookie c : listCookies) {
                        if (c.getName().equals("cart")) {
                            c.setMaxAge(0);
                            response.addCookie(c);
                        }
                    }

                    //set attribute
                    request.setAttribute("order", order);
                    request.setAttribute("totalQuantity", totalQuantity);

                    //send email
                    String contentMail = BillEmail.billEmail(user, order, totalQuantity);
                    boolean email = Email.sendEmail(user.getEmail(), EMAIL_SUBJECT + orderCode, contentMail);
                    
                    url = SUCCESS;
                } else {
                    order.setStatus("CANCELLED");
                    odao.updateOrder(order);
                    for (ItemCart i : listInCart) {
                        pdao.refundQuantity(i);
                    }
                    request.setAttribute("order", order);
                    request.setAttribute("totalQuantity", totalQuantity);
                    url = ERROR;
                }

            } else {
                throw new Exception("Mã vnp_SecureHash không đúng");
            }
        } catch (Exception e) {
            log("Error at PaymentController: " + e.toString());
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
