<%--
  Created by IntelliJ IDEA.
  User: Shuo
  Date: 7/10/2018
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style type="text/css">
        body {
            background-image: linear-gradient(to bottom, #e6edf1, #badae4, #8ac8d1, #55b6b7, #06a396);
        }
        body{text-align:center}

    </style>
    <b>
    <font color="#d35400" face="Rockwell" size="10">
        MovieStarter
    </font>
    </b>
</head>
</div>
<body>
<p>
    <font color="#636e72" face="Comic Sans MS" size="3.5">
        a movie resource finding community that exhibits creative movie project ideas and look for possible resources
    </font>
</p>
<h2>${newUser.toString()}</h2>
<form method="post" action="login">
    <center><table style="with: 80%">
        <tr>
            <td>Username</td>
            <td><input type="text" name="UserName"></td>
        </tr></center>
        <tr>
            <td>Password</td></center>
            <td><input type="password" name="PassWord"></td>
        </tr>
    </table>

    <button type="submit">LogIn</button>

    <button type="submit">
        <a href="CreateAccount.jsp">Create Your Account</a>
    </button>
</form>
</center>
</body>
</html>
