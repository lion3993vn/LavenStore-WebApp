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
import static jdk.nashorn.tools.Shell.SUCCESS;
import lavenstore.email.Email;
import lavenstore.users.UserDAO;
import lavenstore.users.UserDTO;

/**
 *
 * @author huyhu
 */
@WebServlet(name = "ProcessAdminUserController", urlPatterns = {"/ProcessAdminUserController"})
public class ProcessAdminUserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "AdminUserController";
    private static final String SUCCESS = "AdminUserController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String url = ERROR;
            String action = (String) request.getParameter("admin-action");
            if (action.equals("add-user")) {
                boolean isValidUser = true;
                String username = request.getParameter("Username");
                UserDAO u = new UserDAO();
                UserDTO user = null;
                String regexUsername = "^[a-zA-Z0-9_]{3,20}$";
                Pattern patternUsername = Pattern.compile(regexUsername);
                Matcher matcherUsername = patternUsername.matcher(username);
                if (!matcherUsername.matches()) {
                    request.setAttribute("errorUsername", "Username không hợp lệ");
                    isValidUser = false;
                } else {
                    user = u.getUserByUsername(username);
                    if (user != null) {
                        request.setAttribute("errorUsername", "Tài khoản đã tồn tại");
                        isValidUser = false;
                    }
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

                //email
                String email = request.getParameter("email");
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
                }
                if (isValidUser) {
                    UserDTO newUser = new UserDTO(u.getAllUser().size() + 1, username, password, email, null, null, null, null);
                    u.insert(newUser);
                    url = SUCCESS;
                } 
            }
        } catch (Exception e) {
            log("Error at ProfileUserController: " + e.toString());
            request.setAttribute("MESSAGE", "Somethings are error...");
        } finally {
            request.getRequestDispatcher("profile_address.jsp").forward(request, response);
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
