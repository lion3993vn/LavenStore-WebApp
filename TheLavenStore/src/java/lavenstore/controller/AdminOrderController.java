/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavenstore.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lavenstore.orders.OrderDAO;
import lavenstore.orders.OrderDTO;
import lavenstore.users.UserDAO;
import lavenstore.users.UserDTO;

/**
 *
 * @author Pham Hieu
 */
@WebServlet(name = "AdminOrderController", urlPatterns = {"/AdminOrderController"})
public class AdminOrderController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "admin-order.jsp";
    private static final String SUCCESS = "admin-order.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            OrderDAO odao = new OrderDAO();
            List<OrderDTO> listODao = new ArrayList<>();
            UserDAO udao = new UserDAO();
            List<UserDTO> listUDao = udao.getAllUserTwoInfo();
            request.setAttribute("listuser", listUDao);

            String index = request.getParameter("index");
            
            if (index == null) {
                index = "1";
            }
            int countPage = 0;
            int currentPage = Integer.parseInt(index);
            String searchCode = request.getParameter("searchCode"); //""
            String statusSeach = request.getParameter("status-search"); //"NONE" //""
            if(statusSeach == null || statusSeach.equals("")){
                statusSeach = "NONE";
            }
            if ((searchCode != null && !searchCode.equals("")) || (statusSeach != null && !statusSeach.equals("NONE"))) {
                listODao = odao.searchOrder(searchCode, statusSeach, currentPage);
                countPage = odao.getNumPageWithSearchOrder(searchCode, statusSeach) / 8;
                request.setAttribute("searchCode", searchCode);
            } else {
                countPage = odao.getPageAllOrder() / 8;
                listODao = odao.getAllOrder(currentPage);
            }

            //lay so trang
            if (odao.getPageAllOrder() % 8 != 0) {
                countPage++;
            }

            request.setAttribute("page", countPage);
            request.setAttribute("curr", currentPage);
            request.setAttribute("statusSeach", statusSeach);
            request.setAttribute("listorder", listODao);
        } catch (Exception e) {
            log("Error at AdminOrderController: " + e.toString());
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
