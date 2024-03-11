<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                <a class="tab-links" href="/home.jsp">Home</a>
                <i class="tab-links fa-solid fa-angle-right"></i>
                <a class="tab-links" href="/shop.jsp">Shop</a>
            </div>
        </div>
        <!-- navbar -->
        <section>
            <div class="container-fluid">
                <div class="row mt-5 mb-5">
                    <ul class="navigation-bar nav offset-md-1 col-md-8">
                        <li class="nav-item">
                            <a class="nav-link ${(requestScope.current == null || requestScope.current == 0)? "current":""}" href="MainController?action=shop&cateid=0">All</a>
                        </li>
                        <c:forEach items="${requestScope.listcate}" var="i">
                            <li class="nav-item">
                                <a class="nav-link ${(requestScope.current == i.cateID)?"current":""}" href="MainController?action=shop&cateid=${i.cateID}">${i.cateName}</a>
                            </li>
                        </c:forEach>

                        <li class="nav-item dropdown" style="margin-left: auto;">
                            <div class="dropdown">
                                <a
                                    type="button"
                                    href="#"
                                    class="dropdown-toggle"
                                    style="text-decoration:none"
                                    data-bs-toggle="dropdown"
                                    >
                                    Lọc giá
                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a class="dropdown-item" href="shop?cateid=${requestScope.list.cateID}&sortPrice=null">Mặc định</a>
                                    </li>
                                    <li>
                                        <a class="dropdown-item" href="shop?cateid=${list}&sortPrice=null">Cao đến thấp</a>
                                    </li>
                                    <li>
                                        <a class="dropdown-item" href="shop?cateid=${list}&sortPrice=acs">Thấp đến cao</a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                    <form action="shop" method="post" class="form-inline col-md-2 justify-content-center">
                        <input
                            name="txt"
                            value="${search}"
                            class="form-control mr-sm-2 border border-dark rounded-0"
                            type="search"
                            placeholder="Search"
                            style="font-family:Arial, FontAwesome"
                            aria-label="Search"
                            >
                    </form>
                </div>
            </div>
        </section>
        <section class="seller">
            <div class="container-fluid">
                <div class="row mt-5 mb-5">
                    <c:forEach items="${requestScope.plist}" var="i">
                        <div class="col-md-3">
                            <div class="item-sell">
                                <div class="img-item text-center position-relative">
                                    <a href="detail?pid=${i.ID}"><img src="${i.image}" alt="" class="w-100"></a>
                                    <a href="addtocart?id= &num=" class="addcart-bestseller position-absolute start-50 translate-middle">ADD TO CART</a>
                                </div>
                                <div class="info-item text-center">
                                    <a href="detail?pid=${i.ID}">${i.name}</a>
                                    <p>VND ${i.price}</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="row mb-5">
                    <div class="col-md-12 d-flex justify-content-end paging">
                        <a href="" class="me-3 active-paging">1</a>
                        <a href="" class="me-3">2</a>
                        <a href="" class="me-3">3</a>
                        <a href="" class="me-3">4</a>
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

