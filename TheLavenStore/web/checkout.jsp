<%-- 
    Document   : checkout
    Created on : Mar 7, 2024, 2:16:28 AM
    Author     : Pham Hieu
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>TODO supply a title</title>
        <link rel="stylesheet" href="./assets/css/bootstrap/bootstrap.css">

        <!-- link font google -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Vidaloka">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Myanmar Sans Pro">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Dongle">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inter">
        <!-- link css checkout -->
        <link rel="stylesheet" href="./assets/css/stylecheckout.css">

        <!-- link font icon -->
        <link rel="stylesheet" href="./assets/css/font/css/all.css">

    </head>

    <body>
        <section>
            <banner>
                <div class="banner container-fluid pb-5 mb-5">
                    <h1 class="pt-5">Checkout</h1>
                    <p class="myhome"><a href="MainController">Home</a><span><i class="px-4 fa-solid fa-chevron-right"></i></span><a href="MainController?action=Cart">Cart</a><span><i class="px-4 fa-solid fa-chevron-right"></i></span><a href="#">Checkout</a>
                    </p>
                </div>
            </banner>
        </section>
        <form class="w-100" action="PaymentController" method="post">
            <div class="background-container">
                <div class="container">
                    <div class="row">
                        <div class="col-md-7">
                            <div class="w-100">
                                <div class="profile-info w-100 d-flex justify-content-center">
                                    <table class="w-75">
                                        <tr>
                                            <td class="py-2"><span class="Vidaloka" style="font-size: 25px;">Delivery
                                                    information</span></td>
                                        </tr>
                                        <tr>
                                            <td class="py-1"><input type="text" name="Fullname" id="" placeholder="Fullname" value="${sessionScope.account.fullName}"
                                                                    class="w-100 p-1 ps-2 nochange" readonly></td>
                                        </tr>
                                        <tr>
                                            <td class="py-1"><input type="text" name="Email" id="" placeholder="Email" value="${sessionScope.account.email}"
                                                                    class="w-100 p-1 ps-2 nochange" readonly></td>
                                        </tr>
                                        <tr>
                                            <td class="py-1"><input type="text" name="phoneNumber" id="" placeholder="Mobile Phone" value="${sessionScope.account.phoneNumber}"
                                                                    class="w-100 p-1 ps-2" required=""></td>
                                        </tr>
                                        <tr>
                                            <td class="py-1"><input type="text" name="address" id="" placeholder="Address" value="${sessionScope.account.address}"
                                                                    class="w-100 p-1 ps-2" required=""></td>
                                        </tr>
                                        <tr>
                                            <td class="py-1"><textarea name="note" id="" cols="30" rows="3" placeholder="Note"
                                                                       style="resize: none;" class="w-100 p-1 ps-2"></textarea></td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="product-info w-100 mt-3">
                                    <table class="w-100">
                                        <tr style="border-bottom: 1px solid black;">
                                            <td class="w-20 py-1 text-center"><span class="Dongle">Product</span></td>
                                            <td class="py-1"><span></span></td>
                                            <td class="py-1 text-center"><span class="Dongle">Price</span></td>
                                            <td class="py-1 text-center"><span class="Dongle">Quantity</span></td>
                                            <td class="py-1 text-center"><span class="Dongle">Total</span></td>
                                        </tr>
                                        <c:forEach items="${requestScope.cartList.items}" var="o">
                                            <tr>
                                                <td class="w-20 py-1">
                                                    <div class="item w-100 text-center"><img src="${o.product.image}" alt=""
                                                                                             class="w-50"></div>
                                                </td>
                                                <td class="py-1 text-center"><span class="Dongle">${o.product.name}</span></td>
                                                <td class="py-1 text-center"><span class="Dongle">VND <fmt:formatNumber pattern="#,###" value="${o.price}"/></span></td>
                                                <td class="py-1 text-center"><span class="Dongle">${o.quantity}</span></td>
                                                <td class="py-1 text-center"><span class="Dongle">VND <fmt:formatNumber pattern="#,###" value="${(o.price*o.quantity)}"/></span></td>
                                                <td class="py-1 text-center w-15"><span class="Dongle text-center" style="color: #FF0000;">${requestScope['error'.concat(o.product.ID)]}</span></td>
                                            </tr>
                                        </c:forEach>
                                        <c:if test="${requestScope.error == 1}">
                                            <tr style="border-top: 1px solid black;">
                                                <td class="w-20 py-3" style=""><div class="p-3 text-center" style="background-color: rgb(245, 245, 245); border-radius: 10px"><a href="#" style="text-decoration: none; color: #FF0000; font-weight: 600">Quay về Cart</a></div></td>
                                        </tr>
                                        </c:if>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-5">
                            <div class="w-100">
                                <div class="w-100 d-flex justify-content-center">
                                    <div class="w-80 table-money px-4 py-3">
                                        <table class="w-100">
                                            <tr>
                                                <td colspan="2" class="py-2 w-50"><Span class="Vidaloka"
                                                                                        style="font-size: 25px;">Cart Total</Span></td>
                                            </tr>
                                            <tr>
                                                <td class="py-2 w-50 text-center"><span style="color: #757575;"
                                                                                        class="float-start">Subtotal:</span></td>
                                                <td class="py-2 w-50 text-center"><span class="float-end"
                                                                                        style="font-weight: 600;">VND <fmt:formatNumber pattern="#,###" value="${requestScope.cartList.totalInCart}"/> </span></td>
                                            </tr>
                                            <tr>
                                                <td class="py-2 w-50 text-center"><span style="color: #757575;"
                                                                                        class="float-start">Shipping:</span></td>
                                                <td class="py-2 w-50 text-center"><span class="float-end"
                                                                                        style="font-weight: 600;">VND 25,000</span></td>
                                            </tr>
                                            <tr style="border-top: 1px solid black;">
                                                <td class="py-2 w-50 text-center"><span class="float-start"
                                                                                        style="font-weight: 600;">Total:</span></td>
                                                <td class="py-2 w-50 text-center"><span class="float-end"
                                                                                        style="color: #FF0000; font-weight: 600;"> VND <fmt:formatNumber pattern="#,###" value="${requestScope.cartList.totalInCart + 25000}"/></span></td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="w-100 mt-5 d-flex justify-content-center">
                                <div class="w-80 px-4 py-3 d-flex flex-column justify-content-center payment-choose">
                                    <span class="Vidaloka m-2" style="font-size: 25px;">Payment Method</span>
                                    <div class="w-100 d-flex flex-column justify-content-center">
                                        <div class="payment w-100 d-flex justify-content-between pe-2" onclick="checkradio('vnpay')" style="cursor: pointer;">
                                            <div class="">
                                                <img src="./assets/img/checkout/vnpay.png" alt="" class="w-25">
                                                <span class="Dongle ps-2">VNPAY</span>
                                            </div>
                                            <input type="radio" name="payment" value="vnpay" id="vnpay" class="float-end">
                                        </div>
                                        <div class="mt-3 payment w-100 d-flex justify-content-between pe-2" onclick="checkradio('cod')" style="cursor: pointer;">
                                            <div class="">
                                                <img src="./assets/img/checkout/cod-icon.png" alt="" class="w-25">
                                                <span class="Dongle ps-2">PAY ON DELIVERY</span>
                                            </div>
                                            <input type="radio" name="payment" value="cod" id="cod" class="float-end">
                                        </div>
                                    </div>
                                    <div class="terms d-flex justify-content-center mt-3">
                                        <input type="checkbox" required="">
                                        <p class="m-0 ps-3">I have read and accept the terms and conditions</p>
                                    </div>
                                    <div class="w-100 d-flex justify-content-center mt-3">
                                        <input type="submit" value="Payment" class="w-90 submit py-2">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>

        <script src="./assets/js/bootstrap/jquery.min.js"></script>
        <script src="./assets/js/bootstrap/popper.min.js"></script>
        <script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="./assets/js/bootstrap/bootstrap.min.js"></script>
        <script src="./assets/js/bootstrap/bootstrap.esm.min.js"></script>
        <script>
                                            function checkradio(a) {
                                                var input = document.getElementById(a);
                                                input.checked = true;
                                            }
        </script>
    </body>

</html>