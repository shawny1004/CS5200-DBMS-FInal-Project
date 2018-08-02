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
    <title>Talkie Movie ^_^</title>
  </head>
  <body>
  <h2>${newuser.toString}</h2>
  <form method="post" action="login">
    <input type="text" name="UserName"> Username </input>
    <input type="password" name="PassWord"> Password </input>
    <button type="submit">LogIn</button>
  </form>


    <a href="CreateAccount.jsp">Create Your Account</a>


  </body>
</html>
