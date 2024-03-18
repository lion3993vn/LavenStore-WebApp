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
        <title>The LAVEN STORE</title>
        <link rel="icon" type="image/x-icon" href="assets/img/logo.png">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noticia+Text">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Josefin+Sans">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Vidaloka">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <link rel="stylesheet" href="./assets/css/bootstrap/bootstrap.css">
        <link rel="stylesheet" href="./assets/css/stylehome.css">
        <link rel="stylesheet" href="./assets/css/font/css/all.css">
        <link rel="stylesheet" href="./assets/css/toastMessage.css">
    </head>

    <body>
        <c:import url="header.jsp"></c:import>
            <div id="toast-cus" style="margin-top: 3em"></div>
            <div class="banner container-fluid py-5" style="margin-top: 3.5em">
                <div class="row">
                    <div class="col-md-12 d-flex justify-content-center py-4">
                        <table>
                            <tr>
                                <td colspan="2">
                                    <p class="text-center noticia banner-title" style="font-weight: 500">The Color Of Your Life</p>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <p class="text-center noticia banner-subtitle">PREMIUM FLOWERS AND BOUQUETS</p>
                                </td>
                            </tr>
                            <tr class="">
                                <td class="width-btn-banner"><div class="d-flex justify-content-center"><div class="btn-banner text-center w-75"><a class="noticia px-4 py-1" href="MainController?action=shop">Shop Now</a></div></div></td>
                                <td class="width-btn-banner"><div class="d-flex justify-content-center"><div class="btn-banner text-center w-75"><a class="noticia px-4 py-1" href="#bestseller">Best Seller</a></div></div></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            <section id="newrelease">
                <div class="container-fluid">
                    <div class="mt-5 container">
                        <div class="row">
                            <div class="col-md-12"><p class="text-center real-title">NEW RELEASE</p></div>
                        <c:forEach items="${requestScope.release}" var="i">  
                            <div class="col-md-4">                   
                                <div class="item-sell">
                                    <div class="img-item text-center position-relative">
                                        <a href="MainController?action=product&id=${i.ID}"><img src="${i.image}" alt="" class="w-100"></a>
                                            <c:if test="${i.quantity != 0}">
                                            <div class="addcart position-absolute start-50 translate-middle" onclick="addCart(${i.ID}, 1)" style="cursor: pointer">ADD TO CART</div>
                                        </c:if>
                                        <c:if test="${i.quantity == 0}">
                                            <div class="sold-out position-absolute top-50 start-50 translate-middle d-flex justify-content-center align-items-center"><p class="m-0">SOLD OUT</p></div>
                                        </c:if>
                                    </div>
                                    <div class="info-item text-center">
                                        <a href="MainController?action=product&id=${i.ID}">${i.name}</a>
                                        <p>VND <fmt:formatNumber pattern="#,###" value="${i.price}"/></p>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </section>
        <section id="bestseller">
            <div class="mt-5 container-fluid">
                <div class="row">
                    <div class="col-md-12"><p class="text-center real-title">BEST SELLER</p></div>
                    <c:forEach items="${requestScope.best}" var="o">
                        <div class="col-md-3">
                            <div class="item-sell">
                                <div class="img-item text-center position-relative">
                                    <a href="MainController?action=product&id=${o.ID}"><img src="${o.image}" alt="" class="w-100"></a>
                                        <c:if test="${o.quantity != 0}">
                                        <div class="addcart position-absolute start-50 translate-middle" onclick="addCart(${o.ID}, 1)" style="cursor: pointer">ADD TO CART</div>
                                    </c:if>
                                    <c:if test="${o.quantity == 0}">
                                        <div class="sold-out position-absolute top-50 start-50 translate-middle d-flex justify-content-center align-items-center">
                                            <p class="m-0">SOLD OUT</p>
                                        </div>
                                    </c:if>
                                </div>
                                <div class="info-item text-center">
                                    <a href="MainController?action=product&id=${o.ID}">${o.name}</a>
                                    <p>VND <fmt:formatNumber pattern="#,###" value="${o.price}"/></p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>
        <div class="container-fluid subscribe">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="float-end content-submail">
                            <table class="table-submail mb-3">
                                <tr>
                                    <td colspan="2" class="submail-title mt-2"><p class="mb-0 mt-3">Subscribe</p></td>
                                </tr>
                                <tr>
                                    <td colspan="2" class="submail-title"><p>For New Updates!</p></td>
                                </tr>
                                <tr>
                                    <td colspan="2"><p class="submail-desb">When looking for a new bouquet idea for Valentines or Mother’s Day, ask us for a tip!</p></td>
                                </tr>
                                <tr>
                                    <td class="w-75"><input type="text" class="mail-input p-2 w-100" placeholder="example@gmail.com"></td>
                                    <td class="w-25"><div class="send float-start px-3 py-2 ms-2"><i class="fa-solid fa-paper-plane"></i></div></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <c:import url="footer.jsp"></c:import>
        <!--script js bootstrap-->
        <script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="./assets/js/bootstrap/bootstrap.min.js"></script>
        <script src="./assets/js/bootstrap/bootstrap.esm.min.js"></script>
        <script src="./assets/js/toastMessage.js"></script>
        <script>
                                            function addCart(id, num) {
                                                console.log('check1');
                                                $.ajax({
                                                    url: "AddToCartController",
                                                    type: "post", //send it through post method
                                                    data: {
                                                        id: id,
                                                        num: num
                                                    },
                                                    success: function (response) {
                                                        console.log(response);
                                                        showSuccessToast(response);
                                                    },
                                                    error: function (xhr) {
                                                        //Do Something to handle error
                                                        alert("Có lỗi xảy ra, vui lòng thử lại sau ít phút");
                                                    }
                                                });
                                            }

                                            function showSuccessToast(product) {
                                                toastCus({
                                                    title: 'Add To Cart',
                                                    message: 'Đã thêm ' + product + ' vào giỏ hàng',
                                                    type: 'success',
                                                });
                                            }
        </script>
    </body>

</html>
