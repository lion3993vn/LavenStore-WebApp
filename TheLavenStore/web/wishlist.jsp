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
        <title>WishList | The LAVEN STORE</title>
        <link rel="icon" type="image/x-icon" href="assets/img/logo.png">
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <link rel="stylesheet" href="./assets/css/stylewishlist.css">
        <link rel="stylesheet" href="./assets/css/font/css/all.css">
        <link rel="stylesheet" href="./assets/css/toastMessage.css">
    </head>
    <body>
        <c:import url="header.jsp"></c:import>
            <div id="toast-cus" style="margin-top: 3em"></div>
            <!-- banner -->
            <div class="banner container-fluid text-center py-5" style="margin-top: 3.5em">
                <h1 class="banner-title ">Wishlist</h1>
                <div class="banner-subtitle">
                    <a class="tab-links" href="MainController">Home</a>
                    <i class="tab-links fa-solid fa-angle-right"></i>
                    <a class="tab-links" href="#">Wishlist</a>
                </div>
            </div>
        <c:if test="${requestScope.size == 0}">
            <section >
                <div class="container-fluid mt-5 mb-5 py-5">
                    <div class="row">
                        <div class="col-md-12 text-center cartnull">
                            <p style="color: #8b8b8b; font-size: 40px;">Your wishlist is empty</p>
                            <a href="MainController" class="p-3 mt-2">Back To Home</a>
                        </div>
                    </div>

                </div>
            </section>
        </c:if>
        <c:if test="${requestScope.size != 0}">
            <section class="">
                <div class="container-fluid">
                    <div class="row mt-5 mb-5">
                        <c:forEach items="${requestScope.listp}" var="o">
                            <div class="col-md-3">
                                <div class="item-sell">
                                    <div class="img-item text-center position-relative">
                                        <a href="MainController?action=product&id=${o.ID}"><img src="${o.image}" alt="" class="w-100"></a>
                                            <c:if test="${o.quantity != 0}">
                                            <div class="addcart-bestseller position-absolute start-50 translate-middle" onclick="addCart(${o.ID}, 1)" style="cursor: pointer">ADD TO CART</div>
                                        </c:if>
                                        <c:if test="${o.quantity == 0}">
                                            <div class="sold-out position-absolute top-50 start-50 translate-middle d-flex justify-content-center align-items-center"><p class="m-0">SOLD OUT</p></div>
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
        </c:if>

        <c:import url="footer.jsp"></c:import>
        <!--script js bootstrap-->
        <script src="./assets/js/bootstrap/popper.min.js"></script>
        <script src="./assets/js/bootstrap/jquery.min.js"></script>
        <!-- <script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script> -->
        <!-- <script src="./assets/js/bootstrap/bootstrap.min.js"></script> -->
        <!-- <script src="./assets/js/bootstrap/bootstrap.esm.min.js"></script> -->
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
