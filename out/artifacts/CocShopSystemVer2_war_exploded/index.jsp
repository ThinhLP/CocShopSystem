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
    <div id="errorMsg" style="color: red"></div>
    <button type="button" onclick="login()">Login</button>
    <button type="reset">Reset</button>
<%--</form:form>--%>
</form>
    <script language="JavaScript" src="resources/js/jquery-3.2.1.js"></script>
    <script>
        function login() {
            var username = $("#username").val();
            var password = $("#password").val();
            $.ajax({
                url: '/api/1.0/login',
                method: 'POST',
                data: 'username=' + username + '&password=' + password,
                statusCode: {
                    200: function(data) {
                        if (data.role == 1) {
                            //window.location.href = "adminPage.jsp?userId=" + data.userId;
                            window.location.href = "adminPage.jsp";
                        } else if (data.role == 3) {
                            localStorage.setItem('userId', data.userId);
                            //window.location = "orderTransaction.jsp?userId=" + data.userId;
                            window.location.href = "orderTransaction.jsp";
                        } else if (data.role == 2) {
                           // window.location.href = "employeeOrderPage.jsp?userId=" + data.userId;
                            window.location.href = "employeeOrderPage.jsp" ;
                        }
                    },
                    401: function() {
                        $('#errorMsg').html("Invalid username or password")
                    }
                }
            })

        }
    </script>
</body>



</html>
