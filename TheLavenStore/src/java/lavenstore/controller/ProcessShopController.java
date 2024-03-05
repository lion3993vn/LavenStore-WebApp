
package lavenstore.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet(name = "ProcessShopController", urlPatterns = {"/processshop"})
public class ProcessShopController extends HttpServlet {
    private static final String ERROR = "shop.jsp";
    private static final String SUCCESS = "shop.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
               String url = ERROR;
        String cid = request.getParameter("cateid");
        String search = request.getParameter("txt");
        String sort = request.getParameter("sortPrice");
        int cateid = 0;
        try {
            if (cid == null) {
                cateid = 1;
            } else {
                cateid = Integer.parseInt(cid);
            }
            CategoryDAO dao = new CategoryDAO();
            ProductDAO pdao = new ProductDAO();
            List<CategoryDTO> list = dao.getListCategory();
            List<ProductDTO> plist = pdao.getListByCateId(cateid);
            List<ProductDTO> listS = pdao.getListBySearch(search);
            List<ProductDTO> listSortPrice = pdao.listSortByPrice(cateid, sort);
            for (CategoryDTO c : list) {
                if (c.getCateID() == cateid) {
                    request.setAttribute("current", c);
                }
            }

            if (search != null) {
                request.setAttribute("plist", listS);
            }
            if (listSortPrice != null) {
                request.setAttribute("plist", listSortPrice);
            }
            request.setAttribute("list", list);
            request.setAttribute("search", search);
            request.setAttribute("plist", plist);
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
