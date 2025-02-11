<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>

    <head>
        <title>Register | The LAVEN STORE</title>
        <meta charset="UTF-8">
        <link rel="icon" type="image/x-icon" href="assets/img/logo.png">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- link bootstrap -->
        <link rel="stylesheet" href="./assets/css/bootstrap/bootstrap.css">

        <!-- link google font -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noticia+Text">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Volkhov">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Averia+Serif+Libre">
        <!-- link icon -->
        <link rel="stylesheet" href="./assets/css/font/css/all.css">

        <!-- link login style -->
        <link rel="stylesheet" href="./assets/css/styleregister.css">
    </head>

    <body>
        <c:import url="header.jsp"></c:import>
            <!-- navbar -->
            <div class="container p-5" style="margin-top: 3.5em">
                <div class="row">
                    <div class="col-md-12 text-center nav-login">
                        <p><a href="MainController">Home</a><span><i class="px-4 fa-solid fa-chevron-right"></i><a href="#">Create
                                    Account</a>
                    </div>
                </div>
            </div>


            <!-- body -->
            <div class="body-register container-fluid mb-5 py-5">
                <div class="container">
                    <div class="row">
                        <div class="col-md-6">
                            <form action="register" method="post" id="register-form">
                                <div class="d-flex justify-content-center">
                                    <table class="table-reg">
                                        <tr>
                                            <td colspan="2"><p class="noticia-text title-login">Đăng ký</p></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" class="input-login py-2"><input type="text" placeholder="Username" name="Username" required></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2"><p class="noticia-text text-error mb-0">${requestScope.errorUsername}</p></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" class="input-login py-2"><input type="text" placeholder="Email" name="Email" required></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"><p class="noticia-text text-error mb-0">${requestScope.errorEmail}</p></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" class="input-login py-2"><input type="password" placeholder="Password" name="Password" required></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"><p class="noticia-text text-error mb-0">${requestScope.errorPassword}</p></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" class="input-login py-2"><input type="password" placeholder="Confirm Password" name="Password2" required></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"><p class="noticia-text text-error mb-0">${requestScope.errorPassword2}</p></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" class="captcha-login d-flex flex-column align-items-center justify-content-center" >
                                            <div class="recaptcha" >
                                                <div class="captcha-content py-3 text-center g-recaptcha w-100" style="border: none"s data-sitekey="6Lft3YgpAAAAAHtCCJwG4kV6ZOIuIDXv1wulOFf6"></div>
                                            </div>
                                            <div id="captcha-error" style="color: red; font-size: 15px;">
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" class="reg-btn py-2"><input type="submit" class="p-2" value="Đăng ký"></td>
                                    </tr>
                                </table>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-6">
                        <p class="text-center noticia-text title-login title-dangki">Đăng nhập</p>
                        <div class="d-flex justify-content-center content-reg">
                            <p class="w-50 text-center noticia-text">Bạn đã có tài khoản, đăng nhập ngay!</p>
                        </div>
                        <div class="d-flex justify-content-center btn-reg">
                            <a href="MainController?action=login" class="text-center">Đăng nhập</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <c:import url="footer.jsp"></c:import>
        <!--script js bootstrap-->
        <script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="./assets/js/bootstrap/bootstrap.min.js"></script>
        <script src="./assets/js/bootstrap/bootstrap.esm.min.js"></script>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <script>
            window.onload = function () {
                let isValid = false;
                const form = document.getElementById("register-form");
                const error = document.getElementById("captcha-error");

                form.addEventListener("submit", function (event) {
                    event.preventDefault();
                    const response = grecaptcha.getResponse();
                    console.log(response);
                    if (response) {
                        form.submit();
                    } else {
                        error.innerHTML = "Lỗi Captcha, vui lòng thử lại!";
                    }
                })
            }
        </script>
    </body>
</html>