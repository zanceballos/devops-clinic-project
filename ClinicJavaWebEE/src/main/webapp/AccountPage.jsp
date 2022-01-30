<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Account | ClinicHub</title>
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
	<div class="account-section shadow">
		<div class='container'>
			<div class='row'>
				<div class='col-lg-6'>
					<div class="header text-white mt-4">
						<h1>
							<b>Account Details Here</b>
						</h1>
						<p class="text-white">
							<b>View or Manage your Account here</b>
						</p>
					</div>
					<c:if test="${role == 'patient' }">
						<div class='clinic-search'>
							<a href='<%=request.getContextPath()%>/PatientHome.jsp'
								class='btn btn-light px-5 py-3 rounded-pill'><i
								class="fas fa-arrow-left pr-2"></i><b>Back To Home</b></a>
						</div>
					</c:if>
					<c:if test="${role == 'doctor' }">
						<div class='clinic-search'>
							<a href='<%=request.getContextPath()%>/DoctorHome.jsp'
								class='btn btn-light px-5 py-3 rounded-pill'><i
								class="fas fa-arrow-left pr-2"></i><b>Back To Home</b></a>
						</div>
					</c:if>

				</div>
				<div class='col-lg-6 text-center'>
					<img
						src="${pageContext.request.contextPath}/assetsimg/manage-appt.svg"
						class="clinic-img-head" alt="" />
				</div>
			</div>
		</div>
	</div>

	<div class='container py-4'>
		<div class='header py-3'>
			<b>Account Details</b>
		</div>

		<div class='card rounded-lg shadow'>
			<div class='card-body'>
				<div class="overview">
					<div class="header pb-4 d-flex justify-content-between">
						<h1>
							<i class="fas fa-user-circle me-2 text-primary"></i> <b>Username</b>
						</h1>
						<p class="text-muted edit-account">
							<a class='text-decoration-none' href="<%=request.getContextPath()%>/AccountEdit.jsp"><i class="fas fa-pen pr-2"></i> <b> Edit Account</b></a>
						</p>
					</div>
					<div class="details py-4">
						<div class="row">
							<div class="col-lg-3 py-2">
								<h5>
									<i class="fas fa-envelope text-primary pr-2"></i> <b>Email</b>
								</h5>
								<p class="text-muted">
									<b>someEmail@mail.com</b>
								</p>
							</div>
							<div class="col-lg-3  py-2">
								<h5>
									<i class="fas fa-user text-primary pr-2"></i> <b>Full Name</b>
								</h5>
								<p class="text-muted">
									<b>Full Name</b>
								</p>
							</div>
							<div class="col-lg-3 py-2">
								<h5>
									<i class="fas fa-phone-alt text-primary pr-2"></i> <b>Contact</b>
								</h5>
								<p class="text-muted">

									<b>Contact</b>
								</p>
							</div>


							<div class="col-lg-3  py-2">

								<h5>
									<i class="fas fa-dice-d6 text-primary pr-2"></i> <b>Role</b>
								</h5>
								<p class="text-muted">
									<b>User Role</b>
								</p>
							</div>
						</div>
					</div>
					<div class="config-sec pt-4">
						<button
							class="btn btn-danger rounded-pill py-2 px-5 ml-2 float-right">
							Delete Account</button>
						<a href="<%=request.getContextPath()%>/ChangePassword.jsp" class="btn btn-primary rounded-pill py-2 px-5 float-right">
							Change Password</a>
					</div>


				</div>
			</div>
		</div>
	</div>

	<!-- -Include the footer jsp to the page -->
	<jsp:include page="footer.jsp" />
</body>
</html>