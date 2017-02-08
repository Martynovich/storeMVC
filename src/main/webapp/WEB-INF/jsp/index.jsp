<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 01.02.2017
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hello</title>
    <style type="text/css">
        .line {
            float: left;
            margin-left: 2px;
            text-align: center;
        }
    </style>
</head>
<body>
<p>Hello.Please select the table.</p>
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
</body>
</html>
