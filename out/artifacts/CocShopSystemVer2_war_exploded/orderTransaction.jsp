<%--
  Created by IntelliJ IDEA.
  User: Nguyen Cong Chinh
  Date: 6/25/2017
  Time: 9:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Page</title>
    <style>
        .web_dialog_overlay {
            position: fixed;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            height: 100%;
            width: 100%;
            margin: 0;
            padding: 0;
            background: #000000;
            opacity: .15;
            filter: alpha(opacity=15);
            -moz-opacity: .15;
            z-index: 101;
            display: none;
            border-radius: 5px;
        }

        .web_dialog {
            display: none;
            position: fixed;
            width: 450px;
            height: auto;
            top: 50%;
            left: 50%;
            padding: 20px;
            padding-top: 0;
            transform: translate(-50%, -50%);
            background-color: #ffffff;
            border: 2px solid #336699;
            padding: 0px;
            z-index: 102;
            font-family: Verdana;
            font-size: 10pt;
        }

        .web_dialog table td {
            padding: 10px;
        }

        .web_dialog_title {
            border-bottom: solid 2px #336699;
            background-color: #336699;
            padding: 4px;
            color: White;
            font-weight: bold;
        }

        .web_dialog_title a {
            color: White;
            text-decoration: none;
        }

        .align_right {
            text-align: right;
        }


    </style>
    <link rel="stylesheet" href="resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/main.css">
    <link rel="stylesheet" href="resources/css/font-awesome-4.7.0/css/font-awesome.min.css">
</head>
<body>
<header id="main-header" class="col-xs-12">
    <div class="col-sm-2" id="header-left"><b>Coc Shop</b></div>
    <div class="col-sm-10" id="header-right">
        <div class="admin-setting pull-right">
            <%--<a href=""><i class="fa fa-shopping-cart" style="font-size: 25px; vertical-align: middle" onclick="CheckOut()"></i></a>--%>
            <button type="button" onclick="CheckOut()" class="fa fa-shopping-cart">CheckOut</button>
        </div>
    </div>
</header>
<section class="col-sm-12" id="main-section-wrapper">
    <div class="col-sm-2" id="section-left">
        <div class="admin-info"><img src="resources/img/chinhnc.jpg"/> <span class="admin-name"><b>Nguyen Cong Chinh</b></span>
            <span class="admin-status"><i class="fa fa-circle text-success" aria-hidden="true"></i> Online</span></div>
        <div class="menu-navigation">Shopping Cart</div>

    </div>
    <div class="col-sm-10" id="section-right">
        <h3>Shopping Cart</h3>
        <form autocomplete="off">
            <input type="text" id="searchValue" class="form-control" style="width: 30%; display: inline-block" required>
            <button onclick="SearchProduct()" type="button" class="btn btn-success" style="display: inline-block"
                    id="btnSearch">
                Search
            </button>
            <button type="button" class="btn btn-success" id="viewAll" onclick="showData()">View all product</button>
        </form>


        <table class="table table-hover table-stripped" id="tblResult">
            <thead>
            <th>ID</th>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>$/unit</th>
            <th>Category</th>
            <th>Description</th>
            </thead>
            <tbody id="result">

            </tbody>
        </table>
        <span id="totalPrice">Total Price: </span>
        <button type="button" onclick="getMoney()" id="btnCheckOut">CheckOut</button>
    </div>

</section>

<div id="overlay" class="web_dialog_overlay"></div>

<div id="dialogAddtoCart" class="web_dialog">
    <table style="width: 100%; border: 0px;" cellpadding="3" cellspacing="0">
        <tr>
            <td class="web_dialog_title">Update</td>
            <td class="web_dialog_title align_right">
                <a href="#" id="btnClose" onclick="HideUpdate()">x</a>
            </td>
        </tr>
        <form>
            <tr>
                <td>ID:</td>
                <td><input type="text" id="productID" readonly/></td>
            </tr>
            <tr>
                <td>Product Name:</td>
                <td><input type="text" id="productName" readonly/></td>
            </tr>
            <tr>
                <td>Quantity:</td>
                <td><input type="number" id="quantity" required onblur="checkQuantity()"/></td>
            </tr>
            <tr>
                <td><label class="error" for="quantity" id="quantity_error">Product in stock is not enough.</label></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><input type="text" id="price" readonly/></td>
            </tr>
            <tr>
                <td>Category:</td>
                <td>
                    <input type="text" id="category" readonly>
                </td>
            </tr>
            <tr>
                <td>Description:</td>
                <td>
                    <input type="text" id="description" readonly>
                </td>
            </tr>
            <tr>
                <td>
                    <button class="btn btn-default pull-right" onclick="HideAddToCart()" type="button">Cancel</button>
                </td>
                <td>
                    <button class="btn btn-warning" type="button" id="btnAddToCart" onclick="AddtoCart()">Add</button>
                </td>
            </tr>
        </form>
    </table>
</div>


<script language="JavaScript" src="resources/js/jquery-3.2.1.js"></script>
<script>
    var arrayOrder = new Array();
    var userId;
    $(document).ready(function () {
        userId = localStorage.getItem('userId');
        console.log("userId: " + userId);
//        var param = window.location.search.split('?')[1];
//        if (param != null) {
//            userId = decodeURIComponent(param.split('=')[1]);
//        }
        var rightHeight = $('#section-right').height();
        $('#section-left').height(rightHeight);
        $("#btnAddToCart").attr("disabled", "disabled");
        $(".error").hide();
        $("#totalPrice").hide();
        $("#btnCheckOut").hide();
        showData();
    });

    function showData() {
        $("#totalPrice").hide();
        $("#btnCheckOut").hide();
        $("#searchValue").val("");
        $.ajax({
            url: '/api/products',
            method: 'GET',
            success: function (data) {
                $("#result").empty();
                var tr;
                for (var i = 0; i < data.length; i++) {
                    tr = $('<tr/>');
                    tr.append('<td>' + data[i].productId + '</td>');
                    tr.append('<td>' + data[i].productName + '</td>');
                    tr.append('<td>' + data[i].quantity + '</td>');
                    tr.append('<td>' + data[i].price + '</td>');
                    tr.append('<td>' + data[i].category.categoryName + '</td>');
                    if (data[i].description == null) {
                        tr.append('<td>' + ' ' + '</td>');
                    } else {
                        tr.append('<td>' + data[i].description + '</td>');
                    }
                    if(data[i].quantity > 0){
                    tr.append('<td><button class="btn btn-warning" onclick=\'ShowAddProduct("' + data[i].productId + '","' + data[i].productName + '","'
                        + data[i].quantity + '","' + data[i].price + '","' + data[i].description + '","'
                        + data[i].category.categoryName + '")\'>Add to Cart</button></td> ');
                    }else{
                        tr.append('<td><button class="btn btn-warning" disabled="disabled"\'>Add To Cart</button></td>');
                    }
                    $("#result").append(tr);
                }
            }

        })
    }


    function ShowAddProduct(id, productName, quantity, price, description, category) {
        $("#productID").val(id);
        $("#productName").val(productName);
        temp = quantity;
        $("#price").val(price);
        if (description === "null") {
            $("#description").val("");
        } else {
            $("#description").val(description);
        }
        $("#category").val(category);
        $("#overlay").show();
        $("#dialogAddtoCart").fadeIn(300);
    }

    function HideAddToCart() {
        $(".error").hide();
        $("#overlay").hide();
        $("#dialogAddtoCart").fadeOut(300);
        $("#quantity").val("");
    }

    function checkQuantity() {
        var inputQuantity = $("#quantity").val();
        if (parseInt(inputQuantity) > parseInt(temp)) {
            $(".error").show();
        } else {
            $(".error").hide();
            $("#btnAddToCart").removeAttr("disabled");
            $("#quantity").val(inputQuantity);
        }
    }

    function AddtoCart() {
        var TblOrder = new Object();
        TblOrder = {
            userId: userId,
            productId: $("#productID").val(),
            productName: $("#productName").val(),
            quantity: $("#quantity").val(),
            price: $("#price").val(),
            category: $("#category").val(),
            description: $("#description").val()
        };
        arrayOrder.push(TblOrder);
        $("#overlay").hide();
        $("#dialogAddtoCart").fadeOut();
        $("#quantity").val("");
    }

    function CheckOut() {
        $("#btnSearch").hide();
        $("#viewAll").hide();
        $("#searchValue").hide();
        $("#totalPrice").show();
        $("#btnCheckOut").show();
        $("#result").empty();
        $("#totalPrice").empty();
        var tr;
        var total = 0;
        for (var i = 0; i < arrayOrder.length; i++) {
            tr = $("<tr/>");
            tr.append("<td>" + arrayOrder[i].productId + "</td>");
            tr.append("<td>" + arrayOrder[i].productName + "</td>");
            tr.append("<td>" + arrayOrder[i].quantity + "</td>");
            tr.append("<td>" + arrayOrder[i].price + "</td>");
            tr.append("<td>" + arrayOrder[i].category + "</td>");
            tr.append("<td>" + arrayOrder[i].description + "</td>");
            total = total + (arrayOrder[i].quantity * arrayOrder[i].price);
            $("#result").append(tr);
        }
        $("#totalPrice").append("$ " + total);
    }

    function getMoney() {
        $.ajax({
            url: '/api/order/checkOut',
            method: 'POST',
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: JSON.stringify(arrayOrder),
            success: function (data) {
                arrayOrder = new Array();
                alert("Order Success");
                showData();
            }
        })
    }
</script>


</body>
</html>
