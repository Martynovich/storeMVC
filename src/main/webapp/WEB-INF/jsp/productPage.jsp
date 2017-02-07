<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 06.02.2017
  Time: 22:11
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
<p>Product page</p>
<hr>
<div id="create">
    <p>Create product</p>
    <form name="createProduct" method="post" action="createProduct">
        Login:<input type="text" name="productNameCr"></br>
        Price:<input type="text" name="productPriceCr">
        <input type="submit" value="Create">
    </form>
</div>
<hr>
<div id="find">
    <p>Find product by id</p>
    <form name="findProduct" method="post" action="findProduct">
        Product id:<input type="text" name="productIdToFind">
        <input type="submit" value="Find">
    </form>
</div>
<hr>
<div id="findAll">
    <p>Show all products</p>
    <form name="findAllProducts" method="post" action="findAllProducts">
        <input type="submit" value="Show">
    </form>
</div>
<hr>
<div id="update">
    <p>Update product</p>
    <form name="updateProducts" method="post" action="updateProduct">
        Product id:<input type="text" name="productIdToUpdate"></br>
        New name:<input type="text" name="productNameToUpdate"></br>
        New price:<input type="text" name="productPriceToUpdate">
        <input type="submit" value="Update">
    </form>
</div>
<hr>
<div id="delete">
    <p>Delete product</p>
    <form name="deleteProduct" method="post" action="deleteProduct">
        Product id:<input type="text" name="productIdToDel">
        <input type="submit" value="Delete">
    </form>
</div>
<hr>
</body>
</html>
