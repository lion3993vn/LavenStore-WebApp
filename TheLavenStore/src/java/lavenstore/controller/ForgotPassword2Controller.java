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
import static org.apache.tomcat.jni.User.username;

/**
 *
 * @author huyhu
 */
@WebServlet(name = "ForgotPassword2Controller", urlPatterns = {"/forgot-password-2"})
public class ForgotPassword2Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "forgot_password2.jsp";
    private static final String SUCCESS = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("Email");
        boolean isValidPassword = true;
        try {
            UserDAO u = new UserDAO();
            UserDTO user = u.getUserByEmail(email);
            String password = request.getParameter("password");
            String regexPassword = "^[A-Za-z0-9]{4,}$";
            Pattern patternPassword = Pattern.compile(regexPassword);
            Matcher matcherPassword = patternPassword.matcher(password);
            if (!matcherPassword.matches()) {
                request.setAttribute("errorPassword", "Password phải có từ 4 kí tự trở lên");
                isValidPassword = false;
            }
            //confirm password
            String password2 = request.getParameter("password2");
            if (!password.equals(password2)) {
                request.setAttribute("errorPassword2", "Mật khẩu không trùng khớp");
                isValidPassword = false;
            }
            if (isValidPassword) {
                user.setPassword(password);
                u.updatePassword(user);
                session.removeAttribute("Email");
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at ForgotPassword2Controller: " + e.toString());
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
