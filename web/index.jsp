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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
  <head>
    <title>Login Page</title>
  </head>
  <body>
    <h2>Login</h2>
    <spring:url value="/productPage" var="login"></spring:url>
  <form:form action="${login}" method="post">
    <table>
      <tr>
        <td>Username: </td>
        <td><input type="text" name="username"></td>
      </tr>
      <tr>
        <td>Password: </td>
        <td><input type="password" name="password"></td>
      </tr>
    </table>
    <input type="submit" value="Login">
    <input type="reset" value="Reset">

  </form:form>
  </body>
</html>
