<%-- 
    Document   : admin-dashboard
    Created on : Mar 11, 2024, 6:38:32 PM
    Author     : Pham Hieu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>

    <head>
        <title>Admin DashBoard | The LAVEN STORE</title>
        <link rel="icon" type="image/x-icon" href="assets/img/logo.png">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./assets/css/bootstrap/bootstrap.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noticia+Text">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inter">
        <link rel="stylesheet" href="./assets/css/font/css/all.css">
        <link rel="stylesheet" href="./assets/css/styleadmin.css">
    </head>

    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2 nav-left">
                    <div class="logo d-flex justify-content-center w-100 mt-3">
                        <a href="#" class="">
                            <p class="logo-left d-inline-block p-1 m-0">LAVEN STORE</p>
                            <p class="d-inline-block logo-right ms-2">ADMIN</p>
                        </a>
                    </div>
                    <div class="nav-admin mt-5 w-100">
                        <table class="w-100">
                            <tr>
                                <td colspan="2" class="py-1"><span class="nav-title">Main</span></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td class="py-1 active ps-3 active"><a href="#" class=""><span>Dashboard</span></a></td>
                            </tr>
                            <tr>
                                <td colspan="2" class="py-1"><span class="nav-title">Shop</span></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td class="py-1 ps-3 hover-dashboard"><a href="MainController?action=Admin-user" class=""><i class="fa-solid fa-user"></i>
                                        <span>User Management</span></a></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td class="py-1 ps-3 hover-dashboard"><a href="MainController?action=Admin-order" class=""><i
                                            class="fa-solid fa-cart-shopping"></i> <span>Order Management</span></a></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td class="py-1 ps-3 hover-dashboard"><a href="MainController?action=Admin-product" class=""><i
                                            class="fa-solid fa-box-open"></i> <span>Product Management</span></a></td>
                            </tr>
                            <tr>
                                <td class="py-2"><i class="fa-solid fa-right-from-bracket"></i></td>
                                <td><span><a href="MainController?action=logout" style="text-decoration: none; color: white">Logout</a></span></td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="col-md-10">
                    <div class="row top-nav">
                        <div class="col-md-2 text-center">
                            <div class="dashboard p-2 py-3">
                                <a href="#" class="">
                                    <p class="m-0">Dashboard</p>
                                </a>
                            </div>
                        </div>
                        <div class="col-md-8 d-flex align-content-center">
                            <div class="icon-nav p-2 py-3">
                                <i class="fa-solid fa-house"></i>
                            </div>
                            <div class="pos-nav d-flex align-content-center py-3">
                                <p class="m-0"><a href="MainController" style="color: white">HomePage</a></p>
                            </div>
                            <div class="pos-nav d-flex align-content-center py-3">
                                <p class="m-0"><a href="#" style="color: #8869E1">/Dashboard</a></p>
                            </div>
                        </div>
                        <div class="col-md-2 d-flex align-content-center justify-content-center">
                            <div class="pos-nav d-flex align-content-center p-2 py-3">
                                <p class="m-0">${sessionScope.account.userName}</p>
                            </div>
                            <div class="icon-nav-log p-2 py-3">
                                <a href="MainController?action=logout" style="color: white"><i class="fa-solid fa-power-off"></i></a>
                            </div>
                        </div>
                        <div class="col-md-12 p-0">
                            <div class="flex-content text-center w-100">
                                <div class="body-top w-100">
                                    <div class="body-title d-flex justify-content-between align-items-center w-100">
                                        <span class="ms-3">Dashboard</span>
                                    </div>
                                </div>
                                <div class="body-center">
                                    <div class="monthly p-4 py-5">
                                        <p class="Inter title-body m-0">MONTHLY</p>
                                        <div class="content-body-center w-100 d-flex justify-content-around pt-4">
                                            <div class="w-27 table-today pt-3">
                                                <table class="w-100">
                                                    <tr>
                                                        <td></td>
                                                        <td class="today-title">
                                                            <p class="m-0 inter float-start">REVENUE</p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="icon-today p-2"><i
                                                                class="fa-solid fa-sack-dollar float-end"></i>
                                                        </td>
                                                        <td class="today-sub">
                                                            <p class="m-0 float-start inter ps-2"><fmt:formatNumber pattern="#,###" value="${requestScope.totalRevenueMonthly}"/> VND</p>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>
                                            <div class="w-27 table-today pt-3">
                                                <table class="w-100">
                                                    <tr>
                                                        <td></td>
                                                        <td class="today-title">
                                                            <p class="m-0 inter float-start">PRODUCT SOLD</p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="icon-today p-2"><i
                                                                class="fa-solid fa-users float-end"></i></i>
                                                        </td>
                                                        <td class="today-sub">
                                                            <p class="m-0 float-start inter ps-2">${requestScope.totalSoldProductMonthly}</p>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>
                                            <div class="w-27 table-today p-3">
                                                <table class="w-100">
                                                    <tr>
                                                        <td></td>
                                                        <td class="today-title">
                                                            <p class="m-0 inter float-start">ORDER</p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="icon-today p-2"><i
                                                                class="fa-solid fa-boxes-stacked float-end"></i></i>
                                                        </td>
                                                        <td class="today-sub">
                                                            <p class="m-0 float-start inter ps-2">${requestScope.totalOrderMonthly}</p>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="monthly p-4 py-5 mt-5">
                                        <p class="Inter title-body m-0">DAILY</p>
                                        <div class="content-body-center w-100 d-flex justify-content-around pt-4">
                                            <div class="w-20 table-today pt-3">
                                                <table class="w-100">
                                                    <tr>
                                                        <td></td>
                                                        <td class="today-title">
                                                            <p class="m-0 inter float-start">REVENUE</p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="icon-today p-2"><i
                                                                class="fa-solid fa-sack-dollar float-end"></i>
                                                        </td>
                                                        <td class="today-sub">
                                                            <p class="m-0 float-start inter ps-2"><fmt:formatNumber pattern="#,###" value="${requestScope.totalRevenueDaily}"/> VND</p>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>
                                            <div class="w-20 table-today pt-3">
                                                <table class="w-100">
                                                    <tr>
                                                        <td></td>
                                                        <td class="today-title">
                                                            <p class="m-0 inter float-start">PRODUCT SOLD</p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="icon-today p-2"><i
                                                                class="fa-solid fa-boxes-stacked float-end"></i></i>
                                                        </td>
                                                        <td class="today-sub">
                                                            <p class="m-0 float-start inter ps-2"><fmt:formatNumber pattern="#,###" value="${requestScope.totalSoldProductDaily}"/></p>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>
                                            <div class="w-20 table-today p-3">
                                                <table class="w-100">
                                                    <tr>
                                                        <td></td>
                                                        <td class="today-title">
                                                            <p class="m-0 inter float-start">NEW ORDER</p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="icon-today p-2"><i class="fa-solid fa-basket-shopping float-end"></i></i>
                                                        </td>
                                                        <td class="today-sub">
                                                            <p class="m-0 float-start inter ps-2">${requestScope.totalOrderDaily}</p>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>
                                            <div class="w-20 table-today p-3">
                                                <table class="w-100">
                                                    <tr>
                                                        <td></td>
                                                        <td class="today-title">
                                                            <p class="m-0 inter float-start">NEW ORDER PENDING</p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="icon-today p-2"><i class="fa-solid fa-boxes-packing float-end"></i></i>
                                                        </td>
                                                        <td class="today-sub">
                                                            <p class="m-0 float-start inter ps-2">${requestScope.totalOrderPendingDaily}</p>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
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