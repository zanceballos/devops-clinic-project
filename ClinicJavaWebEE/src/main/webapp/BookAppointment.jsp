
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Book Appointment | Clinic Hub</title>
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
			<div class='header py-3'>
				<div class='booking-section'>
					<div class='container'>
						<div class='row'>
							<div class='col-lg-12 text-center'>
								<h1>
									<b>Book an Appointment</b>
								</h1>
								<p class="text-white">
									<b>Fill up the Form below and we're all set!</b>
								</p>

								<div class='py-3'>
									<a class='btn btn-light px-5 py-3 rounded-pill text-dark'><b>Booking
											Form</b><i class="fas fa-arrow-alt-circle-down text-dark pl-2"></i></a>
								</div>

							</div>

						</div>

					</div>

				</div>
			</div>

			<div class='forms-section py-3'>
				<div class='header'>
					<h5 class='text-muted'>
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
												class="badge shadow-sm text-white bg-info text-capitalize mr-2">
												<i class="fas fa-star mr-2"></i> Reviews
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

				<div class='booking-form py-3'>
					<div class='header'>
						<h5 class='text-muted'>
							<b>Appointment Form</b>
						</h5>
					</div>
					<div class='forms'>
						<form method='post' action="BookAppointment">
							<div class="py-4 card w-100 border-0 shadow rounded-lg mb-5">

								<div class='card-body'>

									<div class="form px-3">
										<div class="form-group mb-3">
											<h6>
												<b>Clinic Identification Number</b>
											</h6>
											<input type="text" name="clinicid"
												value='${clinicDetails.id}'
												placeholder="Clinic ID"
												class="bg-light w-100 border-0 shadow-sm px-4 py-3"
												readonly
												>

										</div>
										<div class="form-group mb-3">
											<h6>
												<b>Appointment Type</b>
											</h6>
											<select class="bg-light w-100 border-0 shadow-sm px-4 py-3"
												name="appointment_type">
												<option value="none" disabled>Select Appointment
													Type</option>
												<option value="Consultation">Consultation</option>
												<option value="Vaccination">Vaccination</option>
												<option value="Swap Test">Swap Test</option>
												<option value="Dental Checkup">Dental Checkup</option>
											</select>
										</div>
										<div class="form-group mb-3">
											<h6>
												<b>Date of Appointment</b>
											</h6>
											<input type="date" name="date" placeholder="Appointment Date"
												class="bg-light w-100 border-0 shadow-sm px-4 py-3" required>

										</div>
										<div class="form-group mb-3">
											<h6>
												<b>Time of Appointment</b>
											</h6>
											<input type="time" name="time" placeholder="Appointment Time"
												class="bg-light w-100 border-0 shadow-sm px-4 py-3" required>

										</div>
										<button class='btn btn-primary w-100 py-3 mt-2'>
											<b> Book Appointment!</b>
										</button>
									</div>


								</div>



							</div>
						</form>

					</div>
				</div>


			</div>

		</div>
	</div>

	<!-- -Include the footer jsp to the page -->
	<jsp:include page="footer.jsp" />

</body>
</html>