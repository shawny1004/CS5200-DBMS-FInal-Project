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
    <title>People</title>
</head>
<body>
<h1>${publishInfo.toString()}</h1>
<h1>${contactInfo.toString()}</h1>
<h1>${addFav.toString()}</h1>

<button onClick="javascript:window.location='./MainPage'"> Back to Main Page</button>
<h2> Discover People </h2></br>

<form method="get" action="./PublishPeople"></br>
    FirstName <input type="text" name="firstName"></br>
    LastName  <input type="text" name="lastName"></br>
    Description  <input type="text" name="description"></br>
    Occupation  <input type="text" name="occupation"></br>
    HourlyRate  <input type="text" name="hourlyRate"></br>
    <button type="submit">Publish Myself</button>
</form>


<form method="get" action="./SearchPeople">
    PeopleID  <input type="text" name="PeopleID"> </input></br>
    FirstName <input type="text" name="FirstName"> </input></br>
    LastName <input type="text" name="LastName"> </input></br>
    Occupation <input type="text" name="Occupation"> </input></br>
    HourlyRate Less Than <input type="text" name="Rate"> </input></br>
    <button type="submit">Search</button>
</form>

<table border="1">
    <tr>
        <td>PeopleID</td>
        <td>Name</td>
        <td>Occupation</td>
        <td>Description</td>
        <td>DOB</td>
        <td>HourlyRate</td>
        <td>UserID</td>
        <td>Contact</td>
        <td>Fav</td>
    </tr>

    <c:forEach items="${peopleList}" var="people">
        <tr>
            <td>${people.getPeopleID()}</td>
            <td>${people.getFirstName()} ${people.getLastName()}</td>
            <td>${people.getOccupation()}</td>
            <td>${people.getDescription()}</td>
            <td>${people.getDob()}</td>
            <td>${people.getHourlyRate()}</td>
            <td>${people.getUserID()}</td>
            <td><button onClick="javascript:window.location='./ContactPeople?PeopleID=${people.getPeopleID()}'">Contact</button></td>
            <td><button onClick="javascript:window.location='./AddFavPeople?PeopleID=${people.getPeopleID()}'">Add Favorate</button></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
