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
import javax.servlet.http.HttpSession;
import lavenstore.users.UserDAO;
import lavenstore.users.UserDTO;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author huyhu
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "register.jsp";
    private static final String SUCCESS = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        boolean isValidUser = true;
        try {
            String username = request.getParameter("Username");
            UserDAO u = new UserDAO();
            UserDTO user = u.getUserByUsername(username);
            if (user != null) {
                request.setAttribute("errorUsername", "Tài khoản đã tồn tại");
                isValidUser = false;
            }
            //email
            String email = request.getParameter("Email");
            String regexEmail = "^[A-Za-z0-9+_.-]+@(.+)$";
            Pattern patternEmail = Pattern.compile(regexEmail);
            Matcher matcherEmail = patternEmail.matcher(email);
            if (!matcherEmail.matches()) {
                request.setAttribute("errorEmail", "Email không hợp lệ");
                isValidUser = false;

            } else {
                user = u.getUserByEmail(email);
                if (user != null) {
                    request.setAttribute("errorEmail", "Email đã tồn tại");
                    isValidUser = false;
                }

                //password
                String password = request.getParameter("Password");
                String regexPassword = "^[A-Za-z0-9]{4,}$";
                Pattern patternPassword = Pattern.compile(regexPassword);
                Matcher matcherPassword = patternPassword.matcher(password);
                if (!matcherPassword.matches()) {
                    request.setAttribute("errorPassword", "Password phải có từ 4 kí tự trở lên");
                    isValidUser = false;
                }
                //confirm password
                String password2 = request.getParameter("Password2");
                if (!password.equals(password2)) {
                    request.setAttribute("errorPassword2", "Mật khẩu không trùng khớp");
                    isValidUser = false;
                }
                if (isValidUser) {
                    UserDTO newUser = new UserDTO(u.getAllUser().size() + 1, username, password, email, null, null, null, null);
                    u.insert(newUser);
                    url = SUCCESS;
                }
            }
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
