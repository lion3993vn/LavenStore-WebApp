<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Product Details - The LAVEN STORE</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noticia+Text">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Josefin+Sans">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Vidaloka">
        <link rel="stylesheet" href="./assets/css/bootstrap/bootstrap.css">
        <link rel="stylesheet" href="./assets/css/styleproduct.css">
        <link rel="stylesheet" href="./assets/css/font/css/all.css">
    </head>

    <body>
        <!-- banner -->
        <div class="banner container-fluid text-center py-5">
            <h1 class="banner-title">${cname}</h1>
            <div class="banner-subtitle">
                <a class="tab-links" href="HomeController">Home</a>
                <i class="tab-links fa-solid fa-angle-right"></i>
                <a class="tab-links" href="shop">Shop</a>
                <i class="tab-links fa-solid fa-angle-right"></i>
                <a class="tab-links" href="">${detail.name}</a>
            </div>
        </div>
        <!-- product -->
        <div class="container-fluid">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <div class="img-content p-5"><img src="${detail.image}" alt="" class="w-100"></div>
                    </div>
                    <div class="col-md-6">
                        <div class="title-info-product pt-5">
                            <table class="w-100">
                                <tr>
                                    <td class="w-75">
                                        <p class="name-product">${detail.name}</p>
                                    </td>
                                    <td class="">
                                        <div class="d-flex justify-content-center"><a href="" class="add-wishlist">
                                                <div class="box-wishlist"><i class="fa-solid fa-star icon-wishlist"></i></div>
                                            </a></div>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <div class="rank-product"><i class="fa-solid fa-star"></i><i
                                                class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i
                                                class="fa-solid fa-star"></i><i class="fa-solid fa-star-half-stroke"></i></div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="product-desb mt-3">
                            <p>${detail.description}</p>
                        </div>
                        <div class="product-price">
                            <p>VND ${detail.price}</p>
                        </div>
                        <div class="delivery w-25 text-center p-3 mt-4">
                            <i class="fa-solid fa-truck-fast"></i>
                            <p class="mt-3 mb-0">Local Delivery</p>
                        </div>
                        <div class="select-time w-50 mt-3">
                            <p class="m-0">Delivery / Pickup Time :</p>
                            <select name="" id="" class="w-100 ps-2">
                                <option disabled selected>Please select</option>
                                <option value="1">12:00 PM - 6:00 PM</option>
                                <option value="2">6:00 PM - 10:00 PM</option>
                            </select>
                        </div>
                        <div class="info-cart-product w-50 mt-3 mb-4">
                            <table class="w-100">
                                <tr>
                                    <td class="w-30">
                                        <div class="btn-quantity w-100 d-flex align-content-center">
                                            <div class="btn btn-secondary rounded-0 w-25 text-center p-2" id="quantity-down"
                                                 onclick="sub()">
                                                <span>-</span>
                                            </div>
                                            <div class="button w-40">
                                                <input type="number" class="text-center w-100 p-2" id="quantity" min="1" max="5"
                                                       value="1"></input>
                                            </div>
                                            <div class="btn btn-secondary rounded-0 w-25 text-center p-2" id="quantity-up"
                                                 onclick="add()">
                                                <span>+</span>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="w-30 text-center"><a href="" class="">
                                            <p class="m-0 add-cart p-2">ADD TO CART</p>
                                        </a></td>
                                    <td class="w-15">
                                        <a href="" class="float-end btn-share py-1 px-3"><i
                                                class="fa-solid fa-share-nodes"></i></a>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container-fluid mt-5">
            <div class="row">
                <div class="col-md-12">
                    <p class="text-center real-title">RELATED FLOWERS</p></div>
            </div>
            <div class="row">
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
    </div>
    <!--script js bootstrap-->
    <script src="./assets/js/bootstrap/jquery.min.js"></script>
    <script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>
    <script src="./assets/js/bootstrap/bootstrap.min.js"></script>
    <script src="./assets/js/bootstrap/bootstrap.esm.min.js"></script>
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
</body>

</html>
