/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavenstore.controller;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "AjaxAdminProductController", urlPatterns = {"/AjaxAdminProductController"})
public class AjaxAdminProductController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String ajaxAdminAction = request.getParameter("ajaxAdminAction");
        request.setCharacterEncoding("UTF-8");
        try {
            if (ajaxAdminAction.equals("edit-product")) {
                int txtProductID = parseInt(request.getParameter("txtProductID"));
                ProductDAO dao = new ProductDAO();
                ProductDTO product = dao.getProductByID(txtProductID);
                JsonObject json = new JsonObject();
                json.addProperty("productName", product.getName());
                json.addProperty("cateID", product.getCateID());
                json.addProperty("quantity", product.getQuantity());
                json.addProperty("price", product.getPrice());
                json.addProperty("rating", product.getRate());
                json.addProperty("description", product.getDescription());
                json.addProperty("image", product.getImage());
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print(json.toString());
                out.flush();
            }
        } catch (Exception e) {
            log("Error at AjaxAdminProductController: " + e.toString());
            request.setAttribute("MESSAGE", "Somethings are error...");
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
