<%-- 
    Document   : profile
    Created on : Mar 2, 2024, 10:36:09 PM
    Author     : Kudo
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html lang="en">

    <head>
        <title>Profile | The LAVEN STORE</title>
        <link rel="icon" type="image/x-icon" href="assets/img/logo.png">
        <meta charset="UTF-8">
        <link rel="stylesheet" href="./assets/css/bootstrap/bootstrap.css">
        <link rel="stylesheet" href="./assets/css/styleprofile.css">
        <link rel="stylesheet" href="./assets/css/font/css/all.css">
    </head>

    <body>
        <c:import url="header.jsp"></c:import>
        <c:set var="a" value="${sessionScope.account}"/>
        <!-- Start banner -->
        <div class="banner container-fuild pb-5" style="margin-top: 3.5em">
            <h1 class="pt-5">My Profile</h1>
            <p><a href="MainController">Home</a><span><i class="px-4 fa-solid fa-chevron-right"></i></span>Profile</p>
        </div>
        <!-- End banner -->

        <!-- Start content -->
        <div class="container-fluid my-5 py-5">
            <div class="container">
                <div class="row">

                    <!-- Start Slider navbar -->
                    <div class="bar col-md-2">
                        <table class="w-100">
                            <tr class="text-center">
                                <th colspan="2">
                                    <p class="fw-normal py-3 fs-3">
                                        <c:choose>
                                            <c:when test="${fn:length(sessionScope.account.userName) <= 12}">${sessionScope.account.userName}
                                            </c:when>
                                            <c:otherwise>${fn:substring(sessionScope.account.userName, 0, 12)}...
                                            </c:otherwise>
                                        </c:choose>
                                    </p>
                                </th>
                            </tr>

                            <tr>
                                <td colspan="2" class="">
                                    <div class="hr ps-4 pe-4"></div>
                                </td>
                            </tr>

                            <tr class="nohover">
                                <td><i class="fa-solid fa-user"></i></td>
                                <td><a href="#">Information</a></td>
                            </tr>
                            <tr class=" profile">
                                <td></td>
                                <td class="ps-3"><a href="#" class="w-100">Profile</a></td>
                            </tr>
                            <tr class="hover-form">
                                <td></td>
                                <td class="ps-3"><a href="MainController?action=profile-address">Address</a></td>
                            </tr>
                            <tr class="hover-form">
                                <td></td>
                                <td class="ps-3"><a href="MainController?action=profile-password">Change password</a></td>
                            </tr>
                            <tr class="hover-form">
                                <td><i class=" fa-solid fa-credit-card"></i></td>
                                <td><a href="MainController?action=profile-purchase">My Purchase</a></td>
                            </tr>
                            <tr class="hover-form">
                                <td><i class=" fa-solid fa-arrow-right-from-bracket"></i></td>
                                <td><a href="MainController?action=logout">Logout</a></td>
                            </tr>
                        </table>

                    </div>

                    <!-- End Slider navbar -->

                    <!-- Start form -->
                    <div class="col-md-10">
                        <form action="MainController" method="post">
                            <h5 class="mt-4 fw-bold">My profile</h5>
                            <table class="fs-5 fw-medium">
                                <tbody>
                                    <tr>
                                        <td class="px-5 pb-2">Username:</td>
                                        <td class="pb-2">${a.userName}</td>
                                    </tr>
                                    <tr>
                                        <td class="px-5 pb-2 w-25">Full name: </td>
                                        <td class="pb-2 w-75"><input
                                                class="nameprofile form-control w-75 border border-2 border-dark rounded-0"
                                                type="text" name="fullname" value="${a.fullName}"></td>
                                    </tr>
                                    <tr>
                                        <td class="px-5 pb-2 w-25">Phone number: </td>
                                        <td class="pb-2"><input
                                                class="form-control w-75 border border-2 border-dark rounded-0" type="text"
                                                name="phone" value="${a.phoneNumber}"</td>
                                    </tr>
                                    <tr>
                                        <td class="px-5 w-25">Email: </td>
                                        <td class="w-75">${a.email}</td>
                                    </tr>
                                    <tr>
                                        <td class=""></td>
                                        <td class=""><p class="noticia-text text-error" style="color: #FF0000;">${requestScope.tt}</p></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <div class="my-5">
                                                <input class="p-2 btn-submit fs-6 rounded-1" type="submit"
                                                       value="Save Changes">
                                                <input type="hidden" name="action" value="profile-update">
                                            </div>
                                        </td>
                                    </tr>

                                </tbody>
                            </table>
                        </form>
                    </div>
                </div>
                <!-- End form -->

            </div>
        </div>
        <!-- End content -->
        <c:import url="footer.jsp"></c:import>
        <!--script js bootstrap-->
        <script src="./assets/js/bootstrap/jquery.min.js"></script>
        <script src="./assets/js/bootstrap/popper.min.js"></script>
        <script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="./assets/js/bootstrap/bootstrap.min.js"></script>
        <script src="./assets/js/bootstrap/bootstrap.esm.min.js"></script>
    </body>

</html>
