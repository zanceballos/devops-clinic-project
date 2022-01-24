<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Clinics | Clinic Hub</title>
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

	<div class='clinic'>
		<div class="Clinic-Section shadow">
			<div class='container'>
				<div class='row'>
					<div class='col-lg-6'>
						<div class="header text-white mt-4">
							<h1>
								<b>Browse Clinics</b>
							</h1>
							<p class="text-white">
								<b>Search for a clinic and Book an Appointment!</b>
							</p>
						</div>
						<div class='clinic-search'>
							<div class="input-group input-group-lg mb-3 shadow">
								<input type="text" class="form-control"
									placeholder="Search Clinics">
								<div class="input-group-append">
									<span class="input-group-text" id="basic-addon2"><i
										class="fas fa-search"></i></span>
								</div>
							</div>
						</div>


					</div>
					<div class='col-lg-6 text-center'>
						<img src="./assetsimg/clinics-list-img4.svg"
							class="clinic-img-head" alt="" />
					</div>
				</div>
			</div>

		</div>

		<div class='container py-4'>
			<div class='header py-4'>
				<h3>
					<b>All Clinics</b>
				</h3>
			</div>

			<div class="row">
				<div class='col-lg-4 py-3'>
					<div class='clinic-cards'>
						<div class="card bg-white border rounded-lg shadow-sm">
							<img src='https://www.onecaremedical.com.sg/wp-content/uploads/2019/07/OCTM.png' class="card-img-top-alt" alt=""
							/>
							<h5>
								<span class="badge bg-info text-white location-name-clinics-card shadow"> <i
									class="fas fa-map-marker-alt text-white mr-2"></i> Location Name
								</span>
							</h5>
							<div class="card-body">
								<div class="header card-event-name">
									<h5>
										<b>Clinic Name</b>
									</h5>
								</div>
								<div class="event-info text-muted">
									<ul class="list-unstyled text-small text-left info">
										<li class="mb-2"><i
											class="fas fa-shopping-bag me-2 text-secondary fa-lg"></i> <b>Operating Days</b>
										</li>
										<li class="mb-2"><i
											class="fas fa-clipboard-list me-2 text-secondary fa-lg"></i> <b>Operating Hours</b>
										</li>
									</ul>
								</div>
								<span class="text-muted"> <b>Contact</b> 
								</span>
								<div class="buttons py-2">
									<a href='<%=request.getContextPath()%>/ClinicDetails.jsp' class="btn btn-primary float-right">
										<b>View More</b>
									</a>
								</div>
							</div>

						</div>
					</div>
				</div>
				
				<div class='col-lg-4 py-3'>
					<div class='clinic-cards'>
						<div class="card bg-white border rounded-lg shadow-sm">
							<img src='https://fdb.clinicstandard.com/api/fdb/download??p=1&ref_file=be6348da-e402-41d1-8f56-44c269590a3c.jpg' class="card-img-top-alt" alt=""
							" />
							<h5>
								<span class="badge bg-info text-white location-name-clinics-card shadow"> <i
									class="fas fa-map-marker-alt text-white mr-2"></i> Location Name
								</span>
							</h5>
							<div class="card-body">
								<div class="header card-event-name">
									<h5>
										<b>Clinic Name</b>
									</h5>
								</div>
								<div class="event-info text-muted">
									<ul class="list-unstyled text-small text-left info">
										<li class="mb-2"><i
											class="fas fa-shopping-bag me-2 text-secondary fa-lg"></i> <b>Operating Days</b>
										</li>
										<li class="mb-2"><i
											class="fas fa-clipboard-list me-2 text-secondary fa-lg"></i> <b>Operating Hours</b>
										</li>
									</ul>
								</div>
								<span class="text-muted"> <b>Contact</b> 
								</span>
								<div class="buttons py-2">
									<button class="btn btn-primary float-right">
										<b>View More</b>
									</button>
								</div>
							</div>

						</div>
					</div>
				</div>
				<div class='col-lg-4 py-3'>
					<div class='clinic-cards'>
						<div class="card bg-white border rounded-lg shadow-sm">
							<img src='https://polyclinic.singhealth.com.sg/patient-care/PublishingImages/shp-punggol/shp-pg.jpg' class="card-img-top-alt" alt=""
							 />
							<h5>
								<span class="badge bg-info text-white location-name-clinics-card shadow"> <i
									class="fas fa-map-marker-alt text-white mr-2"></i> Location Name
								</span>
							</h5>
							<div class="card-body">
								<div class="header card-event-name">
									<h5>
										<b>Clinic Name</b>
									</h5>
								</div>
								<div class="event-info text-muted">
									<ul class="list-unstyled text-small text-left info">
										<li class="mb-2"><i
											class="fas fa-shopping-bag me-2 text-secondary fa-lg"></i> <b>Operating Days</b>
										</li>
										<li class="mb-2"><i
											class="fas fa-clipboard-list me-2 text-secondary fa-lg"></i> <b>Operating Hours</b>
										</li>
									</ul>
								</div>
								<span class="text-muted"> <b>Contact</b> 
								</span>
								<div class="buttons py-2">
									<button class="btn btn-primary float-right">
										<b>View More</b>
									</button>
								</div>
							</div>

						</div>
					</div>
				</div>
				<div class='col-lg-4 py-3'>
					<div class='clinic-cards'>
						<div class="card bg-white border rounded-lg shadow-sm">
							<img src='https://onecms-res.cloudinary.com/image/upload/s--nx-K60a5--/c_crop%2Ch_810%2Cw_1440%2Cx_0%2Cy_135/f_auto%2Cq_auto/c_fill%2Cg_auto%2Ch_676%2Cw_1200/v1/mediacorp/cna/image/2021-10/242702303_408531773962235_6330686202698429774_n.jpg?itok=GCDaGhXR' class="card-img-top-alt" alt=""
							" />
							<h5>
								<span class="badge bg-info text-white location-name-clinics-card shadow"> <i
									class="fas fa-map-marker-alt text-white mr-2"></i> Location Name
								</span>
							</h5>
							<div class="card-body">
								<div class="header card-event-name">
									<h5>
										<b>Clinic Name</b>
									</h5>
								</div>
								<div class="event-info text-muted">
									<ul class="list-unstyled text-small text-left info">
										<li class="mb-2"><i
											class="fas fa-shopping-bag me-2 text-secondary fa-lg"></i> <b>Operating Days</b>
										</li>
										<li class="mb-2"><i
											class="fas fa-clipboard-list me-2 text-secondary fa-lg"></i> <b>Operating Hours</b>
										</li>
									</ul>
								</div>
								<span class="text-muted"> <b>Contact</b> 
								</span>
								<div class="buttons py-2">
									<button class="btn btn-primary float-right">
										<b>View More</b>
									</button>
								</div>
							</div>

						</div>
					</div>
				</div>
				<div class='col-lg-4 py-3'>
					<div class='clinic-cards'>
						<div class="card bg-white border rounded-lg shadow-sm">
							<img src='https://www.moh.gov.sg/images/librariesprovider5/default-album/khatib-polyclinic_200131.jpeg?sfvrsn=ed7d0d61_2' class="card-img-top-alt" alt=""
							" />
							<h5>
								<span class="badge bg-info text-white location-name-clinics-card shadow"> <i
									class="fas fa-map-marker-alt text-white mr-2"></i> Location Name
								</span>
							</h5>
							<div class="card-body">
								<div class="header card-event-name">
									<h5>
										<b>Clinic Name</b>
									</h5>
								</div>
								<div class="event-info text-muted">
									<ul class="list-unstyled text-small text-left info">
										<li class="mb-2"><i
											class="fas fa-shopping-bag me-2 text-secondary fa-lg"></i> <b>Operating Days</b>
										</li>
										<li class="mb-2"><i
											class="fas fa-clipboard-list me-2 text-secondary fa-lg"></i> <b>Operating Hours</b>
										</li>
									</ul>
								</div>
								<span class="text-muted"> <b>Contact</b> 
								</span>
								<div class="buttons py-2">
									<button class="btn btn-primary float-right">
										<b>View More</b>
									</button>
								</div>
							</div>

						</div>
					</div>
				</div>
				<div class='col-lg-4 py-3'>
					<div class='clinic-cards'>
						<div class="card bg-white border rounded-lg shadow-sm">
							<img src='https://www.onecaremedical.com.sg/wp-content/uploads/2019/07/OCTM.png' class="card-img-top-alt" alt=""
							" />
							<h5>
								<span class="badge bg-info text-white location-name-clinics-card shadow"> <i
									class="fas fa-map-marker-alt text-white mr-2"></i> Location Name
								</span>
							</h5>
							<div class="card-body">
								<div class="header card-event-name">
									<h5>
										<b>Clinic Name</b>
									</h5>
								</div>
								<div class="event-info text-muted">
									<ul class="list-unstyled text-small text-left info">
										<li class="mb-2"><i
											class="fas fa-shopping-bag me-2 text-secondary fa-lg"></i> <b>Operating Days</b>
										</li>
										<li class="mb-2"><i
											class="fas fa-clipboard-list me-2 text-secondary fa-lg"></i> <b>Operating Hours</b>
										</li>
									</ul>
								</div>
								<span class="text-muted"> <b>Contact</b> 
								</span>
								<div class="buttons py-2">
									<button class="btn btn-primary float-right">
										<b>View More</b>
									</button>
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