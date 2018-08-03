<%--
  Created by IntelliJ IDEA.
  User: Shuo Yang
  Date: 8/3/2018
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Shuo Yang
  Date: 8/2/2018
  Time: 8:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Resource</title>
</head>
<body>
<h1>${publishInfo.toString()}</h1>
<h1>${contactInfo.toString()}</h1>
<h1>${addFav.toString()}</h1>

<button onClick="javascript:window.location='./MainPage'"> Back to Main Page</button>
<h2> Discover Items </h2></br>

<form method="get" action="./PublishResource"></br>
    Type <input type="text" name="type"></br>
    Description  <input type="text" name="description"></br>
    Quantity  <input type="text" name="quantity"></br>
    HourlyRate  <input type="text" name="hourlyRate"></br>
    <button type="submit">Publish Item!</button>
</form>


<form method="get" action="./SearchResource">
    ResourceID  <input type="text" name="ResourceID"> </input></br>
    Type <input type="text" name="Type"> </input></br>
    Quantity More Than <input type="text" name="Quantity"> </input></br>
    HourlyRate Less Than <input type="text" name="Rate"> </input></br>
    <button type="submit">Search</button>
</form>

<table border="1">
    <tr>
        <td>ResourceID</td>
        <td>Type</td>
        <td>Description</td>
        <td>Quantity</td>
        <td>HourlyRate</td>
        <td>UserID</td>
        <td>Contact</td>
        <td>Fav</td>
    </tr>

    <c:forEach items="${ResourceList}" var="Resource">
        <tr>
            <td>${Resource.getResourceID()}</td>
            <td>${Resource.getType()}</td>
            <td>${Resource.getDescription()}</td>
            <td>${Resource.getQuantity()}</td>
            <td>${Resource.getHourlyRate()}</td>
            <td>${Resource.getUserID()}</td>
            <td><button onClick="javascript:window.location='./ContactResource?ResourceID=${Resource.getResourceID()}'">Contact</button></td>
            <td><button onClick="javascript:window.location='./AddFavResource?ResourceID=${Resource.getResourceID()}'">Add Favorate</button></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

