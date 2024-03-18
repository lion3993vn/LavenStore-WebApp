<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>

    <head>
        <title>Forgot Password | The LAVEN STORE</title>
        <link rel="icon" type="image/x-icon" href="assets/img/logo.png">
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
        <link rel="stylesheet" href="./assets/css/styleforgot_password.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    </head>

    <body>
        <c:import url="header.jsp"></c:import>
            <section class="my-5">
                <div class="container" style="margin-top: 15em; margin-bottom: 15em">
                    <div class="row">
                        <div class="col-md-12 d-flex justify-content-around">
                            <div class="w-50 px-3 py-5 box-forgot">
                                <p class="forgot-title text-center content-forgot">Reset Password</p>
                                <div class="d-flex justify-content-center">

                                    <table class="w-50">
                                        <form action="verify-otp" class="w-100" method="post">
                                            <tr>
                                                <td colspan="3">Email</td>
                                            </tr>
                                            <tr>
                                                <td class="w-70 py-2"><input class="w-100 ps-2" id="email" type="text"
                                                                             placeholder="example@gmail.com" name="Email" required></td>
                                                <td class="w-20">
                                                    <div class="text-center btn-send w-75 float-end" onclick="startCountdown(this)">
                                                        <i class="fa-solid fa-paper-plane"></i>
                                                    </div>
                                                </td>
                                                <td class="w-10">
                                                    <div class="w-100 countdown-box hidden">
                                                        <p class="countdown m-0 float-end">30s</p>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="3">OTP Code (send via Email)</td>
                                            </tr>
                                            <tr>
                                                <td colspan="2" class="py-2"><input class="w-100 ps-2" type="text" placeholder="" name="txtOTP">
                                                </td>
                                                <td></td>
                                            </tr>
                                            <tr class="hidden-error">
                                                <td colspan="3">
                                                    <p class="text-error m-0">${requestScope.message}</p>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" class="py-2"><input class="w-100 btn-continue p-1" type="submit"
                                                                                value="CONTINUE"></td>
                                            <td></td>
                                        </tr>
                                        <!--                                <tr>
                                                                            <td colspan="3" class="py-2">Không nhận được mail? Hãy thử lại</td>
                                                                        </tr>-->
                                        <tr>
                                            <td colspan="3" class="py-2"><span id="notice" ></span></td>
                                        </tr>
                                    </form>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <c:import url="footer.jsp"></c:import>
        <!--script js bootstrap-->
        <script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="./assets/js/bootstrap/bootstrap.min.js"></script>
        <script src="./assets/js/bootstrap/bootstrap.esm.min.js"></script>
        <script>
                                                var btn_send = 0;

                                                function startCountdown(btn) {
                                                    var notice = document.getElementById("notice");
                                                    var Email = document.getElementById("email").value;
                                                    if (Email === "") {
                                                        alert("Bạn chưa nhập email!");
                                                    } else {
                                                        btn.classList.add('btn-fade');
                                                        btn.onclick = null;
                                                        btn_send++;
                                                        let seconds = 30;
                                                        var countdown = document.querySelector('.countdown');
                                                        var countdown_box = document.querySelector('.countdown-box');
                                                        countdown_box.classList.remove('hidden');
                                                        $.ajax({
                                                            url: "forgot-password",
                                                            type: "post", //send it through post method
                                                            data: {
                                                                txtEmail: Email
                                                            },
                                                            success: function (response) {
                                                                console.log("response ", response);
                                                                notice.innerHTML = response;
                                                            },
                                                            error: function (xhr) {
                                                                //Do Something to handle error
                                                                alert("Có lỗi xảy ra, vui lòng thử lại sau ít phút");
                                                            }
                                                        });
                                                        const countdownInterval = setInterval(() => {
                                                            seconds--;
                                                            if (seconds >= 10) {
                                                                countdown.innerHTML = seconds + "s";
                                                            } else if (seconds < 10 && seconds >= 0) {
                                                                countdown.innerHTML = "0" + seconds + "s";
                                                            } else {
                                                                btn.classList.remove('btn-fade');
                                                                btn.onclick = function () {
                                                                    startCountdown(this);
                                                                };
                                                                countdown.innerHTML = "30s";
                                                                countdown_box.classList.add('hidden');
                                                                clearInterval(countdownInterval);
                                                            }
                                                        }, 1000);
                                                    }
                                                }
        </script>
    </body>

</html>