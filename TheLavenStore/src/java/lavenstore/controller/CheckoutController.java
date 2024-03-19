/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavenstore.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lavenstore.orders.Cart;
import lavenstore.products.ProductDAO;
import lavenstore.products.ProductDTO;
import lavenstore.users.UserDAO;
import lavenstore.users.UserDTO;

/**
 *
 * @author Pham Hieu
 */
@WebServlet(name = "CheckoutController", urlPatterns = {"/CheckoutController"})
public class CheckoutController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "MainController?action=login";
    private static final String SUCCESS = "checkout.jsp";
    private static final String MAINPAGE = "MainController?action=null";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession(false);
            UserDTO user = (UserDTO) session.getAttribute("account");
            if (user != null) {
                if (!user.getRole().equals("admin")) {
                    ProductDAO pdao = new ProductDAO();
                    List<ProductDTO> list = pdao.getAllProduct();
                    Cookie[] listCookies = request.getCookies();
                    String valueCart = "";
                    if (listCookies != null) {
                        for (Cookie c : listCookies) {
                            if (c.getName().equals("cart")) {
                                valueCart += c.getValue();
                            }
                        }
                    }
                    Cart cart = new Cart(valueCart, list);
                    request.setAttribute("cartList", cart);
                    url = SUCCESS;
                } else {
                    url = MAINPAGE;
                }
            }
        } catch (Exception e) {
            log("Error at CheckoutController: " + e.toString());
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
