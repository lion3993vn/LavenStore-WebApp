/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavenstore.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pham Hieu
 */
public class MainController extends HttpServlet {

    private static final String NOT_FOUND = "notfound.html";
    private static final String HOME_CONTROLLER = "HomeController";
    private static final String LOGIN_PAGE = "login.jsp";
    private static final String REGISTER_PAGE = "register.jsp";
    private static final String FORGOT_PASSWORD_PAGE = "forgot_password.jsp";
    private static final String LOGIN = "login";
    private static final String REGISTER = "register";
    private static final String FORGOT_PASSWORD = "forgot-password";

    private static final String CART = "Cart";
    private static final String CART_CONTROLLER = "CartController";

    private static final String CHECKOUT = "Checkout";
    private static final String CHECKOUT_CONTROLLER = "CheckoutController";

    private static final String ADMIN = "Admin";
    private static final String ADMIN_CONTROLLER = "AdminDashBoardController";

    private static final String ADMIN_ORDER = "Admin-order";
    private static final String ADMIN_ORDER_CONTROLLER = "AdminOrderController";

    private static final String ADMIN_USER = "Admin-user";
    private static final String ADMIN_USER_CONTROLLER = "AdminUserController";

    private static final String ADMIN_PRODUCT = "Admin-product";
    private static final String ADMIN_PRODUCT_CONTROLLER = "AdminProductController";

    private static final String SHOP = "shop";
    private static final String SHOP_CONTROLLER = "ShopController";

    private static final String PRODUCT = "product";
    private static final String PRODUCT_CONTROLLER = "DetailProductController";

    private static final String PROFILE = "profile";
    private static final String PROFILE_UPDATE = "profile-update";
    private static final String PROFILE_UPDATE_CONTROLLER = "ProfileUpdateController";
    private static final String PROFILE_PAGE = "profile.jsp";
    private static final String PROFILE_ADDRESS = "profile-address";
    private static final String PROFILE_ADDRESS_PAGE = "profile_address.jsp";
    private static final String PROFILE_ADDRESS_UPDATE = "address-update";
    private static final String PROFILE_ADDRESS_CONTROLLER = "ProfileAddressController";
    private static final String PROFILE_PURCHASE = "profile-purchase";
    private static final String PROFILE_PURCHASE_CONTROLLER = "ProfilePurchaseController";
    private static final String PROFILE_PASSWORD = "profile-password";
    private static final String PROFILE_PASSWORD_PAGE = "profile_password.jsp";
    private static final String LOGOUT = "logout";
    private static final String LOGOUT_CONTROLLER = "LogoutController";

    private static final String WISHLIST = "wishlist";
    private static final String WISHLIST_CONTROLLER = "WishListController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = HOME_CONTROLLER;
        try {
            String action = request.getParameter("action");
            if (action == null) {
                url = HOME_CONTROLLER;
            } else if (action.equals(SHOP)) {
                url = SHOP_CONTROLLER;
            } else if (action.equals(PRODUCT)) {
                url = PRODUCT_CONTROLLER;
            } else if (action.equals(CART)) {
                url = CART_CONTROLLER;
            } else if (action.equals(CHECKOUT)) {
                url = CHECKOUT_CONTROLLER;
            } else if (action.equals(ADMIN)) {
                url = ADMIN_CONTROLLER;
            } else if (action.equals(ADMIN_ORDER)) {
                url = ADMIN_ORDER_CONTROLLER;
            } else if (action.equals(ADMIN_USER)) {
                url = ADMIN_USER_CONTROLLER;
            } else if (action.equals(ADMIN_PRODUCT)) {
                url = ADMIN_PRODUCT_CONTROLLER;
            } else if (action.equals(LOGIN)) {
                url = LOGIN_PAGE;
            } else if (action.equals(REGISTER)) {
                url = REGISTER_PAGE;
            } else if (action.equals(FORGOT_PASSWORD)) {
                url = FORGOT_PASSWORD_PAGE;
            } else if (action.equals(PROFILE)) {
                url = PROFILE_PAGE;
            } else if (action.equals(PROFILE_UPDATE)) {
                url = PROFILE_UPDATE_CONTROLLER;
            } else if (action.equals(PROFILE_ADDRESS)) {
                url = PROFILE_ADDRESS_PAGE;
            } else if (action.equals(PROFILE_ADDRESS_UPDATE)) {
                url = PROFILE_ADDRESS_CONTROLLER;
            } else if (action.equals(PROFILE_PASSWORD)) {
                url = PROFILE_PASSWORD_PAGE;
            } else if (action.equals(PROFILE_PURCHASE)) {
                url = PROFILE_PURCHASE_CONTROLLER;
            } else if (action.equals(LOGOUT)) {
                url = LOGOUT_CONTROLLER;
            } else if (action.equals(WISHLIST)) {
                url = WISHLIST_CONTROLLER;
            } else {
                url = NOT_FOUND;
            }

        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
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
