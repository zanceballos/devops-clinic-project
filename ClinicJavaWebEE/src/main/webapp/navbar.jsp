
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

</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light shadow-sm bg-white">
		<a class="navbar-brand" href="#"> <b>ClinicHub</b>
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
			<li class="nav-item"><a class="nav-link" href="#">Home
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Clinics
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Appointments</a></li>
				<li class="nav-item"><a class="nav-link" href="#">About</a></li>
	
			</ul>
			<div class="form-inline my-2 my-lg-0">
				<button class="btn btn-primary my-2 my-sm-0 px-5 mr-2 pr-2 rounded-pill" type="submit"><b>Login</b> </button>
				<button class="btn btn-outline-primary my-2 my-sm-0 px-5 rounded-pill" type="submit"><b>Register</b></button>
			</div>
		</div>
	</nav>
</body>
</html>