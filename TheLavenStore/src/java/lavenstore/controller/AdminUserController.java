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
import javax.servlet.http.HttpSession;
import lavenstore.users.UserDAO;
import lavenstore.users.UserDTO;

/**
 *
 * @author huyhu
 */
@WebServlet(name = "AdminUserController", urlPatterns = {"/AdminUserController"})
public class AdminUserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "MainController?action=";
    private static final String SUCCESS = "admin-user.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession(false);
            UserDTO user = (UserDTO) session.getAttribute("account");
            if(user != null && user.getRole().equals("admin")){
                String index = request.getParameter("index");
                if (index == null) {
                    index = "1";
                }
                int countPage = 0;
                int currentPage = parseInt(index);
                UserDAO dao = new UserDAO();

                List<UserDTO> userList = new ArrayList();
                String searchUser = request.getParameter("searchUser");
                if (searchUser == null || searchUser.equals("")) {
                    countPage = dao.getPageNumber();

                    userList = dao.getPagingUser(currentPage);
                } else {
                    countPage = dao.getPageNumberWithSearch(searchUser);
                    userList = dao.getUserByKeywordPaging(searchUser, currentPage);

                }
                int totalPage = countPage / 8;
                if (countPage % 8 != 0) {
                    totalPage++;
                }
                request.setAttribute("searchUser", searchUser);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("currentPage", currentPage);
                request.setAttribute("userList", userList);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at AdminUserController: " + e.toString());
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
