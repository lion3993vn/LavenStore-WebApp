<%-- 
    Document   : admin-order-modify
    Created on : Mar 3, 2024, 7:43:44 PM
    Author     : Pham Hieu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./assets/css/bootstrap/bootstrap.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noticia+Text">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inter">
        <link rel="stylesheet" href="./assets/css/font/css/all.css">
        <link rel="stylesheet" href="./assets/css/styleadmin-order-modify.css">
        <title>Document</title>
    </head>

    <body>
        <div class="container mt-5 mb-5">
            <div class="row">
                <div class="col-md-12 d-flex justify-content-center">
                    <div class="box-item w-75 p-3">
                        <form action="process-admin-order" method="post" accept-charset="UTF-8">
                            <h4 class="modal-title inter ms-3 text-center mb-3">Details Order</h4>
                            <table class="w-100 table-modal">
                                <tr>
                                    <td class="p-2"><span>Order Code:</span></td>
                                    <td class="p-2"><span>${requestScope.order.orderCode}</span></td>
                                    <td class="p-2"><span>Customer:</span></td>
                                    <td class="p-2"><span>${requestScope.user.userName}</span></td>
                                </tr>
                                <tr>
                                    <td class="p-2">Date:</td>
                                    <td class="p-2">${requestScope.order.date}</td>
                                    <td class="p-2">Method:</td>
                                    <td class="p-2">${requestScope.order.paymentMethod}</td>
                                </tr>
                                <tr>
                                    <td class="p-2">Phone Number: </td>
                                    <td colspan="3" class="p-2"><input type="text" name="phonenumber" id="" class="w-50 ps-2 py-1" value="${requestScope.order.phoneNumber}"></td>
                                </tr>
                                <tr>
                                    <td class="p-2">Location: </td>
                                    <td colspan="3" class="p-2"><input type="text" name="location" id="" class="w-100 ps-2 py-1" value="${requestScope.order.location}"></td>
                                </tr>
                            </table>
                            <div class="title-product-modal p-2 my-1">
                                <span class="w-100">Product:</span>
                            </div>
                            <div class="p-2">
                                <table class="w-100 text-center table-product-modal">
                                    <tr class="product-modal-title">
                                        <td><span class="py-2">ID</span></td>
                                        <td><span class="py-2">Name</span></td>
                                        <td><span class="py-2">Quantity</span></td>
                                        <td class="w-25"><span class="float-start py-2">Price</span></td>
                                    </tr>
                                    <c:forEach items="${requestScope.listOrderDetails}" var="lod">
                                        <tr class="product-modal-list">
                                            <c:forEach items="${requestScope.listproduct}" var="lp">
                                                <c:if test="${lod.productID == lp.ID}">
                                                    <td><span class="py-2">${lp.ID}</span></td>
                                                    <td><span class="py-2">${lp.name}</span></td>
                                                    </c:if>
                                                </c:forEach>
                                            <td><span class="py-2">${lod.quantity}</span></td>
                                            <td class="w-25"><span class="float-start py-2"><fmt:formatNumber pattern="#,###" value="${lod.price}"/></span></td>
                                        </tr>
                                    </c:forEach>
                                    <tr class="product-modal-title">
                                        <td><span class="py-2"></span></td>
                                        <td><span class="py-2"style="color: #FFCC33">Total</span></td>
                                        <td><span class="py-2 text-important">${requestScope.totalQuantity}</span></td>
                                        <td class="w-25"><span class="float-start py-2 text-important"><fmt:formatNumber pattern="#,###" value="${requestScope.order.amount}"/></span></td>
                                    </tr>
                                </table>
                            </div>
                            <div class="p-2 mt-2">
                                <table class="w-100 table-option-modal">
                                    <tr>
                                        <td class="py-2 w-20"><span>Status:</span></td>
                                        <td class="py-2 w-80"><select name="status" id="" class="ps-2 py-1">
                                                <option value="CANCELLED" ${(requestScope.order.status == "CANCELLED") ? "selected":""}>Cancelled</option>
                                                <option value="PENDING" ${(requestScope.order.status == "PENDING") ? "selected":""}>Pending</option>
                                                <option value="COMPLETED" ${(requestScope.order.status == "COMPLETED") ? "selected":""}>Completed</option>
                                            </select></td>
                                    </tr>
                                    <tr>
                                        <td class="py-2 w-20"><span>Note:</span></td>
                                        <td class="py-2 w-80"><textarea id="myTextarea" name="note" rows="4" cols="50"
                                                                        class="w-75 p-2">${requestScope.order.note}</textarea></td>
                                    </tr>
                                </table>
                            </div>
                            <div class="footer-modal py-4 d-flex justify-content-end">
                                <div class="close me-4">
                                    <div class="modal-btn-close p-2 px-4"><a href="admin-order" style="color: white; text-decoration: none">Cancel</a></div>
                                </div>
                                <div class="save-modal me-4">
                                    <input type="hidden" value="${requestScope.order.ID}" name="orderID">
                                    <input type="submit" value="Save" class="input-submit p-2 px-4 inter">
                                </div>
                            </div>
                            <div class="mt-2 w-100">
                                <span style="color: red">${requestScope.error}</span>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="./assets/js/bootstrap/bootstrap.min.js"></script>
        <script src="./assets/js/bootstrap/bootstrap.esm.min.js"></script>
    </body>

</html>