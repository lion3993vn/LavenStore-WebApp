/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavenstore.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lavenstore.email.Email;
import lavenstore.users.UserDAO;
import lavenstore.users.UserDTO;

/**
 *
 * @author huyhu
 */
@WebServlet(name = "ForgotPasswordController", urlPatterns = {"/forgot-password"})
public class ForgotPasswordController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String OTP_SUBJECT = "[TheLavenStore] Password reset";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            UserDAO u = new UserDAO();
            String email = request.getParameter("txtEmail");
            UserDTO user = u.getUserByEmail(email);
            if (user != null) {
                String otp = new DecimalFormat("000000").format(new Random().nextInt(999999));
                Email eUtil = new Email();
                String OTP_CONTENT = emailContent(user.getFullName(), otp);
                eUtil.sendEmail(email, OTP_SUBJECT, OTP_CONTENT);
                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(2*60);
                session.setAttribute("OTP", otp);
                out.print("Đã gửi email xác thực thành công!");
            } else {
                out.print("Tài khoản không tồn tại");
            }
        } catch (Exception e) {
            log("Error at ForgotPasswordController " + e.toString());
            out.print("Có lỗi xảy ra, vui lòng thử lại sau ít phút");
        } finally {
//            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private static String emailContent(String fullName, String txtOTP) {
        if (fullName == null){
            fullName = "User";
        }
        return "<div style=\"background-color:#f8f8f8;font-family:sans-serif;padding:15px\">\n"
                + "    <div style=\"max-width:1000px;margin:auto\">\n"
                + "        <div class=\"adM\">\n"
                + "            <div style=\"background-color:#fff;padding:5px 20px;color:#000;border-radius:0px 0px 2px 2px\">\n"
                + "                <div style=\"padding:35px 15px\">\n"
                + "                    <p style=\"margin:0;font-size:16px\">\n"
                + "                        <b>Xin chào  , " + fullName + "</b>\n"
                + "                    </p>\n"
                + "                    <br>\n"
                + "                    <p style=\"margin:0;font-size:16px\">\n"
                + "                        Bạn vừa nhận được mã\n"
                + "                        <span class=\"il\">OTP</span>\n"
                + "                        xác nhận tại\n"
                + "                        <a style=\"text-decoration:none\" href=\"http://localhost:8080/thelavenstore/\" target=\"_blank\">theLavenStore.vn</a>\n"
                + "                    </p>\n"
                + "                    <div style=\"padding:40px;margin:auto;text-align:center\">\n"
                + "                        <div style=\"width:fit-content;border:#3cc892 thin solid;color:#3cc892;font-weight:bold;text-align:center;padding:7px 12px;border-radius:2px;margin:auto;font-size:large\">" + txtOTP + "</div>\n"
                + "                    </div>\n"
                + "                    <div style=\"border-top:1px solid #dcdbdb\"></div>\n"
                + "                    <br>\n"
                + "                    <p style=\"margin:0;font-size:16px\">\n"
                + "                        Nếu bạn không thực hiện yêu cầu này, xin vui lòng bỏ qua nó hoặc nếu cần hỗ trợ, liên hệ với chúng tôi\n"
                + "                        <a style=\"text-decoration:none\" href=\"http://localhost:8080/thelavenstore/\" target=\"_blank\">ngay</a>\n"
                + "                        .\n"
                + "                    </p>\n"
                + "                    <br>\n"
                + "                    <p style=\"margin:0;font-size:16px\">Trân trọng,</p>\n"
                + "                    <p style=\"margin:0;font-size:16px\">The Laven Store Team.</p>\n"
                + "                </div>\n"
                + "            </div>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "</div>";
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
