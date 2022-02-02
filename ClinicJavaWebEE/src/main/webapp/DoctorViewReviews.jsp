
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Reviews | Clinic Hub</title>
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
				<div class='header'>
					<h3>
						<b>Clinic Reviews</b>
					</h3>
					<p class='text-muted'>
						<b>All Review for: Clinic Name </b>
					</p>
				</div>

				<div class='review py-3'>

					<div class='card shadow-sm'>
						<div class='card-body'>
							<div class="d-flex flex-start">
								<img class="rounded-circle shadow-sm mr-3"
									src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/img%20(23).webp"
									alt="avatar" width="60" height="60" />
								<div>
									<h6 class="mb-1">
										<b>Maggie Marsh</b>
									</h6>
									<div class="d-flex align-items-center mb-3">
										<p class="mb-0">
											March 07, 2021 <span class="badge bg-primary text-white py-1">Verified</span>
										</p>
	
									</div>
									<p class="mb-0">Lorem Ipsum is simply dummy text of the
										printing and typesetting industry. Lorem Ipsum has been the
										industry's standard dummy text ever since the 1500s, when an
										unknown printer took a galley of type and scrambled it.</p>
										
									<div class='buttons'>
										<a class='btn btn-primary px-5 py-2 text-white float-right'><b>Options</b></a>
									</div>
								</div>
							</div>
						</div>


					</div>


				</div>
			</div>
		</div>


		<jsp:include page="footer.jsp" />
</body>
</html>