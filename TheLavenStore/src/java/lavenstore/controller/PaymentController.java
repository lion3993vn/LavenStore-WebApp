/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavenstore.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
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
@WebServlet(name = "PaymentController", urlPatterns = {"/PaymentController"})
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
    private static final String ERROR = "CheckoutController";
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
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }

            //tao don hang
            HttpSession session = request.getSession(false);
            UserDTO user = (UserDTO) session.getAttribute("account");

            //tao moi doi tuong
            OrderDAO oDao = new OrderDAO();
            OrderDTO order = new OrderDTO();

            //tao ordercode
            String orderCode = Config.getRandomNumber(8);
            while (oDao.getOrderByOrderCode(orderCode) != null) {
                orderCode = Config.getRandomNumber(8);
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
                
                //chuyen huong
                request.getRequestDispatcher(url).forward(request, response);
            } else if (payment.equals("vnpay")) {
                
                //set thuoc tinh vnpay
                order.setPaymentMethod("VNPAY");
                order.setStatus("PENDING");
                
                //insert vao bang
                int orderID = oDao.insertOrder(order);
                OrderDetailsDAO oDeDao = new OrderDetailsDAO();
                OrderDetailsDTO oDetails = new OrderDetailsDTO();
                
                //insert order details
                int totalQuantity = 0;
                for (ItemCart i : listInCart) {
                    oDeDao.insertOrderDetails(i, orderID);
                    pdao.updateQuantity(i);
                    totalQuantity += i.getQuantity();
                }
                
                requestVNPay(orderCode, order.getAmount(), request, response);
            }
        } catch (Exception e) {
            log("Error at PaymentController: " + e.toString());
            request.setAttribute("MESSAGE", "Somethings are error...");
        }
    }

    private void requestVNPay(String orderCode, int price, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        long amount = price * 100;
        String bankCode = "";

        String vnp_TxnRef = orderCode;
        String vnp_IpAddr = Config.getIpAddress(request);

        String vnp_TmnCode = Config.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");

        if (bankCode != null && !bankCode.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bankCode);
        }
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

        String locate = request.getParameter("language");
        if (locate != null && !locate.isEmpty()) {
            vnp_Params.put("vnp_Locale", locate);
        } else {
            vnp_Params.put("vnp_Locale", "vn");
        }
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
        response.sendRedirect(paymentUrl);
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
