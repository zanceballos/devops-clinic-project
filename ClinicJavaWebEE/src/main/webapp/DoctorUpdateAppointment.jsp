
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
	<div class="patient-update-section shadow">
		<div class='container'>
			<div class='row'>
				<div class='col-lg-6'>
					<div class="header text-white mt-4">
						<h1>
							<b>Update Patient's Appointment</b>
						</h1>
						<p class="text-white">
							<b>Make changes to your Patient's Appointment Booking</b>
						</p>
					</div>
					<div class='clinic-search'>
						<a
							href='<%=request.getContextPath()%>/AppointmentServlet/ClinicAppointments?clinicid=${id}'
							class='btn btn-light px-5 py-3 rounded-pill'><i
							class="fas fa-arrow-left pr-2"></i><b>Back To Appointments</b></a>
					</div>


				</div>
				<div class='col-lg-6 text-center'>
					<img
						src="${pageContext.request.contextPath}/assetsimg/doctor-home.svg"
						class="clinic-img-head" alt="" />
				</div>
			</div>
		</div>
	</div>

	<div class='container'>
		<div class='update-form section py-5'>
			<div class='header'>
				<h3>
					<b>Update Form</b>
				</h3>
			</div>
			<form method='post' action='UpdateAppointmentDetails'>
				<div class="py-4 card w-100 border-0 shadow rounded-lg mb-5">
					<div class='card-body'>
						<div class="form px-3">
							<div class="form-group mb-3 d-none">
								<h6>
									<b>Appointment ID</b>
								</h6>
								<input type="text" name="id" value='${appointment.id}'
									placeholder="Clinic ID"
									class="bg-light w-100 border-0 shadow-sm px-4 py-3" readonly>

							</div>
							<div class="form-group mb-3 d-none">
								<h6>
									<b>Clinic Identification Number</b>
								</h6>
								<input type="text" name="clinicid"
									value='${appointment.clinic_id}' placeholder="Clinic ID"
									class="bg-light w-100 border-0 shadow-sm px-4 py-3" readonly>

							</div>
							<div class="form-group mb-3 d-none">
								<h6>
									<b>Userid</b>
								</h6>
								<input type="text" name="user_id" value='${appointment.user_id}'
									placeholder="Clinic ID"
									class="bg-light w-100  border-0 shadow-sm px-4 py-3" readonly>

							</div>
							<div class="form-group mb-3">
								<h6>
									<b>Status</b>
								</h6>
								<select class="bg-light w-100 border-0 shadow-sm px-4 py-3"
									name="status">
									<option value="${appointment.status}">${appointment.status}</option>
									<option value="upcoming">Upcoming</option>
									<option value="completed">Complete</option>
									<option value="cancelled">Cancel</option>

								</select>
							</div>
							<div class="form-group mb-3">
								<h6>
									<b>Appointment Type</b>
								</h6>
								<select class="bg-light w-100 border-0 shadow-sm px-4 py-3"
									name="appointment_type">
									<option value="${appointment.appointment_type}">${appointment.appointment_type}</option>
									<option value="Consultation">Consultation</option>
									<option value="Vaccination">Vaccination</option>
									<option value="Swap Test">Swap Test</option>
									<option value="Dental Checkup">Dental Checkup</option>
								</select>
							</div>
							<div class="form-group mb-3">
								<h6>
									<b>Current Date & Time of Appointment</b>
								</h6>

								<input type="text" name="datetime"
									value="${appointment.date_time}" placeholder="Appointment Date"
									class="bg-light w-100 border-0 shadow-sm px-4 py-3" readonly>

							</div>
							<p class='text-muted pt-4'>Change the Date & Time below if
								needed</p>
							<div class="form-group mb-3">
								<h6>
									<b>Date of Appointment</b>
								</h6>
								<input type="date" name="date" placeholder="Appointment Date"
									class="bg-light w-100 border-0 shadow-sm px-4 py-3">

							</div>
							<div class="form-group mb-3">
								<h6>
									<b>Time of Appointment</b>
								</h6>
								<input type="time" name="time" placeholder="Appointment Time"
									class="bg-light w-100 border-0 shadow-sm px-4 py-3">

							</div>
							<c:if test="${update_datetime_error == true}">
								<div class="alert alert-danger py-3" role="alert">
									<i class="fas fa-exclamation-circle pr-2"></i><span>Date
										or Time Cannot be empty when updating the fields!!</span>
								</div>
								<c:set var="update_datetime_error" value="" scope="session" />

							</c:if>
							<button type="submit" class='btn btn-primary w-100 py-3 mt-2'>
								<b> Update Appointment!</b>
							</button>

						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<!-- -Include the footer jsp to the page -->
	<jsp:include page="footer.jsp" />
</body>
</html>