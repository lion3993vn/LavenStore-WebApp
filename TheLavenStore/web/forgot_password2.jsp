<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>

<head>
    <title>Laven Store - Forgot Password</title>
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
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inter">

    <!-- link icon -->
    <link rel="stylesheet" href="./assets/css/font/css/all.css">

    <!-- link login style -->
    <link rel="stylesheet" href="./assets/css/styleforgot_password2.css">
</head>

<body>
    <section class="my-5">
        <div class="container">
            <div class="row">
                <div class="col-md-12 d-flex justify-content-around">
                    <div class="w-50 px-3 py-5 box-forgot">
                        <p class="forgot-title text-center content-forgot">Reset Password</p>
                        <div class="d-flex justify-content-center">
                            <table class="w-50">
                                <form action="forgot-password-2" method="post"> 
                                <tr>
                                    <td colspan="3">Password</td>
                                </tr>
                                <tr>
                                    <td colspan="3" class="w-100 pt-2"><input class="w-100 ps-2" type="text"
                                            placeholder="" name="password"></td>
                                </tr>
                                <tr class="hidden-error">
                                    <td colspan="3">
                                        <p class="text-error m-0">${requestScope.errorPassword}</p>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3">Confirm password</td>
                                </tr>
                                <tr>
                                    <td colspan="2" class="pt-2"><input class="w-100 ps-2" type="text" placeholder="" name="password2">
                                    </td>
                                    <td></td>
                                </tr>
                                <tr class="hidden-error">
                                    <td colspan="3">
                                        <p class="text-error m-0">${requestScope.errorPassword2}</p>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2" class="py-2"><input class="w-100 btn-continue p-1" type="submit"
                                            value="CONFIRM RESET"></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td colspan="3" class="py-2">Không nhận được mail? Hãy thử lại</td>
                                </tr>
                                </form>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>


    <!--script js bootstrap-->
    <script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>
    <script src="./assets/js/bootstrap/bootstrap.min.js"></script>
    <script src="./assets/js/bootstrap/bootstrap.esm.min.js"></script>

</body>

</html>