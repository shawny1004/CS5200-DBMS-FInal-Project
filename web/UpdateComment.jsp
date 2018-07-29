<%--
  Created by IntelliJ IDEA.
  User: yangyangyy
  Date: 7/29/18
  Time: 5:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Edit Comment</title>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script
                src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script
                src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

        <script
                src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.js"></script>
        <script
                src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.min.js"></script>
        <script
                src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/additional-methods.js"></script>
        <script
                src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/additional-methods.min.js"></script>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <h3>Edit Comment @.@</h3>
        <h3>${comment.getCommentID()}</h3>
        <form role="form" action="comment" method="POST"}>
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="commentId" value=${comment.getCommentID()}>
            <div class="form-group">
                <label>ProjectID</label>
                <input type="text" class="form-control" name="projectID" value=${comment.getProjectID()} required/>
            </div>

            <div class="form-group">
                <label>Content</label>
                <input type="text" class="form-control" name="content" value=${comment.getContent()} required/>
            </div>

            <div class="form-group">
                <label>Reply to commentID</label>
                <input type="text" class="form-control" name="replyToCommentID" value=${comment.getReplyToCommentID()} required/>
            </div>

            <div class="form-group">
                <label>Dislike Count</label>
                <input type="text" class="form-control" name="dislikeCount" value=${comment.getDislikedCount()} required/>
            </div>

            <div class="form-group">
                <label>Like Count</label>
                <input type="text" class="form-control" name="likeCount" value=${comment.getLikedCount()} required/>
            </div>

            <div class="form-group">
                <label>IsAvailable</label>
                <input type="radio" class="form-control" value="IsAvailable" name="available" checked>Yes<required/>
                <input type="radio" class="form-control" value="NotAvailable" name="available">No<required/>
            </div>

            <div class="form-group">
                <label>User ID</label>
                <input type="text" class="form-control" name="userId" value=${comment.getUserID()} required disabled/>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-default">Update Comment</button>
            </div>
        </form>

    </body>
</html>
