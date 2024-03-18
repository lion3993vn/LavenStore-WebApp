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
import lavenstore.orders.OrderDAO;
import lavenstore.orders.OrderDTO;
import lavenstore.orders.OrderDetailsDAO;
import lavenstore.orders.OrderDetailsDTO;
import lavenstore.products.ProductDAO;
import lavenstore.products.ProductDTO;
import lavenstore.users.UserDTO;

/**
 *
 * @author Pham Hieu
 */
@WebServlet(name = "ProfilePurchaseController", urlPatterns = {"/ProfilePurchaseController"})
public class ProfilePurchaseController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "home.html";
    private static final String SUCCESS = "home.html";

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
            
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("account");
            
            ProductDAO pdao = new ProductDAO();
            
            OrderDAO odao = new OrderDAO();
            List<OrderDTO> listOrder = odao.getOrderByUserID(user.getID(), currentPage);
            OrderDetailsDAO odedao = new OrderDetailsDAO();
            for (OrderDTO o : listOrder) {
                List<OrderDetailsDTO> listDetails = odedao.getOrderDetailsByID(o.getID());
                request.setAttribute("details"+o.getID(), listDetails);
                int totalMoney = 25000;
                List<ProductDTO> listpro = new ArrayList<>();
                for (OrderDetailsDTO i : listDetails) {
                    listpro.add(pdao.getProductByID(i.getProductID()));
                    totalMoney += i.getPrice();
                }
                request.setAttribute("listpro"+o.getID(), listpro);
                request.setAttribute("totalMoney"+o.getID(), totalMoney);
            }
            
            countPage = odao.getAllOrderByUserID(user.getID()) /3;
            
            if (odao.getAllOrderByUserID(user.getID()) % 3 != 0) {
                countPage++;
            }
            
            request.setAttribute("page", countPage);
            request.setAttribute("curr", currentPage);
            request.setAttribute("listorder", listOrder);
            url = "profile_purchase.jsp";
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
