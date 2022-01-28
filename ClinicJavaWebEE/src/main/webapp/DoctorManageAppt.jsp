
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Appointments | Clinic Hub</title>
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

		<div class='background'>
			<div class='container'>
				<div class='header py-3'>
					<h3>
						<i class="fas fa-hospital-user pr-2 text-primary"></i> <b>Select a Clinic</b>
					</h3>
					<p class='text-muted'>
						<b>Select a clinic where you want to manage appointments </b>
					</p>
				</div>
				
				
				<div class='clinics'>
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
		</div>
		
	</div>

</body>
</html>