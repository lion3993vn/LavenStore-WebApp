
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
        <link rel="stylesheet" href="./assets/css/styleadmin-product.css">
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
                                <td class="py-1 ps-3 hover-dashboard"><a href="" class=""><i class="fa-solid fa-user"></i>
                                        <span>User Management</span></a></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td class="py-1 ps-3 hover-dashboard"><a href="" class=""><i class="fa-solid fa-cart-shopping"></i>
                                        <span>Order Management</span></a></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td class="py-1 ps-3 active"><a href="" class=""><i
                                            class="fa-solid fa-box-open"></i> <span>Product Management</span></a></td>
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
                                <p class="m-0">Home</p><span>/Product Management</span>
                            </div>
                        </div>
                        <div class="col-md-2 d-flex align-content-center justify-content-center">
                            <div class="pos-nav d-flex align-content-center p-2 py-3">
                                <p class="m-0">phamhieu</p>
                            </div>
                            <div class="icon-nav-log p-2 py-3">
                                <i class="fa-solid fa-power-off"></i>
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
                                            <div class="col-md-12 d-flex justify-content-start">
                                                <div class="search-user p-3"><input type="text" class="p-1 ps-3"
                                                                                    placeholder="Search Product"></div>
                                                <div class="filter-status p-3">
                                                    <select name="" id="" class="p-1">
                                                        <option value="" selected disabled>Type</option>
                                                        <option value="">Cancelled</option>
                                                        <option value="">Pending</option>
                                                        <option value="">Completed</option>
                                                    </select>
                                                </div>
                                                <div class="search-user-submit p-3"><input type="submit" class="p-1 px-3"
                                                                                               value="GO"></div>
                                            </div>
                                            <div class="col-md-12 p-0">
                                                <table class="w-100 table-body">
                                                    <tr class="table-header">
                                                        <td class="p-3 px-4 w-5"><span class="float-start">ProductID</span></td>
                                                        <td class="p-3 px-4 w-15"><span class="float-start">Name</span></td>
                                                        <td class="p-3 px-4 w-10"><span class="float-start">Type</span></td>
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
                                                                    <div class="edit-product p-2" data-bs-toggle="modal" data-bs-target="#edit-product">
                                                                        <i class="fa-solid fa-pen-to-square"></i>
                                                                    </div>
                                                                    <div class="delete-product p-2"><i class="fa-solid fa-trash"></i></div>
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
                    <div class="modal-body">
                        <div class="p-2">
                            <table class="w-100 table-modal">
                                <tr>
                                    <td class="w-20"><span class="py-2">Name:</span></td>
                                    <td class="py-2"><input type="text" class="ps-2 p-1 w-100"></td>
                                </tr>
                                <tr>
                                    <td><span class="py-2">Type:</span></td>
                                    <td class="py-2"><select name="" id="" class="ps-2 p-1">
                                            <option value="" selected disabled>Choose</option>
                                            <option value="">Birthday</option>
                                            <option value="">Weeding</option>
                                            <option value="">Boxes</option>
                                        </select></td>
                                </tr>
                                <tr>
                                    <td><span class="py-2">Price:</span></td>
                                    <td class="py-2"><input type="text" class="ps-2 p-1 w-100"></td>
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
                                                <input type="number" class="text-center w-100 p-2" id="quantity1"
                                                       value="5"></input>
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
                                                <input type="text" pattern="[0-9]*[.,]?[0-9]+" class="text-center w-100 p-2" id="rate1"
                                                       value="5"></input>
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
                            <textarea id="myTextarea" name="myTextarea" rows="4" cols="50" class="w-100 p-2"></textarea>
                        </div>
                        <div class="title-product-modal p-2 my-1">
                            <span class="w-100">URL image: </span>
                        </div>
                        <div class="p-2 description-text">
                            <textarea id="myTextarea" name="myTextarea" rows="4" cols="50" class="w-100 p-2"></textarea>
                        </div>

                    </div>

                    <!-- Modal footer -->
                    <div class="footer-modal py-4 d-flex justify-content-end">
                        <div class="close me-4">
                            <div class="modal-btn-close p-2 px-4" data-bs-dismiss="modal"><span>Close</span></div>
                        </div>
                        <div class="save-modal me-4">
                            <input type="submit" value="Save" class="input-submit p-2 px-4 inter">
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <!-- modal edit product -->
        <div class="modal" id="edit-product">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">

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
                                    <td class="py-2"><input type="text" class="ps-2 p-1 w-100"></td>
                                </tr>
                                <tr>
                                    <td><span class="py-2">Type:</span></td>
                                    <td class="py-2"><select name="" id="" class="ps-2 p-1">
                                            <option value="" selected disabled>Choose</option>
                                            <option value="">Birthday</option>
                                            <option value="">Weeding</option>
                                            <option value="">Boxes</option>
                                        </select></td>
                                </tr>
                                <tr>
                                    <td><span class="py-2">Price:</span></td>
                                    <td class="py-2"><input type="text" class="ps-2 p-1 w-100"></td>
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
                                                <input type="number" class="text-center w-100 p-2" id="quantity2"
                                                       value="5"></input>
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
                                                <input type="text" pattern="[0-9]*[.,]?[0-9]+" class="text-center w-100 p-2" id="rate2"
                                                       value="5"></input>
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
                            <textarea id="myTextarea" name="myTextarea" rows="4" cols="50" class="w-100 p-2"></textarea>
                        </div>
                        <div class="title-product-modal p-2 my-1">
                            <span class="w-100">URL image: </span>
                        </div>
                        <div class="p-2 description-text">
                            <textarea id="myTextarea" name="myTextarea" rows="4" cols="50" class="w-100 p-2"></textarea>
                        </div>

                    </div>

                    <!-- Modal footer -->
                    <div class="footer-modal py-4 d-flex justify-content-end">
                        <div class="close me-4">
                            <div class="modal-btn-close p-2 px-4" data-bs-dismiss="modal"><span>Close</span></div>
                        </div>
                        <div class="save-modal me-4">
                            <input type="submit" value="Save" class="input-submit p-2 px-4 inter">
                        </div>
                    </div>

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
    </body>

</html>
