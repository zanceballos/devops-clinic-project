<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Clinic</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet"
href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
crossorigin="anonymous">

</head>
<body>
<div class="row">
<div class="container">
<h3 class="text-center">List of Clinic</h3>
<hr>
<div class="container text-left">
<!-- Add new user button redirects to the register.jsp page -->
<a href="<%=request.getContextPath()%>/CreateClinic.jsp" class="btn btnsuccess">Add New Clinic</a>
</div>
<br>
<!-- Create a table to list out all current users information -->
<table class="table">
<thead>
<tr>
<th>ID</th>
<th>Clinic Name</th>
<th>Address</th>
<th>Location Name</th>
<th>Image</th>
<th>Description</th>
<th>Opening Hours</th>
<th>Opening Days</th>
<th>Contact Number</th>
<th>Actions</th>
</tr>
</thead>
<!-- Pass in the list of users receive via the Servlet’s response to a loop
-->
<tbody>
<c:forEach var="clinic" items="${listClinic}">
<!-- For each user in the database, display their
information accordingly -->
<tr>
<td>
<c:out value="${clinic.id}" />
</td>
<td>
<c:out value="${clinic.clinic_name}" />
</td>
<td>
<c:out value="${clinic.address}" />
</td>
<td>
<c:out value="${clinic.location_name}" />
</td>
<td>
<c:out value="${clinic.image}" />
</td>
<td>
<c:out value="${clinic.description}" />
</td>
<td>
<c:out value="${clinic.opening_hours}" />
</td>
<td>
<c:out value="${clinic.opening_days}" />
</td>
<td>
<c:out value="${clinic.contact_number}" />
</td>
<!-- For each user in the database, Edit/Delete
buttons which invokes the edit/delete functions -->
<td>
<a href="edit?name=<c:out value='${clinic.id}'
/>">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp;
<a href="delete?name=<c:out
value='${clinic.id}' />">Delete</a>
</td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
</div>
</body>
</html>