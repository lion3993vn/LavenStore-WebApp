/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavenstore.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lavenstore.orders.Cart;
import lavenstore.orders.ItemCart;
import lavenstore.products.ProductDAO;
import lavenstore.products.ProductDTO;

/**
 *
 * @author Pham Hieu
 */
@WebServlet(name = "CartController", urlPatterns = {"/CartController"})
public class CartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String SUCCESS = "cart.jsp";
    private static final String ERROR = "cart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
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
            List<ItemCart> listItem = cart.getItems();
            int n;
            if(listItem !=null){
                n=listItem.size();
            } else{
                n=0;
            }
            request.setAttribute("totalItemInCart", n);
        } catch (Exception e) {
            log("Error at CartController: " + e.toString());
            request.setAttribute("MESSAGE", "Somethings are error...");
        } finally {
            request.getRequestDispatcher(SUCCESS).forward(request, response);
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