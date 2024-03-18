<%-- 
    Document   : cart
    Created on : Feb 28, 2024, 6:26:10 PM
    Author     : Pham Hieu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Cart | The LAVEN STORE</title>
        <link rel="icon" type="image/x-icon" href="assets/img/logo.png">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./assets/css/bootstrap/bootstrap.css">

        <!-- link font google -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Vidaloka">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Myanmar Sans Pro">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Dongle">

        <!-- link css cart -->
        <link rel="stylesheet" href="./assets/css/stylecart.css">

        <!-- link font icon -->
        <link rel="stylesheet" href="./assets/css/font/css/all.css">
    </head>

    <body>
        <c:import url="header.jsp"></c:import>
        <c:if test="${requestScope.totalItemInCart == 0}">
            <c:import url="cartnull.jsp"></c:import>
        </c:if>

        <c:if test="${requestScope.totalItemInCart != 0}">
            <div class="banner container-fluid pb-5 mb-5" style="margin-top: 3.7em">
                <h1 class="pt-5">Shopping Cart</h1>
                <p class="myhome"><a href="MainController">Home</a><span><i class="px-4 fa-solid fa-chevron-right"></i></span><a href="#">Cart</a></p>
            </div>                         

            <section>
                <div class="container-fluid py-5 mb-5">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <table class="w-100">
                                    <tr class="a">
                                        <td class="text-center Dongle p-3 dam">Product</td>
                                        <td class="text-center p-3"></td>
                                        <td class="text-center Dongle p-3 dam">Price</td>
                                        <td class="text-center Dongle p-3 dam">Quantity</td>
                                        <td class="text-center Dongle p-3 w-15 dam">Total</td>
                                        <td class="text-center Dongle p-3 dam">Remove</td>
                                    </tr>
                                    <c:set var="o" value="${requestScope.cartList}"/>
                                    <c:forEach items="${o.items}" var="i">
                                        <tr>
                                            <td class="text-center p-1 w-25"><img src="${i.product.image}" alt="" class="w-25"></td>
                                            <td class="textbody">
                                                <p class="mb-0 Dongle" style="font-size: 30px">${i.product.name}</p>
                                            </td>
                                            <td class="text-center textbody ">
                                                <p class="mb-0 Dongle" style="font-size: 30px">VND <fmt:formatNumber pattern="#,###" value="${i.price}"/></p>
                                            </td>
                                            <td class="">
                                                <div class="btn-quantity w-100 d-flex justify-content-center">
                                                    <form action="ProcessCartController" method="post">
                                                        <input type="hidden" value="-1" name="quantity">
                                                        <input type="hidden" value="${i.product.ID}" name="pid">
                                                        <input type="submit" href="" class="btn btn-secondary rounded-0 w-100 text-center p-2 px-3 h-100"
                                                               value="-">
                                                    </form>
                                                    <div class="button w-15">
                                                        <input type="number" class="text-center w-100 p-2" id="quantity1"
                                                               value="${i.quantity}" readonly></input>
                                                    </div>
                                                    <form action="ProcessCartController" method="post">
                                                        <input type="hidden" value="1" name="quantity">
                                                        <input type="hidden" value="${i.product.ID}" name="pid">
                                                        <input type="submit" href="" class="btn btn-secondary rounded-0 w-100 text-center p-2 px-3 h-100"
                                                               value="+">
                                                    </form>
                                                </div>
                                            </td>
                                            <td class="text-center textbody w-15">
                                                <div class="mb-0 float-end w-100" style="color: #8B6E4A;"><span class="Dongle" style="font-size: 30px">VND <fmt:formatNumber pattern="#,###" value="${(i.price*i.quantity)}"/></span></div>
                                            </td>
                                            <td class="text-center ">
                                                <div class="trash" onclick="submitDeleteForm(${i.product.ID})"><i class="fa-solid fa-trash-can"></i></div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <tr class="a">
                                        <td class="text-center Dongle dam">Total payment:</td>
                                        <td class="p-3">
                                            <p class="mb-0 Dongle" style="color: #FF0000; font-size: 35px">VND <fmt:formatNumber pattern="#,###" value="${(o.totalInCart)}"/></p>   
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td class="text-center  ">
                                            <div class="">
                                                <a class="textbody checkout py-3 px-3" id="checkout" href="MainController?action=Checkout" style="text-decoration: none; font-size: 16px">Checkout</a>                             
                                            </div>
                                        </td>
                                    </tr>
                                </table>

                            </div>

                        </div>
                    </div>

                </div>
            </section>
        </c:if>
        <c:import url="footer.jsp"></c:import>
        <!--script js bootstrap-->
        <script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="./assets/js/bootstrap/bootstrap.min.js"></script>
        <script src="./assets/js/bootstrap/bootstrap.esm.min.js"></script>
        <script>
                                                    function submitDeleteForm(id) {
                                                        // Kiểm tra xem form đã tồn tại
                                                        var form = document.getElementById('deleteForm');
                                                        if (!form) {
                                                            // Nếu không tồn tại, tạo mới form
                                                            form = document.createElement('form');
                                                            form.id = 'deleteForm';

                                                            // Tạo input ID
                                                            var inputID = document.createElement('input');
                                                            inputID.type = 'hidden';
                                                            inputID.value = id;
                                                            inputID.name = 'deleteID';

                                                            // Thêm input vào form
                                                            form.appendChild(inputID);

                                                            // Thêm form vào body
                                                            document.body.appendChild(form);
                                                        }

                                                        // Cấu hình action và method của form
                                                        form.action = 'ProcessCartController';
                                                        form.method = 'post';

                                                        // Submit form
                                                        form.submit();
                                                    }
        </script>


    </body>
</html>