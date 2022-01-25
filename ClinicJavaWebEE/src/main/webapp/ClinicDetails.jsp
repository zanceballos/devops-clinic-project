<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Clinic Details | Clinic Hub</title>
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

	<div class='fixed-top-body'>
		<div class='container'>
			<div class='header'>
				<h6>
					<b>Clinics Details</b>
				</h6>
				<ol class="breadcrumb text-dark">
					<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/PatientHome.jsp">Home</a></li>
					<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/ClinicServlet/all-clinics">Listings</a></li>
					<li class="breadcrumb-item active" aria-current="page">Clinic
						Details</li>
				</ol>
			</div>
			<div class='row py-4'>
				<div class='col-lg-8'>
					<img class='shadow-sm rounded img-clinic'
						src=<c:out value='${clinicDetails.image}' />>


				</div>
				<div class='col-lg-4'>
					<div class="header">
						<h4>
							<b><c:out value='${clinicDetails.clinic_name}' /></b>
						</h4>
						<p class="text-muted">
							@ <b> <c:out value='${clinicDetails.location_name}' /> </b>
						</p>
					</div>
					<div class="event-info">
						<div class="points">
							<h5>
								<span class="badge bg-info shadow text-white"> <i
									class="fas fa-star text-white mr-2"></i>Reviews
								</span>
							</h5>
						</div>
						<div class="info py-2 text-muted">
							<b>Brief Details</b>
						</div>

						<div class="datetime">
							<p class="text-muted">
								<i class="far fa-clock mr-2 text-primary fa-lg"></i><c:out value='${clinicDetails.opening_hours}' />
							</p>
						</div>
						<div class="datetime">
							<p class="text-muted">
								<i class="fas fa-calendar mr-2 text-primary fa-lg"></i><c:out value='${clinicDetails.opening_days}' />
							</p>
						</div>
						<div class="contact">
							<p class="text-muted">
								<i class="fas fa-phone mr-2 text-primary fa-lg"></i><c:out value='${clinicDetails.contact_number}' />
							</p>
						</div>
						<c:if test="${logged_in == true}">
							<div class="buttons text-white">
								<a class='btn btn-primary w-100 py-3'><b> Book an
										Appointment</b></a>
							</div>
						</c:if>
						<c:if test="${logged_in == null}">
							<div class="buttons text-white">
								<button type='button' disabled class='btn btn-primary w-100 py-3'><i class="fas fa-lock pr-2"></i><b> Sign In to
										Book an Appointment</b></button>
							</div>
						</c:if>

					</div>
				</div>
			</div>

			<div class='details'>
				<div class='details py-3'>
					<div class='about-clinic'>
						<div class='py-3'>
							<h5 class='text-center'>
								<i class="fas fa-info-circle text-primary pr-2"></i><b>Clinic
									Details</b>
							</h5>
						</div>

						<div class="card border-0 bg-white shadow rounded-lg px-3 py-5">
							<div class="description">
								<div class="header">
									<h5>
										<i class="fas fa-info-circle pr-2 text-primary"></i> <b>Description
											of Clinic</b>
									</h5>
								</div>
								<div class="text-desc">
									<p class="text-muted">Lorem ipsum dolor sit amet,
										consectetur adipiscing elit, sed do eiusmod tempor incididunt
										ut labore et dolore magna aliqua. Ut enim ad minim veniam,
										quis nostrud exercitation ullamco laboris nisi ut aliquip ex
										ea commodo consequat.</p>
								</div>
							</div>
							<div class="row py-3">
								<div class="col-lg-4 col-md-6 col-sm-6 col-6 py-3">
									<h5>
										<i class="far fa-calendar-alt pr-2 text-primary"></i> <b>Name
											of Clinic</b>
									</h5>
									<div class="text-desc">
										<p class="text-muted"><c:out value='${clinicDetails.clinic_name}' /></p>
									</div>
								</div>
								<div class="col-lg-4 col-md-6 col-sm-6 col-6 py-3">
									<h5>
										<i class="fas fa-map-marker-alt pr-2 text-primary"></i> <b>Location
											Name</b>
									</h5>
									<div class="text-desc">
										<p class="text-muted"><c:out value='${clinicDetails.location_name}' /></p>
									</div>
								</div>
								<div class="col-lg-4 col-md-6 col-sm-6 col-6 py-3">
									<h5>
										<i class="fas fa-map-marked-alt pr-2 text-primary"></i><b>Address</b>
									</h5>
									<div class="text-desc">
										<p class="text-muted"><c:out value='${clinicDetails.address}' /></p>
									</div>
								</div>
								<div class="col-lg-4 col-md-6 col-sm-6 col-6 py-3">
									<h5>
										<i class="fas fa-user-clock pr-2 text-primary"></i> <b>Operating
											Hours</b>
									</h5>
									<div class="text-desc">
										<p class="text-muted"><c:out value='${clinicDetails.opening_hours}' /></p>
									</div>
								</div>
								<div class="col-lg-4 col-md-6 col-sm-6 col-6 py-3">
									<div class="status">
										<div class="header">
											<h5>
												<i class="fas fa-business-time pr-2 text-primary"></i> <b>Operating
													Days</b>
											</h5>
											<div class="text-desc">
												<p class="text-muted">
													<span><c:out value='${clinicDetails.opening_days}' /></span>
												</p>
											</div>
										</div>

									</div>
								</div>
								<div class="col-lg-4 col-md-6 col-sm-6 col-6 py-3">
									<div class="header">
										<h5>
											<i class="fas fa-envelope pr-2 text-primary"></i> <b>Clinic
												Contact</b>
										</h5>
										<div class="text-desc">
											<p class="text-muted">
												<span><c:out value='${clinicDetails.contact_number}' /></span>
											</p>
										</div>
									</div>
								</div>
							</div>

						</div>
					</div>

				</div>

			</div>

			<div class='reviews py-4'>
				<div class="other card shadow rounded-lg">
					<div class='card-body'>
						<div class="row no-gutter">
							<!-- The image half -->
							<div class="col-md-6 d-none d-md-flex bg-image">
								<img class="img-fluid px-4 py-4"
									src="${pageContext.request.contextPath}/assetsimg/review-img1.svg" alt="">
							</div>
							<!-- The content half -->
							<div class="col-md-6">
								<div class="login d-flex align-items-center py-5">
									<!-- Demo content-->
									<div class="container text-center">
										<div class="row mt-5">
											<div class="col-lg-12 ">
												<h3>
													<b>Clinic Reviews</b>
												</h3>
												<h5 class="text-muted mb-4">View All Reviews For this
													Clinic</h5>
												<a class="btn btn-primary text-white rounded-pill py-2 px-5">Reviews
												</a>
											</div>

										</div>
										<!-- End -->

									</div>
								</div>
								<!-- End -->

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