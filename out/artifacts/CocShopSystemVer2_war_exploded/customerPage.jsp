<%--
  Created by IntelliJ IDEA.
  User: Nguyen Cong Chinh
  Date: 6/22/2017
  Time: 11:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer Page</title>
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
        <h3>Customer Management</h3>

        <form autocomplete="off">
            <input type="text" id="searchValue" class="form-control" style="width: 30%; display: inline-block" required>
            <button onclick="SearchCustomer()" type="button" class="btn btn-success" style="display: inline-block"
                    id="btnSearch">
                Search
            </button>
            <button type="button" class="btn btn-success" onclick="showData()">View all customers</button>
        </form>

        <table class="table table-hover table-stripped" id="tblResult">
            <thead>
            <th>Customer ID</th>
            <th>User</th>
            <th>Password</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>BirthDate</th>
            </thead>
            <tbody id="result">

            </tbody>
        </table>
    </div>


    <div id="overlay" class="web_dialog_overlay"></div>

    <div id="dialogDelete" class="web_dialog">
        <table style="width: 100%; height: 100%; border: 0px;" cellpadding="3" cellspacing="0">
            <tr style="height: 20%">
                <td class="web_dialog_title">Delete</td>
                <td class="web_dialog_title align_right">
                    <a href="#" onclick="HideDelete()">x</a>
                </td>
            </tr>
            <tr style="height: 40%">
                <td colspan="2">Are you sure?</td>
            </tr>
            <tr style="height: 20%">
                <td>
                    <button class="btn btn-default pull-right" onclick="HideDelete()">Cancel</button>
                </td>
                <td>
                    <button class="btn btn-danger" id="customerDelete" onclick="deleteData()">Delete</button>
                </td>
            </tr>
        </table>
    </div>

</section>
<footer class="col-sm-12" id="footer-wrapper">
    <div class="col-sm-2" id="footer-left">&nbsp;</div>
    <div class="col-sm-10" id="footer-right"><span> Design by <b>ThinhLP</b></span> <span
            class="pull-right">Version 1.0</span></div>
</footer>

<script language="JavaScript" src="resources/js/jquery-3.2.1.js"></script>
<script>
    $(document).ready(function () {
        showData();
    })

    function showData() {
        $.ajax({
            url: '/api/customers',
            method: 'GET',
            success: function (data) {
                $("#result").empty();
                var tr;
                for (var i = 0; i < data.length; i++) {
                    tr = $('<tr/>');
                    tr.append('<td>' + data[i].userId + '</td>');
                    tr.append('<td>' + data[i].username + '</td>');
                    tr.append("<td><input readonly type='password' value=" + data[i].password + ">" + "</td>");
                    tr.append('<td>'  + data[i].firstname + '</td>');
                    tr.append('<td>' + data[i].lastname +  '</td>');
                    tr.append('<td>' + data[i].email + '</td>');
                    tr.append('<td>' + data[i].birthdate + '</td>');
                    tr.append('<td><button class="btn btn-warning" onclick=\'ShowOrderDetails("' + data[i].userId + '")\'>View Orders</button></td>');
                    tr.append('<td><button class="btn btn-danger" onclick=\'ShowDelete("' + data[i].userId + '")\'>Delete</button></td>');
                    $("#result").append(tr);
                }
            }
        });
    }


    function SearchCustomer() {
        var searchValue = $("#searchValue").val();
        if (searchValue != ""){
            $.ajax({
                url: '/api/customers/searchValue',
                method: 'POST',
                data: 'searchValue=' + searchValue,
                success: function (data) {
                    $("#result").empty();
                    var tr;
                    for (var i = 0; i < data.length; i++) {
                        tr = $('<tr/>');
                        tr.append('<td>' + data[i].userId + '</td>');
                        tr.append('<td>' + data[i].username + '</td>');
                        tr.append("<td><input readonly type='password' value=" + data[i].password + ">" + "</td>");
                        tr.append('<td>' + data[i].lastname + ' ' + data[i].firstname + '</td>');
                        tr.append('<td>' + data[i].email + '</td>');
                        tr.append('<td>' + data[i].birthdate + '</td>');
                        tr.append('<td><button class="btn btn-warning" onclick=\'ShowOrderDetails("' + data[i].userId + '")\'>View Orders</button></td>');
                        tr.append('<td><button class="btn btn-danger" onclick=\'ShowDelete("' + data[i].userId + '")\'>Delete</button></td>');
                        $("#result").append(tr);
                    }
                }
            })
        }
    }

    function ShowDelete(customerId) {
        $("#customerDelete").val(customerId);
        $("#overlay").show();
        $("#dialogDelete").fadeIn(300);
    }

    function deleteData() {
        $.ajax({
            url: '/api/customers/delete',
            method: 'POST',
            data: 'customerId=' + $("#customerDelete").val(),
            success: function (data) {
                HideDelete();
                showData();
            }
        })
    }

    function HideDelete() {
        $("#overlay").hide();
        $("#dialogDelete").fadeOut(300);
    }

    function ShowOrderDetails(userId) {
            window.location.href = "customerOrderDetailsPage.jsp?customerId=" + userId;
    }

</script>

</body>
</html>
