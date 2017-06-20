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
    <title>Admin Page</title>
    <style>
        .dropdown-content {
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
            z-index: 1;
        }

        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
            margin: 5px;
        }

        .dropdown-content a:hover {
            background-color: #f1f1f1
        }

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
            margin-left: -190px;
            margin-top: -100px;
            background-color: #ffffff;
            border: 2px solid #336699;
            padding: 0px;
            z-index: 102;
            font-family: Verdana;
            font-size: 10pt;
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

        #btnCreate {
            display: block;
            width: 10%;
            margin: 10px;
            text-align: center;
            background-color: #4cae4c;
            border: none;
            border-radius: 5px;
            padding: 5px;
            font-weight: bold;
        }

        #tblResult {
            padding: 10px;
            margin: 10px;
        }

        #tblResult tr, td {
            text-align: center;
            padding-left: 20px !important;
        }

        #tblResult tr:nth-child(odd) {
            background-color: #f0ffff;

        }

        #tblResult thead th {
            padding: 10px 20px;
            background-color: #4684ed;
            text-align: center;
        }

    </style>
    <link rel="stylesheet" href="resources/css/bootstrap.css">
    <script language="JavaScript" src="resources/js/jquery-3.2.1.js"></script>
</head>
<body>
<script>
    $(document).ready(function () {
        showData();
    });

    function showData() {
        $.ajax({
            url: '/api/productList',
            method: 'GET',
            success: function (data) {
                $("#result").empty();
                var tr;
//                var date;
//                var dateTemp;
//                var month;
//                var year;
                for (var i = 0; i < data.length; i++) {
//                    dateTemp = new Date(data[i].createAt);
//                    date = dateTemp.getDate();
//                    month = dateTemp.getMonth();
//                    year = dateTemp.getFullYear();

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
                        tr.append('<td>' + data[i].tblCategoryByTblCategoryCategoryId.categoryName + '</td>');
                        tr.append('<td>' + data[i].description + '</td>');
                        tr.append('<td><button class="btn btn-success" onclick=\'ShowUpdate("' + data[i].productId + '","' + data[i].productName + '","'
                            + data[i].quantity + '","' + data[i].price + '","'
                            + data[i].tblCategoryByTblCategoryCategoryId.categoryId + '")\'>Update</button></td> ');
                        tr.append('<td><button class="btn btn-warning" onclick=\'ShowDelete("' + data[i].productId + '")\'>Delete</button></td>');
                        $("#result").append(tr);
                    }
                }

        })
    }


    function ShowUpdate(productId, productName, quantity, price, categoryId) {
        $("#productID").val(productId);
        $("#productName").val(productName);
        $("#quantity").val(quantity);
        $("#price").val(price);

        $.ajax({
            url: '/api/getCategory',
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


    function updateData() {
        $.ajax({
            url: '/updateProduct',
            method: 'POST',
            data: 'productId=' + $("#productID").val() + '&productName=' + $("#productName").val() + '&quantity=' + $("#quantity").val()
            + '&price=' + $("#price").val() + '&description=' + $("#description").val(),
            success: function (data) {
                HideUpdate();
                showData();
            }
        });
    }

    function HideUpdate() {
        $("#overlay").hide();
        $("#dialogUpdate").fadeOut(300);
    }


    function deleteData() {
        console.log($("#delete").val());
        $.ajax({
            url: '/deleteProduct',
            method: 'POST',
            data: 'productId=' + $("#delete").val(),
            success: function (data) {
                HideDelete();
                showData();
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
            url: '/api/getCategory',
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


    function CreateProduct() {
        $.ajax({
            url: '/createProduct',
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

    function HideCreate() {
        $("#overlay").hide();
        $("#dialogCreate").fadeOut(300);
    }

</script>

<div style="width: 15%;float:left" class="dropdown-content">
    <a href="#">Product</a>
    <a href="#">Order</a>
</div>
<div style="width:5%;float:left;">
    <p>&nbsp</p>
</div>
<div style="width: 80%;float:left">
    <button type="button" id="btnCreate"
            onclick="ShowCreate()">Add new product
    </button>
    <table id="tblResult">
        <thead>
        <th>Product ID</th>
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


    <div id="overlay" class="web_dialog_overlay"></div>
    <div id="dialogUpdate" class="web_dialog">
        <table style="width: 100%; border: 0px;" cellpadding="3" cellspacing="0">
            <tr>
                <td class="web_dialog_title">Update</td>
                <td class="web_dialog_title align_right">
                    <a href="#" id="btnClose" onclick="HideUpdate()">Close</a>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td>Product ID:</td>
                <td><input type="text" id="productID" readonly/></td>
            </tr>
            <tr>
                <td>Product Name:</td>
                <td><input type="text" id="productName"/></td>
            </tr>
            <tr>
                <td>Quantity:</td>
                <td><input type="text" id="quantity"/></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><input type="text" id="price"/></td>
            </tr>
            <tr>
                <td>Category:</td>
                <td>
                    <select id="updateCategory" style="width: 87%">

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
                    <button onclick="HideUpdate()">Cancel</button>
                </td>
                <td>
                    <button onclick="updateData()">Update</button>
                </td>
            </tr>
        </table>
    </div>

    <div id="dialogDelete" class="web_dialog">
        <table style="width: 100%; height: 100%; border: 0px;" cellpadding="3" cellspacing="0">
            <tr style="height: 20%">
                <td class="web_dialog_title">Delete</td>
                <td class="web_dialog_title align_right">
                    <a href="#" onclick="HideDelete()">Close</a>
                </td>
            </tr>
            <tr style="height: 20%">
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr style="height: 40%">
                <td colspan="2">Are you sure?</td>
            </tr>
            <tr style="height: 20%">
                <td>
                    <button onclick="HideDelete()">Cancel</button>
                </td>
                <td>
                    <button id="delete" onclick="deleteData()">Delete</button>
                </td>
            </tr>
        </table>
    </div>

    <div id="dialogCreate" class="web_dialog">
        <table style="width: 100%; border: 0px;" cellpadding="3" cellspacing="0">
            <tr>
                <td class="web_dialog_title">Add New Product</td>
                <td class="web_dialog_title align_right">
                    <a href="#" id="close" onclick="HideCreate()">Close</a>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <form autocomplete="off">
            <tr>
                <td>Product Name:</td>
                <td ><input type="text" id="createProductName" required/></td>
            </tr>
            <tr>
                <td>Quantity:</td>
                <td > <input type="text" id="createQuantity" required/></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><input type="text"  id="createPrice" required/></td>
            </tr>
            <tr>
                <td>Category:</td>
                <td>
                    <select id="createCategory" required>

                    </select>
                </td>
            </tr>

            <tr>
                <td>Description:</td>
                <td>
                    <textarea cols="21" rows="10" id="createDescription"></textarea>
                </td>
            </tr>
            <tr>
                <td><button onclick="HideCreate()">Cancel</button></td>
                <td><button onclick="CreateProduct()" type="button">Create</button></td>
            </tr>
            </form>
        </table>
    </div>


</div>

</body>
</html>
