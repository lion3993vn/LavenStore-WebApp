/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavenstore.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "login.jsp";
    private static final String SUCCESS = "HomeController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            String username = request.getParameter("Username");
            String password = request.getParameter("Password");
            String remember = request.getParameter("Remember");
            UserDAO u = new UserDAO();
            UserDTO user = u.login(username, password);
            if (user == null) {
                request.setAttribute("error", "Sai tài khoản hoặc mật khẩu");
            } else {
                url = SUCCESS;
                //tao session
                HttpSession s = request.getSession();
                s.setAttribute("account", user);
                //tao cookie
                Cookie cUser = new Cookie("Username", username);
                Cookie cPass = new Cookie("Password", password);
                Cookie cRem = new Cookie("Remember", remember);
                if (remember != null) {
                    cUser.setMaxAge(60 * 60 * 24 * 365); //cookie ton tai 365 ngay
                    cPass.setMaxAge(60 * 60 * 24 * 365);
                    cRem.setMaxAge(60 * 60 * 24 * 365);
                } else {
                    cUser.setMaxAge(0);
                    cPass.setMaxAge(0);
                    cRem.setMaxAge(0);
                }
                response.addCookie(cUser);
                response.addCookie(cPass);
                response.addCookie(cRem);
            }
        } catch (Exception e) {
            log("Error at LoginController: " + e.toString());
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
