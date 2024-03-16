<%-- 
    Document   : wishlist
    Created on : Mar 13, 2024, 10:09:48 PM
    Author     : Pham Hieu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Boxes - The LAVEN STORE</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noticia+Text">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Josefin+Sans">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Vidaloka">
        <link rel="stylesheet" href="./assets/css/bootstrap/bootstrap.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="./assets/css/stylewishlist.css">
        <link rel="stylesheet" href="./assets/css/font/css/all.css">
    </head>
    <body>
        <!-- banner -->
        <div class="banner container-fluid text-center py-5">
            <h1 class="banner-title ">Wishlist</h1>
            <div class="banner-subtitle">
                <a class="tab-links" href="/home.html">Home</a>
                <i class="tab-links fa-solid fa-angle-right"></i>
                <a class="tab-links" href="/wishlist.html">Wishlist</a>
            </div>
        </div>
        <section class="">
            <div class="container-fluid">
                <div class="row mt-5 mb-5">
                    <c:forEach items="${requestScope.listp}" var="o">
                        <div class="col-md-3">
                            <div class="item-sell">
                                <div class="img-item text-center position-relative">
                                    <a href="MainController?action=product&id=${o.ID}"><img src="${o.image}" alt="" class="w-100"></a>
                                    <a href="" class="addcart-bestseller position-absolute start-50 translate-middle">ADD TO CART</a>
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
            <div class="container-fluid">
                <div class="row mb-5">
                    <div class="col-md-12 d-flex justify-content-end paging">
                        <c:forEach begin="1" end="${requestScope.page}" var="p">
                            <form action="WishListController" method="POST">
                                <input type="submit" class="py-2 px-3 me-3 ${requestScope.curr == p ? "active-paging":""}" value="${p}">
                                <input type="hidden" name="actionWL" value="show-wishlist">
                                <input type="hidden" name="index" value="${p}">
                            </form>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </section>
        <!--script js bootstrap-->
        <script src="./assets/js/bootstrap/popper.min.js"></script>
        <script src="./assets/js/bootstrap/jquery.min.js"></script>
        <!-- <script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script> -->
        <!-- <script src="./assets/js/bootstrap/bootstrap.min.js"></script> -->
        <!-- <script src="./assets/js/bootstrap/bootstrap.esm.min.js"></script> -->
    </body>
</html>
