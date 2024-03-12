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
import lavenstore.products.CategoryDAO;
import lavenstore.products.CategoryDTO;
import lavenstore.products.ProductDAO;
import lavenstore.products.ProductDTO;

@WebServlet(name = "ShopController", urlPatterns = {"/ShopController"})
public class ShopController extends HttpServlet {

    private static final String ERROR = "shop.jsp";
    private static final String SUCCESS = "shop.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            
            String sortPrice = request.getParameter("sortPrice");
            String search = request.getParameter("search");
            String cateId = request.getParameter("cateid");
            
            if (cateId == null) {
                cateId = "0";
            }
            
            ProductDAO pdao = new ProductDAO();
            List<ProductDTO> list = new ArrayList<>();
            int cate = Integer.parseInt(cateId);
            if (cate == 0) {
                list = pdao.getListProduct();
            } else {
                list = pdao.getListByCateId(cate);
            }

            CategoryDAO cdao = new CategoryDAO();
            List<CategoryDTO> catelist = cdao.getListCategory();

            request.setAttribute("current", cate);
            request.setAttribute("plist", list);
            request.setAttribute("listcate", catelist);
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at HomeController: " + e.toString());
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
