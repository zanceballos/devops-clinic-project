<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Patient Appointments | Clinic Hub</title>
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
						<i class="fas fa-hospital-user pr-2 text-primary"></i> <b>Your
							Appointments</b>
					</h3>
					<p class='text-muted'>
						<b>All of your appointments are listed here</b>
					</p>
				</div>

				<c:if test="${empty listAppointments}">
					<div class='empty-icon text-center'>
						<img src="${pageContext.request.contextPath}/assetsimg/empty.svg"
							width="250" class='' alt="" />
						<p class='pt-5'>
							<b>Nothing To See Here!</b>
						</p>
					</div>
				</c:if>

				<div class='clinic-list'>

					<c:forEach var='appointment' items='${listAppointments}'>
						<div class="card rounded-lg bg-white shadow mb-3 w-100 border-0">
							<div class="row no-gutters">
								<div class="col-md-4">
									<img src=<c:out value="${appointment.image}" />
										class="card-img-h" alt="..." />
								</div>
								<div class="col-md-8">
									<div class="card-body">
										<h5 class="card-title">
											<b><c:out value="${appointment.clinic_name}" /></b>
										</h5>
										<p class="text-muted">
											at <b> <c:out value="${appointment.address}" />
											</b>
										</p>
										<div class="points">
											<h5>
												<c:if test="${appointment.status == 'upcoming' }">
													<span
														class="badge bg-primary shadow-sm text-white text-capitalize px-3 py-2 mr-2">
														<i class="fas fa-clipboard-list text-white mr-2"></i> <c:out
															value="${appointment.status}" />
													</span>
												</c:if>
												<c:if test="${appointment.status == 'completed' }">
													<span
														class="badge bg-success shadow-sm text-white text-capitalize px-3 py-2 mr-2">
														<i class="fas fa-clipboard-list text-white mr-2"></i> <c:out
															value="${appointment.status}" />
													</span>
												</c:if>
												<c:if test="${appointment.status == 'cancelled' }">
													<span
														class="badge bg-danger shadow-sm text-white text-capitalize px-3 py-2 mr-2">
														<i class="fas fa-clipboard-list text-white mr-2"></i> <c:out
															value="${appointment.status}" />
													</span>
												</c:if>
											</h5>
										</div>

										<div class="event-details py-2">
											<div class="row">
												<div class="col-lg-3 col-6">
													<div class="location">
														<p class="text-muted">
															<i class="fas fa-map-marker-alt mr-2 text-primary"></i> <b><c:out
																	value="${appointment.location_name}" /></b>
														</p>
													</div>
												</div>
												<div class="col-lg-3 col-6">
													<div class="datetime">
														<p class="text-muted">
															<i class="far fa-calendar-alt mr-2 text-primary"></i> <b><c:out
																	value="${appointment.date_time}" /></b>
														</p>
													</div>
												</div>
												<div class="col-lg-3 col-6">
													<div class="shift-type">
														<p class="text-muted">
															<i class="fas fa-user-clock mr-2 text-primary"></i> <b><c:out
																	value="${appointment.appointment_type}" /></b>
														</p>
													</div>
												</div>

												<div class="col-lg-3 col-6">
													<div class="organisation">
														<p class="text-muted">
															<i class="fas fa-envelope mr-2 text-primary"></i> <b><c:out
																	value="${appointment.contact_number}" /></b>
														</p>
													</div>
												</div>
											</div>
										</div>
										<div class="dropdown pb-5">
											<button
												class="btn btn-primary px-5 dropdown-toggle float-right"
												type="button" id="dropdownMenuButton" data-toggle="dropdown"
												aria-haspopup="true" aria-expanded="false">
												<b> More Options</b>
											</button>
											<div class="dropdown-menu">
												<a class="dropdown-item"
													href="/ClinicJavaWebEE/AppointmentServlet/ShowAppointmentDetails?id=${appointment.id}">Update</a>
												<a class="dropdown-item"
													href="/ClinicJavaWebEE/AppointmentServlet/DeleteAppointment?id=${appointment.id}">Delete</a>
											</div>
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
	<!-- -Include the footer jsp to the page -->
	<jsp:include page="footer.jsp" />
</body>
</html>