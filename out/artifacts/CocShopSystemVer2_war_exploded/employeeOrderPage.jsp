<%--
  Created by IntelliJ IDEA.
  User: Nguyen Cong Chinh
  Date: 6/27/2017
  Time: 4:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee Order Page</title>
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
        <h3>CheckOut Order</h3>
        <table class="table table-hover table-stripped" id="tblResult">
            <thead>
            <th>Order ID</th>
            <th>Order Date</th>
            <th>Customer Name</th>
            </thead>
            <tbody id="result">

            </tbody>
        </table>
    </div>
</section>
</body>


<script language="JavaScript" src="resources/js/jquery-3.2.1.js"></script>

<script>
    $(document).ready(function () {
        showData();
    });

    function showData() {
        $.ajax({
            url: '/api/employee/getAllOrderForCheckout',
            method: 'GET',
            success: function (data) {
                $("#result").empty();
                var tr;
                for(var i = 0; i < data.length; i++){
                    tr = $("<tr/>");
                    tr.append("<td>" + data[i].orderId + "</td>");
                    tr.append("<td>" + data[i].orderDate + "</td>");
                    tr.append("<td>" + data[i].customer.firstname + " " + data[i].customer.lastname + "</td>");
                    tr.append('<td><button class="btn btn-warning" onclick=\'viewDetail("' + data[i].orderId + '")\'>View Details</button></td>');
                    $("#result").append(tr);
                }
            }
        });
    }

    function viewDetail(orderId) {
        window.location.href = "employeeOrderDetailPage.jsp?orderId=" + orderId;
    }



</script>
</html>
