
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

	<<div class='fixed-top-body'>
		<div class='container'>
			<div class='d-flex justify-content-between py-2'>
				<div class='header'>
					<a 	href="<%=request.getContextPath()%>/AppointmentServlet/ClinicAppointments?clinicid=${clinicid}" class='btn btn-primary shadow px-4 py-2 text-white rounded-pill'><i
									class="fas fa-arrow-left pr-2"></i><b>Back To Appointments</b> </a>
					<h3 class='mt-4'>
						<b>Clinic Reviews</b>
					</h3>
				</div>
				
			</div>

			<div class='reviews py-3'>
				<c:forEach var="review" items="${reviews}">

					<div class='card shadow-sm mb-2'>
						<div class='card-body py-4'>
							<div class='stars float-right'>
								<c:forEach begin="1" end="${review.rating_score }"
									varStatus="loop">
									<i class="fas fa-star text-warning"></i>
								</c:forEach>
							</div>
							<div class="d-flex flex-start">
								<h1 class='pr-3'>
									<i class="fas fa-user-circle fa-2x text-primary"></i>
								</h1>
								<div>
									<div class='d-flex justify-content-between'>
										<h6 class="mb-1">
											<b><c:out value="${review.username }"></c:out></b> <span
												class="badge bg-primary text-white py-1 pl-2 pr-2">Patient</span>
										</h6>

									</div>

									<div class="d-flex align-items-center mb-1">
										<p class="mb-0 text-muted">
											<c:out value="${review.email }"></c:out>

										</p>

									</div>
									<h4 class="mb-0">
										<b><c:out value="${review.review_title }"></c:out></b>
									</h4>
									<p class="mb-0">
										<c:out value="${review.review }"></c:out>
									</p>



								</div>
							</div>
						</div>


					</div>



				</c:forEach>




			</div>


			<c:if test="${empty reviews}">
				<div class='empty-icon text-center py-5'>
					<img src="${pageContext.request.contextPath}/assetsimg/empty.svg"
						width="250" class='' alt="" />
					<p class='pt-5'>
						<b>Nothing To See Here!</b>
					</p>
				</div>
			</c:if>




		</div>

	</div>
	

	<jsp:include page="footer.jsp" />
</body>
</html>