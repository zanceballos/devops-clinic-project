
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



	<div class="success-Section">
		<div class="container text-center text-white">

			<h1>
				<b> <i class="fas fa-heart text-white fa-2x"></i>
				</b>
			</h1>
			<h1>
				<b>Thank you for booking an appointment <br /> with us!
				</b>
			</h1>
			<div class="py-5">
				<p class="py-2">More Options Below!</p>
				<i class="fas fa-chevron-down fa-3x"></i>
			</div>

		</div>
	</div>
	<div class='container py-5'>

		<div class='row'>
			<div class='col-lg-6 text-center'>
				<div class='image py-3'>
					<img src='${pageContext.request.contextPath}/assetsimg/success.svg'
						class='img-fluid-success'>
				</div>
				<h5 class='text-muted'>
					<b>There's more things to explore!</b>
				</h5>
			</div>
			<div class='col-lg-6 mt-5'>
				<h4>
					<b>Go To</b>
				</h4>
				<div class='clinics-card-selected mb-2'>
					<div class="card bg-white shadow mb-3 w-100 border-0 rounded-lg">
						<div class="row no-gutters">
							<div class="col-md-4 selected-clinic-md-4">
								<img
									src="${pageContext.request.contextPath}/assetsimg/manage-clinic.svg"
									class="img-fluid py-5 px-4" alt="..." />
							</div>
							<div class="col-md-8">
								<div class='card-body'>
									<h6>
										<b>View Appointments</b>
									</h6>
									<p class='text-muted'>View All Of Your Appointments</p>
									<a href="<%=request.getContextPath()%>/AppointmentServlet/PatientAppointments?userid=${id}"
										class='btn btn-primary px-5 py-2  rounded-pill text-white mt-3 float-right'><b>More</b></a>
								</div>

							</div>

						</div>
					</div>
				</div>
				<div class='clinics-card-selected'>
					<div class="card bg-white shadow mb-3 w-100 border-0 rounded-lg">

						<div class="row no-gutters">
							<div class="col-md-4 selected-clinic-md-4">
								<img
									src="${pageContext.request.contextPath}/assetsimg/home-intro.svg"
									class="img-fluid py-5 px-4" alt="..." />
							</div>
							<div class="col-md-8">
								<div class='card-body'>
									<h6>
										<b>Explore Other Clinics</b>
									</h6>
									<p class='text-muted'>Browse & Explore other clinics
										available!</p>
									<a
										href="<%=request.getContextPath()%>/ClinicServlet/all-clinics"
										class='btn btn-primary px-5 py-2 rounded-pill text-white mt-3 float-right'><b>More</b>
									</a>
								</div>

							</div>

						</div>
					</div>
				</div>
			</div>
		</div>


	</div>

	<!-- -Include the footer jsp to the page -->
	<jsp:include page="footer.jsp" />
</body>
</html>