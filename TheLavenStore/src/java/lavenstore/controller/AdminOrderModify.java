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
import lavenstore.orders.OrderDAO;
import lavenstore.orders.OrderDTO;
import lavenstore.orders.OrderDetailsDAO;
import lavenstore.orders.OrderDetailsDTO;
import lavenstore.products.ProductDAO;
import lavenstore.products.ProductDTO;
import lavenstore.users.UserDAO;
import lavenstore.users.UserDTO;

/**
 *
 * @author Pham Hieu
 */
@WebServlet(name = "AdminOrderModify", urlPatterns = {"/AdminOrderModify"})
public class AdminOrderModify extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "admin-order-modify.jsp";
    private static final String SUCCESS = "admin-order-modify.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String oid = request.getParameter("orderID"); //lay id
            
            //lay order
            OrderDAO oDao = new OrderDAO();
            OrderDTO order = oDao.getOneOrder(Integer.parseInt(oid));
            
            //lay ten user
            UserDAO uDao = new UserDAO();
            UserDTO user = uDao.getOneUserTwoInfo(order.getUserID());
            
            //lay list order details
            OrderDetailsDAO deDao = new OrderDetailsDAO();
            List<OrderDetailsDTO> listOrderDetails = deDao.getOrderDetailsByID(Integer.parseInt(oid));
            
            //lay product
            int totalQuantity = 0;
            ProductDAO pDao = new ProductDAO();
            List<ProductDTO> listProductOrder = new ArrayList<>();
            for (OrderDetailsDTO o : listOrderDetails) {
                listProductOrder.add(pDao.getProductByID(o.getProductID()));
                totalQuantity += o.getQuantity();
            }
            
            request.setAttribute("order", order);
            request.setAttribute("user", user);
            request.setAttribute("totalQuantity", totalQuantity);
            request.setAttribute("listOrderDetails", listOrderDetails);
            request.setAttribute("listproduct", listProductOrder);
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
