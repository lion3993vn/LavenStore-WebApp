/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavenstore.controller;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lavenstore.users.UserDAO;
import lavenstore.users.UserDTO;

/**
 *
 * @author huyhu
 */
@WebServlet(name = "AjaxAdminUserController", urlPatterns = {"/AjaxAdminUserController"})
public class AjaxAdminUserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String ajaxAdminAction = request.getParameter("ajaxAdminAction");
        request.setCharacterEncoding("UTF-8");
        try {
            if (ajaxAdminAction.equals("add-user")) {
                String txtUsername = request.getParameter("txtUsername");
                String txtEmail = request.getParameter("txtEmail");
                String txtPassword = request.getParameter("txtPassword");
                String txtPhoneNumber = request.getParameter("txtPhoneNumber");
                String errorUsername = "", errorEmail = "", errorPassword = "", errorPhone = "";
                UserDAO u = new UserDAO();
                UserDTO user = null;
                String regexUsername = "^[a-zA-Z0-9_]{3,20}$";
                Pattern patternUsername = Pattern.compile(regexUsername);
                Matcher matcherUsername = patternUsername.matcher(txtUsername);
                if (!matcherUsername.matches()) {
                    errorUsername = "Username không hợp lệ";
                } else {
                    user = u.getUserByUsername(txtUsername);
                    if (user != null) {
                        errorUsername = "Tài khoản đã tồn tại";
                    }
                }

                //password
                String regexPassword = "^[A-Za-z0-9]{4,}$";
                Pattern patternPassword = Pattern.compile(regexPassword);
                Matcher matcherPassword = patternPassword.matcher(txtPassword);
                if (!matcherPassword.matches()) {
                    errorPassword = "Password phải có từ 4 kí tự trở lên";
                }

                //email
                String regexEmail = "^[A-Za-z0-9+_.-]+@(.+)$";
                Pattern patternEmail = Pattern.compile(regexEmail);
                Matcher matcherEmail = patternEmail.matcher(txtEmail);
                if (!matcherEmail.matches()) {
                    errorEmail = "Email không hợp lệ";
                } else {
                    user = u.getUserByEmail(txtEmail);
                    if (user != null) {
                        errorEmail = "Email đã tồn tại";
                    }
                }
                String regex = "^1?(\\d{10})$";
                Pattern patternPhone = Pattern.compile(regex);
                Matcher matcherPhone = patternPhone.matcher(txtPhoneNumber);
                if (txtPhoneNumber.isEmpty()) {
                    txtPhoneNumber = null;
                }
                if (!matcherPhone.matches() && txtPhoneNumber != null) {
                    errorPhone = "Số điện thoại không hợp lệ!";
                }
                JsonObject json = new JsonObject();
                json.addProperty("errorUsername", errorUsername);
                json.addProperty("errorPassword", errorPassword);
                json.addProperty("errorEmail", errorEmail);
                json.addProperty("errorPhone", errorPhone);
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print(json.toString());
                out.flush();
            } else if (ajaxAdminAction.equals("edit-user")) {
                int txtUserID = parseInt(request.getParameter("txtUserID"));
                UserDAO dao = new UserDAO();
                UserDTO user = dao.getUserByUserID(txtUserID);
                JsonObject json = new JsonObject();
                json.addProperty("username", user.getUserName());
                json.addProperty("password", user.getPassword());
                json.addProperty("email", user.getEmail());
                json.addProperty("phoneNumber", user.getPhoneNumber());
                json.addProperty("role", user.getRole());
                json.addProperty("address", user.getAddress());
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print(json.toString());
                out.flush();
            } else if (ajaxAdminAction.equals("check-edit")) {
                int userID = parseInt(request.getParameter("userID"));
                String txtPassword = request.getParameter("txtPassword");
                String txtEmail = request.getParameter("txtEmail");
                String txtPhoneNumber = request.getParameter("txtPhoneNumber");
                String errorEmail = "", errorPassword = "", errorPhone = "";
                UserDAO u = new UserDAO();
                UserDTO editingUser = u.getUserByUserID(userID);
                //password
                String regexPassword = "^[A-Za-z0-9]{4,}$";
                Pattern patternPassword = Pattern.compile(regexPassword);
                Matcher matcherPassword = patternPassword.matcher(txtPassword);
                if (!matcherPassword.matches()) {
                    errorPassword = "Password phải có từ 4 kí tự trở lên";
                }
                //email
                String regexEmail = "^[A-Za-z0-9+_.-]+@(.+)$";
                Pattern patternEmail = Pattern.compile(regexEmail);
                Matcher matcherEmail = patternEmail.matcher(txtEmail);
                if (!matcherEmail.matches()) {
                    errorEmail = "Email không hợp lệ";
                } else {
                    //check if user exist
                    UserDTO user = u.getUserByEmail(txtEmail);
                    if (user != null && user.getID() != editingUser.getID()) {
                        errorEmail = "Email đã tồn tại";
                    }
                }
                //phone
                String regex = "^1?(\\d{10})$";
                Pattern patternPhone = Pattern.compile(regex);
                Matcher matcherPhone = patternPhone.matcher(txtPhoneNumber);
                if (txtPhoneNumber.isEmpty()) {
                    txtPhoneNumber = null;
                }
                if (!matcherPhone.matches() && txtPhoneNumber != null) {
                    errorPhone = "Số điện thoại không hợp lệ!";
                }
                JsonObject json = new JsonObject();
                json.addProperty("errorPassword", errorPassword);
                json.addProperty("errorEmail", errorEmail);
                json.addProperty("errorPhone", errorPhone);
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print(json.toString());
                out.flush();
            }
        } catch (Exception e) {
            log("Error at AjaxAdminUserController: " + e.toString());
            request.setAttribute("MESSAGE", "Somethings are error...");
        } finally {
//            request.getRequestDispatcher("profile_address.jsp").forward(request, response);
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
