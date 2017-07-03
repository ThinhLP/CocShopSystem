<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Nguyen Cong Chinh
  Date: 6/19/2017
  Time: 10:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<h2>Login</h2>
<%--<spring:url value="/login" var="login"></spring:url>--%>
<form>
    <table>
        <tr>
            <td>Username:</td>
            <td><input type="text" name="username" id="username"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password" id="password"></td>
        </tr>
    </table>
    <%--<input type="submit" value="Login">--%>
    <%--<input type="reset" value="Reset">--%>
    <button type="button" onclick="login()">Login</button>
    <button type="reset">Reset</button>
<%--</form:form>--%>
</body>


<script language="JavaScript" src="resources/js/jquery-3.2.1.js"></script>
<script>
    function login() {
        var username = $("#username").val();
        var password = $("#password").val();
        $.ajax({
            url: '/api/login',
            method: 'POST',
            data: 'username=' + username + '&password=' + password,
            success: function (data) {
                if(data.tblRoleByTblRoleRoleId.roleId == 1){
                    window.location.href = "adminPage.jsp?userId=" + data.userId;
                }else if(data.tblRoleByTblRoleRoleId.roleId == 3){
                    window.location.href = "orderTransaction.jsp?userId=" + data.userId;
                }else if(data.tblRoleByTblRoleRoleId.roleId == 2){
                    window.location.href = "employeeOrderPage.jsp?userId=" + data.userId;
                }
            }
        })
    }
</script>
</html>
