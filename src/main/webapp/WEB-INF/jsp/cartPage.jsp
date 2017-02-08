<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 07.02.2017
  Time: 9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create order</title>
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
    <p>Cart page</p>
    <hr>
    <div id="create">
        <p>Create cart</p>
        <form name="createCart" method="post" action="createCart">
            Client id:<input type="text" name="clientIdToCr">
            <input type="submit" value="Create">
            </form>
        </div>
    <hr>
    <div id="find">
        <p>Find cart by id</p>
        <form name="findCart" method="post" action="findCart">
            Cart id:<input type="text" name="cartIdToFind">
            <input type="submit" value="Find">
            </form>
        </div>
    <hr>
    <div id="findAll">
            <p>Show all carts</p>
        <form name="findAllCarts" method="post" action="findAllCarts">
            <input type="submit" value="Show">
            </form>
        </div>
    <hr>
    <div id="update">
        <p>Update cart</p>
        <form name="updateCart" method="post" action="updateCart">
            Cart id:<input type="text" name="cartIdToUpdate">
            <input type="submit" value="Update">
            </form>
        </div>
    <hr>
    <div id="delete">
        <p>Delete cart</p>
        <form name="deleteCart" method="post" action="deleteCart">
            Cart id:<input type="text" name="cartIdToDel">
            <input type="submit" value="Delete">
            </form>
        </div>
    <hr>
</body>
</html>
