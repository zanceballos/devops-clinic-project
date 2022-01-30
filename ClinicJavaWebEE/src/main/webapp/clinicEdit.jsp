<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Update Clinic| Clinic Hub</title>
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
		<div class="container col-md-6 py-5">
			<div class="card shadow border-0">
				<div class="card-body">


					<form action="update" method="post">


						<h2 class='py-2'><i class="fas fa-edit pr-2 text-primary"></i><b>Edit Clinic</b></h2>

						<c:if test="${clinic != null}">
							<input type="hidden" name="id"
								value="<c:out
value='${clinic.id}' />" />
						</c:if>
						<fieldset class="form-group">
							<label>Clinic Name</label> <input type="text"
								value="<c:out
value='${clinic.clinic_name}' />"
								class="bg-light w-100 border-0 shadow-sm px-4 py-3" name="clinic_name" required="required">
						</fieldset>
						<fieldset class="form-group">
							<label>Address</label> <input type="text"
								value="<c:out
value='${clinic.address}' />"
								class="bg-light w-100 border-0 shadow-sm px-4 py-3" name="address">
						</fieldset>
						<fieldset class="form-group">
							<label>Location Name</label> <input type="text"
								value="<c:out
value='${clinic.location_name}' />"
								class="bg-light w-100 border-0 shadow-sm px-4 py-3" name="location_name">
						</fieldset>
						<fieldset class="form-group">
							<label> Image</label> <input type="text"
								value="<c:out
value='${clinic.image}' />" class="bg-light w-100 border-0 shadow-sm px-4 py-3"
								name="image">
						</fieldset>
						<fieldset class="form-group">
							<label> Description </label> <input type="text"
								value="<c:out
value='${clinic.description}' />"
								class="bg-light w-100 border-0 shadow-sm px-4 py-3" name="description">
						</fieldset>
						<fieldset class="form-group">
							<label> Opening Hours</label> <input type="text"
								value="<c:out
value='${clinic.opening_hours}' />"
								class="bg-light w-100 border-0 shadow-sm px-4 py-3" name="opening_hours">
						</fieldset>
						<fieldset class="form-group">
							<label> Opening_days</label> <input type="text"
								value="<c:out
value='${clinic.opening_days}'/>"
								class="bg-light w-100 border-0 shadow-sm px-4 py-3" name="opening_days">
						</fieldset>
						<fieldset class="form-group">
							<label> Contact Number</label> <input type="text"
								value="<c:out
value='${clinic.contact_number}' />"
								class="bg-light w-100 border-0 shadow-sm px-4 py-3" name="contact_number">
						</fieldset>

						<button type="submit"
							class="btn btn-primary px-5 py-3 float-right">
							<b>Update Clinic</b>
						</button>
					</form>
				</div>
			</div>
		</div>

	</div>

	<!-- -Include the footer jsp to the page -->
	<jsp:include page="footer.jsp" />

</body>
</html>