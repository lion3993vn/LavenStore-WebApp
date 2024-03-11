<%-- 
    Document   : profile_address
    Created on : Mar 5, 2024, 10:47:57 AM
    Author     : huyhu
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
        <link rel="stylesheet" href="./assets/css/styleprofile_address.css">
        <link rel="stylesheet" href="./assets/css/font/css/all.css">
    </head>

    <body>

        <!-- Start banner -->
        <div class="banner container-fuild pb-5">
            <h1 class="pt-5">My Profile</h1>
            <p><a href="#">Home</a><span><i class="px-4 fa-solid fa-chevron-right"></i></span>Profile</p>
        </div>
        <!-- End banner -->

        <!-- Start content -->
        <div class="container-fluid mb-5">
            <div class="container">
                <div class="row">

                    <!-- Start Slider navbar -->
                    <div class="bar col-md-2">
                        <table class="w-100">
                            <tr class="text-center">
                                <th colspan="2">
                                    <p class="fw-normal fs-1 p-2">TaiLe</p>
                                </th>
                            </tr>

                            <tr>
                                <td colspan="2" class="">
                                    <div class="hr ps-4 pe-4"></div>
                                </td>
                            </tr>

                            <tr>
                                <td><i class=" fa-solid fa-user"></i></td>
                                <td><a href="#">Information</a></td>
                            </tr>

                            <tr>
                                <td></td>
                                <td class="ps-3"><a href="#">Profile</a></td>
                            </tr>
                            <tr class=" profile">
                                <td></td>
                                <td class="ps-3"><a href="#">Address</a></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td class="ps-3"><a href="#">Change password</a></td>
                            </tr>
                            <tr>
                                <td><i class=" fa-solid fa-credit-card"></i></td>
                                <td><a href="#">My Purchase</a></td>
                            </tr>
                            <tr>
                                <td><i class=" fa-solid fa-arrow-right-from-bracket"></i></td>
                                <td><a href="#">Logout</a></td>
                            </tr>
                        </table>

                    </div>

                    <!-- End Slider navbar -->

                    <!-- Start form -->
                    <div class="col-md-10">
                        <form action="">
                            <h5 class="mt-4 fw-bold">My profile</h5>
                            <table class="fs-5 fw-medium">
                                <tbody>
                                    <tr>
                                        <td class="px-5 pb-2 w-25">City: </td>
                                        <td class="pb-2 w-75"><input
                                                class="form-control w-75 border border-2 border-dark rounded-1" type="text"
                                                name="City"></td>
                                    </tr>
                                    <tr>
                                        <td class="px-5 pb-2 w-25">District: </td>
                                        <td class="pb-2"><input
                                                class="form-control w-75 border border-2 border-dark rounded-1" type="text"
                                                name="district"></td>
                                    </tr>
                                    <tr>
                                        <td class="px-5 pb-2 w-25">Ward: </td>
                                        <td class="pb-2"><input
                                                class="form-control w-75 border border-2 border-dark rounded-1" type="text"
                                                name="ward"></td>
                                    </tr>
                                    <tr>
                                        <td class="px-5 pb-2 w-25">Note: </td>
                                        <td class="pb-2"><input
                                                class="form-control w-75 border border-2 border-dark rounded-1" type="text"
                                                name="note" placeholder="Street Name, Building, House No."></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <div class="my-3">
                                                <input class="p-2 btn-submit fs-6 rounded-1" type="submit"
                                                       value="Save Changes">
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

        <!--script js bootstrap-->
        <script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="./assets/js/bootstrap/bootstrap.min.js"></script>
        <script src="./assets/js/bootstrap/bootstrap.esm.min.js"></script>
    </body>

</html>