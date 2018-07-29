<%--
  Created by IntelliJ IDEA.
  User: yangyangyy
  Date: 7/29/18
  Time: 1:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
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
        <title>Create Comment</title>
    </head>
    <body>
        <h3>Create Comment @.@</h3>

        <form role="form" action="comment" method="POST">
            <div class="form-group">
                <label>ProjectID</label>
                <input type="text" class="form-control" name="projectID" required/>
            </div>

            <div class="form-group">
                <label>Content</label>
                <input type="text" class="form-control" name="content" required/>
            </div>

            <div class="form-group">
                <label>Reply to commentID</label>
                <input type="text" class="form-control" name="replyToCommentID" required/>
            </div>

            <div class="form-group">
                <label>Dislike Count</label>
                <input type="text" class="form-control" name="dislikeCount" required/>
            </div>

            <div class="form-group">
                <label>Like Count</label>
                <input type="text" class="form-control" name="likeCount" required/>
            </div>

            <div class="form-group">
                <label>IsAvailable</label>
                <input type="radio" class="form-control" value="IsAvailable" name="available">Yes
                <checked required/>
                <input type="radio" class="form-control" value="NotAvailable" name="available">No
                <required/>
            </div>

            <div class="form-group">
                <label>User ID</label>
                <input type="text" class="form-control" name="userId" value=${userID} required disabled/>
            </div>

            <%--<div class="form-group">--%>
            <%--<label for="available">Available:</label> <select--%>
            <%--class="form-control" id="available" name="available">--%>
            <%--<option>Yes</option>--%>
            <%--<option>No</option>--%>
            <%--</select>--%>
            <%--</div>--%>

            <div class="form-group">
                <button type="submit" value="insert" name="action" class="btn btn-default">Add Comment</button>
            </div>

        </form>
    </body>

</html>
