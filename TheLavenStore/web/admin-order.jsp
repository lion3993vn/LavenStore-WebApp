<%-- 
    Document   : admin-order
    Created on : Mar 2, 2024, 3:27:59 PM
    Author     : Pham Hieu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        <link rel="stylesheet" href="./assets/css/styleadmin-order.css">
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
                                <td class="py-1 ps-3 active"><a href="" class=""><i class="fa-solid fa-cart-shopping"></i>
                                        <span>Order Management</span></a></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td class="py-1 ps-3 hover-dashboard"><a href="" class=""><i
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
                                <p class="m-0">Home</p><span>/User Management</span>
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
                                        <span class="ms-3">Order Management</span>
                                    </div>
                                </div>
                                <div class="body-center">
                                    <div class="container-fluid">
                                        <div class="row"><form action="admin-order" method="post">
                                            <div class="col-md-12 d-flex justify-content-start">
                                                <div class="search-user p-3"><input type="text" class="p-1 ps-3"
                                                                                    placeholder="Search Order Code" name="searchCode"
                                                                                    value="${requestScope.searchCode}"></div>
                                                <div class="filter-status p-3">
                                                    <select name="status-search" id="" class="p-1">
                                                        <option value="" ${requestScope.statusSeach == null ? "selected":""} disabled>Status</option>
                                                        <option value="CANCELLED" ${requestScope.statusSeach eq "CANCELLED" ? "selected":""}>Cancelled</option>
                                                        <option value="PENDING" ${requestScope.statusSeach eq "PENDING" ? "selected":""}>Pending</option>
                                                        <option value="COMPLETED" ${requestScope.statusSeach eq "COMPLETED" ? "selected":""}>Completed</option>
                                                        <option value="NONE" ${requestScope.statusSeach eq "NONE" ? "selected":""}>None</option>
                                                    </select>
                                                </div>
                                                <div class="search-user-submit p-3"><input type="submit" class="p-1 px-3"
                                                                                    value="GO"></div>
                                            </div></form>
                                            <div class="col-md-12 p-0">
                                                <table class="w-100 table-body">
                                                    <tr class="table-header">
                                                        <td class="p-3 px-4"><span class="float-start">OrderCode</span></td>
                                                        <td class="p-3 px-4 "><span class="float-start">Customer</span></td>
                                                        <td class="p-3 px-4 "><span class="float-start">Date</span></td>
                                                        <td class="p-3 px-4 "><span class="float-start">Location</span></td>
                                                        <td class="p-3 px-4 "><span class="float-start">Phone number</span>
                                                        </td>
                                                        <td class="p-3 px-4 "><span class="float-start">Method</span></td>
                                                        <td class="p-3 px-4 "><span class="float-start">Price</span></td>
                                                        <td class="p-3 px-4 "><span class="">Status</span></td>
                                                        <td class="p-3 px-4 "><span>Action</span></td>
                                                    </tr>
                                                    <c:forEach items="${listorder}" var="o">
                                                        <tr class="table-content">
                                                            <td class="p-3 px-4 "><span class="float-start">${o.orderCode}</span></td>
                                                                <c:forEach items="${listuser}" var="u">
                                                                    <c:if test="${o.userID == u.ID}">
                                                                    <td class="p-3 px-4"><span class="float-start">${u.userName}</span></td>
                                                                    </c:if>
                                                                </c:forEach>
                                                            <td class="p-3 px-4 "><span class="float-start">${o.date}</span></td>
                                                            <td class="p-3 px-4 "><span class="float-start">${o.location}</span></td>
                                                            <td class="p-3 px-4 "><span class="float-start">${o.phoneNumber}</span>
                                                            </td>
                                                            <td class="p-3 px-4 "><span class="float-start">${o.paymentMethod}</span></td>
                                                            <td class="p-3 px-4 "><span class="float-start">${o.amount}</span></td>
                                                            <td class="p-3 px-4 ">
                                                                <c:if test="${o.status eq 'COMPLETED'}">
                                                                    <div class="status-order-complete px-1 py-2">
                                                                        <span class="inter">${o.status}</span>
                                                                    </div>
                                                                </c:if>
                                                                <c:if test="${o.status eq 'PENDING'}">
                                                                    <div class="status-order-pending px-1 py-2">
                                                                        <span class="inter">PENDING</span>
                                                                    </div>
                                                                </c:if>
                                                                <c:if test="${o.status eq 'CANCELLED'}">
                                                                    <div class="status-order-cancelled px-1 py-2">
                                                                        <span class="inter">CANCELLED</span>
                                                                    </div>
                                                                </c:if>
                                                            </td>
                                                            <td class="p-3 px-4">
                                                                <div class="view-details d-flex justify-content-center" onclick="formdetails('${o.ID}')">
                                                                    <div class="edit-order p-2">
                                                                        <i class="fa-solid fa-pen-to-square"></i>
                                                                    </div>
                                                                    <div class="edit-user-2 p-2 ps-1"><span>View Details</span></i>
                                                                    </div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
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

        <!-- Modal details order -->
        <!--script js bootstrap-->
        <script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="./assets/js/bootstrap/bootstrap.min.js"></script>
        <script src="./assets/js/bootstrap/bootstrap.esm.min.js"></script>
        <script>
            function formdetails(id){
                var form = document.createElement('form');
                form.setAttribute('action', 'admin-order-modify'); // Chỉnh action
                form.setAttribute('method', 'post'); // Chỉnh method
                
                var input = document.createElement('input');
                input.setAttribute('type', 'text');
                input.setAttribute('name', 'orderID');
                input.value = id;
                
                form.appendChild(input);
                
                form.style.display = 'none';
                document.body.appendChild(form);
                form.submit();
            }
        </script>
    </body>

</html>