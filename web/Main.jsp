<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Shuo
  Date: 7/13/2018
  Time: 3:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>My HomePage</title>
</head>

<body>
<h1> Hello ${userName.toString()} </h1>
<h1> ${changepassword.toString()} </h1>
<h3> My Notification </h3>
<form action = "button/deleNoti">
<table border="1">
    <tr>
        <td>NotificationID</td>
        <td>CreatedTime</td>
        <td>Content</td>
        <td>Viewed</td>
        <td>Type</td>
        <td>Delete</td>
    </tr>

    <c:forEach items="${myNoti}" var="item">
        <tr>
            <td>${item.getNotificationID()}</td>
            <td>${item.getCreateTime()}</td>
            <td>${item.getContent()}</td>
            <td>${item.isViewed()}</td>
            <td>${item.getType()}</td>
            <td>
                <button type="submit" name="deletebutton">Delete:${item.getNotificationID()}</button>
            </td>
        </tr>
    </c:forEach>
</table>
</form>

<h3> My Projects </h3>
<button onClick="javascript:window.location='./CreateProject.jsp'"> Create new Project</button>
<table border="1">
    <tr>
        <td>ProjectID</td>
        <td>Title</td>
        <td>Description</td>
        <td>CreateTime</td>
        <td>DislikedCount</td>
        <td>LikedCount</td>
    </tr>

    <c:forEach items="${myProjects}" var="project">
        <tr>
            <td>${project.getProjectID()}</td>
            <td><a href="projectItem/${project.getProjectID()}"> ${project.getTitle()} </a></td>
            <td>${project.getDescription()}</td>
            <td>${project.getCreateTime()}</td>
            <td>${project.getDislikedCount()}</td>
            <td>${project.getLikedCount()}</td>
        </tr>
    </c:forEach>
</table>

<form action="./SearchProject">
    <textarea>search</textarea>
    <button type="submit"> Search!</button>
</form>

<h3>Change My Password</h3>
<form method="post" action="changePassword">
    OldPassword <input type="password" name="oldpassword">  </input>
    NewPassword <input type="password" name="newpassword">  </input>
    <button type="submit">Change</button>
</form>

</body>
</html>
