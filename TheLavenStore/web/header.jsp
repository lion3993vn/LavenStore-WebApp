<%-- 
    Document   : header
    Created on : Mar 16, 2024, 11:27:46 AM
    Author     : Pham Hieu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

    <head>
        <title></title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- bootstrap -->
        <link rel="stylesheet" href="./assets/css/bootstrap/bootstrap.css">

        <!-- font -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Averia+Serif+Libre">
        <link rel="stylesheet" href="./assets/css/font/css/all.css">

        <!-- css style -->
        <link rel="stylesheet" href="./assets/css/styleheader.css">
    </head>

    <body>
        <header>
            <div class="container-fluid bg-light p-2 header fixed-top">
                <div class="container">
                    <div class="row">
                        <div class="col-md-4">
                            <nav>
                                <ul class="nav">
                                    <li class="nav-item nav-left">
                                        <a class="nav-link" href="MainController" style="color: #8B6E4A">Home</a>
                                    </li>
                                    <li class="nav-item nav-left">
                                        <a class="nav-link " href="MainController?action=shop" style="color: #8B6E4A">Shop</a>
                                    </li>
                                    <li class="nav-item nav-left">
                                        <a class="nav-link" href="#" style="color: #8B6E4A">About</a>
                                    </li>
                                    <c:if test="${sessionScope.account.role eq 'admin'}">
                                        <li class="nav-item nav-left">
                                            <a class="nav-link" href="MainController?action=Admin" style="color: #8B6E4A">Admin Dashboard</a>
                                        </li>
                                    </c:if>

                                </ul>
                            </nav>
                        </div>
                        <div class="col-md-4 text-center">
                            <a href="MainController" class="logo">The LAVEN STORE</a>
                        </div>
                        <div class="col-md-4">
                            <ul class="nav float-end d-flex justify-content-around">
                                <li class="nav-item">
                                    <a class="nav-link nav-right" href="MainController?action=wishlist&actionWL=show-wishlist" style="color: #8B6E4A"><i class="fa-solid fa-star"></i></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link nav-right" href="MainController?action=Cart" style="color: #8B6E4A"><i class="fa-solid fa-cart-shopping"></i></a>
                                </li>
                                <c:if test="${sessionScope.account == null}">
                                    <li class="nav-item">
                                        <a class="nav-link d-inline-block nav-right pe-3 ps-3" id="name" href="MainController?action=login">
                                            <i class="fa-solid fa-user" style="color: #8B6E4A"></i>
                                            <p class="d-inline ps-2" style="color: #8B6E4A">Login</p>
                                        </a>
                                    </li>
                                </c:if>
                                <c:if test="${sessionScope.account != null}">
                                    <li class="nav-item">
                                        <a class="nav-link d-inline-block nav-right pe-3 ps-3" id="name" href="MainController?action=profile">
                                            <i class="fa-solid fa-user" style="color: #8B6E4A"></i>
                                            <p class="d-inline ps-2" style="color: #8B6E4A">${sessionScope.account.userName}</p>
                                        </a>
                                    </li>
                                </c:if>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </header>


        <!--script js bootstrap-->
        <script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="./assets/js/bootstrap/bootstrap.min.js"></script>
        <script src="./assets/js/bootstrap/bootstrap.esm.min.js"></script>
    </body>

</html>