/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavenstore.controller;

import java.io.IOException;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lavenstore.products.ProductDAO;
import lavenstore.products.ProductDTO;

/**
 *
 * @author huyhu
 */
@WebServlet(name = "ProcessAdminProductController", urlPatterns = {"/ProcessAdminProductController"})
public class ProcessAdminProductController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String SUCCESS = "AdminProductController";
    private static final String ERROR = "AdminProductController";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String adminProductAction = request.getParameter("adminProductAction");
        String url = ERROR;
        try {
            if (adminProductAction.equals("add-product")) {
                String productName = request.getParameter("add-name");
                int cateID = parseInt(request.getParameter("add-cateID"));
                int quantity = parseInt(request.getParameter("add-quantity"));
                int price = parseInt(request.getParameter("add-price"));
                float rating = parseFloat(request.getParameter("add-rate"));
                String description = request.getParameter("add-description");
                String image = request.getParameter("add-image");
                ProductDAO dao = new ProductDAO();
                ProductDTO product = new ProductDTO();
                product.setName(productName);
                product.setCateID(cateID);
                product.setQuantity(quantity);
                product.setPrice(price);
                product.setRate(rating);
                product.setDescription(description);
                product.setImage(image);
                dao.insert(product);
                url = SUCCESS;
            }else if (adminProductAction.equals("edit-product")) {
                int productID = parseInt(request.getParameter("productID"));
                String productName = request.getParameter("edit-name");
                int cateID = parseInt(request.getParameter("edit-cateID"));
                int quantity = parseInt(request.getParameter("edit-quantity"));
                int price = parseInt(request.getParameter("edit-price"));
                float rating = parseFloat(request.getParameter("edit-rate"));
                String description = request.getParameter("edit-description");
                String image = request.getParameter("edit-image");
                ProductDAO dao = new ProductDAO();
                ProductDTO product = new ProductDTO();
                product.setID(productID);
                product.setName(productName);
                product.setCateID(cateID);
                product.setQuantity(quantity);
                product.setPrice(price);
                product.setRate(rating);
                product.setDescription(description);
                product.setImage(image);
                dao.update(product);
                url = SUCCESS;
            }else if (adminProductAction.equals("delete-product")) {
                int productID = parseInt(request.getParameter("productID"));
                ProductDAO dao = new ProductDAO();
                dao.deleteProduct(productID);
                url = SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
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
