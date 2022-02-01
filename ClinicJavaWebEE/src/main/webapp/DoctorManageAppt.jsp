
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


	<div class='select-clinic'>

		<div class='background'>
			<div class="doctor-manage-section shadow">
				<div class='container'>
					<div class='row'>
						<div class='col-lg-6'>
							<div class="header text-white mt-4">
								<h1>
									<b>Manage Appointments</b>
								</h1>
								<p class="text-white">
									<b>Complete & Update your Patient's Appointments here!</b>
								</p>
							</div>
							<div class='clinic-search'>
								<a href='<%=request.getContextPath()%>/UserServlet/home'
									class='btn btn-light px-5 py-3 rounded-pill'><i
									class="fas fa-arrow-left pr-2"></i><b>Back To Home</b></a>
							</div>


						</div>
						<div class='col-lg-6 text-center'>
							<img
								src="${pageContext.request.contextPath}/assetsimg/work-manage.svg"
								class="clinic-img-head" alt="" />
						</div>
					</div>
				</div>
			</div>

			<div class='container'>
				<div class='header mt-5 pb-2'>
					<h3>
						<i class="fas fa-hospital-user pr-2 text-primary"></i> <b>Select
							a Clinic</b>
					</h3>
					<p class='text-muted'>
						<b>Select a clinic where you want to manage appointments </b>
					</p>
				</div>


				<div class='clinics'>
					<div class="row">
						<c:forEach var='clinic' items='${listClinic}'>
							<div class='col-lg-4'>
								<div
									class="card event-card-root border rounded-lg-clinic-card shadow-sm mb-3">
									<img class="card-img-top-manage-clinic"
										src=<c:out value="${clinic.image}" /> alt="" />
									<h5>
										<span
											class="badge bg-info text-white location-name-clinics-card  shadow">
											<i class="fas fa-map-marker-alt text-white mr-2"></i> <c:out
												value="${clinic.location_name}" />
										</span>
									</h5>
									<div class="card-body event-card">
										<div class="event-header">
											<h5>
												<b>${clinic.clinic_name}</b>
											</h5>
											<p class="text-muted organisation-name-p">
												at <b><c:out value="${clinic.location_name}" /></b>
											</p>
										</div>
										<div class="event-info text-muted">
											<ul class="list-unstyled text-small text-left">
												<li class="mb-3"><i
													class="fas fa-map-marker-alt mr-2 text-primary fa-lg"></i>
													<c:out value="${clinic.location_name}" /></li>
												<li class="mb-3"><i
													class="far fa-calendar-alt mr-2 text-primary fa-lg"></i> <c:out
														value="${clinic.opening_days}" /></li>
												<li class="mb-3"><i
													class="far fa-clock mr-2 text-primary fa-lg"></i> <c:out
														value="${clinic.opening_hours}" /></li>

												<li class="mb-3"><i
													class="fas fa-phone mr-2 text-primary fa-lg"></i> <c:out
														value="${clinic.contact_number}" /></li>
											</ul>
										</div>
										<div class="event-card-btn align-self-end">

											<a
												href='<%=request.getContextPath()%>/AppointmentServlet/ClinicAppointments?clinicid=<c:out value='${clinic.id}'/>'
												class="btn btn-primary w-100 py-3 text-white"> <b>Manage</b>
											</a>

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

	<!-- -Include the footer jsp to the page -->
	<jsp:include page="footer.jsp" />

</body>
</html>