<%-- 
    Document   : admin-user
    Created on : Mar 11, 2024, 10:35:35 PM
    Author     : huyhu
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
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./assets/css/bootstrap/bootstrap.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noticia+Text">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inter">
        <link rel="stylesheet" href="./assets/css/font/css/all.css">
        <link rel="stylesheet" href="./assets/css/styleadmin-user.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    </head>

    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2 nav-left">
                    <div class="logo d-flex justify-content-center w-100 mt-3">
                        <a href="" class="">
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
                                <td class="py-1 ps-3 hover-dashboard"><a href="" class=""><span>Dashboard</span></a></td>
                            </tr>
                            <tr>
                                <td colspan="2" class="py-1"><span class="nav-title">Shop</span></td>
                            </tr>
                            <tr>
                                <td></td> 
                                <td class="py-1 ps-3 active"><a href="" class=""><i class="fa-solid fa-user"></i> <span>User Management</span></a></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td class="py-1 ps-3 hover-dashboard"><a href="" class=""><i class="fa-solid fa-cart-shopping"></i> <span>Order Management</span></a></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td class="py-1 ps-3 hover-dashboard"><a href="" class=""><i class="fa-solid fa-box-open"></i> <span>Product Management</span></a></td>
                            </tr>
                            <tr>
                                <td class="py-2"><i class="fa-solid fa-right-from-bracket"></i></td>
                                <td><span>Logout</span></td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="col-md-10">
                    <div class="row top-nav">
                        <div class="col-md-2 text-center">
                            <div class="dashboard p-2 py-3">
                                <a href="" class="">
                                    <p class="m-0">Dashboard</p>
                                </a>
                            </div>
                        </div>
                        <div class="col-md-8 d-flex align-content-center">
                            <div class="icon-nav p-2 py-3">
                                <i class="fa-solid fa-house"></i>
                            </div>
                            <div class="pos-nav d-flex align-content-center p-2 py-3">
                                <p class="m-0">Home</p><span>/User Management</span>
                            </div>
                        </div>
                        <div class="col-md-2 d-flex align-content-center justify-content-center">
                            <div class="pos-nav d-flex align-content-center p-2 py-3">
                                <p class="m-0">${sessionScope.account.userName}</p>
                            </div>
                            <div class="icon-nav-log p-2 py-3">
                                <i class="fa-solid fa-power-off"></i>
                            </div>
                        </div>
                        <div class="col-md-12 p-0">
                            <div class="flex-content text-center w-100">
                                <div class="body-top w-100">
                                    <div class="body-title d-flex justify-content-between align-items-center w-100">
                                        <span class="ms-3">Users</span>
                                        <div class="add-user px-3 py-1 me-3" data-bs-toggle="modal" data-bs-target="#add-user">
                                            <a href="#"><p class="m-0 inter">+ Add User</p></a>
                                        </div>
                                    </div>
                                </div>
                                <div class="body-center">
                                    <div class="container-fluid">
                                        <div class="row">
                                            <form action="AdminUserController" method="">
                                                <div class="col-md-12">
                                                    <div class="search-user float-start p-3"><input type="text" class="p-1 ps-3" placeholder="Search user" name="searchUser" value="${requestScope.searchUser}"></div>
                                                    <div class="search-user-submit p-3 float-start"><input type="submit" class="p-1 px-3" value="GO"></div>
                                                </div></form>
                                            <div class="col-md-12 p-0">
                                                <table class="w-100 table-body">
                                                    <tr class="table-header">
                                                        <td class="p-3 px-4"><span class="float-start">Username</span></td>
                                                        <td class="p-3 px-4 "><span class="float-start">Fullname</span></td>
                                                        <td class="p-3 px-4 "><span class="float-start">Email</span></td>
                                                        <td class="p-3 px-4 "><span class="float-start">Phone number</span></td>
                                                        <td class="p-3 px-4 "><span class="float-start">Address</span></td>
                                                        <td class="p-3 px-4 "><span class="float-start">Role</span></td>
                                                        <td class="p-3 px-4 "><span>Action</span></td>
                                                    </tr>
                                                    <c:forEach items="${requestScope.userList}" var="u">
                                                        <tr class="table-content">
                                                            <td class="p-3 px-4 "><span class="float-start">${u.userName}</span></td>
                                                            <td class="p-3 px-4 "><span class="float-start">${u.fullName}</span></td>
                                                            <td class="p-3 px-4 "><span class="float-start">${u.email}</span></td>
                                                            <td class="p-3 px-4 "><span class="float-start">${u.phoneNumber}</span></td>
                                                            <td class="p-3 px-4 "><span class="float-start">${u.address}</span></td>
                                                            <td class="p-3 px-4 "><span class="float-start">${u.role}</span></td>
                                                            <td class="p-3 px-4 d-flex justify-content-center">
                                                                <div class="edit-user p-2" data-bs-toggle="modal" data-bs-target="#edit-user">
                                                                    <i class="fa-solid fa-pen-to-square" onclick="editUser(${u.ID})"></i>
                                                                </div>
                                                                <div class="delete-user p-2"><i class="fa-solid fa-trash" onclick="deleteUser(${u.ID})"></i></div>
                                                            </td>
                                                        </tr>
                                                    </c:forEach> 
                                                </table>
                                            </div>
                                            <div class="col-md-12 d-flex justify-content-end paging p-2">
                                                <c:forEach begin="1" end="${requestScope.totalPage}" var="i">
                                                    <form action="AdminUserController" method="post">
                                                        <input type="hidden" name="index" value="${i}">
                                                        <input type="hidden" name="searchUser" value="${requestScope.searchUser}">
                                                        <input class="p-2 me-3 ${requestScope.currentPage == i?"active-paging":""}" type="submit" value="${i}">
                                                    </form>
                                                </c:forEach>
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

        <!-- Modal add user -->
        <div class="modal" id="add-user">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="py-2 header-modal d-flex justify-content-between">
                        <h4 class="modal-title inter ms-3">Add new user</h4>
                        <div class="btn-close-modal me-3" data-bs-dismiss="modal"><i class="fa-solid fa-x"></i></div>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <form action="ProcessAdminUserController" method="post" id="add-user-form">
                            <table class="w-100 table-modal">
                                <tr>
                                    <td colspan="2" class="py-2"><span class="inter">Username</span></td>
                                </tr>
                                <tr>
                                    <td class="pe-1 py-1"><input id="username" type="text" placeholder="" class="w-100 p-1" name="username" value="" onchange="checkUser()"></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td colspan="2"><span class="error-text" id="errorUsername"></span></td>
                                </tr>
                                <tr>
                                    <td colspan="2" class="py-2">Password</td>
                                </tr>
                                <tr>
                                    <td colspan="2" class="pe-1 py-1"><input id="password" type="text" class="w-100 p-1" name="password" value="" onchange="checkUser()"></td>
                                </tr>
                                <tr>
                                    <td colspan="2"><span class="error-text" id="errorPassword"></span></td>
                                </tr>
                                <tr>
                                    <td class="py-2">Role</td>
                                    <td class="py-2">Phone number</td>
                                </tr>
                                <tr>
                                    <td class="pe-1 py-1"><select class="w-100 p-1" name="role">
                                            <option value="admin">Admin</option>
                                            <option value="user">User</option>
                                        </select></td>
                                    <td class="pe-1 py-1"><input id="phoneNumber" type="text" class="w-100 p-1" name="phoneNumber" value="" onchange="checkUser()"></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td><span class="error-text" id="errorPhone"></span></td>
                                </tr>
                                <tr>
                                    <td colspan="2" class="py-2">Email</td>
                                </tr>
                                <tr>
                                    <td colspan="2" class="pe-1 py-1"><input id="email" type="text" class="w-100 p-1" name="email" value="" onchange="checkUser()"></td>
                                </tr>
                                <tr>
                                    <td colspan="2"><span class="error-text" id="errorEmail"></span></td>
                                </tr>
                                <tr>
                                    <td colspan="2" class="py-2">Address</td>
                                </tr>
                                <tr>
                                    <td colspan="2" class="pe-1 py-1"><input id="address" type="text" class="w-100 p-1" name="address" required></td>
                                </tr>
                            </table>
                            <input name="admin-action" type="hidden" value="add-user">

                            </div>

                            <!-- Modal footer -->
                            <div class="footer-modal py-4 d-flex justify-content-end">
                                <div class="close me-4">
                                    <div class="modal-btn-close p-2 px-4" data-bs-dismiss="modal"><span>Close</span></div>
                                </div>
                                <div class="save-modal me-4">
                                    <input type="hidden" id="add-user-submit" value="Add user" class="input-submit p-2 px-3 inter">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- Modal edit user -->
            <div class="modal" id="edit-user">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <div class="py-2 header-modal d-flex justify-content-between">
                            <h4 class="modal-title inter ms-3">Edit user</h4>
                            <div class="btn-close-modal me-3" data-bs-dismiss="modal"><i class="fa-solid fa-x"></i></div>
                        </div>

                        <!-- Modal body -->
                        <div class="modal-body">
                            <form action="ProcessAdminUserController" method="post" id="edit-user-form">
                                <table class="w-100 table-modal">
                                    <tr>
                                        <td colspan="2" class="py-2"><span class="inter">Username</span></td>
                                    </tr>
                                    <tr>
                                        <td class="pe-1 py-1"><input type="text" placeholder="" class="w-100 p-1" id="edit-username" readonly style="background-color: #182237" onchange="checkEdit(document.getElementById('edit-id').value)" name="username"></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" class="py-2">Password</td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" class="pe-1 py-1"><input type="text" class="w-100 p-1" id="edit-password" onchange="checkEdit(document.getElementById('edit-id').value)" name="password"></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"><span class="error-text" id="errorPasswordEdit"></span></td>
                                    </tr>
                                    <tr>
                                        <td class="py-2">Role</td>
                                        <td class="py-2">Phone number</td>
                                    </tr>
                                    <tr>
                                        <td class="pe-1 py-1"><select class="w-100 p-1" id="edit-role" name="role">
                                                <option value="admin">Admin</option>
                                                <option value="user">User</option>
                                            </select></td>
                                        <td class="pe-1 py-1"><input type="text" class="w-100 p-1" id="edit-phone" onchange="checkEdit(document.getElementById('edit-id').value)" name="phoneNumber"> </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td><span class="error-text" id="errorPhoneEdit"></span></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" class="py-2">Email</td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" class="pe-1 py-1"><input type="text" class="w-100 p-1" id="edit-email" onchange="checkEdit(document.getElementById('edit-id').value)" name="email"> </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"><span class="error-text" id="errorEmailEdit"></span></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" class="py-2">Address</td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" class="pe-1 py-1"><input type="text" class="w-100 p-1" id="edit-address" name="address"></td>
                                    </tr>
                                    <tr>
                                        <td><input type="hidden" id="edit-id"></td>
                                        <td><input type="hidden" name="ajaxAdminAction" value="update-user"></td>
                                    <input name="admin-action" type="hidden" value="edit-user">
                                    </tr>
                                </table>

                        </div>

                        <!-- Modal footer -->
                        <div class="footer-modal py-4 d-flex justify-content-end">
                            <div class="close me-4">
                                <div class="modal-btn-close p-2 px-4" data-bs-dismiss="modal"><span>Close</span></div>
                            </div>
                            <div class="save-modal me-4">
                                <input type="hidden" value="Save" id="edit-user-submit" class="input-submit p-2 px-4 inter">
                            </div>
                        </div>
                        </form>
                    </div>
                </div>
            </div>
            <!--script js bootstrap-->
            <script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>
            <script src="./assets/js/bootstrap/bootstrap.min.js"></script>
            <script src="./assets/js/bootstrap/bootstrap.esm.min.js"></script>
            <script>
                                            function checkUser() {
                                                var checker = 4;
                                                console.log("check user ne ");
                                                var username = document.getElementById("username").value;
                                                var email = document.getElementById("email").value;
                                                var password = document.getElementById("password").value;
                                                var phoneNumber = document.getElementById("phoneNumber").value;
                                                $.ajax({
                                                    url: "AjaxAdminUserController",
                                                    type: "post", //send it through post method
                                                    data: {
                                                        ajaxAdminAction: "add-user",
                                                        txtUsername: username,
                                                        txtEmail: email,
                                                        txtPassword: password,
                                                        txtPhoneNumber: phoneNumber
                                                    },
                                                    success: function (response) {
                                                        var errorUsername = response.errorUsername;
                                                        var errorEmail = response.errorEmail;
                                                        var errorPassword = response.errorPassword;
                                                        var errorPhone = response.errorPhone;
                                                        document.getElementById("errorUsername").innerHTML = errorUsername;
                                                        document.getElementById("errorPassword").innerHTML = errorPassword;
                                                        document.getElementById("errorPhone").innerHTML = errorPhone;
                                                        document.getElementById("errorEmail").innerHTML = errorEmail;
                                                        if (errorUsername === "")
                                                            checker--;
                                                        if (errorPassword === "")
                                                            checker--;
                                                        if (errorPhone === "")
                                                            checker--;
                                                        if (errorEmail === "")
                                                            checker--;
                                                        if (checker == 0) {
                                                            document.getElementById("add-user-submit").type = "submit";
                                                        } else {
                                                            document.getElementById("add-user-submit").type = "hidden";
                                                        }
                                                        console.log(checker);
                                                        console.log("response ", response);
                                                    },
                                                    error: function (xhr) {
                                                        //Do Something to handle error
                                                        alert("Có lỗi xảy ra, vui lòng thử lại sau ít phút");
                                                    }
                                                });
                                            }
            </script>
            <script>
                function editUser(userID) {
                    console.log(userID);
                    console.log("edit-user ne ");
                    var username = "";
                    var password = "";
                    var role = "";
                    var phoneNumber = "";
                    var email = "";
                    var address = "";
                    $.ajax({
                        url: "AjaxAdminUserController",
                        type: "post", //send it through post method
                        data: {
                            ajaxAdminAction: "edit-user",
                            txtUserID: userID
                        },
                        success: function (response) {
                            username = response.username;
                            password = response.password;
                            role = response.role;
                            phoneNumber = response.phoneNumber;
                            email = response.email;
                            address = response.address;
                            document.getElementById("edit-id").value = userID;
                            document.getElementById("edit-username").value = username;
                            document.getElementById("edit-password").value = password;
                            document.getElementById("edit-role").value = role;
                            document.getElementById("edit-phone").value = phoneNumber;
                            document.getElementById("edit-email").value = email;
                            document.getElementById("edit-address").value = address;
                            console.log("response ", response);
                        },
                        error: function (xhr) {
                            //Do Something to handle error
                            alert("Có lỗi xảy ra, vui lòng thử lại sau ít phút");
                        }
                    });
                }
            </script>
            <script>
                function checkEdit(userID) {
                    var checker = 3;
                    console.log("check edit ne ");
                    var email = document.getElementById("edit-email").value;
                    var password = document.getElementById("edit-password").value;
                    var phoneNumber = document.getElementById("edit-phone").value;
                    $.ajax({
                        url: "AjaxAdminUserController",
                        type: "post", //send it through post method
                        data: {
                            ajaxAdminAction: "check-edit",
                            userID: userID,
                            txtEmail: email,
                            txtPassword: password,
                            txtPhoneNumber: phoneNumber
                        },
                        success: function (response) {
                            var errorEmail = response.errorEmail;
                            var errorPassword = response.errorPassword;
                            var errorPhone = response.errorPhone;
                            document.getElementById("errorPasswordEdit").innerHTML = errorPassword;
                            document.getElementById("errorPhoneEdit").innerHTML = errorPhone;
                            document.getElementById("errorEmailEdit").innerHTML = errorEmail;
                            if (errorPassword === "")
                                checker--;
                            if (errorPhone === "")
                                checker--;
                            if (errorEmail === "")
                                checker--;
                            if (checker == 0) {
                                document.getElementById("edit-user-submit").type = "submit";
                            } else {
                                document.getElementById("edit-user-submit").type = "hidden";
                            }
                            console.log(checker);
                            console.log("response ", response);
                        }
                        ,
                        error: function (xhr) {
                            //Do Something to handle error
                            alert("Có lỗi xảy ra, vui lòng thử lại sau ít phút");
                        }
                    });
                }
            </script>
            <script>
                function deleteUser(userID) {
                    console.log(userID);
                    console.log("delete");

                    // Tạo một phần tử form
                    var form = document.createElement('form');
                    form.action = "ProcessAdminUserController";
                    form.method = "post";

                    // Tạo một input cho userID
                    var inputUserID = document.createElement('input');
                    inputUserID.type = 'text';
                    inputUserID.name = 'userID';
                    inputUserID.value = userID;

                    // Tạo một input cho admin-action
                    var inputAction = document.createElement('input');
                    inputAction.type = 'text';
                    inputAction.name = 'admin-action';
                    inputAction.value = 'delete-user';

                    // Thêm các input vào form
                    form.appendChild(inputUserID);
                    form.appendChild(inputAction);

                    // Thêm form vào body để có thể submit
                    document.body.appendChild(form);

                    // Submit form
                    form.submit();
                }
            </script>
    </body>

</html>