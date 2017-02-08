<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 07.02.2017
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update cart page.</title>
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
<form  id="upCart" name="UpdateOrder" method="post" action="updatedCart">
    <br>
    <br>
    <p>Enter new client id. Current client id - ${cart.client.id}. For hold current client, enter current client id.</p>
    New client id:<input type="text" name="newClientId">
    <br>
    <br>
    Select product:<br>
    <select name="product" onchange="selectProduct (this.value)">
        <c:forEach items="${products}" var="product">
            <option value=${product.productName}>${product.productName} price: ${product.productPrice}</option>
        </c:forEach>
    </select>
    <input type="button" value="add" onclick="addClick ()"/>
    <input type="submit" value="Update cart" onclick="updateCart ()">
    <input type="hidden" name="allProducts" value="${cartProducts}"/>
    <input type="hidden" name="cartId" value="${cart.id}"/>
</form>
<div id="products">
    <c:forEach items="${cart.products}" var="cartProduct">
        <div class="${cartProduct.productName}">${cartProduct.productName}
            <input value="del" onclick="delProd (this)" type="button">
        </div>
    </c:forEach>
</div>
<script type="text/javascript">
    var product = document.getElementsByName('product')[0].value;
    var products = document.getElementsByName('allProducts')[0];
    var delBtn = "<input type=\"button\" value=\"del\" onclick=\"delProd (this)\"/>";
    function selectProduct(p) {
        product = p;
    }
    function addClick() {
        var parentElement = document.getElementById("products");
            var div = document.createElement('div');
            div.className = product;
            div.innerHTML += product;
            div.innerHTML += delBtn;
            parentElement.appendChild(div);
            products.value += (product + ',');
    }
    function delProd(btn) {
        var newCart = products.value;
        products.value = newCart.replace((btn.parentNode.className + ","), "");
        btn.parentNode.remove();
    }
    function updateCart() {
        document.getElementById('upCart').submit();
    }
</script>
</body>
</html>
