<%@ page import="Dao.ProjectDao" %>
<%@ page import="Model.Project" %><%--
  Created by IntelliJ IDEA.
  User: Shuo
  Date: 7/13/2018
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Project ${currentProject.getProjectID()}</title>
</head>

<body>
<h1> ${currentProject.getTitle()} </h1>
<h2>by</h2>
<h2>${projectUserName}</h2>

<h3>${currentProject.getLikedCount()}</h3>
<button onclick=<%ProjectDao.getInstance().addLikeBy1(((Project)request.getAttribute("currentProject")).getProjectID());%>}>
    Like
</button>
<h3>${currentProject.getDislikedCount()}</h3>
<button onclick=<%ProjectDao.getInstance().addDislikeBy1(((Project)request.getAttribute("currentProject")).getProjectID());%>}>
    DisLike
</button>

<textarea width: 800px; height: 400px;> ${currentProject.getDescription()}


</body>
</html>
