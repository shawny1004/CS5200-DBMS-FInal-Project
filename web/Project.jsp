<%@ page import="Dao.ProjectDao" %>
<%@ page import="Model.Project" %><%--
  Created by IntelliJ IDEA.
  User: Shuo
  Date: 7/13/2018
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Project ${currentProject.getProjectID()}</title>

</head>

<body>

<button onClick="javascript:window.location='./MainPage'">
    Back to Main Page
</button>
<h1> ${currentProject.getTitle()} </h1>
<h2>by</h2>
<h2>${projectUserName}</h2>

<h3>${currentProject.getLikedCount()}</h3>
<button onClick="javascript:window.location='./LikeProj?ProjectID=${currentProject.getProjectID()}'">
    Like
</button>
<h3>${currentProject.getDislikedCount()}</h3>
<button onClick="javascript:window.location='./UnLiProj?ProjectID=${currentProject.getProjectID()}'">
    DisLike
</button>

<textarea id="description" rows="15" cols="300"> ${currentProject.getDescription()} </textarea>

<p>Tags with this Project</p>
<table border="1">
    <tr>
        <td>TagName</td>
        <td>TagDescription</td>
    </tr>

    <c:forEach items="${tagsList}" var="item">
        <tr>
            <td>${item.getTagName()}</td>
            <td>${item.getTagDescription()}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
