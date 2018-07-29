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
        <form action="button/deleNoti">
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

        <%--display comment--%>
        <h3> My Comments </h3>
        <a class="btn btn-sm btn-danger" href="./CreateComment.jsp?id=${userID}" role="button">Create new Comment</a>
        <table border="1">
            <tr>
                <td>CommentID</td>
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

            <c:forEach items="${myComments}" var="comment">
                <tr>
                    <td>${comment.getCommentID()}</td>
                    <td>${comment.getProjectID()}</td>
                    <td>${comment.getContent()}</td>
                    <td>${comment.getCreateTime()}</td>
                    <td>${comment.isAvailable()}</td>
                    <td>${comment.getReplyToCommentID()}</td>
                    <td>${comment.getDislikedCount()}</td>
                    <td>${comment.getLikedCount()}</td>
                    <td>${comment.getUserID()}</td>
                    <td>
                        <a href="comment?action=getCommentByCommentId&commentId=${comment.getCommentID()}" role="button">Edit</a>
                        <a href="comment?action=delete&commentId=${comment.getCommentID()}" role="button">Delete</a>
                        <%--<a href="customer?action=update&id=${comment.getCommentID}&source=${source}&name=${customer.firstName}" role="button">Edit</a>--%>
                        <%--<a href="customer?action=delete&id=${customer.userId}&source=${source}&name=${customer.firstName}" role="button">Delete</a>--%>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <h3>Change My Password</h3>
        <form method="post" action="changePassword">
            OldPassword <input type="password" name="oldpassword"></input>
            NewPassword <input type="password" name="newpassword"></input>
            <button type="submit">Change</button>
        </form>

    </body>
</html>
