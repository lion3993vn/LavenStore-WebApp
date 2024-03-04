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
        <title>TODO supply a title</title>
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

        <c:if test="${requestScope.totalItemInCart == 0}">
            <c:import url="cartnull.jsp"></c:import>
        </c:if>

        <c:if test="${requestScope.totalItemInCart != 0}">
            <section>
                <banner>
                    <div class="banner container-fluid pb-5 mb-5">
                        <h1 class="pt-5">Shopping Cart</h1>
                        <p class="myhome"><a href="#">Home</a><span><i class="px-4 fa-solid fa-chevron-right"></i></span>Cart</p>
                    </div>                         
                </banner>
            </section>

            <section>
                <div class="container-fluid">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <table class="w-100">
                                    <tr class="a">
                                        <td class="text-center Dongle p-3">Product</td>
                                        <td class="text-center p-3"></td>
                                        <td class="text-center Dongle p-3">Price</td>
                                        <td class="text-center Dongle p-3">Quantity</td>
                                        <td class="text-center Dongle p-3 w-15">Total</td>
                                        <td class="text-center Dongle p-3">Remove</td>
                                    </tr>
                                    <c:set var="o" value="${requestScope.cartList}"/>
                                    <c:forEach items="${o.items}" var="i">
                                        <tr>
                                            <td class="text-center p-1 w-25"><img src="${i.product.image}" alt="" class="w-25"></td>
                                            <td class="textbody">
                                                <p class="mb-0">${i.product.name}</p>
                                            </td>
                                            <td class="text-center textbody ">
                                                <p class="mb-0">VND <fmt:formatNumber pattern="#,###" value="${i.price}"/></p>
                                            </td>
                                            <td class="">
                                                <div class="btn-quantity w-100 d-flex justify-content-center">
                                                    <form action="process-cart" method="post">
                                                        <input type="hidden" value="-1" name="quantity">
                                                        <input type="hidden" value="${i.product.ID}" name="pid">
                                                        <input type="submit" href="" class="btn btn-secondary rounded-0 w-100 text-center p-2 px-3 h-100"
                                                               value="-">
                                                        </form>
                                                    <div class="button w-15">
                                                        <input type="number" class="text-center w-100 p-2" id="quantity1"
                                                               value="${i.quantity}" readonly></input>
                                                    </div>
                                                    <form action="process-cart" method="post">
                                                        <input type="hidden" value="1" name="quantity">
                                                        <input type="hidden" value="${i.product.ID}" name="pid">
                                                        <input type="submit" href="" class="btn btn-secondary rounded-0 w-100 text-center p-2 px-3 h-100"
                                                               value="+">
                                                        </form>
                                                </div>
                                            </td>
                                            <td class="text-center textbody w-15">
                                                <div class="mb-0 float-end w-100" style="color: #8B6E4A;"><span class="float-end Dongle">VND <fmt:formatNumber pattern="#,###" value="${(i.price*i.quantity)}"/></span></div>
                                            </td>
                                            <td class="text-center ">
                                                <div class="trash" onclick="submitDeleteForm(${i.product.ID})"><i class="fa-solid fa-trash-can"></i></div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <tr class="a">
                                        <td class="text-center Dongle">Total payment:</td>
                                        <td class="Dongle p-3 ">
                                            <p class="mb-0" style="color: #FF0000">VND <fmt:formatNumber pattern="#,###" value="${(o.totalInCart)}"/></p>   
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td class="text-center  ">
                                            <div class="">
                                                <div class="textbody checkout " type="submit" value="">Checkout</div>                             
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
                form.action = 'process-cart';
                form.method = 'post';

                // Submit form
                form.submit();
            }
        </script>


    </body>
</html>