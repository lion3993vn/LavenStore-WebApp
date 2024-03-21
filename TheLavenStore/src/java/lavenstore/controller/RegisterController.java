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
import lavenstore.email.Email;

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
    private static final String REGISTER_SUBJECT = "[TheLavenStore] Welcome to The Laven Store";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        boolean isValidUser = true;
        try {
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
                }}

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
                    UserDTO newUser = new UserDTO(u.getAllUser().size() + 1, username, password, email, "user", null, null, null);
                    u.insert(newUser);
                    Email eUtil = new Email();
                    String REGISTER_CONTENT = emailContent(newUser.getUserName());
                    eUtil.sendEmail(email, REGISTER_SUBJECT,REGISTER_CONTENT);
                    url = SUCCESS;
                }
        } catch (Exception e) {
            log("Error at RegisterController: " + e.toString());
            request.setAttribute("MESSAGE", "Somethings are error...");
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private static String emailContent(String username) {
        return "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "    <head>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "        <title>Chào mừng đến với Flower Shop</title>\n"
                + "        <style>\n"
                + "        body {\n"
                + "            font-family: Arial, sans-serif;\n"
                + "            background-color: #f4f4f4;\n"
                + "            margin: 0;\n"
                + "            padding: 0;\n"
                + "        }\n"
                + "\n"
                + "        .container {\n"
                + "            max-width: 600px;\n"
                + "            margin: 20px auto;\n"
                + "            background-color: #fff;\n"
                + "            padding: 20px;\n"
                + "            border-radius: 8px;\n"
                + "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n"
                + "        }\n"
                + "\n"
                + "        h1 {\n"
                + "            color: #4CAF50;\n"
                + "        }\n"
                + "\n"
                + "        p {\n"
                + "            color: #333;\n"
                + "        }\n"
                + "\n"
                + "        .button {\n"
                + "            display: inline-block;\n"
                + "            padding: 10px 20px;\n"
                + "            background-color: #4CAF50;\n"
                + "            color: #fff;\n"
                + "            text-decoration: none;\n"
                + "            border-radius: 4px;\n"
                + "        }\n"
                + "        </style>\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <div class=\"container\">\n"
                + "            <h1>Chào mừng bạn đến với The Laven Store!</h1>\n"
                + "            <p>Xin chào " + username + ",</p>\n"
                + "            <p>Cảm ơn bạn đã đăng ký tài khoản tại The Laven Store. Chúng tôi rất vui mừng được chào đón bạn vào cộng đồng của chúng tôi.</p>\n"
                + "            <p>The Laven Store là địa chỉ tốt nhất để tìm kiếm và mua sắm những bông hoa tuyệt vời nhất. Hãy khám phá các bộ sưu tập của chúng tôi và tìm kiếm những sản phẩm hoa độc đáo để làm cho mỗi dịp trở nên đặc biệt.</p>\n"
                + "            <p>Cảm ơn bạn đã lựa chọn The Laven Store!</p>\n"
                + "            <p>Trân trọng,\n"
                + "                <br>Đội ngũ The Laven Store\n"
                + "            </p>\n"
                + "            <p>\n"
                + "                <a href=\"http://localhost:8080/thelavenstore\" class=\"button\">Khám phá ngay</a>\n"
                + "            </p>\n"
                + "        </div>\n"
                + "    </body>\n"
                + "</html>";
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
