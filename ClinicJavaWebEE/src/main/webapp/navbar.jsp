
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title></title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
	integrity="sha512-Fo3rlrZj/k7ujTnHg4CGR2D7kSs0v4LLanw2qksYuRlEzO+tcaEPQogQ0KaoGN26/zrn20ImR1DfuLWnOo7aBA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/styles.css"
	type="text/css">

</head>
<body>
	<nav
		class="navbar navbar-expand-lg navbar-light shadow fixed-top bg-white">
		<c:if test="${logged_in == null}">
			<a href="<%=request.getContextPath()%>" class="navbar-brand"> <img
				src='${pageContext.request.contextPath}/assetsimg/devops-clinic-logo.png'
				height='45' />
			</a>
		</c:if>

		<c:if test="${logged_in == true}">
			<c:if test="${role == 'patient' }">
				<a href="<%=request.getContextPath()%>/PatientHome.jsp"
					class="navbar-brand"> <img
					src='${pageContext.request.contextPath}/assetsimg/devops-clinic-logo.png'
					height='45' />
				</a>
			</c:if>
			<c:if test="${role == 'doctor' }">
				<a href="<%=request.getContextPath()%>/DoctorHome.jsp"
					class="navbar-brand"> <img
					src='${pageContext.request.contextPath}/assetsimg/devops-clinic-logo.png'
					height='45' />
				</a>
			</c:if>
		</c:if>

		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">



				<c:if test="${logged_in == true}">
					<c:if test="${role == 'patient' }">
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/PatientHome.jsp"><b>Home</b>
						</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/ClinicServlet/all-clinics"><b>Clinics</b>
						</a></li>

						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/AppointmentServlet/PatientAppointments?userid=${id}"><b>Appointments</b></a></li>
					</c:if>

					<c:if test="${role == 'doctor' }">
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/DoctorHome.jsp"><b>Home</b>
						</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/ClinicServlet/dashboard"><b>Clinics</b>
						</a></li>

						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/ClinicServlet/doctor-all-clinics"><b>Appointments</b></a></li>
					</c:if>


				</c:if>




				<c:if test="${logged_in == null}">
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>"><b>Home</b> </a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/ClinicServlet/all-clinics"><b>Clinics</b>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>#features"><b>Features</b></a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>#about"><b>About</b></a></li>
				</c:if>

			</ul>
			<div class="form-inline my-2 my-lg-0">
				<c:if test="${logged_in == null}">

					<a href="<%=request.getContextPath()%>/login.jsp"
						class="btn btn-primary my-2 my-sm-0 px-5 mr-2 pr-2 rounded-pill"
						type="submit"><b>Login</b> </a>
					<a href="<%=request.getContextPath()%>/register.jsp"
						class="btn btn-outline-primary my-2 my-sm-0 px-5 rounded-pill"
						type="submit"><b>Register</b></a>
				</c:if>
				<c:if test="${logged_in != null}">
			
						<div class="dropdown">
							<button class="btn btn-primary rounded-pill px-5 dropdown-toggle"
								type="button" id="dropdownMenuButton" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false">
								<i class="fas fa-user-circle mr-2"></i> <span>${username}</span>
							</button>

							<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
								<a class="dropdown-item" href="<%=request.getContextPath()%>/AccountPage.jsp">Account</a>
								<form action='/ClinicJavaWebEE/UserServlet/logout' method='post'>
									<button type='submit' class="dropdown-item">Logout</button>
								</form>
							</div>


						</div>

				
				

				</c:if>
			</div>
		</div>
	</nav>



</body>
</html>