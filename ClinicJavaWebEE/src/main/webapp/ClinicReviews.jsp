<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Clinic Reviews | Dashboard</title>
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
		<div class='container'>
			<div class='d-flex justify-content-between py-2'>
				<div class='header'>
					<h3>
						<b>Clinic Reviews</b>
					</h3>
				</div>
				<a
					href='<%=request.getContextPath()%>/ReviewServlet/ClinicReviewForm?id=${clinicid}'
					class='btn btn-primary text-white px-5 py-2 shadow'><b>Review
						Clinic</b></a>
			</div>

			<!-- Create a table to list out all current users information -->
			<table class="table py-5">
				<thead>
					<tr>
						<th>ID</th>
						<th>Clinic id</th>
						<th>User id</th>
						<th>Review Title</th>
						<th>Review</th>
						<th>Score</th>

					</tr>
				</thead>
				<!-- Pass in the list of users receive via the Servlet’s response to a loop
-->
				<tbody>
					<c:forEach var="review" items="${reviews}">
						<!-- For each user in the database, display their
information accordingly -->
						<tr>
							<td><c:out value="${review.id}" /></td>
							<td><c:out value="${review.clinic_id}" /></td>
							<td><c:out value="${review.user_id}" /></td>
							<td><c:out value="${review.review_title}" /></td>
							<td><c:out value="${review.review}" /></td>
							<td><c:out value="${review.rating_score}" /></td>
							<!-- For each user in the database, Edit/Delete
buttons which invokes the edit/delete functions -->

							<td><c:if test="${logged_in != null}">
									<c:if test="${ review.user_id == id}">
										<a href="ShowUpdateForm?id=<c:out value='${review.id}'/>">Edit</a>
									</c:if> &nbsp;&nbsp;&nbsp;&nbsp; <c:if
										test="${review.user_id == id}">
										<a
											href="delete?id=<c:out
value='${review.id}' />&clinicid=<c:out value="${review.clinic_id}" />">Delete</a>
									</c:if>
								</c:if></td>


						</tr>
					</c:forEach>
				</tbody>
			</table>


		</div>

	</div>

</body>
</html>