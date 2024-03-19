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
import lavenstore.products.CategoryDAO;
import lavenstore.products.ProductDAO;
import lavenstore.products.ProductDTO;
import lavenstore.users.UserDTO;
import lavenstore.wishlist.WishListDAO;

/**
 *
 * @author Pham Hieu
 */
@WebServlet(name = "DetailProductController", urlPatterns = {"/DetailProductController"})
public class DetailProductController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "home.jsp";
    private static final String SUCCESS = "product.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String id = request.getParameter("id");
            ProductDAO pdao = new ProductDAO();
            ProductDTO product = pdao.getProductByID(Integer.parseInt(id));
            List<ProductDTO> listp = pdao.getRelatedProduct(product.getID(), product.getCateID());
            
            HttpSession session = request.getSession(false);
            UserDTO user = (UserDTO) session.getAttribute("account");
            
            boolean isWishlist = false;
            if(user != null){
                WishListDAO wdao = new WishListDAO();
                isWishlist = wdao.isWishList(product.getID(), user.getID());
            }
            
            CategoryDAO cdao = new CategoryDAO();
            String cateName = cdao.getCateNameByCateID(product.getCateID());
            
            float start = product.getRate(); //4.0
            int starFull = (int) start; //4
            int starHalf = start - starFull == 0.5 ? 1 : 0; //0
            int starNo = 5 - starFull - starHalf; //1
            
            
            request.setAttribute("starFull", starFull);
            request.setAttribute("starHalf", starHalf);
            request.setAttribute("starNo", starNo);
            request.setAttribute("wishlist", isWishlist);
            request.setAttribute("cate", cateName);
            request.setAttribute("product", product);
            request.setAttribute("related", listp);
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at DetailProductController: " + e.toString());
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
