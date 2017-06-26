<%--
  Created by IntelliJ IDEA.
  User: Nguyen Cong Chinh
  Date: 6/22/2017
  Time: 3:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Details</title>
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
        <div class="admin-setting pull-right"><img src="resources/img/chinhnc.jpg"/>
            <span><b>Nguyen Cong Chinh</b></span><a href="">Log out</a></div>
    </div>
</header>
<section class="col-sm-12" id="main-section-wrapper">
    <div class="col-sm-2" id="section-left">
        <div class="admin-info"><img src="resources/img/chinhnc.jpg"/><span class="admin-name"><b>Nguyen Cong Chinh</b></span>
            <span class="admin-status"><i class="fa fa-circle text-success" aria-hidden="true"></i> Online</span></div>
        <div class="menu-navigation">MANAGEMENT MENU</div>
        <ul id="menu-wrapper">
            <li class="menu-active"><a href="adminPage.jsp"><i class="fa fa-dropbox" aria-hidden="true"></i>Products</a>
            </li>
            <li><a href="employeePage.jsp"><i class="fa fa-users" aria-hidden="true"></i>Employees</a></li>
            <li><a href="customerPage.jsp"><i class="fa fa-users" aria-hidden="true"></i>Customer</a></li>
            <li></li>
        </ul>
    </div>
    <div class="col-sm-10" id="section-right">
        <h3 id="customerName"></h3>
        <select id="orderDate" onchange="selectDate()">

        </select>
        <table class="table table-hover table-stripped" id="tblResult">
            <thead>
            <th>Order ID</th>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Unit Price</th>
            <th>Price(Quantity * Unit Price)</th>
            </thead>
            <tbody id="result">

            </tbody>
        </table>
        <span id="total">Total Price: </span>
    </div>

</section>
<script language="JavaScript" src="resources/js/jquery-3.2.1.js"></script>
<script>
    var temp;
    var param = window.location.search.split('?')[1];
    temp = decodeURIComponent(param.split('=')[1]);
    console.log("Temp: " + temp);
    $(document).ready(function () {
        showData();
    });

    function showData() {
            $.ajax({
                url: '/api/customer/orderDetails',
                method: 'POST',
                data: 'customerId=' + temp,
                success: function (data) {
                    if(data.length == 0){
                        var tr = $("<div/>");
                        tr.append("<h3>No Information" + "</h3>");
                        $("#result").append(tr);
                        $("#orderDate").hide();
                    }else {
                        var tmp;
                        var tr;
                        var array = [];
                        var total = 0;
                        $("#result").empty();
                        $("#orderDate").empty();
                        $("#customerName").append(data[0].tblOrderByTblOrderOrderId.tblUserByCustomerId.firstname + " "
                            + data[0].tblOrderByTblOrderOrderId.tblUserByCustomerId.lastname);

                        /*Set OrderDate*/
                        for (var i = 0; i < data.length; i++) {
                            array.push(data[i].tblOrderByTblOrderOrderId.orderDate);
                        }
                        array = jQuery.unique(array);
                        tmp = $("<option selected value=" + array[0].toString() + ">" + array[0].toString() + "</option>");
                        $("#orderDate").append(tmp);
                        for (var i = 1; i < array.length; i++) {
                            tmp = $("<option  value=" + array[i].toString() + ">" + array[i].toString() + "</option>");
                            $("#orderDate").append(tmp);
                        }

                        /*View Order Details*/
                        for (var i = 0; i < data.length; i++) {
                            if (data[i].tblOrderByTblOrderOrderId.orderDate == array[0].toString()) {
                                tr = $('<tr>');
                                tr.append('<td>' + data[i].tblOrderByTblOrderOrderId.orderId + '</td>');
                                tr.append('<td>' + data[i].tblProductByTblProductProductId.productName + '</td>');
                                tr.append('<td>' + data[i].quantity + '</td>');
                                tr.append('<td>' + data[i].price + '</td>');
                                tr.append('<td>' + data[i].quantity * data[i].price + '</td>');
                                total = total + (data[i].quantity * data[i].price);
                                $("#result").append(tr);
                            }
                        }
                        $("#total").append(total);
                    }
                }

            })

    }

    function selectDate() {
        var date = $("#orderDate").find(":selected").val();
        var total = 0;
        $.ajax({
            url: '/api/customers/viewOrderByOrderDate',
            method: 'POST',
            data: 'orderDate=' + date,
            success: function (data) {
                $("#result").empty();
                var tr;
                for (var i = 0; i < data.length; i++) {
                    tr = $('<tr>');
                    tr.append('<td>' + data[i].tblOrderByTblOrderOrderId.orderId + '</td>');
                    tr.append('<td>' + data[i].tblProductByTblProductProductId.productName + '</td>');
                    tr.append('<td>' + data[i].quantity + '</td>');
                    tr.append('<td>' + data[i].price + '</td>');
                    tr.append('<td>' + data[i].quantity * data[i].price + '</td>');
                    total = total + (data[i].quantity * data[i].price);
                    $("#result").append(tr);
                }
            }
        })
    }


</script>
</body>
</html>
