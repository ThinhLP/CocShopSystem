<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Nguyen Cong Chinh
  Date: 6/19/2017
  Time: 2:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Admin Page</title>
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
        <div class="admin-info"><img src="resources/img/chinhnc.jpg"/> <span class="admin-name"><b>Nguyen Cong Chinh</b></span>
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
        <h3>Product Management</h3>
        <button type="button" class="btn btn-success" id="btnCreate" onclick="ShowCreate()">Add new product</button>

        <form autocomplete="off">
            <input type="text" id="searchValue" placeholder="Enter Product Name" class="form-control" style="width: 30%; display: inline-block" required>
            <button onclick="SearchProduct()" type="button" class="btn btn-success" style="display: inline-block"
                    id="btnSearch">
                Search
            </button>
            <button type="button" class="btn btn-success" onclick="showData()">View all product</button>
        </form>


        <table class="table table-hover table-stripped" id="tblResult">
            <thead>
            <th>ID</th>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Create At</th>
            <th>Update At</th>
            <th>Category</th>
            <th>Description</th>
            </thead>
            <tbody id="result">

            </tbody>
        </table>
    </div>
</section>
<footer class="col-sm-12" id="footer-wrapper">
    <div class="col-sm-2" id="footer-left">&nbsp;</div>
    <div class="col-sm-10" id="footer-right"><span> Design by <b>ThinhLP</b></span> <span
            class="pull-right">Version 1.0</span></div>
</footer>


<div id="overlay" class="web_dialog_overlay"></div>


<div id="dialogUpdate" class="web_dialog">
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
                <td><input type="text" id="productName" required/></td>
            </tr>
            <tr>
                <td><label class="error" for="productName" id="productName_error">This field is required.</label></td>
            </tr>
            <tr>
                <td>Quantity:</td>
                <td><input type="number" id="quantity" required/></td>
            </tr>
            <tr>
                <td><label class="error" for="quantity" id="quantity_error">This field is required.</label></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><input type="text" id="price" required/></td>
            </tr>
            <tr>
                <td><label class="error" for="price" id="price_error">This field is required.</label></td>
            </tr>
            <tr>
                <td>Category:</td>
                <td>
                    <select id="updateCategory" style="width: 87%" required>

                    </select>
                </td>
            </tr>
            <tr>
                <td>Description:</td>
                <td>
                    <textarea cols="21" rows="10" id="description"></textarea>
                </td>
            </tr>
            <tr>
                <td>
                    <button class="btn btn-default pull-right" onclick="HideUpdate()" type="button">Cancel</button>
                </td>
                <td>
                    <button class="btn btn-warning" type="button" onclick="updateData()">Update</button>
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
                <button class="btn btn-danger" id="delete" onclick="deleteData()">Delete</button>
            </td>
        </tr>
    </table>
</div>

<div id="dialogCreate" class="web_dialog">
    <table style="width: 100%; border: 0px;" cellpadding="3" cellspacing="0">
        <tr>
            <td class="web_dialog_title">Add New Product</td>
            <td class="web_dialog_title align_right">
                <a href="#" id="close" onclick="HideCreate()">x</a>
            </td>
        </tr>
        <form autocomplete="off">
            <tr>
                <td>Product Name:</td>
                <td><input type="text" id="createProductName" required/></td>
            </tr>
            <tr>
                <td><label class="error" for="createProductName" id="createProName_error">This field is
                    required.</label></td>
            </tr>
            <tr>
                <td>Quantity:</td>
                <td><input type="text" id="createQuantity" required/></td>
            </tr>
            <tr>
                <td><label class="error" for="createQuantity" id="createQuantity_error">This field is required.</label>
                </td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><input type="text" id="createPrice" required/></td>
            </tr>
            <tr>
                <td><label class="error" for="createPrice" id="createPrice_error">This field is required.</label></td>
            </tr>
            <tr>
                <td>Category:</td>
                <td>
                    <select id="createCategory" required>

                    </select>
                </td>
            </tr>
            <tr>
                <td><label class="error" for="createCategory" id="createCategory_error">Please select category.</label>
                </td>
            </tr>
            <tr>
                <td>Description:</td>
                <td>
                    <textarea cols="21" rows="10" id="createDescription"></textarea>
                </td>
            </tr>
            <tr>
                <td>
                    <button class="btn btn-default pull-right" onclick="HideCreate()" type="button">Cancel</button>
                </td>
                <td>
                    <button class="btn btn-success" type="button" onclick="CreateProduct()" id="btnCreatePro">Create
                    </button>
                </td>
            </tr>
        </form>
    </table>
</div>


</div>
<script language="JavaScript" src="resources/js/jquery-3.2.1.js"></script>
<script>
    var temp;
    $(document).ready(function () {

        var tmp = window.location.href;
        var param = window.location.search.split('?')[1];
        if (param != null) {
            temp = decodeURIComponent(param.split('=')[1]);
        }
        showData();
        var rightHeight = $('#section-right').height();
        $('#section-left').height(rightHeight);
    });

    
    function showData() {
        $("#searchValue").val("");
        $(".error").hide();

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
                    //tr.append('<td>' + data[i].price + '</td>');
                    tr.append('<td>' + data[i].price + '</td>');
                    tr.append('<td>' + data[i].createAt + '</td>');
                    if (data[i].updateAt == null) {
                        tr.append('<td>' + ' ' + '</td>');
                    } else {
                        tr.append('<td>' + data[i].updateAt + '</td>');
                    }
                    //tr.append('<td>' + data[i].tblCategoryByTblCategoryCategoryId.categoryName + '</td>');
                    tr.append('<td>' + data[i].category.categoryName + '</td>');
                    if (data[i].description == null) {
                        tr.append('<td>' + ' ' + '</td>');
                    } else {
                        tr.append('<td>' + data[i].description + '</td>');
                    }
//                    tr.append('<td><button class="btn btn-warning" onclick=\'ShowUpdate("' + data[i].productId + '","' + data[i].productName + '","'
//                        + data[i].quantity + '","' + data[i].price + '","'
//                        + data[i].tblCategoryByTblCategoryCategoryId.categoryId + '","' + data[i].description +'")\'>Update</button></td> ');
                    tr.append('<td><button class="btn btn-warning" onclick=\'ShowUpdate("' + data[i].productId + '","' + data[i].productName + '","'
                        + data[i].quantity + '","' + data[i].price + '","'
                        + data[i].category.categoryId + '","' + data[i].description +'")\'>Update</button></td> ');
                    tr.append('<td><button class="btn btn-danger" onclick=\'ShowDelete("' + data[i].productId + '")\'>Delete</button></td>');
                    $("#result").append(tr);
                }
            }

        })
    }


    function ShowUpdate(productId, productName, quantity, price, categoryId, description) {
        $("#productID").val(productId);
        $("#productName").val(productName);
        $("#quantity").val(quantity);
        $("#price").val(price);
        $("#description").val(description);
        $.ajax({
            url: '/api/categories',
            method: 'GET',
            success: function (data) {
                $("#updateCategory").empty();
                var tmp = "<option selected disabled>Choose Here</option>";
                $("#updateCategory").append(tmp);
                for (var i = 0; i < data.length; i++) {
                    if (data[i].categoryId == categoryId) {
                        var option = "<option selected value=" + data[i].categoryId + ">" + data[i].categoryName + "</option>";
                    } else {
                        var option = "<option value=" + data[i].categoryId + ">" + data[i].categoryName + "</option>";
                    }
                    $("#updateCategory").append(option);
                }
            }
        });
        $("#overlay").show();
        $("#dialogUpdate").fadeIn(300);
    }

    function checkValid() {
        var isValid = true;
        $('.error').hide();
        if ($("#productName").val() === "") {
            $("label#productName_error").show();
            $("input#productName").focus();
            isValid = false;
        }
        if ($("#quantity").val() === "") {
            $("label#quantity_error").show();
            $("input#quantity").focus();
            isValid = false;
        }
        if ($("#price").val() === "") {
            $("label#price_error").show();
            $("input#price").focus();
            isValid = false;
        }
        return isValid;
    }

    function updateData() {
        var check = checkValid();
        if (check == true) {
            $.ajax({
                url: '/api/product/update',
                method: 'POST',
                data: 'productId=' + $("#productID").val() + '&productName=' + $("#productName").val() + '&quantity=' + $("#quantity").val()
                + '&price=' + $("#price").val() + '&description=' + $("#description").val() + '&categoryId=' + $("#updateCategory").find(":selected").val(),
                success: function (data) {
                    HideUpdate();
                    if ($("#searchValue").val() != "") {
                        SearchProduct();
                    } else {
                        showData();
                    }
                }
            });
        }

    }

    function HideUpdate() {
        $(".error").hide();
        $("#overlay").hide();
        $("#dialogUpdate").fadeOut(300);
    }


    function deleteData() {
        console.log($("#delete").val());
        $.ajax({
            url: '/api/product/delete',
            method: 'POST',
            data: 'productId=' + $("#delete").val(),
            success: function (data) {
                HideDelete();
                if ($("#searchValue").val() != "") {
                    SearchProduct();
                } else {
                    showData();
                }
            }
        });
    }

    function ShowDelete(productId) {
        $("#delete").val(productId);
        $("#overlay").show();
        $("#dialogDelete").fadeIn(300);
    }

    function HideDelete() {
        $("#overlay").hide();
        $("#dialogDelete").fadeOut(300);
    }


    function ShowCreate() {
        $("#createProductName").empty();
        $("#createQuantity").empty();
        $("#createPrice").empty();
        $("#createDescription").empty();
        $.ajax({
            url: '/api/categories',
            method: 'GET',
            success: function (data) {
                $("#createCategory").empty();
                var tmp = "<option selected disabled>Choose Here</option>";
                $("#createCategory").append(tmp);
                for (var i = 0; i < data.length; i++) {
                    var option = "<option value=" + data[i].categoryId + ">" + data[i].categoryName + "</option>";
                    $("#createCategory").append(option);
                }
            }
        });
        $("#overlay").show();
        $("#dialogCreate").fadeIn(300);
    }

    function checkCreate() {
        var isValid = true;
        $('.error').hide();
        if ($("#createProductName").val() === "") {
            $("label#createProName_error").show();
            $("input#createusername").focus();
            isValid = false;
        }
        if ($("#createQuantity").val() === "") {
            $("label#createQuantity_error").show();
            $("input#createQuantity").focus();
            isValid = false;
        }
        if ($("#createPrice").val() === "") {
            $("label#createPrice_error").show();
            $("input#createPrice").focus();
            isValid = false;
        }
        if ($("#createCategory").val() === "") {
            $("label#createCategory_error").show();
            $("input#createCategory").focus();
            isValid = false;
        }
        return isValid;
    }

    function CreateProduct() {
        var check = checkCreate();
        if (check == true) {
            $.ajax({
                url: '/api/product/create',
                method: 'POST',
                data: 'createProductName=' + $("#createProductName").val() + '&createQuantity=' + $("#createQuantity").val()
                + "&createPrice=" + $("#createPrice").val() + "&createCategory=" + $("#createCategory").find(":selected").val()
                + "&createDescription=" + $("#createDescription").val(),
                success: function (data) {
                    HideCreate();
                    showData();
                }
            });
        }
    }

    function HideCreate() {
        $(".error").hide();
        $("#overlay").hide();
        $("#dialogCreate").fadeOut(300);
    }

    function SearchProduct() {
        var searchValue = $("#searchValue").val();
        if (searchValue != "") {
            $.ajax({
                url: '/api/prorudct/searchValue',
                method: 'POST',
                data: 'searchValue=' + $("#searchValue").val(),
                success: function (data) {
                    $("#result").empty();
                    if (data.length == 0) {
                        var tr = $('<div/>');
                        tr.append("<h3>No record is matched" + "</h3>");
                        $("#result").append(tr);

                    } else {
                        var tr;
                        for (var i = 0; i < data.length; i++) {
                            tr = $('<tr/>');
                            tr.append('<td>' + data[i].productId + '</td>');
                            tr.append('<td>' + data[i].productName + '</td>');
                            tr.append('<td>' + data[i].quantity + '</td>');
                            tr.append('<td>' + data[i].price + '</td>');
                            tr.append('<td>' + data[i].createAt + '</td>');
                            if (data[i].updateAt == null) {
                                tr.append('<td>' + ' ' + '</td>');
                            } else {
                                tr.append('<td>' + data[i].updateAt + '</td>');
                            }
//                            tr.append('<td>' + data[i].tblCategoryByTblCategoryCategoryId.categoryName + '</td>');
                            tr.append('<td>' + data[i].category.categoryName + '</td>');
                            if (data[i].description == null) {
                                tr.append('<td>' + ' ' + '</td>');
                            } else {
                                tr.append('<td>' + data[i].description + '</td>');
                            }
//                            tr.append('<td><button class="btn btn-warning" onclick=\'ShowUpdate("' + data[i].productId + '","' + data[i].productName + '","'
//                                + data[i].quantity + '","' + data[i].price + '","'
//                                + data[i].tblCategoryByTblCategoryCategoryId.categoryId + '")\'>Update</button></td> ');
                            tr.append('<td><button class="btn btn-warning" onclick=\'ShowUpdate("' + data[i].productId + '","' + data[i].productName + '","'
                                + data[i].quantity + '","' + data[i].price + '","'
                                + data[i].category.categoryId + '")\'>Update</button></td> ');
                            tr.append('<td><button class="btn btn-danger" onclick=\'ShowDelete("' + data[i].productId + '")\'>Delete</button></td>');
                            $("#result").append(tr);
                        }
                    }
                }
            });
        }

    }

</script>
</body>
</html>
