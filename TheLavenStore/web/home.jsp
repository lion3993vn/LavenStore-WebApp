<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>

    <head>
        <title>The LAVEN STORE - Home</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noticia+Text">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Josefin+Sans">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Vidaloka">
        <link rel="stylesheet" href="./assets/css/bootstrap/bootstrap.css">
        <link rel="stylesheet" href="./assets/css/stylehome.css">
        <link rel="stylesheet" href="./assets/css/font/css/all.css">
    </head>

    <body>
        <div class="banner container-fluid py-5">
            <div class="row">
                <div class="col-md-12 d-flex justify-content-center">
                    <table>
                        <tr>
                            <td colspan="2">
                                <p class="text-center noticia banner-title">The Color Of Your Life</p>
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
                        <c:forEach items="${release}" var="i">  
                            <div class="col-md-4">                   
                                <div class="item-sell">
                                    <div class="img-item text-center position-relative">
                                        <a href="detail?pid=${i.ID}"><img src="${i.image}" alt="" class="w-100"></a>
                                        <a href="addtocart?id= &num=" class="addcart position-absolute start-50 translate-middle">ADD TO CART</a>
                                    </div>
                                    <div class="info-item text-center">
                                        <a href="detail?pid=${i.ID}">${i.name}</a>
                                        <p>VND ${i.price}</p>
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

                    <div class="col-md-3">
                        <div class="item-sell">
                            <div class="img-item text-center position-relative">
                                <a href="#"><img src="./assets/img/item.png" alt="" class="w-100"></a>
                                <a href="" class="addcart-bestseller position-absolute start-50 translate-middle">ADD TO CART</a>
                            </div>
                            <div class="info-item text-center">
                                <a href="">Wild</a>
                                <p>VND 299.000</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="item-sell">
                            <div class="img-item text-center position-relative">
                                <a href="#"><img src="./assets/img/item.png" alt="" class="w-100"></a>
                                <a href="" class="addcart-bestseller position-absolute start-50 translate-middle">ADD TO CART</a>
                            </div>
                            <div class="info-item text-center">
                                <a href="">Wild</a>
                                <p>VND 299.000</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="item-sell">
                            <div class="img-item text-center position-relative">
                                <a href="#"><img src="./assets/img/item.png" alt="" class="w-100"></a>
                                <a href="" class="addcart-bestseller position-absolute start-50 translate-middle">ADD TO CART</a>
                            </div>
                            <div class="info-item text-center">
                                <a href="">Wild</a>
                                <p>VND 299.000</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="item-sell">
                            <div class="img-item text-center position-relative">
                                <a href="#"><img src="./assets/img/item.png" alt="" class="w-100"></a>
                                <a href="" class="addcart-bestseller position-absolute start-50 translate-middle">ADD TO CART</a>
                            </div>
                            <div class="info-item text-center">
                                <a href="">Wild</a>
                                <p>VND 299.000</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="item-sell">
                            <div class="img-item text-center position-relative">
                                <a href="#"><img src="./assets/img/item.png" alt="" class="w-100"></a>
                                <a href="" class="addcart-bestseller position-absolute start-50 translate-middle">ADD TO CART</a>
                            </div>
                            <div class="info-item text-center">
                                <a href="">Wild</a>
                                <p>VND 299.000</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="item-sell">
                            <div class="img-item text-center position-relative">
                                <a href="#"><img src="./assets/img/item.png" alt="" class="w-100"></a>
                                <a href="" class="addcart-bestseller position-absolute start-50 translate-middle">ADD TO CART</a>
                            </div>
                            <div class="info-item text-center">
                                <a href="">Wild</a>
                                <p>VND 299.000</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="item-sell">
                            <div class="img-item text-center position-relative">
                                <a href="#"><img src="./assets/img/item.png" alt="" class="w-100"></a>
                                <a href="" class="addcart-bestseller position-absolute start-50 translate-middle">ADD TO CART</a>
                            </div>
                            <div class="info-item text-center">
                                <a href="">Wild</a>
                                <p>VND 299.000</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="item-sell">
                            <div class="img-item text-center position-relative">
                                <a href="#"><img src="./assets/img/item.png" alt="" class="w-100"></a>
                                <a href="" class="addcart-bestseller position-absolute start-50 translate-middle">ADD TO CART</a>
                            </div>
                            <div class="info-item text-center">
                                <a href="">Wild</a>
                                <p>VND 299.000</p>
                            </div>
                        </div>
                    </div>
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
        <!--script js bootstrap-->
        <script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="./assets/js/bootstrap/bootstrap.min.js"></script>
        <script src="./assets/js/bootstrap/bootstrap.esm.min.js"></script>
    </body>

</html>
