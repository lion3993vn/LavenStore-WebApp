<%-- 
    Document   : payment-success
    Created on : Mar 9, 2024, 6:28:28 PM
    Author     : Pham Hieu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@100;200;300;400&display=swap"
              rel="stylesheet">
        <link href='https://fonts.googleapis.com/css?family=Signika Negative' rel='stylesheet'>
        <link rel="stylesheet" href="./assets/css/stylepayment-success.css">
        <link rel="stylesheet" href="./assets/css/font/css/all.css">
    </head>

    <body>
        <div class="outline">
            <div class="container payment-body">
                <div class="row payment-container">
                    <div class="col-md-12 pay-header">
                        <h5>PAYMENT INFORMATION</h5>
                    </div>

                    <div class="col-md-12 pay-img">
                        <img src="./assets/img/checkout/success-icon.png" alt="" width="100px">
                        <h4>Payment success</h4>
                    </div>
                    <div class="col-md-12 payment-in4">
                        <table>
                            <tbody>
                                <tr>
                                    <th>Customer:</th>
                                    <td>${sessionScope.account.userName}</td>
                                </tr>
                                <tr>
                                    <th>Email:</th>
                                    <td>${sessionScope.account.email}</td>
                                </tr>
                                <tr>
                                    <th>Order code:</th>
                                    <td>#${requestScope.order.orderCode}</td>
                                </tr>
                                <tr>
                                    <th>Amount paid:</th>
                                    <td class="amout">VND <fmt:formatNumber pattern="#,###" value="${requestScope.order.amount}"/></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-12 break">
                        <div class="line"></div>
                        <span>Details</span>
                        <div class="line"></div>
                    </div>
                    <div class="col-md-12 payment-details">
                        <table>
                            <tbody>
                                <tr>
                                    <th>Order date:</th>
                                    <td><fmt:formatDate value="${requestScope.order.date}" pattern="dd/MM/yyyy"/></td>
                            </tr>
                            <tr>
                                <th>Location:</th>
                                <td>${requestScope.order.location}</td>
                            </tr>
                            <tr>
                                <th>Phone number:</th>
                                <td>${requestScope.order.phoneNumber}</td>
                            </tr>
                            <tr>
                                <th>Total quantity:</th>
                                <td>${requestScope.totalQuantity}</td>
                            </tr>
                            <tr>
                                <th>Payment type:</td>
                                <td>VNPAY</td>
                            </tr>
                            <tr>
                                <th>Note:</th>
                                <td>${requestScope.order.note}</td>
                            </tr>
                            </tbody>
                        </table>
                        <p>We'll email you an order confirmation with details information.</p>
                        <div class="thanks">
                            <p>Thank you for choosing us.</p>
                            <p>See you again!</p>
                        </div>
                        <div class="payment-footer">
                            <p><a href="MainController">Back to home page</a></p>
                            <p>Powered by <a href="#">VNPAY</a></p>
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
