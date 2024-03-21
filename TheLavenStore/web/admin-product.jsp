
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
        <title>Admin Order | The LAVEN STORE</title>
        <link rel="icon" type="image/x-icon" href="assets/img/logo.png">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./assets/css/bootstrap/bootstrap.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noticia+Text">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inter">
        <link rel="stylesheet" href="./assets/css/font/css/all.css">
        <link rel="stylesheet" href="./assets/css/styleadmin-product.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    </head>

    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2 nav-left">
                    <div class="logo d-flex justify-content-center w-100 mt-3">
                        <a href="MainController?action=Admin" class="">
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
                                <td class="py-1 ps-3 hover-dashboard"><a href="MainController?action=Admin" class=""><span>Dashboard</span></a></td>
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
                                <td class="py-1 ps-3 hover-dashboard"><a href="MainController?action=Admin-order" class=""><i class="fa-solid fa-cart-shopping"></i>
                                        <span>Order Management</span></a></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td class="py-1 ps-3 active"><a href="#" class=""><i
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
                                <a href="MainController?action=Admin" class="">
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
                                <p class="m-0"><a href="#" style="color: #8869E1">/Admin Product</a></p>
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
                                        <span class="ms-3">Product Management</span>
                                        <div class="add-product px-3 py-1 me-3" data-bs-toggle="modal" data-bs-target="#add-product">
                                            <a href="#"><p class="m-0 inter">+ Add Product</p></a>
                                        </div>
                                    </div>
                                </div>
                                <div class="body-center">
                                    <div class="container-fluid">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <form class="d-flex justify-content-start" action="AdminProductController" method="post">
                                                    <div class="search-user p-3"><input type="text" class="p-1 ps-3" placeholder="Search Product" name="keyword" value="${requestScope.keyword}"></div>
                                                    <div class="filter-status p-3">
                                                        <select name="searchCateID" id="" class="p-1">
                                                            <option value="" ${requestScope.searchCateID == null || requestScope.searchCateID == 0 ? "selected" : ""} selected >Type</option>
                                                            <c:forEach items="${requestScope.listc}" var="i">
                                                                <option ${requestScope.searchCateID == i.cateID ? "selected" : ""} value="${i.cateID}">${i.cateName}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="search-user-submit p-3"><input type="submit" class="p-1 px-3"
                                                                                               value="GO"></div></form>
                                            </div>
                                            <div class="col-md-12 p-0">
                                                <table class="w-100 table-body">
                                                    <tr class="table-header">
                                                        <td class="p-3 px-4 w-5"><span class="float-start">ProductID</span></td>
                                                        <td class="p-3 px-4 w-15"><span class="float-start">Name</span></td>
                                                        <td class="p-3 px-4 w-10"><span class="float-start">Category</span></td>
                                                        <td class="p-3 px-4 "><span>Quantity</span></td>
                                                        <td class="p-3 px-4 w-10"><span class="float-start">Price</span></td>
                                                        <td class="p-3 px-4 "><span>Rating</span></td>
                                                        <td class="p-3 px-4 "><span>Image</span></td>
                                                        <td class="p-3 px-4 description-product"><span>Description</span></td>
                                                        <td class="p-3 px-4 "><span>Action</span></td>
                                                    </tr> 
                                                    <c:forEach items="${requestScope.listp}" var="i">
                                                        <tr class="table-content">
                                                            <td class="p-3 px-4 "><span class="float-start">${i.ID}</span></td>
                                                            <td class="p-3 px-4 "><span class="float-start">${i.name}</span></td>
                                                            <td class="p-3 px-4 ">
                                                                <c:forEach items="${requestScope.listc}" var="o">
                                                                    <c:if test="${i.cateID == o.cateID}">
                                                                        <span class="float-start">${o.cateName}</span>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </td>
                                                            <td class="p-3 px-4 "><span>${i.quantity}</span></td>
                                                            <td class="p-3 px-4 "><span class="float-start">VND <fmt:formatNumber pattern="#,###" value="${i.price}"/></span></td>
                                                            <td class="p-3 px-4 "><span>${i.rate}</span></td>
                                                            <td class="w-10">
                                                                <div class="img-product py-1">
                                                                    <img src="${i.image}" alt="">
                                                                </div>
                                                            </td>
                                                            <td class="p-3 px-4 description-product"><span>${i.description}</span></td>
                                                            <td class="p-3 px-4">
                                                                <div class="d-flex justify-content-center">
                                                                    <div class="edit-product p-2" data-bs-toggle="modal" data-bs-target="#edit-product" onclick="editProduct(${i.ID})">
                                                                        <i class="fa-solid fa-pen-to-square"></i>
                                                                    </div>
                                                                    <div class="delete-product p-2"><i class="fa-solid fa-trash" onclick="deleteProduct(${i.ID})"></i></div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </table>
                                            </div>
                                            <div class="col-md-12 d-flex justify-content-end p-2">
                                                <c:forEach begin="1" end="${requestScope.page}" var="p">
                                                    <form action="AdminProductController" method="post">
                                                        <input type="submit" class="p-2 me-3 paging ${requestScope.curr == p ? "active-paging":""}" value="${p}">
                                                        <input type="hidden" name="index" value="${p}">
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

        <!-- Modal add product -->
        <div class="modal" id="add-product">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="py-2 header-modal d-flex justify-content-between">
                        <h4 class="modal-title inter ms-3">Product Add</h4>
                        <div class="btn-close-modal me-3" data-bs-dismiss="modal"><i class="fa-solid fa-x"></i></div>
                    </div>

                    <!-- Modal body -->
                    <form action="ProcessAdminProductController" method="post">
                        <div class="modal-body">
                            <div class="p-2">
                                <table class="w-100 table-modal">
                                    <tr>
                                        <td class="w-20"><span class="py-2">Name:</span></td>
                                        <td class="py-2"><input id="add-name" name="add-name" type="text" class="ps-2 p-1 w-100" required></td>
                                    </tr>
                                    <tr>
                                        <td><span class="py-2">Category:</span></td>
                                        <td class="py-2"><select name="add-cateID" id="add-cateID" class="ps-2 p-1" required>
                                                <option value="" selected disabled>Choose</option>
                                                <c:forEach items="${requestScope.listc}" var="i">
                                                    <option value="${i.cateID}">${i.cateName}</option>
                                                </c:forEach>
                                            </select></td>
                                    </tr>
                                    <tr>
                                        <td><span class="py-2">Price:</span></td>
                                        <td class="py-2"><input name="add-price" type="number" class="ps-2 p-1 w-100 required"></td>
                                    </tr>
                                    <tr>
                                        <td><span class="py-2">Quantity:</span></td>
                                        <td>
                                            <div class="btn-quantity w-100 d-flex justify-content-start p-2">
                                                <div class="btn btn-secondary rounded-0 w-10 text-center p-2"
                                                     id="quantity-down" onclick="sub('quantity1')">
                                                    <span>-</span>
                                                </div>
                                                <div class="button w-15">
                                                    <input name="add-quantity" type="number" class="text-center w-100 p-2" id="quantity1"
                                                           value="5" required>
                                                </div>
                                                <div class="btn btn-secondary rounded-0 w-10 text-center p-2"
                                                     id="quantity-up" onclick="add('quantity1')">
                                                    <span>+</span>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><span class="py-2">Rate:</span></td>
                                        <td>
                                            <div class="btn-quantity w-100 d-flex justify-content-start p-2">
                                                <div class="btn btn-secondary rounded-0 w-10 text-center p-2"
                                                     id="quantity-down" onclick="subfloat('rate1')">
                                                    <span>-</span>
                                                </div>
                                                <div class="button w-15">
                                                    <input name="add-rate" type="text" pattern="[0-9]*[.,]?[0-9]+" class="text-center w-100 p-2" id="rate1"
                                                           value="5" required readonly>
                                                </div>
                                                <div class="btn btn-secondary rounded-0 w-10 text-center p-2"
                                                     id="quantity-up" onclick="addfloat('rate1')">
                                                    <span>+</span>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <div class="title-product-modal p-2 my-1">
                                <span class="w-100">Description: </span>
                            </div>
                            <div class="p-2 description-text">
                                <textarea id="add-description" name="add-description" rows="4" cols="50" class="w-100 p-2" required></textarea>
                            </div>
                            <div class="title-product-modal p-2 my-1">
                                <span class="w-100">URL image: </span>
                            </div>
                            <div class="p-2 description-text">
                                <textarea id="add-image" name="add-image" rows="4" cols="50" class="w-100 p-2" required></textarea>
                            </div>

                        </div>

                        <!-- Modal footer -->
                        <div class="footer-modal py-4 d-flex justify-content-end">
                            <div class="close me-4">
                                <div class="modal-btn-close p-2 px-4" data-bs-dismiss="modal"><span>Close</span></div>
                            </div>
                            <div class="save-modal me-4">
                                <input type="hidden" name="adminProductAction" value="add-product">
                                <input type="submit" value="Save" class="input-submit p-2 px-4 inter">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- modal edit product -->
        <div class="modal" id="edit-product">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <form action="ProcessAdminProductController" method="post">
                        <!-- Modal Header -->
                        <div class="py-2 header-modal d-flex justify-content-between">
                            <h4 class="modal-title inter ms-3">Product Modify</h4>
                            <div class="btn-close-modal me-3" data-bs-dismiss="modal"><i class="fa-solid fa-x"></i></div>
                        </div>

                        <!-- Modal body -->
                        <div class="modal-body">
                            <div class="p-2">
                                <table class="w-100 table-modal">
                                    <tr>
                                        <td class="w-20"><span class="py-2">Name:</span></td>
                                        <td class="py-2"><input name="edit-name" type="text" class="ps-2 p-1 w-100" id="edit-productName" required></td>
                                    </tr>
                                    <tr>
                                        <td><span class="py-2">Category:</span></td>
                                        <td class="py-2"><select name="edit-cateID" id="edit-cateID" class="ps-2 p-1" required>
                                                <option value="" selected disabled>Choose</option>
                                                <c:forEach items="${requestScope.listc}" var="i">
                                                    <option value="${i.cateID}">${i.cateName}</option>
                                                </c:forEach>
                                            </select></td>
                                    </tr>
                                    <tr>
                                        <td><span class="py-2">Price:</span></td>
                                        <td class="py-2"><input name="edit-price" type="number" class="ps-2 p-1 w-100" id="edit-price" required></td>
                                    </tr>
                                    <tr>
                                        <td><span class="py-2">Quantity:</span></td>
                                        <td>
                                            <div class="btn-quantity w-100 d-flex justify-content-start p-2">
                                                <div class="btn btn-secondary rounded-0 w-10 text-center p-2"
                                                     id="quantity-down" onclick="sub('quantity2')">
                                                    <span>-</span>
                                                </div>
                                                <div class="button w-15">
                                                    <input name="edit-quantity" type="number" class="text-center w-100 p-2" id="quantity2"
                                                           required>
                                                </div>
                                                <div class="btn btn-secondary rounded-0 w-10 text-center p-2"
                                                     id="quantity-up" onclick="add('quantity2')">
                                                    <span>+</span>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><span class="py-2">Rate:</span></td>
                                        <td>
                                            <div class="btn-quantity w-100 d-flex justify-content-start p-2">
                                                <div class="btn btn-secondary rounded-0 w-10 text-center p-2"
                                                     id="quantity-down" onclick="subfloat('rate2')">
                                                    <span>-</span>
                                                </div>
                                                <div class="button w-15">
                                                    <input name="edit-rate" type="text" pattern="[0-9]*[.,]?[0-9]+" class="text-center w-100 p-2" id="rate2" required>
                                                </div>
                                                <div class="btn btn-secondary rounded-0 w-10 text-center p-2"
                                                     id="quantity-up" onclick="addfloat('rate2')">
                                                    <span>+</span>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <div class="title-product-modal p-2 my-1">
                                <span class="w-100">Description: </span>
                            </div>
                            <div class="p-2 description-text">
                                <textarea name="edit-description" id="edit-description" rows="4" cols="50" class="w-100 p-2" required></textarea>
                            </div>
                            <div class="title-product-modal p-2 my-1">
                                <span class="w-100">URL image: </span>
                            </div>
                            <div class="p-2 description-text">
                                <textarea name="edit-image" id="edit-image" rows="4" cols="50" class="w-100 p-2" required></textarea>
                            </div>

                        </div>

                        <!-- Modal footer -->
                        <div class="footer-modal py-4 d-flex justify-content-end">
                            <div class="close me-4">
                                <div class="modal-btn-close p-2 px-4" data-bs-dismiss="modal"><span>Close</span></div>
                            </div>
                            <div class="save-modal me-4">
                                <input type="hidden" name="productID" id="edit-productID">
                                <input type="hidden" name="adminProductAction" value="edit-product">
                                <input type="submit" value="Save" class="input-submit p-2 px-4 inter">
                            </div>
                        </div>
                        <form>
                            </div>
                            </div>
                            </div>
                            <!--script js bootstrap-->
                            <script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>
                            <script src="./assets/js/bootstrap/bootstrap.min.js"></script>
                            <script src="./assets/js/bootstrap/bootstrap.esm.min.js"></script>
                            <script>
                                                         function sub(a) {
                                                             var input = document.getElementById(a);
                                                             if (input.value > 1) {
                                                                 input.value = parseInt(input.value) - 1;
                                                             }
                                                         }
                                                         function subfloat(a) {
                                                             var input = document.getElementById(a);
                                                             if (parseFloat(input.value) >= 0.5) {
                                                                 input.value = parseFloat(input.value) - 0.5;
                                                             }
                                                         }
                                                         function add(a) {
                                                             var input = document.getElementById(a);
                                                             if (input.value < 100) {
                                                                 input.value = parseInt(input.value) + 1;
                                                             }
                                                         }
                                                         function addfloat(a) {
                                                             var input = document.getElementById(a);
                                                             if (parseFloat(input.value) < 5) {
                                                                 input.value = parseFloat(input.value) + 0.5;
                                                             }
                                                         }
                            </script>
                            <script>
                                function editProduct(productID) {
                                    console.log(productID);
                                    console.log("edit-product ne ");
                                    var productName = "";
                                    var cateID = "";
                                    var quantity = "";
                                    var price = "";
                                    var rating = "";
                                    var description = "";
                                    var image = "";
                                    $.ajax({
                                        url: "AjaxAdminProductController",
                                        type: "post", //send it through post method
                                        data: {
                                            ajaxAdminAction: "edit-product",
                                            txtProductID: productID
                                        },
                                        success: function (response) {
                                            console.log("response", response);
                                            productName = response.productName;
                                            cateID = response.cateID;
                                            quantity = parseInt(response.quantity);
                                            price = parseInt(response.price);
                                            rating = parseFloat(response.rating);
                                            description = response.description;
                                            image = response.image;
                                            document.getElementById("edit-productID").value = productID;
                                            document.getElementById("edit-productName").value = productName;
                                            document.getElementById("edit-cateID").value = cateID;
                                            document.getElementById("edit-price").value = price;
                                            document.getElementById("quantity2").value = quantity;
                                            document.getElementById("rate2").value = rating;
                                            document.getElementById("edit-description").value = description;
                                            document.getElementById("edit-image").value = image;
                                        },
                                        error: function (xhr) {
                                            //Do Something to handle error
                                            alert("Có lỗi xảy ra, vui lòng thử lại sau ít phút");
                                        }
                                    });
                                }
                            </script>
                            <script>
                                function deleteProduct(productID) {
                                    var form = document.createElement('form');
                                    form.action = "ProcessAdminProductController";
                                    form.method = "post";

                                    // Tạo một input cho userID
                                    var inputUserID = document.createElement('input');
                                    inputUserID.type = 'text';
                                    inputUserID.name = 'productID';
                                    inputUserID.value = productID;

                                    // Tạo một input cho admin-action
                                    var inputAction = document.createElement('input');
                                    inputAction.type = 'text';
                                    inputAction.name = 'adminProductAction';
                                    inputAction.value = 'delete-product';

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
