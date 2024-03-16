<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <link rel="stylesheet" href="./assets/css/styleshop.css">
        <link rel="stylesheet" href="./assets/css/font/css/all.css">
    </head>
    <body>

        <!-- banner -->
        <div class="banner container-fluid text-center py-5">
            <c:if test="${requestScope.current == null || requestScope.current == 0}">
                <h1 class="banner-title ">All</h1>
            </c:if>
            <c:if test="${requestScope.current != null && requestScope.current != 0}">
                <c:forEach items="${requestScope.listcate}" var="i">
                    <c:if test="${requestScope.current == i.cateID}">
                        <h1 class="banner-title ">${i.cateName}</h1>
                    </c:if>
                </c:forEach>
            </c:if>
            <div class="banner-subtitle">
                <a class="tab-links" href="MainController">Home</a>
                <i class="tab-links fa-solid fa-angle-right"></i>
                <a class="tab-links" href="#">Shop</a>
            </div>
        </div>
        <!-- navbar -->
        <section>
            <div class="container">
                <div class="row mt-5 mb-5">
                    <ul class="navigation-bar nav col-md-6">
                        <li class="nav-item">
                            <a class="nav-link ${(requestScope.current == null || requestScope.current == 0)? "current":""}" href="MainController?action=shop&cateid=0&search=${requestScope.search}&sort=${requestScope.sort}">All</a>
                        </li>
                        <c:forEach items="${requestScope.listcate}" var="i">
                            <li class="nav-item">
                                <a class="nav-link ${(requestScope.current == i.cateID)?"current":""}" href="MainController?action=shop&cateid=${i.cateID}&search=${requestScope.search}&sort=${requestScope.sort}">${i.cateName}</a>
                            </li>
                        </c:forEach>
                    </ul>
                    <div class="h-100 col-md-3" style="">
                        <div class="w-100 d-flex justify-content-end">
                            <form action="ShopController" method="GET">
                                <select name="sort" id="" class="p-2" style="" class="w-25">
                                    <option class="dropdown" ${requestScope.sort == null ? "selected":""} disabled>Lọc giá</option>
                                    <option class="dropdown" value="DESC" ${requestScope.sort eq "DESC" ? "selected":""}>Cao đến thấp</option>
                                    <option class="dropdown" value="ASC" ${requestScope.sort eq "ASC" ? "selected":""}>Thấp đến cao</option>
                                    <option class="dropdown" value="">Không</option>
                                </select>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-inline d-flex justify-content-between ">
                            <input
                                name="search"
                                value="${requestScope.search}"
                                class="form-control  border border-dark rounded-0 p-2 w-75"
                                type="search"
                                placeholder="Search"
                                style="font-family:Arial, FontAwesome"
                                aria-label="Search"
                                >
                            <input type="hidden" name="cateid" value="${requestScope.current}">
                            <input type="submit" value="GO" class="w-20" style="background-color: #B68B3E; color: white">
                            </form>
                        </div>
                    </div>
                </div>
        </section>
        <section class="seller">
            <div class="container-fluid">
                <div class="row mt-5 mb-5">
                    <c:forEach items="${requestScope.plist}" var="o">
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
                <div class="row mb-5">
                    <div class="col-md-12 d-flex justify-content-end paging">
                        <c:forEach begin="1" end="${requestScope.page}" var="p">
                            <form action="ShopController" method="POST">
                                <input type="submit" class="py-2 px-3 me-3 ${requestScope.curr == p ? "active-paging":""}" value="${p}">
                                <input type="hidden" name="cateid" value="${requestScope.cateid}">
                                <input type="hidden" name="index" value="${p}">
                                <input type="hidden" name="search" value="${requestScope.search}">
                                <input type="hidden" name="sort" value="${requestScope.sort}">
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

