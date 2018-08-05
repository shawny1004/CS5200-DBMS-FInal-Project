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
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="style.css">
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

        <div class="form-group">
            <textarea id="description" rows="10" cols="300"> ${currentProject.getDescription()} </textarea>
        </div>
        <h2>Tags with this Project</h2>
        <table class="table" border="1">
            <tr class="info">
                <td>TagName</td>
                <td>TagDescription</td>
            </tr>

            <c:forEach items="${tagsList}" var="item">
                <tr class="active">
                    <td>${item.getTagName()}</td>
                    <td>${item.getTagDescription()}</td>
                </tr>
            </c:forEach>
        </table>

        <h2>Comments with this Project</h2>
        <table class="table" border="1">
            <tr class="info">
                <td>ProjectID</td>
                <td>Content</td>
                <td>CreateTime</td>
                <td>Available</td>
                <td>ReplyToCommentID</td>
                <td>DislikedCount</td>
                <td>LikedCount</td>
                <td>UserID</td>
                <td>Action</td>
            </tr>

            <c:forEach items="${commentsList}" var="comment">
                <tr class="active">
                    <td>${comment.getProjectID()}</td>
                    <td>${comment.getContent()}</td>
                    <td>${comment.getCreateTime()}</td>
                    <td>${comment.isAvailable()}</td>
                    <td>${comment.getReplyToCommentID()}</td>
                    <td>${comment.getDislikedCount()}</td>
                    <td>${comment.getLikedCount()}</td>
                    <td>${comment.getUserID()}</td>
                    <td>
                        <a href="comment?action=getCommentByCommentId&commentId=${comment.getCommentID()}"
                           role="button">Edit</a>
                        <a href="comment?action=delete&commentId=${comment.getCommentID()}" role="button">Delete</a>
                            <%--<a href="customer?action=update&id=${comment.getCommentID}&source=${source}&name=${customer.firstName}" role="button">Edit</a>--%>
                            <%--<a href="customer?action=delete&id=${customer.userId}&source=${source}&name=${customer.firstName}" role="button">Delete</a>--%>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <%--this.projectID = projectID;--%>
        <%--this.type = type;--%>
        <%--this.parameter = parameter;--%>
        <%--this.description = description;--%>
        <%--this.quantity = quantity;--%>
        <%--this.createTime = createTime;--%>
        <%--this.active = active;--%>
        <h2>Needs with this Project</h2>
        <table class="table" border="1">
            <tr class="info">
                <td>ProjectID</td>
                <td>Type</td>
                <td>Parameter</td>
                <td>Description</td>
                <td>Quantity</td>
                <td>Create Time</td>
                <td>Active</td>
                <td>Action</td>
            </tr>

            <c:forEach items="${needList}" var="need">
                <tr class="active">
                    <td>${need.getProjectID()}</td>
                    <td>${need.getType()}</td>
                    <td>${need.getParameter()}</td>
                    <td>${need.getDescription()}</td>
                    <td>${need.getQuantity()}</td>
                    <td>${need.getCreateTime()}</td>
                    <td>${need.isActive()}</td>
                    <td>
                        <a href="need?action=getNeedByNeedId&needId=${need.getNeedID()}" role="button">Edit</a>
                        <a href="need?action=delete&needId=${need.getNeedID()}&projectId=${need.getProjectID()}"
                           role="button">Delete</a>
                            <%--<a href="need?action=getNeedByNeedId&needId=${need.getNeedID()}" role="button">Edit</a>--%>
                            <%--<a href="need?action=delete&needId=${need.getNeedID()}" role="button">Delete</a>--%>
                    </td>
                </tr>
            </c:forEach>
        </table>


    </body>
</html>
