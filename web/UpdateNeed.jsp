<%--
  Created by IntelliJ IDEA.
  User: yangyangyy
  Date: 8/5/18
  Time: 2:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Update Need</title>
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
    <%--<body background="./image/91807f89d8fd1b9f91e4d22cf1f3f594.jpg">--%>
        <h3>Edit Need @.@</h3>
        <h3>NeedId: ${need.getNeedID()}</h3>
        <form role="form" action="need" method="POST"}>
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="needId" value=${need.getNeedID()}>

            <div class="form-group">
                <label>ProjectID</label>
                <input type="text" class="form-control" name="projectID" value=${need.getProjectID()} disabled/>
            </div>

            <div class="form-group">
                <label>Type</label>
                <select name="type">
                    <option value="People">People</option>
                    <option value="Resource">Resource</option>
                    <option value="Fund" selected>Fund</option>
                </select>
            </div>

            <div class="form-group">
                <label>Parameter</label>
                <input type="text" class="form-control" name="parameter" value=${need.getParameter()} required/>
            </div>

            <div class="form-group">
                <label>Description</label>
                <input type="text" class="form-control" name="description" value=${need.getDescription()} required/>
            </div>

            <div class="form-group">
                <label>Quantity</label>
                <input type="text" class="form-control" name="quantity" value=${need.getQuantity()} required/>
            </div>

            <div class="form-group">
                <label>Create Time</label>
                <input type="text" class="form-control" name="createTime" value=${need.getCreateTime()} disabled/>
            </div>


            <div class="form-group">
                <label>IsActive</label>
                <input type="radio" class="form-control" value="IsActive" name="active" checked>Yes<required/>
                <input type="radio" class="form-control" value="NotActive" name="active">No<required/>
            </div>


            <div class="form-group">
                <button type="submit" class="btn btn-default">Update Need</button>
            </div>
        </form>

    </body>
</html>
