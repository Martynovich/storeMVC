<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 07.02.2017
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Find all clients page.</title>
    <style type="text/css">
        .line {
            float: left;
            margin-left: 2px;
            text-align: center;
        }
    </style>
</head>
<body>
<div id="container">
    <div id="tables">
        <div class="line">
            <form name="checkClient" method="get" action="client">
                <input type="submit" value="Client">
            </form>
        </div>
        <div class="line">
            <form name="checkProduct" method="get" action="product">
                <input type="submit" value="Product">
            </form>
        </div>
        <div class="line">
            <form name="checkCart" method="get" action="cart">
                <input type="submit" value="Cart">
            </form>
        </div>
    </div>
</div>
<br>
<br>
<table>
    <c:forEach items="${message}" var="client">
        <tr>
            <td>Client id: ${client.id}</td>
            <td>Client login: ${client.login}</td>
        </tr>
    </c:forEach>
</table>
<c:if test="${empty message}">
    No currant clients.
</c:if>
</body>
</html>
