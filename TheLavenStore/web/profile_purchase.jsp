<%-- 
    Document   : profile_purchase
    Created on : Mar 10, 2024, 10:58:14 PM
    Author     : Pham Hieu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>

    <head>
        <title>Profile Purchase | The LAVEN STORE</title>
        <link rel="icon" type="image/x-icon" href="assets/img/logo.png">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./assets/css/bootstrap/bootstrap.css">
        <link rel="stylesheet" href="./assets/css/styleprofile_purchase.css">
        <link rel="stylesheet" href="./assets/css/font/css/all.css">
    </head>

    <body>
        <c:import url="header.jsp"></c:import>
            <!-- Start banner -->
            <div class="banner container-fuild pb-5" style="margin-top: 3.5em">
                <h1 class="pt-5">My Profile</h1>
                <p><a href="MainController">Home</a><span><i class="px-4 fa-solid fa-chevron-right"></i></span><a href="MainController?action=profile">Profile</a></p>
            </div>
            <!-- End banner -->

            <!-- Start content -->
            <div class="container-fluid my-5 py-5">
                <div class="container">
                    <div class="row">

                        <!-- Start Slider navbar -->
                        <div class="bar col-md-2">
                            <table class="w-100">
                                <tr class="text-center nohover">
                                    <td colspan="2">
                                        <p class="fw-normal py-3 fs-3">
                                        <c:choose>
                                            <c:when test="${fn:length(sessionScope.account.userName) <= 12}">${sessionScope.account.userName}
                                            </c:when>
                                            <c:otherwise>${fn:substring(sessionScope.account.userName, 0, 12)}...
                                            </c:otherwise>
                                        </c:choose>
                                    </p>
                                </td>
                            </tr>

                            <tr>
                                <td colspan="2" class="">
                                    <div class="hr ps-4 pe-4"></div>
                                </td>
                            </tr>
                            <tr class="nohover">
                                <td><i class=" fa-solid fa-user "></i></td>
                                <td><a href="MainController?action=profile">
                                        <div class="subnav_group">
                                            Information
                                        </div>
                                    </a>
                                </td>
                            </tr>

                            <tr>
                                <td></td>
                                <td class="ps-3"><a href="MainController?action=profile">
                                        <div class="subnav">
                                            Profile
                                        </div>
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td class="ps-3 "><a href="MainController?action=profile-address">
                                        <div class="subnav">
                                            Address
                                        </div>
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td class="ps-3"><a href="MainController?action=profile-password">
                                        <div class="subnav">
                                            Change password
                                        </div>
                                    </a>
                                </td>
                            </tr>
                            <tr class="profile">
                                <td><i class=" fa-solid fa-credit-card"></i></td>
                                <td><a href="#">My Purchase</a></td>
                            </tr>
                            <tr>
                                <td><i class=" fa-solid fa-arrow-right-from-bracket"></i></td>
                                <td><a href="MainController?action=logout">Logout</a></td>
                            </tr>
                        </table>
                    </div>

                    <!-- End Slider navbar -->

                    <!-- Start form -->
                    <div class="col-md-10">
                        <h5 class="mt-4 fw-medium">My purchase</h5>
                        <table class="ms-5">
                            <tr>
                                <td class="order_header pb-2">
                                    <p class="fs-5 m-0 text-center ">All orders</p>
                                </td>
                            </tr>
                        </table>
                        <c:forEach items="${requestScope.listorder}" var="a">
                            <div class="mb-5 order_item">
                                <div>
                                    <div class="d-flex justify-content-between align-items-center mt-3">
                                        <div class="ms-5 fs-5 idpurchase fw-medium">#${a.orderCode}</div>
                                        <div class="d-flex justify-content-around align-items-center">
                                            <p class="fs-5 fw-semibold text-secondary mb-0">Payment status:</p>
                                            <c:if test="${a.status eq 'COMPLETED'}">
                                                <div class="complete ms-5 fw-semibold rounded-1 fs-6">${a.status}</div>
                                            </c:if>
                                            <c:if test="${a.status eq 'CANCELLED'}">
                                                <div class="cancelled ms-5 fw-semibold rounded-1 fs-6">${a.status}</div>
                                            </c:if>
                                            <c:if test="${a.status eq 'PENDING'}">
                                                <div class="pending ms-5 fw-semibold rounded-1 fs-6">${a.status}</div>
                                            </c:if>
                                            <div class="vline mx-5"></div>
                                            <div class="opacity-75">
                                                <i class="fa-solid fa-truck"></i>
                                                <p class="d-inline-block m-0"><fmt:formatDate value="${a.date}" pattern="dd/MM/yyyy HH:mm"/></p>
                                            </div>
                                        </div>
                                    </div>
                                    <hr class="mt-3">

                                    <table class="fs-5 ms-5">
                                        <tbody>
                                            <c:forEach items="${requestScope['details'.concat(a.ID)]}" var="b">
                                                <tr>
                                                    <c:forEach items="${requestScope['listpro'.concat(a.ID)]}" var="c">
                                                        <c:if test="${b.productID == c.ID}">
                                                            <td>
                                                                <a href="MainController?action=product&id=${c.ID}">
                                                                    <div style="width: 3.5em" class="py-2">
                                                                        <img class="w-100" src="${c.image}" alt="">
                                                                    </div>
                                                                </a>
                                                            </td>
                                                            <td class="w-75 fs-6" colspan="3">
                                                                <div class="fs-5 ps-3">
                                                                    <a href="MainController?action=product&id=${c.ID}" style="text-decoration: none; color: black"><p class="mb-0">${c.name}</p></a>
                                                                    <p class="mb-0">x${b.quantity}</p>
                                                                </div>
                                                            </td>
                                                            <td class="pb-2 w-75 fs-5">
                                                                <p class="text-end fw-medium">VND <fmt:formatNumber pattern="#,###" value="${b.price}"/></p>
                                                            </td>
                                                        </c:if>
                                                    </c:forEach>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <hr>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="ms-5 fs-5 d-flex align-items-center success_content">
                                            <p class="mb-0 ms-4"></p>
                                        </div>
                                        <div class="d-flex justify-content-around align-items-center fs-5">
                                            <p class=" fw-semibold text-secondary mb-0 ">Total:</p>
                                            <div>
                                                <p class="total_money ms-3 me-2 d-inline-block m-0 fw-medium">VND</p>
                                            </div>
                                            <p class="total_money m-0 fw-medium"><fmt:formatNumber pattern="#,###" value="${requestScope['totalMoney'.concat(a.ID)]}"/> <span style="color: #6C757D">(25,000 Ship)</span></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        <div class="mb-5">
                            <div class="col-md-12 d-flex justify-content-end p-2">
                                <c:forEach begin="1" end="${requestScope.page}" var="p">
                                    <form action="ProfilePurchaseController" method="post">
                                        <input type="submit" class="p-2 me-3 paging ${requestScope.curr == p ? "active-paging":""}" value="${p}">
                                        <input type="hidden" name="index" value="${p}">
                                    </form>
                                </c:forEach>


                            </div>
                        </div>
                    </div>
                    <!-- End form -->

                </div>
            </div>
        </div>
        <!-- End content -->
        <c:import url="footer.jsp"></c:import>
        <!--script js bootstrap-->
        <script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="./assets/js/bootstrap/bootstrap.min.js"></script>
        <script src="./assets/js/bootstrap/bootstrap.esm.min.js"></script>
    </body>

</html>
