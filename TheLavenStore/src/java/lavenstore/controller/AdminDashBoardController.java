/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavenstore.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lavenstore.orders.OrderDAO;

/**
 *
 * @author Pham Hieu
 */
@WebServlet(name = "AdminDashBoardController", urlPatterns = {"/AdminDashBoardController"})
public class AdminDashBoardController extends HttpServlet {

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
    private static final String SUCCESS = "admin-dashboard.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            OrderDAO odao = new OrderDAO();
            
            int totalRevenueDaily = odao.getTotalRevenueDaily();
            int totalSoldProductDaily = odao.getTotalSoldProductDaily();
            int totalOrderDaily = odao.getTotalOrderDaily();
            int totalOrderPendingDaily = odao.getTotalOrderPenDingDaily();
            
            int totalOrderMonthly = odao.getTotalOrderMonthly();
            int totalSoldProductMonthly = odao.getTotalSoldProductMonthly();
            int totalRevenueMonthly = odao.getTotalRevenueMonthly();
            
            request.setAttribute("totalRevenueDaily", totalRevenueDaily);
            request.setAttribute("totalSoldProductDaily", totalSoldProductDaily);
            request.setAttribute("totalOrderDaily", totalOrderDaily);
            request.setAttribute("totalOrderPendingDaily", totalOrderPendingDaily);
            
            request.setAttribute("totalOrderMonthly", totalOrderMonthly);
            request.setAttribute("totalSoldProductMonthly", totalSoldProductMonthly);
            request.setAttribute("totalRevenueMonthly", totalRevenueMonthly);
            
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at AdminDashBoardController: " + e.toString());
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
