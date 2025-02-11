/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavenstore.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
@WebServlet(name = "ProfileUpdateController", urlPatterns = {"/ProfileUpdateController"})
public class ProfileUpdateController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "MainController?action=login";
    private static final String SUCCESS = "profile.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            //process
            HttpSession session = request.getSession(false);
            UserDTO user = (UserDTO) session.getAttribute("account");
            if (user != null) {
                UserDAO u = new UserDAO();
                boolean isValidPhone = false;
                String fullname = request.getParameter("fullname");
                String phone = request.getParameter("phone");
                String regex = "^1?(\\d{10})$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(phone);
                if (phone.isEmpty()) {
                    phone = null;
                }
                if (matcher.matches() || phone == null) {
                    isValidPhone = true;
                } else {
                    request.setAttribute("tt", "Số điện thoại không hợp lệ!");
                }
                if (isValidPhone) {
                    url = SUCCESS;
                    user.setPhoneNumber(phone);
                    request.setAttribute("tt", "Đã cập nhật thông tin thành công!");
                }
                user.setFullName(fullname);
                u.update(user);
            }

        } catch (Exception e) {
            log("Error at ProfileUpdateController: " + e.toString());
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
