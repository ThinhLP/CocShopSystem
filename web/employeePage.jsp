<%--
  Created by IntelliJ IDEA.
  User: Nguyen Cong Chinh
  Date: 6/21/2017
  Time: 10:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Employee Page</title>
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
        <div class="admin-setting pull-right"><img src="resources/img/thinhlp.jpg" /> <span><b>Le Phuc Thinh</b></span><a href="">Log out</a></div>
    </div>
</header>
<section class="col-sm-12" id="main-section-wrapper">
    <div class="col-sm-2" id="section-left">
        <div class="admin-info"> <img src="resources/img/thinhlp.jpg" /> <span class="admin-name"><b>Le Phuc Thinh</b></span> <span class="admin-status"><i class="fa fa-circle text-success" aria-hidden="true"></i> Online</span></div>
        <div class="menu-navigation">MANAGEMENT MENU</div>
        <ul id="menu-wrapper">
            <li><a href="adminPage.jsp"><i class="fa fa-dropbox" aria-hidden="true"></i>Products</a></li>
            <li class="menu-active"><a href="employeePage.jsp"><i class="fa fa-users" aria-hidden="true"></i>Employees</a></li>
            <li></li>
        </ul>
    </div>
    <div class="col-sm-10" id="section-right">
        <h3>Employee Management</h3>
        <button type="button" class="btn btn-success" id="btnCreate"
                onclick="ShowCreate()">Add new employee
        </button>
        <table class="table table-hover table-stripped" id="tblResult">
            <thead>
                <th>Employee ID</th>
                <th>User</th>
                <th>Password</th>
                <th>Name</th>
                <th>Email</th>
                <th>BirthDate</th>
             </thead>
            <tbody id="result">

            </tbody>
        </table>
    </div>
</section>
<footer class="col-sm-12" id="footer-wrapper">
    <div class="col-sm-2" id="footer-left">&nbsp;</div>
    <div class="col-sm-10" id="footer-right"><span> Design by <b>ThinhLP</b></span> <span class="pull-right">Version 1.0</span></div>
</footer>

<%--For popup--%>
<div id="overlay" class="web_dialog_overlay"></div>

<div id="dialogUpdate" class="web_dialog">
        <table style="width: 100%; border: 0px;" cellpadding="3" cellspacing="0">
            <tr>
                <td class="web_dialog_title">Update</td>
                <td class="web_dialog_title align_right">
                    <a href="#" id="btnClose" onclick="HideUpdate()">x</a>
                </td>
            </tr>
            <form id="empUpdate">
            <tr>
                <td>Employee ID:</td>
                <td><input type="text" id="empId" readonly/></td>
            </tr>
            <tr>
                <td>Username:</td>
                <td><input type="text" id="username" required/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" id="password"/></td>
            </tr>
            <tr>
                <td>New Password:</td>
                <td><input type="password" id="newpassword" required></td>
            </tr>
            <tr>
                <td>FirstName:</td>
                <td><input type="text" id="firstname"/></td>
            </tr>
            <tr>
                <td>LastName:</td>
                <td><input type="text" id="lastname"></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="email" id="mail" required></td>
            </tr>
            <tr>
                <td>Birthdate:</td>
                <td><input type="date" id="birthdate"></td>
            </tr>
            <tr>
                <td>
                    <button class="btn btn-default pull-right" onclick="HideUpdate()" type="button">Cancel</button>
                </td>
                <td>
                    <button class="btn btn-warning" onclick="updateData()" id="btnUpdate" value="Update">Update</button>
                </td>
            </tr>
            </form>
        </table>
</div>

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
                <button class="btn btn-danger" id="empDelete" onclick="deleteData()">Delete</button>
            </td>
        </tr>
    </table>
</div>

<div id="dialogCreate" class="web_dialog">
    <table style="width: 100%; border: 0px;" cellpadding="3" cellspacing="0">
        <tr>
            <td class="web_dialog_title">Add new employee</td>
            <td class="web_dialog_title align_right">
                <a href="#" id="close" onclick="HideCreate()">x</a>
            </td>
        </tr>
        <form autocomplete="off" id="empCreate">
            <tr>
                <td>Username:</td>
                <td><input type="text" id="createusername" required/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" id="createpassword" required/></td>
            </tr>
            <tr>
                <td>FirstName:</td>
                <td><input type="text" id="createFirstName" required/></td>
            </tr>
            <tr>
                <td>LastName:</td>
                <td><input type="text" id="createLastName"></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td>
                  <input type="email" id="createMail" required>
                </td>
            </tr>

            <tr>
                <td>Birthdate:</td>
                <td>
                    <input type="date" id="createBirthdate">
                </td>
            </tr>
            <tr>
                <td>
                    <button class="btn btn-default pull-right" onclick="HideCreate()" type="button">Cancel</button>
                </td>
                <td>
                    <button class="btn btn-success" onclick="CreateEmployee()" id="btnCreatePro">Create</button>
                </td>
            </tr>
        </form>
    </table>
</div>

<script language="JavaScript" src="resources/js/jquery-3.2.1.js"></script>
<script>

    $(document).ready(function () {
        showData();
    });

    function showData() {
        $.ajax({
            url: '/api/employees',
            method: 'GET',
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
                    tr.append('<td><button class="btn btn-warning" onclick=\'ShowUpdate("' + data[i].userId + '","' + data[i].username + '","'
                        + data[i].password + '","' + data[i].firstname + '","' + data[i].lastname + '","' + data[i].email
                        + '","' + data[i].birthdate + '")\'>Update</button></td>');
                    tr.append('<td><button class="btn btn-danger" onclick=\'ShowDelete("' + data[i].userId + '")\'>Delete</button></td>');
                    $("#result").append(tr);
                }
            }
        });
    }

    function ShowUpdate(userId, username, password, firstname, lastname, email, birthdate) {
        $("#empId").val(userId);
        $("#username").val(username);
        $("#password").val(password);
        $("#firstname").val(firstname);
        $("#lastname").val(lastname);
        $("#mail").val(email);
        $("#birthdate").val(birthdate);
        $("#overlay").show();
        $("#dialogUpdate").fadeIn(300);
    }

    function updateData() {
        $("#btnUpdate").click(function () {
            if($("#empUpdate").validity()){
                $.ajax({
                    url: '/api/employees/update',
                    method: 'POST',
                    data: 'userId=' + $("#empId").val() + '&username=' + $("#username").val() + '&password=' + $("#newpassword").val()
                    + '&firstname=' + $("#firstname").val() + '&lastname=' + $("#lastname").val() + '&mail=' + $("#mail").val()
                    + '&birthdate=' + $("#birthdate").val(),
                    success: function (data) {
                        HideUpdate();
                        showData();
                    }
                });
            }
        })

    }

    function HideUpdate() {
        $("#overlay").hide();
        $("#dialogUpdate").fadeOut(300);
    }


    function ShowDelete(userId) {
        $("#empDelete").val(userId);
        $("#overlay").show();
        $("#dialogDelete").fadeIn(300);
    }

    function HideDelete() {
        $("#overlay").hide();
        $("#dialogDelete").fadeOut(300);
    }

    function deleteData() {
        console.log($("#empDelete").val());
        $.ajax({
            url: '/api/employees/delete',
            method: 'POST',
            data: 'empId=' + $("#empDelete").val(),
            success: function (data) {
                HideDelete();
                showData();
            }
        });
    }

    function ShowCreate() {
        $("#createusername").empty();
        $("#createpassword").empty();
        $("#createFirstName").empty();
        $("#createLastName").empty();
        $("#createMail").empty();
        $("#createBirthdate").empty();
        $("#overlay").show();
        $("#dialogCreate").fadeIn(300);
    }

    function HideCreate() {
        $("#overlay").hide();
        $("#dialogCreate").fadeOut(300);
    }

    function CreateEmployee(){
        $.ajax({
            url: '/api/employees/create',
            method: 'POST',
            data: 'username=' + $("#createusername").val() + '&password=' + $("#createpassword").val() + '&firstname=' + $("#createFirstName").val()
                    + '&lastname=' + $("#createLastName").val() + '&mail=' + $("#createMail").val() + '&birthdate=' + $("#createBirthdate").val(),
            success: function (data) {
                HideCreate();
                showData();
            }
        })
    }

</script>
</body>
</html>
