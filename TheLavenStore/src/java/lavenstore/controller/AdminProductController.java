/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavenstore.controller;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lavenstore.products.CategoryDAO;
import lavenstore.products.CategoryDTO;
import lavenstore.products.ProductDAO;
import lavenstore.products.ProductDTO;

/**
 *
 * @author Pham Hieu
 */
@WebServlet(name = "AdminProductController", urlPatterns = {"/AdminProductController"})
public class AdminProductController extends HttpServlet {

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
    private static final String SUCCESS = "admin-product.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String index = request.getParameter("index");
            if (index == null) {
                index = "1";
            }

            int countPage = 0;
            int currentPage = Integer.parseInt(index);
            ProductDAO pdao = new ProductDAO();
            CategoryDAO cdao = new CategoryDAO();
            List<CategoryDTO> listc = cdao.getListCategory();
            List<ProductDTO> listp = new ArrayList<>();
            String keyword = request.getParameter("keyword");
            String searchCateID = request.getParameter("searchCateID");
            int cateID;
            if (keyword == null || keyword.equals("")) {
                keyword = "";
            }
            if (searchCateID == null || searchCateID.equals("")) {
                cateID = 0;
            } else {
                cateID = parseInt(searchCateID);
            }
            if (keyword.equals("") && cateID == 0) {
                listp = pdao.getListProduct(currentPage);
                countPage = pdao.getPageListProduct();
            } else {
                listp = pdao.getListWithSearch(keyword, cateID, currentPage);
                countPage = pdao.getTotalWithSearch(keyword, cateID);
            }
            int totalPage = countPage / 12;
            if (countPage % 12 != 0) {
                totalPage++;
            }

            url = SUCCESS;
            request.setAttribute("keyword", keyword);
            request.setAttribute("searchCateID", cateID);
            request.setAttribute("page", totalPage);
            request.setAttribute("curr", currentPage);
            request.setAttribute("listp", listp);
            request.setAttribute("listc", listc);
        } catch (Exception e) {
            e.printStackTrace();
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
