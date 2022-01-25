<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Clinics | Clinic Hub</title>
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

	<div class='clinic'>
		<div class="Clinic-Section shadow">
			<div class='container'>
				<div class='row'>
					<div class='col-lg-6'>
						<div class="header text-white mt-4">
							<h1>
								<b>Browse Clinics</b>
							</h1>
							<p class="text-white">
								<b>Search for a clinic and Book an Appointment!</b>
							</p>
						</div>
						<div class='clinic-search'>
							<div class="input-group input-group-lg mb-3 shadow">
								<input type="text" class="form-control"
									placeholder="Search Clinics">
								<div class="input-group-append">
									<span class="input-group-text" id="basic-addon2"><i
										class="fas fa-search"></i></span>
								</div>
							</div>
						</div>


					</div>
					<div class='col-lg-6 text-center'>
						<img
							src="${pageContext.request.contextPath}/assetsimg/clinics-list-img4.svg"
							class="clinic-img-head" alt="" />
					</div>
				</div>
			</div>

		</div>

		<div class='container py-4'>
			<div class='header py-4'>
				<h3>
					<b>All Clinics</b>
				</h3>
			</div>

			<div class="row">
				<c:forEach var='clinic' items='${listPatientClinics}'>

					<div class='col-lg-4 py-3'>
						<div class='clinic-cards'>
							<div class="card clinic-card bg-white border rounded-lg-clinic-card shadow-sm">
								<img
									src=<c:out value="${clinic.image}" />
									class="card-img-top-alt" alt="" />
								<h5>
									<span
										class="badge bg-info text-white location-name-clinics-card shadow">
										<i class="fas fa-map-marker-alt text-white mr-2"></i> <c:out value="${clinic.location_name}" />
									</span>
								</h5>
								<div class="card-body">
									<div class="header card-event-name">
										<h5>
											<b><c:out value="${clinic.clinic_name}" /></b>
										</h5>
									</div>
									<div class="event-info text-muted">
										<ul class="list-unstyled text-small text-left info">
											<li class="mb-2"><i
												class="fas fa-shopping-bag me-2 text-secondary fa-lg"></i> <b><c:out value="${clinic.opening_days}" /></b></li>
											<li class="mb-2"><i
												class="fas fa-clipboard-list me-2 text-secondary fa-lg"></i>
												<b><c:out value="${clinic.opening_hours}" /></b></li>
										</ul>
									</div>
									<span class="text-muted"> <b><c:out value="${clinic.contact_number}" /></b>
									</span>
									<div class="buttons py-2">
										<a href='<%=request.getContextPath()%>/ClinicServlet/clinic-details?id=<c:out value='${clinic.id}'/>'
											class="btn btn-primary float-right"> <b>View More</b>
										</a>
									</div>
								</div>

							</div>
						</div>
					</div>

				</c:forEach>








			</div>
		</div>

	</div>


	<!-- -Include the footer jsp to the page -->
	<jsp:include page="footer.jsp" />

</body>
</html>