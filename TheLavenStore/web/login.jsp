<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>

    <head>
        <title>Laven Store - Login</title>
        <meta charset="UTF-8">
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
        <link rel="stylesheet" href="./assets/css/stylelogin.css">
    </head>

    <body>
    <c:set var="cookie" value="${pageContext.request.cookies}"/>
    <div class="container p-5">
        <div class="row">
            <div class="col-md-12 text-center nav-login">
                <p><a href="#">Home</a><span><i class="px-4 fa-solid fa-chevron-right"></i><a href="#">Account</a>
            </div>
        </div>
    </div>
    <div class="body-login container-fluid mb-4">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <form action="login" method="post">
                        <div class="d-flex justify-content-center">
                            <table class="w-50">
                                <tr>
                                    <td colspan="2"><p class="noticia-text title-login">Đăng nhập</p></td>
                                </tr>
                                <tr>
                                    <td colspan="2" class="input-login py-2"><input type="text" placeholder="Username" name="Username" value="${cookie.Username.value}" required></td>
                                </tr>
                                <tr>
                                    <td colspan="2" class="input-login py-2"><input type="password" placeholder="Password" name="Password" value="${cookie.Password.value}" required></td>
                                </tr>
                                <tr>
                                    <td colspan="2"><p class="noticia-text text-error">${error}</p></td>
                                </tr>
                                <tr>
                                    <td class="remember-login"><input type="checkbox" name="Remember" ${cookie.Remember !=null ? 'checked' : ''}><p>Remember me</p></td>
                                    <td class="forgot-pass"><a href="MainController?action=forgot-password" class="float-end">Quên mật khẩu ?</a></td>
                                </tr>
                                <tr>
                                    <td colspan="2" class="login-btn py-2"><input type="submit" class="p-2" value="Đăng nhập"></td>
                                </tr>
                                <tr>
                                    <td colspan="2" class="py-4 px-5"><div class="hr-login"></div></td>
                                </tr>
                            </table>
                        </div>
                        <div class="d-flex justify-content-center">
                            <div class="login-google text-center">
                                <img src="./assets/img/logogoogle.png" class="" alt=""> 
                                <p class="mb-0">Đăng nhập với Google</p>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-6">
                    <p class="text-center noticia-text title-login title-dangki">Đăng ký</p>
                    <div class="d-flex justify-content-center content-reg">
                        <p class="w-50 text-center noticia-text">Bạn là người dùng mới, hãy đăng kí ngay để có thể nhận những ưu đãi từ chúng tôi</p>
                    </div>
                    <div class="d-flex justify-content-center btn-reg">
                        <a href="MainController?action=register" class="text-center">Đăng ký</a>
                    </div>
                </div>
            </div>
        </div>
    </div>



    <!--script js bootstrap-->
    <script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>
    <script src="./assets/js/bootstrap/bootstrap.min.js"></script>
    <script src="./assets/js/bootstrap/bootstrap.esm.min.js"></script>
</body>

</html>