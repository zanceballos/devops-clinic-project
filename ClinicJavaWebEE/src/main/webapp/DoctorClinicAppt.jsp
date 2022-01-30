
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Clinic Appointment | Clinic Hub</title>
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
			<div class='header'>
				<h6>
					<b>Clinics Appointments</b>
				</h6>
				<ol class="breadcrumb text-dark">
					<li class="breadcrumb-item"><a
						href="<%=request.getContextPath()%>/DoctorHome.jsp">Home</a></li>
					<li class="breadcrumb-item"><a
						href="<%=request.getContextPath()%>/ClinicServlet/doctor-all-clinics">All
							Clinics</a></li>
					<li class="breadcrumb-item active" aria-current="page">Clinic
						Appointments</li>
				</ol>
			</div>

			<div class='clinic-appointment pb-5'>
				<div class='header'>
					<h5 class='text-dark'>
						<b>Selected Clinic</b>
					</h5>
				</div>
				<div class='clinics-card-selected'>
					<div class="card bg-white shadow mb-3 w-100 border-0  rounded-lg">
						<div class="row no-gutters">
							<div class="col-md-4 selected-clinic-md-4">
								<img
									src="${pageContext.request.contextPath}/assetsimg/home-intro.svg"
									class="img-fluid py-5 px-4" alt="..." />
							</div>
							<div class="col-md-8">
								<div class="card-body">
									<h4 class="card-title">
										<b>${clinicDetails.clinic_name}</b>
									</h4>
									<div class="points">
										<h5>
											<span
												class="badge shadow-sm text-white bg-info text-capitalize px-3 py-2 mr-2">
												<i class="fas fa-map-marked-alt mr-2"></i>
												${clinicDetails.location_name }
											</span>
										</h5>
									</div>

									<div class="event-details py-3">
										<div class="row">
											<div class="col-lg-4 col-6">
												<div class="location">
													<p class="text-muted">
														<i class="fas fa-map-marker-alt mr-2 text-primary"></i><b>${clinicDetails.location_name}</b>
													</p>
												</div>
											</div>
											<div class="col-lg-4 col-6">
												<div class="address">
													<p class="text-muted">
														<i class="fas fa-map-marked-alt  mr-2 text-primary"></i> <b>${clinicDetails.address}</b>
													</p>
												</div>
											</div>
											<div class="col-lg-4 col-6">
												<div class="contact">
													<p class="text-muted">
														<i class="fas fa-phone mr-2 text-primary"></i> <b>${clinicDetails.contact_number}</b>
													</p>
												</div>
											</div>
											<div class="col-lg-4 col-6">
												<div class="opHours">
													<p class="text-muted">
														<i class="fas fa-user-clock mr-2 text-primary"></i> <b>${clinicDetails.opening_hours}</b>
													</p>
												</div>
											</div>
											<div class="col-lg-4 col-6">
												<div class="opDays">
													<p class="text-muted">
														<i class="fas fa-layer-group mr-2 text-primary"></i> <b>${clinicDetails.opening_days}</b>
													</p>
												</div>
											</div>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class='d-flex justify-content-between py-3'>
					<div class='header'>
						<h5 class='text-dark'>
							<b>Appointments for this clinic</b>
						</h5>
					</div>
					<a class='btn btn-primary px-5 py-2 rounded-pill shadow text-white'><i class="fas fa-comments mr-2"></i><b>Reviews</b></a>
				</div>

				<div class='appt-table'>
					<div class="card shadow rounded-lg border-0">

						<c:choose>
							<c:when test="${empty listAppointments }">
								<div class='empty-icon-appt text-center'>
									<img
										src="${pageContext.request.contextPath}/assetsimg/empty-appt1.svg"
										width="250" class='' alt="" />
									<p class='pt-5'>
										<b>No Appointments Yet!</b>
									</p>
								</div>
							</c:when>
							<c:otherwise>
								<div class="table-responsive-lg appt-tb">
									<table class="table table-striped table-hover table-borderless">
										<thead>
											<tr>
												<th scope="col">ID</th>
												<th scope="col">Username</th>
												<th scope="col">Email</th>
												<th scope="col">DateTime</th>
												<th scope="col">Appointment</th>
												<th scope="col">Status</th>
												<th scope="col">Actions</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="appt" items="${listAppointments}">
												<!-- For each user in the database, display their
information accordingly -->
												<tr>
													<td><c:out value="${appt.id}" /></td>
													<td><c:out value="${appt.username}" /></td>
													<td><c:out value="${appt.email}" /></td>
													<td><c:out value="${appt.date_time}" /></td>

													<td><c:out value="${appt.appointment_type}" /></td>
													<td><c:if test="${appt.status == 'completed' }">
															<span
																class="badge shadow-sm text-white bg-success text-capitalize px-3 py-2 mr-2">
																<i class="fas fa-check mr-2"></i> <c:out
																	value="${appt.status}" />
															</span>

														</c:if> <c:if test="${appt.status == 'cancelled' }">
															<span
																class="badge shadow-sm text-white bg-danger text-capitalize px-3 py-2 mr-2">
																<i class="fas fa-times-circle mr-2"></i> <c:out
																	value="${appt.status}" />
															</span>

														</c:if> <c:if test="${appt.status == 'upcoming' }">
															<span
																class="badge shadow-sm text-white bg-primary text-capitalize px-3 py-2 mr-2">
																<i class="fas fa-clock mr-2"></i> <c:out
																	value="${appt.status}" />
															</span>

														</c:if></td>

													<!-- For each user in the database, Edit/Delete
buttons which invokes the edit/delete functions -->
													<td>
														<div class="dropdown">
															<button class="btn btn-none dropdown-toggle"
																type="button" id="dropdownMenuButton"
																data-toggle="dropdown" aria-haspopup="true"
																aria-expanded="false">
																<b>Options</b>
															</button>
															<div class="dropdown-menu"
																aria-labelledby="dropdownMenuButton">
																<a class="dropdown-item"
																	href='<%=request.getContextPath()%>/AppointmentServlet/ShowAppointmentDetails?id=<c:out value='${appt.id}'/>'>Update</a>
																<a class="dropdown-item"
																	href="/ClinicJavaWebEE/AppointmentServlet/DeleteAppointment?id=${appt.id}&clinicid=${appt.clinic_id}">Delete</a>
															</div>
														</div>
													</td>
												</tr>
											</c:forEach>
										</tbody>

									</table>
								</div>
							</c:otherwise>

						</c:choose>


					</div>

				</div>
			</div>

		</div>
	</div>
	<!-- -Include the footer jsp to the page -->
	<jsp:include page="footer.jsp" />
</body>
</html>