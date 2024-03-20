/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavenstore.controller;

import java.io.IOException;
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
@WebServlet(name = "ProfilePasswordController", urlPatterns = {"/profile-password"})
public class ProfilePasswordController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "profile_password.jsp";
    private static final String SUCCESS = "MainController?action=logout"; //sua thanh profile controller

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession(false);
            UserDTO user = (UserDTO) session.getAttribute("account");
                if (user != null) {
                    boolean isValidPassword = true;
                    String oldPassword = request.getParameter("oldPassword");
                    String newPassword1 = request.getParameter("newPassword1");
                    String newPassword2 = request.getParameter("newPassword2");
                    UserDAO u = new UserDAO();
                    //old pasword
                    String onDBPassword = user.getPassword();
                    if (!oldPassword.equals(onDBPassword)) {
                        request.setAttribute("errorOldPassword", "Mật khẩu không đúng!");
                        isValidPassword = false;
                    }
                    // new password
                    String regexPassword = "^[A-Za-z0-9]{4,}$";
                    Pattern patternPassword = Pattern.compile(regexPassword);
                    Matcher matcherPassword = patternPassword.matcher(newPassword1);
                    if (!matcherPassword.matches()) {
                        request.setAttribute("errorNewPassword1", "Password phải có từ 4 kí tự trở lên");
                        isValidPassword = false;
                    }
                    //confirm password
                    if (!newPassword1.equals(newPassword2)) {
                        request.setAttribute("errorNewPassword2", "Mật khẩu không trùng khớp");
                        isValidPassword = false;
                    }
                    if (isValidPassword) {
                        user.setPassword(newPassword1);
                        u.updatePassword(user);
                        url = SUCCESS;
                    } else url = "profile_password.jsp";
            }
            
            
        } catch (Exception e) {
            log("Error at ProfilePasswordController: " + e.toString());
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
