<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Clinic | Clinic Hub</title>

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
	<div class='fixed-top-body'>
		<div class='container mt-2'>
			<div class="row no-gutter g-0">
				<div class="col-md-12">
					<div class="d-flex align-items-center">
						<div class="container py-3 px-3">
							<div class="row text-dark">
								<div class="col-lg-6 col-xl-7 mx-auto bg-light py-5 px-5 border shadow border-rounded">
									<h2>
										<i class="fas fa-plus-square pr-2 text-primary"></i> <b>Create
											Clinic</b>
									</h2>


									<form autoComplete="off" noValidate
										action='CreateClinicServlet' method="post">
										<div class="form-group mb-3">
											<input id="inputClinic_Name" type="text"
												placeholder="Clinic Name" name="clinic_name" required
												class="form-control border-0 shadow-sm px-4 py-4"
												onChange={handleInputChange} />

										</div>
										<div class="form-group mb-3">
											<input id="inputAddress" type="text" placeholder="Address"
												required name="address"
												class="form-control border-0 shadow-sm px-4 py-4" />

										</div>

										<div class='row'>
											<div class='col-lg-6'>
												<div class="form-group mb-3">
													<input id="inputLocation_Name" type="text"
														placeholder="Location Name" required name="location_name"
														class="form-control border-0 shadow-sm px-4 py-4" />


												</div>

											</div>
											<div class='col-lg-6'>
												<div class="form-group mb-3">
													<input id="inputImage" type="text" placeholder="Image"
														required name="image"
														class="form-control border-0 shadow-sm px-4 py-4" />

												</div>
											</div>
										</div>


										<div class="form-group mb-3">
											<input id="inputDescription" type="text"
												placeholder="Description" required name="description"
												class="form-control border-0 shadow-sm px-4 py-4 " />
										</div>

										<div class="form-group mb-3">
											<input id="inputOpening_Hours" type="text"
												placeholder="Opening Hours" required name="opening_hours"
												class="form-control border-0 shadow-sm px-4 py-4 ">

										</div>

										<div class="form-group mb-3">
											<input id="inputOpening_Days" type="text"
												placeholder="Opening Days" required name="opening_days"
												class="form-control border-0 shadow-sm px-4 py-4 " />

										</div>

										<div class="form-group mb-3">
											<input id="inputContact_Number" type="text"
												placeholder="Contact Number" required name="contact_number"
												class="form-control border-0 shadow-sm px-4 py-4 " />

										</div>

										<c:if test="${empty_clinic_add == true}">
											<div class="alert alert-danger py-3" role="alert">
												<i class="fas fa-exclamation-circle pr-2"></i> <span>Please
													Fill Up All Fields!</span>
											</div>

											<c:set var="empty_clinic_add" value="" scope="session" />

										</c:if>
										<button type='submit'
											class="btn btn-primary py-3 btn-block text-uppercase mb-2 shadow-sm w-100">
											Create Clinic</button>

									</form>

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