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
    <link rel="stylesheet" href="./assets/css/styleforgot_password.css">
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
                                <tr>
                                    <td colspan="3">Email</td>
                                </tr>
                                <tr>
                                    <td class="w-70 py-2"><input class="w-100 ps-2" type="text"
                                            placeholder="example@gmail.com" name="email"></td>
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
                                    <td colspan="2" class="py-2"><input class="w-100" type="text" placeholder="" name="otp">
                                    </td>
                                    <td></td>
                                </tr>
                                <tr class="hidden-error">
                                    <td colspan="3">
                                        <p class="text-error m-0">OTP không đúng</p>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2" class="py-2"><input class="w-100 btn-continue p-1" type="submit"
                                            value="CONTINUE"></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td colspan="3" class="py-2">Không nhận được mail? Hãy thử lại</td>
                                </tr>
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
    <script>
        var btn_send = 0;

        function startCountdown(btn) {
            btn.classList.add('btn-fade');
            btn.onclick = null;
            btn_send++;
            let seconds = 30;
            var countdown = document.querySelector('.countdown');
            var countdown_box = document.querySelector('.countdown-box');
            countdown_box.classList.remove('hidden');
            const countdownInterval = setInterval(() => {
                seconds--;
                if (seconds >= 10) {
                    countdown.innerHTML = seconds + "s";
                } else if (seconds < 10 && seconds >= 0) {
                    countdown.innerHTML = "0" + seconds + "s";
                }
                else {
                    btn.classList.remove('btn-fade');
                    btn.onclick = function () { startCountdown(this); };
                    countdown.innerHTML = "30s";
                    countdown_box.classList.add('hidden');
                    clearInterval(countdownInterval);
                }
            }, 1000);
        }
    </script>

</body>

</html>