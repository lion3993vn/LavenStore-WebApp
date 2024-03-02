<%-- 
    Document   : admin-order
    Created on : Mar 2, 2024, 3:27:59 PM
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
                                    <div class="row">
                                        <div class="col-md-12 d-flex justify-content-start">
                                            <div class="search-user p-3"><input type="text" class="p-1 ps-3"
                                                    placeholder="Search user"></div>
                                            <div class="filter-status p-3">
                                                <select name="" id="" class="p-1">
                                                    <option value="" selected disabled>Status</option>
                                                    <option value="">Cancelled</option>
                                                    <option value="">Pending</option>
                                                    <option value="">Completed</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-12 p-0">
                                            <table class="w-100 table-body">
                                                <tr class="table-header">
                                                    <td class="p-3 px-4"><span class="float-start">OrderID</span></td>
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
                                                <tr class="table-content">
                                                    <td class="p-3 px-4 "><span class="float-start">200803</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">taile03</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">2024-1-2 13:23:44</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">Thu Duc</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">0903112345</span>
                                                    </td>
                                                    <td class="p-3 px-4 "><span class="float-start">VNPAY</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">250.000</span></td>
                                                    <td class="p-3 px-4 ">
                                                        <div class="status-order-complete px-1 py-2">
                                                            <span class="inter">COMPLETED</span>
                                                        </div>
                                                    </td>
                                                    <td class="p-3 px-4">
                                                        <div class="view-details d-flex justify-content-center" data-bs-toggle="modal" data-bs-target="#details-order">
                                                            <div class="edit-order p-2">
                                                                <i class="fa-solid fa-pen-to-square"></i>
                                                            </div>
                                                            <div class="edit-user-2 p-2 ps-1"><span>View Details</span></i>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr class="table-content">
                                                    <td class="p-3 px-4 "><span class="float-start">200803</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">taile03</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">2024-1-2 13:23:44</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">Thu Duc</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">0903112345</span>
                                                    </td>
                                                    <td class="p-3 px-4 "><span class="float-start">VNPAY</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">250.000</span></td>
                                                    <td class="p-3 px-4 ">
                                                        <div class="status-order-cancelled px-1 py-2">
                                                            <span class="inter">CANCELLED</span>
                                                        </div>
                                                    </td>
                                                    <td class="p-3 px-4">
                                                        <div class="view-details d-flex justify-content-center" data-bs-toggle="modal" data-bs-target="#details-order">
                                                            <div class="edit-order p-2">
                                                                <i class="fa-solid fa-pen-to-square"></i>
                                                            </div>
                                                            <div class="edit-user-2 p-2 ps-1"><span>View Details</span></i>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr class="table-content">
                                                    <td class="p-3 px-4 "><span class="float-start">200803</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">taile03</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">2024-1-2 13:23:44</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">Thu Duc</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">0903112345</span>
                                                    </td>
                                                    <td class="p-3 px-4 "><span class="float-start">VNPAY</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">250.000</span></td>
                                                    <td class="p-3 px-4 ">
                                                        <div class="status-order-pending px-1 py-2">
                                                            <span class="inter">PENDING</span>
                                                        </div>
                                                    </td>
                                                    <td class="p-3 px-4">
                                                        <div class="view-details d-flex justify-content-center" data-bs-toggle="modal" data-bs-target="#details-order">
                                                            <div class="edit-order p-2">
                                                                <i class="fa-solid fa-pen-to-square"></i>
                                                            </div>
                                                            <div class="edit-user-2 p-2 ps-1"><span>View Details</span></i>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr class="table-content">
                                                    <td class="p-3 px-4 "><span class="float-start">200803</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">taile03</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">2024-1-2 13:23:44</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">Thu Duc</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">0903112345</span>
                                                    </td>
                                                    <td class="p-3 px-4 "><span class="float-start">VNPAY</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">250.000</span></td>
                                                    <td class="p-3 px-4 ">
                                                        <div class="status-order-complete px-1 py-2">
                                                            <span class="inter">COMPLETED</span>
                                                        </div>
                                                    </td>
                                                    <td class="p-3 px-4">
                                                        <div class="view-details d-flex justify-content-center" data-bs-toggle="modal" data-bs-target="#details-order">
                                                            <div class="edit-order p-2">
                                                                <i class="fa-solid fa-pen-to-square"></i>
                                                            </div>
                                                            <div class="edit-user-2 p-2 ps-1"><span>View Details</span></i>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr class="table-content">
                                                    <td class="p-3 px-4 "><span class="float-start">200803</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">taile03</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">2024-1-2 13:23:44</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">Thu Duc</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">0903112345</span>
                                                    </td>
                                                    <td class="p-3 px-4 "><span class="float-start">VNPAY</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">250.000</span></td>
                                                    <td class="p-3 px-4 ">
                                                        <div class="status-order-cancelled px-1 py-2">
                                                            <span class="inter">CANCELLED</span>
                                                        </div>
                                                    </td>
                                                    <td class="p-3 px-4">
                                                        <div class="view-details d-flex justify-content-center" data-bs-toggle="modal" data-bs-target="#details-order">
                                                            <div class="edit-order p-2">
                                                                <i class="fa-solid fa-pen-to-square"></i>
                                                            </div>
                                                            <div class="edit-user-2 p-2 ps-1"><span>View Details</span></i>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr class="table-content">
                                                    <td class="p-3 px-4 "><span class="float-start">200803</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">taile03</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">2024-1-2 13:23:44</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">Thu Duc</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">0903112345</span>
                                                    </td>
                                                    <td class="p-3 px-4 "><span class="float-start">VNPAY</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">250.000</span></td>
                                                    <td class="p-3 px-4 ">
                                                        <div class="status-order-pending px-1 py-2">
                                                            <span class="inter">PENDING</span>
                                                        </div>
                                                    </td>
                                                    <td class="p-3 px-4">
                                                        <div class="view-details d-flex justify-content-center" data-bs-toggle="modal" data-bs-target="#details-order">
                                                            <div class="edit-order p-2">
                                                                <i class="fa-solid fa-pen-to-square"></i>
                                                            </div>
                                                            <div class="edit-user-2 p-2 ps-1"><span>View Details</span></i>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr class="table-content">
                                                    <td class="p-3 px-4 "><span class="float-start">200803</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">taile03</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">2024-1-2 13:23:44</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">Thu Duc</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">0903112345</span>
                                                    </td>
                                                    <td class="p-3 px-4 "><span class="float-start">VNPAY</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">250.000</span></td>
                                                    <td class="p-3 px-4 ">
                                                        <div class="status-order-cancelled px-1 py-2">
                                                            <span class="inter">CANCELLED</span>
                                                        </div>
                                                    </td>
                                                    <td class="p-3 px-4">
                                                        <div class="view-details d-flex justify-content-center" data-bs-toggle="modal" data-bs-target="#details-order">
                                                            <div class="edit-order p-2">
                                                                <i class="fa-solid fa-pen-to-square"></i>
                                                            </div>
                                                            <div class="edit-user-2 p-2 ps-1"><span>View Details</span></i>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr class="table-content">
                                                    <td class="p-3 px-4 "><span class="float-start">200803</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">taile03</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">2024-1-2 13:23:44</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">Thu Duc</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">0903112345</span>
                                                    </td>
                                                    <td class="p-3 px-4 "><span class="float-start">VNPAY</span></td>
                                                    <td class="p-3 px-4 "><span class="float-start">250.000</span></td>
                                                    <td class="p-3 px-4 ">
                                                        <div class="status-order-pending px-1 py-2">
                                                            <span class="inter">PENDING</span>
                                                        </div>
                                                    </td>
                                                    <td class="p-3 px-4">
                                                        <div class="view-details d-flex justify-content-center" data-bs-toggle="modal" data-bs-target="#details-order">
                                                            <div class="edit-order p-2">
                                                                <i class="fa-solid fa-pen-to-square"></i>
                                                            </div>
                                                            <div class="edit-user-2 p-2 ps-1"><span>View Details</span></i>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                        <div class="col-md-12 d-flex justify-content-end paging p-2">
                                            <a href="" class="p-2 me-3 active-paging">1</a>
                                            <a href="" class="p-2 me-3">2</a>
                                            <a href="" class="p-2 me-3">3</a>
                                            <a href="" class="p-2 me-3">4</a>
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
    <div class="modal" id="details-order">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="py-2 header-modal d-flex justify-content-between">
                    <h4 class="modal-title inter ms-3">Details Order</h4>
                    <div class="btn-close-modal me-3" data-bs-dismiss="modal"><i class="fa-solid fa-x"></i></div>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <table class="w-100 table-modal">
                        <tr>
                            <td class="p-2"><span>Order ID:</span></td>
                            <td class="p-2"><span>200803</span></td>
                            <td class="p-2"><span>Customer:</span></td>
                            <td class="p-2"><span>taile03</span></td>
                        </tr>
                        <tr>
                            <td class="p-2">Date:</td>
                            <td class="p-2">2024-1-2 13:23:44</td>
                            <td class="p-2">Method:</td>
                            <td class="p-2">VNPAY</td>
                        </tr>
                        <tr>
                            <td class="p-2">Location</td>
                            <td colspan="3" class="p-2"><input type="text" name="" id="" class="w-100 ps-2"></td>
                        </tr>
                        <tr>
                            <td class="p-2">Phone Number: </td>
                            <td colspan="3" class="p-2"><input type="text" name="" id="" class="w-100 ps-2"></td>
                        </tr>
                    </table>
                    <div class="title-product-modal p-2 my-1">
                        <span class="w-100">Product:</span>
                    </div>
                    <div class="p-2">
                        <table class="w-100 text-center table-product-modal">
                            <tr class="product-modal-title">
                                <td><span class="py-2">Name</span></td>
                                <td><span class="py-2">Quantity</span></td>
                                <td class="w-25"><span class="float-start py-2">Price</span></td>
                            </tr>
                            <tr class="product-modal-list">
                                <td><span class="py-2">Wild</span></td>
                                <td><span class="py-2">5</span></td>
                                <td class="w-25"><span class="float-start py-2">750.000</span></td>
                            </tr>
                            <tr class="product-modal-list">
                                <td><span class="py-2">Stylish</span></td>
                                <td><span class="py-2">7</span></td>
                                <td class="w-25"><span class="float-start py-2">750.000</span></td>
                            </tr>
                            <tr class="product-modal-title">
                                <td><span class="py-2">Total</span></td>
                                <td><span class="py-2">12</span></td>
                                <td class="w-25"><span class="float-start py-2">1.500.500</span></td>
                            </tr>
                        </table>
                    </div>
                    <div class="p-2 mt-2">
                        <table class="w-100 table-option-modal">
                        <tr>
                            <td class="py-2 w-20"><span>Status:</span></td>
                            <td class="py-2 w-80"><select name="" id="" class="ps-2">
                                <option value="">Cancelled</option>
                                <option value="">Pending</option>
                                <option value="">Completed</option>
                            </select></td>
                        </tr>
                        <tr>
                            <td class="py-2 w-20"><span>Note:</span></td>
                            <td class="py-2 w-80"><textarea id="myTextarea" name="myTextarea" rows="4" cols="50" class="w-75 p-2"></textarea></td>
                        </tr>
                    </table>
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
</body>

</html>