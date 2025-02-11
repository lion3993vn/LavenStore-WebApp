<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>${requestScope.product.name} | The LAVEN STORE</title>
        <link rel="icon" type="image/x-icon" href="assets/img/logo.png">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noticia+Text">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Josefin+Sans">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Vidaloka">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Merriweather">
        <link rel="stylesheet" href="./assets/css/bootstrap/bootstrap.css">
        <link rel="stylesheet" href="./assets/css/styleproduct.css">
        <link rel="stylesheet" href="./assets/css/font/css/all.css">
        <link rel="stylesheet" href="./assets/css/toastMessage.css">
    </head>

    <body>
        <c:import url="header.jsp"></c:import>
            <div id="toast-cus" style="margin-top: 3em"></div>
            <!-- banner -->
            <div class="banner container-fluid text-center py-5" style="margin-top: 3.5em">
                <h1 class="banner-title">${requestScope.cate}</h1>
            <div class="banner-subtitle">
                <a class="tab-links" href="MainController">Home</a>
                <i class="tab-links fa-solid fa-angle-right"></i>
                <a class="tab-links" href="MainController?action=shop">Shop</a>
                <i class="tab-links fa-solid fa-angle-right"></i>
                <a class="tab-links" href="#">${requestScope.product.name}</a>
            </div>
        </div>
        <!-- product -->
        <div class="container-fluid">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <div class="img-content p-5"><img src="${requestScope.product.image}" alt="" class="w-100"></div>
                    </div>
                    <div class="col-md-6">
                        <div class="title-info-product pt-5">
                            <table class="w-100">
                                <tr>
                                    <td class="w-75">
                                        <p class="name-product">${requestScope.product.name}</p>
                                    </td>
                                    <td class="">
                                        <div class="d-flex justify-content-center">
                                            <c:if test="${requestScope.wishlist == false}">
                                                <div href="" class="add-wishlist" style="cursor: pointer" onclick="wishlist(${requestScope.product.ID})">
                                                    <div><i class="fa-regular fa-star"></i></div>
                                                </div>
                                            </c:if>
                                            <c:if test="${requestScope.wishlist == true}">
                                                <div href="" class="add-wishlist" style="cursor: pointer" onclick="wishlist(${requestScope.product.ID})">
                                                    <div class="box-wishlist-added"><i class="fa-solid fa-star icon-wishlist-added" ></i></div>
                                                </div>
                                            </c:if>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <div class="rank-product">
                                            <c:forEach begin="1" end="${requestScope.starFull}">
                                                <i class="fa-solid fa-star"></i>
                                            </c:forEach>
                                            <c:if test="${requestScope.starHalf == 1}">
                                                <i class="fa-solid fa-star-half-stroke"></i>
                                            </c:if>
                                            <c:forEach begin="1" end="${requestScope.starNo}">
                                                <i class="fa-regular fa-star"></i>
                                            </c:forEach> 
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="product-desb mt-3">
                            <p class="py-3" style="font-family: 'Merriweather'; font-size: 20px">${requestScope.product.description}</p>
                        </div>
                        <div class="product-price">
                            <p style="font-weight: 600">VND <fmt:formatNumber pattern="#,###" value="${requestScope.product.price}"/></p>
                        </div>
                        <div class="delivery w-25 text-center p-3 mt-4">
                            <i class="fa-solid fa-truck-fast"></i>
                            <p class="mt-3 mb-0">Local Delivery</p>
                        </div>
                        <div class="select-time w-50 mt-3">
                            <p class="m-0">Product Buy Info :</p>
                        </div>
                        <div class="info-cart-product w-50 mt-3 mb-4">
                            <table class="w-100">
                                <tr>
                                    <td class="w-30">
                                        <div class="btn-quantity w-100 d-flex align-content-center">
                                            <div class="btn btn-secondary rounded-0 w-25 text-center p-2" id="quantity-down"
                                                 onclick="sub()" style="cursor: pointer">
                                                <span>-</span>
                                            </div>
                                            <div class="button w-40">
                                                <input type="number" class="text-center w-100 p-2" id="quantity" min="1"
                                                       value="1"></input>
                                            </div>
                                            <div class="btn btn-secondary rounded-0 w-25 text-center p-2" id="quantity-up"
                                                 onclick="add()" style="cursor: pointer">
                                                <span>+</span>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="w-30 text-center">
                                        <c:if test="${requestScope.product.quantity != 0}">
                                            <div onclick="addCart(${requestScope.product.ID})" class="" style="cursor: pointer">
                                                <p class="m-0 add-cart p-2" style="font-family: serif">ADD TO CART</p>
                                            </div>
                                        </c:if>
                                        <c:if test="${requestScope.product.quantity == 0}">
                                            <div class="" style="opacity: 0.7; cursor: no-drop">
                                                <p class="m-0 add-cart p-2" style="font-family: serif">SOLD OUT</p>
                                            </div>
                                        </c:if>
                                    </td>
                                    <td class="w-15">
                                        <div onclick="copyUrl(${requestScope.product.ID})" class="float-end btn-share py-1 px-3" style="cursor: pointer"><i
                                                class="fa-solid fa-share-nodes"></i></div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container-fluid mt-5 mb-5">
            <div class="row">
                <div class="col-md-12">
                    <p class="text-center real-title py-3">RELATED FLOWERS</p></div>
            </div>
            <div class="row">
                <c:forEach items="${requestScope.related}" var="o">
                    <div class="col-md-3">
                        <div class="item-sell">
                            <div class="img-item text-center position-relative">
                                <a href="MainController?action=product&id=${o.ID}"><img src="${o.image}" alt="" class="w-100"></a>
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
    </div>
    <c:import url="footer.jsp"></c:import>
    <!--script js bootstrap-->
    <script src="./assets/js/bootstrap/jquery.min.js"></script>
    <script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>
    <script src="./assets/js/bootstrap/bootstrap.min.js"></script>
    <script src="./assets/js/bootstrap/bootstrap.esm.min.js"></script>
    <script src="./assets/js/toastMessage.js"></script>
    <script>
                                            function sub() {
                                                var input = document.getElementById('quantity');
                                                if (input.value > 1) {
                                                    input.value = parseInt(input.value) - 1;
                                                }
                                            }
                                            function add() {
                                                var input = document.getElementById('quantity');
                                                if (input.value < 100) {
                                                    input.value = parseInt(input.value) + 1;
                                                }
                                            }
    </script>
    <script>
        function wishlist(id) {
            var form = document.createElement("form");
            form.setAttribute("method", "POST");
            form.setAttribute("action", "MainController");

            var input = document.createElement("input");
            input.setAttribute("type", "text");
            input.setAttribute("name", "productID");
            input.value = id;

            var actionWL = document.createElement("input");
            actionWL.setAttribute("type", "text");
            actionWL.setAttribute("name", "actionWL");
            actionWL.value = "add-wishlist";

            var action = document.createElement("input");
            action.setAttribute("type", "text");
            action.setAttribute("name", "action");
            action.value = "wishlist";

            form.appendChild(input);
            form.appendChild(actionWL);
            form.appendChild(action);
            form.style.display = 'none';
            document.body.appendChild(form);

            form.submit();
        }
        function showSuccessCopyUrl() {
            toastCus({
                title: 'Copy to clip board',
                message: 'Đã sao chép link sản phẩm',
                type: 'success',
            });
        }
        function copyUrl(id) {
            var textToCopy = "http://localhost:8080/thelavenstore/MainController?action=product&id=" + id;

            // Tạo một thẻ input ẩn để sao chép nội dung vào clipboard
            var tempInput = document.createElement("input");
            tempInput.setAttribute("value", textToCopy);
            document.body.appendChild(tempInput);
            tempInput.select();
            document.execCommand("copy");
            document.body.removeChild(tempInput);
            showSuccessCopyUrl();
        }

        function addCart(id) {
            var num = document.getElementById('quantity').value;
            $.ajax({
                url: "AddToCartController",
                type: "post", //send it through post method
                data: {
                    id: id,
                    num: num
                },
                success: function (response) {
                    console.log(response);
                    showSuccessCart(response);
                },
                error: function (xhr) {
                    //Do Something to handle error
                    alert("Có lỗi xảy ra, vui lòng thử lại sau ít phút");
                }
            });
        }

        function showSuccessCart(product) {
            toastCus({
                title: 'Add To Cart',
                message: 'Đã thêm ' + product + ' vào giỏ hàng',
                type: 'success'
            });
        }
    </script>
</body>

</html>
