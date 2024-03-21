/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavenstore.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lavenstore.products.ProductDAO;
import lavenstore.products.ProductDTO;
import lavenstore.users.UserDTO;
import lavenstore.wishlist.WishListDAO;
import lavenstore.wishlist.WishListDTO;

/**
 *
 * @author Pham Hieu
 */
@WebServlet(name = "WishListController", urlPatterns = {"/WishListController"})
public class WishListController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "login.jsp";
    private static final String SUCCESS = "MainController?action=product";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession(false);
            UserDTO user = (UserDTO) session.getAttribute("account");
            if (user != null) {
                String actionWL = request.getParameter("actionWL");
                if (actionWL.equals("add-wishlist")) {
                    String id = request.getParameter("productID");

                    WishListDAO wdao = new WishListDAO();
                    if (wdao.isWishList(Integer.parseInt(id), user.getID())) {
                        wdao.removeWishList(Integer.parseInt(id), user.getID());
                        url = SUCCESS + "&id=" + id;
                    } else {
                        wdao.addWishList(Integer.parseInt(id), user.getID());
                        url = SUCCESS + "&id=" + id;
                    }
                    response.sendRedirect(url);

                } else if (actionWL.equals("show-wishlist")) {
                    if (user != null) {

                        String index = request.getParameter("index");

                        if (index == null) {
                            index = "1";
                        }
                        int countPage = 0;
                        int currentPage = Integer.parseInt(index);

                        WishListDAO wdao = new WishListDAO();
                        List<WishListDTO> list = wdao.getWishListByUserID(user.getID(), currentPage);

                        ProductDAO pdao = new ProductDAO();
                        List<ProductDTO> listp = new ArrayList<>();
                        for (WishListDTO i : list) {
                            listp.add(pdao.getProductByID(i.getProductID()));
                        }
                        countPage = wdao.getPageWishListByUserID(user.getID());
                        int totalPage = countPage / 12;
                        if (countPage % 12 != 0) {
                            totalPage++;
                        }
                        request.setAttribute("size", listp.size());
                        request.setAttribute("page", totalPage);
                        request.setAttribute("curr", currentPage);
                        request.setAttribute("listp", listp);
                        request.getRequestDispatcher("wishlist.jsp").forward(request, response);
                    } else {
                        response.sendRedirect(ERROR);
                    }
                }
            } else request.getRequestDispatcher("MainController?action=login").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
