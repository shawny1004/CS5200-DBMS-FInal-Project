<%--
  Created by IntelliJ IDEA.
  User: Shuo
  Date: 7/13/2018
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Project</title>

</head>
<body>
<form method="post" action="CreateProject">
    ProjectTitle  <input type="text" name="projectTitle"> </input>
    Project Description <input type="text" name="Description"> </input>
    Project Tag1 <input type="text" name="Tag1"> </input>
    Project Tag2 <input type="text" name="Tag2"> </input>
    Project Tag3 <input type="text" name="Tag3"> </input>
    
    <button type="submit">Create</button>
</form>
</body>
</html>
