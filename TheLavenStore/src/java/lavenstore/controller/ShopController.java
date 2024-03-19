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
            //bat parameter
            String index = request.getParameter("index");
            
            if (index == null) {
                index = "1";
            }
            int countPage = 0;
            int currentPage = Integer.parseInt(index);
            
            String cid = request.getParameter("cateid");
            String search = request.getParameter("search");
            String sort = request.getParameter("sort");

            CategoryDAO dao = new CategoryDAO();
            ProductDAO pdao = new ProductDAO();
            List<ProductDTO> list = new ArrayList<>();

            //lay cate
            int cateid = 0;
            if (cid == null || cid.equals("")) {
                cateid = 0;
            } else {
                cateid = Integer.parseInt(cid);
            }

            if (search == null || search.equals("")) {
                search = null;
            }

            if (sort == null || sort.equals("")) {
                sort = null;
            }

            if (sort == null && search == null && cateid != 0) {
                list = pdao.getListByCateId(cateid, currentPage);
                countPage = pdao.getPageListByCateId(cateid);
            } else if (sort == null && search == null && cateid == 0) {
                list = pdao.getListProduct(currentPage);
                countPage = pdao.getPageListProduct();
            } else if (sort != null || search != null) {
                list = pdao.getListRequireByCateId(cateid, search, sort, currentPage);
                countPage = pdao.getPageListRequireByCateId(cateid, search, sort);
            }
            int totalPage = countPage / 12;
            if (countPage % 12 != 0) {
                totalPage++;
            }
            
            CategoryDAO cdao = new CategoryDAO();
            List<CategoryDTO> catelist = cdao.getListCategory();

            request.setAttribute("page", totalPage);
            request.setAttribute("curr", currentPage);
            request.setAttribute("search", search);
            request.setAttribute("sort", sort);
            request.setAttribute("current", cateid);
            request.setAttribute("plist", list);
            request.setAttribute("listcate", catelist);
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at ShopController: " + e.toString());
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
