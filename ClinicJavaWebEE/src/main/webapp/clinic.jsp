
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Clinic | Dashboard</title>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/styles.css"
	type="text/css">

</head>
<body>
	<!-- -Include the navbar jsp to the page -->
	<jsp:include page="navbar.jsp" />

	<div class='fixed-top-body'>

		<div class="container text-center">
			<h3 class="text-center py-3">Manage Clinics</h3>
				<hr>
				<div class="text-left">
					<!-- Add new user button redirects to the register.jsp page -->
					<a href="<%=request.getContextPath()%>/CreateClinic.jsp"
						class="btn btn-success py-3">Add New Clinic</a>
				</div>
				<br>
				<!-- Create a table to list out all current users information -->
				<table class="table py-5">
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
								<td><c:out value="${clinic.id}" /></td>
								<td ><c:out value="${clinic.clinic_name}" /></td>
								<td><c:out value="${clinic.address}" /></td>
								<td ><c:out value="${clinic.location_name}" /></td>
								<td ><c:out value="${clinic.image}" /></td>
								<td ><c:out value="${clinic.description}" /></td>
								<td><c:out value="${clinic.opening_hours}" /></td>
								<td ><c:out value="${clinic.opening_days}" /></td>
								<td ><c:out value="${clinic.contact_number}" /></td>
								<!-- For each user in the database, Edit/Delete
buttons which invokes the edit/delete functions -->
								<td><a href="edit?name=<c:out value='${clinic.id}'
/>">Edit</a>
									&nbsp;&nbsp;&nbsp;&nbsp; <a
									href="delete?name=<c:out
value='${clinic.id}' />">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		</div>

	</div>



	<!-- -Include the Footer jsp to the page -->
	<jsp:include page="footer.jsp" />
</body>
</html>