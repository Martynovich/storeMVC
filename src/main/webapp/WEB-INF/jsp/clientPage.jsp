<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 06.02.2017
  Time: 21:34
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
<p>Client page</p>
<hr>
<div id="create">
    <p>Create client</p>
    <form name="createClient" method="post" action="createClient">
        Login:<input type="text" name="clientName">
        <input type="submit" value="Create">
    </form>
</div>
<hr>
<div id="find">
    <p>Find client by id</p>
    <form name="findClient" method="post" action="findClient">
        Client id:<input type="text" name="clientIdToFind">
        <input type="submit" value="Find">
    </form>
</div>
<hr>
<div id="findAll">
    <p>Show all clients</p>
    <form name="findAllClients" method="post" action="findAllClients">
        <input type="submit" value="Show">
    </form>
</div>
<hr>
<div id="update">
    <p>Update client</p>
    <form name="updateClient" method="post" action="updateClient">
        Client id:<input type="text" name="clientIdToUpdate">
        </br>
        New login:<input type="text" name="clientLoginToUpdate">
        <input type="submit" value="Update">
    </form>
</div>
<hr>
<div id="delete">
    <p>Delete client</p>
    <form name="deleteClient" method="post" action="deleteClient">
        Client id:<input type="text" name="clientIdToDel">
        <input type="submit" value="Delete">
    </form>
</div>
<hr>
</body>
</html>
