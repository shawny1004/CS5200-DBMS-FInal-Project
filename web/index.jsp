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
  <form method="get" action="login">
    <input type="text" name="UserName"> Username </input>
    <input type="password" name="Password"> Password </input>
    <button type="submit">LogIn</button>
  </form>

  <form method="post" action="newUser">
    <input type="text" name="UserName"> Username </input>
    <input type="password" name="PassWord"> Password </input>
    <button type="submit">Create Your Account</button>
  </form>

  </body>
</html>
