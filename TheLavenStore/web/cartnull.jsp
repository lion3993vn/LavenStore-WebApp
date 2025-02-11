<%-- 
    Document   : cartnull
    Created on : Feb 29, 2024, 5:39:07 PM
    Author     : Pham Hieu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>

<head>
    <title>TODO supply a title</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./assets/css/bootstrap/bootstrap.css">

    <!-- link font google -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Vidaloka">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Myanmar Sans Pro">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Dongle">

    <!-- link css cart -->
    <link rel="stylesheet" href="./assets/css/stylecartnull.css">

    <!-- link font icon -->
    <link rel="stylesheet" href="./assets/css/font/css/all.css">
</head>

<body>
    <section>
        <banner>
            <div class="banner container-fluid pb-5 mb-5" style="margin-top: 3.7em">
                <h1 class="pt-5">Shopping Cart</h1>
                <p class="myhome"><a href="MainController">Home</a><span><i class="px-4 fa-solid fa-chevron-right"></i></span>Cart
                </p>
            </div>
        </banner>
    </section>

    <section>
        <div class="container-fluid mb-5">
                <div class="row">
                    <div class="col-md-12 text-center cartnull pb-5 py-5">
                        <p style="color: #8b8b8b; font-size: 40px;">Your cart is empty</p>
                        <div class="px-5 pb-5">
                            <div class="d-inline-block py-3 px-3" style="background-color: #8B6E4A; border-radius: 10px">
                                <a href="MainController" style="color: white; text-decoration: none">Back To Home</a>
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
