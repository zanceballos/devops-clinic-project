
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Clinic Hub | Home</title>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>ClinicHub</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<link rel="stylesheet" href="./CSS/styles.css" type="text/css">

</head>
<body>
	<!-- -Include the navbar jsp to the page -->
	<jsp:include page="navbar.jsp" />

	<div >
		<div class="Hero-Section shadow">
			<div class="container">
				<div class="row align-item-center">
					<div class="col-lg-7">
						<div class="landing-content">
							<p>
								<b>Clinic Appointment</b>
							</p>
							<h1 class="head-title">
								<b>Book an Appointment with a Clinic here</b>
							</h1>
							<p class="text-muted">Select across multiple clinics
								available in this website. Choose a convenient Date & Time of
								appointment and You're good to go!</p>
						</div>
						<div class="intro-button">

							<a href='${pageContext.request.contextPath}/ClinicServlet/all-clinics' class="btn btn-primary py-3 px-5 mt-3">
								<b>Explore Clinics</b>
							</a>

						</div>

					</div>
					<div class="col-lg-5">
						<div class="intro-img">
							<img src="./assetsimg/home-intro.svg" class="img-fluid pe-4"
								alt="" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="container py-5" id="features">
		<div class="intro-section py-4">
			<div class='header'>
				<h4>
					<b>Features </b>
				</h4>
			</div>

			<div class="row">

				<div class='col-lg-3'>
					<div class="card border-0 bg-white rounded-lg flow-card shadow">
						<div class="card-header rounded-lg border-0">
							<img class="card-img-top" src="./assetsimg/select-clinic-2.svg"
								alt="" />
						</div>
						<div class="card-body index-card text-center">
							<h5>
								<b>Browse clinic</b>
							</h5>
							<p>Select a clinic for Appointment</p>
						</div>
					</div>

				</div>

				<div class='col-lg-3'>
					<div class="card border-0 bg-white rounded-lg flow-card shadow">
						<div class="card-header rounded-lg border-0">
							<img class="card-img-top" src="./assetsimg/select-date.svg" alt="" />
						</div>
						<div class="card-body index-card text-center">
							<h5>
								<b>Select Date & Time</b>
							</h5>
							<p>Select date & time of appointment</p>
						</div>
					</div>
				</div>
				<div class='col-lg-3'>
					<div class="card border-0 bg-white rounded-lg flow-card shadow">
						<div class="card-header rounded-lg border-0">
							<img class="card-img-top" src="./assetsimg/attend-clinic.svg" alt="" />
						</div>
						<div class="card-body index-card text-center">
							<h5>
								<b>Visit the clinic</b>
							</h5>
							<p>Visit clinic based on your date & time</p>
						</div>
					</div>
				</div>
				<div class='col-lg-3'>
					<div class="card border-0 bg-white rounded-lg flow-card shadow">
						<div class="card-header rounded-lg border-0">
							<img class="card-img-top" src="./assetsimg/review-img.svg" alt="" />
						</div>
						<div class="card-body index-card text-center">
							<h5> 
								<b>Review Experience</b>
							</h5>
							<p>Give a review to share your experience!</p>
						</div>
					</div>
				</div>
			</div>

		</div>

		<div class="about-section py-4" id="about">
			<div class='mt-3'>
				<h4>
					<b>About</b>
				</h4>
			</div>
			<div class="row">
				<div class="col-lg-6">
					<img src="./assetsimg/about-img.svg"
						class='img-fluid rounded shadow'>

				</div>
				<div class="col-lg-6 text-center">
					<h6>
						<b>About the Project</b>
					</h6>
					<p class='text-muted mb-3'>Lorem Ipsum is simply dummy text of
						the printing and typesetting industry. Lorem Ipsum has been the
						industry's standard dummy text ever since the 1500s, when an
						unknown printer took a galley of type and scrambled it to make a
						type specimen book. It has survived not only five centuries, but
						also the leap into electronic typesetting, remaining essentially
						unchanged.</p>
					<button class="btn btn-primary py-3 w-100 mt-3">
						<b>Sign In & Start Booking!</b>
					</button>

				</div>
			</div>

		</div>

	</div>

	<!-- -Include the Footer jsp to the page -->
	<jsp:include page="footer.jsp" />

</body>
</html>