
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Doctors | Home</title>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<link rel="stylesheet" href="./CSS/styles.css" type="text/css">

</head>
<body>
	<!-- -Include the navbar jsp to the page -->
	<jsp:include page="navbar.jsp" />
	
	<div>
		<div class="Home-section shadow">
			<div class="container">
				<div class="row">
					<div class="col-lg-5">
						<div class="header">
							<h3>Hello,</h3>
							<h1>
								<b>${username}</b>
							</h1>
							<p class="text-muted">
								<b>Manage your patient's appointments and clinics!</b>
							</p>
						</div>
						<div class="header-buttons py-3">
							<button class="btn btn-primary px-5 py-3 mr-2">Manage Appointments</button>
					
						</div>
					</div>
					<div class="col-lg-7">
						<img src="./assetsimg/doctor-home.svg" class="img-fluid" alt="" />
					</div>
				</div>
			</div>
		</div>

		<div class='container py-5'>
			<div class='header'>
				<h3>
					<b>Navigate</b>
				</h3>
			</div>
			<div>
				<div>
					<div class="row">
						<div class="col-lg-6">
							<div class="card shadow border-0 mb-3">
								<div class="card-header card-home bg-home border-0 py-4">
									<img class="card-img-top" src="./assetsimg/manage-appt.svg"
										alt="" />
								</div>
								<div class="card-body">
									<h5 class="card-title">
										<b>Manage Appointments</b>
									</h5>
									<p class="card-text text-muted mb-4">Manage All Patient Appointments</p>
									<button
										class="btn btn-primary float-right px-5 rounded-pill py-2">
										View More</button>

								</div>
							</div>
						</div>
						<div class="col-lg-6">

							<div class="card shadow border-0 mb-3">
								<div class="card-header card-home bg-home border-0 py-4">
									<img class="card-img-top" src="./assetsimg/manage-clinic.svg"
										alt="" />
								</div>
								<div class="card-body">
									<h5 class="card-title">
										<b>Manage Clinics</b>
									</h5>
									<p class="card-text text-muted mb-4">Add, Update or Remove Clinics here!</p>
									<button
										class="btn btn-primary float-right px-5 rounded-pill py-2">
										View More</button>

								</div>
							</div>
						</div>
						
					</div>
				</div>

			</div>

		</div>

	</div>
	
	<!-- -Include the Footer jsp to the page -->
	<jsp:include page="footer.jsp" />
	
</body>
</html>