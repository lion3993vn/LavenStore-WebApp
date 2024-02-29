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
import lavenstore.orders.Cart;
import lavenstore.orders.ItemCart;
import lavenstore.products.ProductDAO;
import lavenstore.products.ProductDTO;

/**
 *
 * @author Pham Hieu
 */
@WebServlet(name = "ProcessCartController", urlPatterns = {"/processcart"})
public class ProcessCartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String SUCCESS = "CartController";
    private static final String ERROR = "CartController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = SUCCESS;
        try {
            ProductDAO pdao = new ProductDAO();
            List<ProductDTO> list = pdao.getAllProduct();
            Cookie[] listCookies = request.getCookies();
            String valueCart = "";
            if (listCookies != null) {
                for (Cookie c : listCookies) {
                    if (c.getName().equals("cart")) {
                        valueCart += c.getValue();
                        c.setMaxAge(0);
                        response.addCookie(c);
                    }
                }
            }
            String deleteItem = request.getParameter("deleteID");
            if (deleteItem != null) {

            } else {
                Cart cart = new Cart(valueCart, list);

                String number = request.getParameter("num");
                String id = request.getParameter("pid");

                int pid = Integer.parseInt("id");

                ProductDTO p = pdao.getProductByID(pid);
                int numStore = p.getQuantity();

                int num = Integer.parseInt("number");
                if (num == -1 && cart.getQuantityByID(pid) <= 1) {
                    cart.removeItemCart(pid);
                } else {
                    if (num == 1 && cart.getQuantityByID(pid) >= numStore) {
                        num = 0;
                    }
                    int price = p.getPrice();
                    ItemCart t = new ItemCart(p, num, price);
                    cart.addItemCart(t);
                }
                List<ItemCart> items = cart.getItems();
                valueCart = "";
                if (items.size() > 0) {
                    valueCart = items.get(0).getProduct().getID() + ":" + items.get(0).getQuantity();
                    for (int i = 1; i < items.size(); i++) {
                        Cookie listCooky = listCookies[i];
                        valueCart += "," + items.get(i).getProduct().getID() + ":" + items.get(i).getQuantity();
                    }
                }
                Cookie c = new Cookie("cart", valueCart);
                c.setMaxAge(60*60*24*60);
                request.setAttribute("cart", c);
            }
        } catch (Exception e) {

        } finally{
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
