<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 07.02.2017
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Find all carts page.</title>
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
            <form name="checkClient" method="get" action="/client">
                <input type="submit" value="Client">
            </form>
        </div>
        <div class="line">
            <form name="checkProduct" method="get" action="/product">
                <input type="submit" value="Product">
            </form>
        </div>
        <div class="line">
            <form name="checkCart" method="get" action="/cart">
                <input type="submit" value="Cart">
            </form>
        </div>
    </div>
</div>
</br>
</br>
<p>${client}</p>
<p>Products in cart.</p>
<c:forEach items="${carts}" var="cart">
    <hr>
    <p>Cart id: ${cart.id}. Client id: ${cart.client.id}. Order date: ${cart.dateOfCreation}.</p>
    <c:forEach items="${cart.products}" var="product">
        <tr>
            <td>Product id: ${product.id}</td>
            <td>Product name: ${product.productName}</td>
            <td>Product price: ${product.productPrice}</td>
        </tr>
        <br>
    </c:forEach>
</c:forEach>
<hr>
</body>
</html>
