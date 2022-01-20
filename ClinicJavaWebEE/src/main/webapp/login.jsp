
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login | Clinic Hub</title>

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

	<div class='fixed-top-body py-5'>
		<div class='container text-center'>
			<div class="row no-gutter g-0 ">
				<div class="col-md-12">
					<div class="login d-flex align-items-center py-5">
						<div class="container py-3 px-3">
							<div class="row text-dark">
								<div
									class="col-lg-6 col-xl-7 mx-auto px-5 py-5 bg-light border shadow border-rounded">
									<img class='py-5'
										src="./assetsimg/devops-clinic-logo-trans.png">
									<h2>
										<b>Login</b>
									</h2>

									<p class="text-muted mb-4">Enter Your Credentials To
										Continue</p>
									<form action="UserServlet/login" method="post"
										autoComplete="off" noValidate>
										<div class="form-group mb-3">
											<input id="inputUsername" type="text" name="username"
												placeholder="Username"
												class="form-control border-0 shadow-sm px-4 py-4" />
										</div>

										<div class="form-group mb-3">
											<input id="inputPassword" type="password" name="password"
												placeholder="Password"
												class="form-control border-0 shadow-sm px-4 py-4 text-primary" />
										</div>

										<c:if test="${username_err == true || password_err == true}">
											<div class="alert alert-danger py-3" role="alert">
												Username or Password is incorrect!</div>
											<c:set var="username_err" value="" scope="session" />
												<c:set var="password_err" value="" scope="session" />
										</c:if>

										<button type="submit"
											class="btn btn-primary py-3 btn-block text-uppercase mb-2 shadow-sm w-100">
											Sign in</button>


										<div class="row justify-content-center">
											<div class="col-sm-4">
												<hr />
											</div>
											or
											<div class="col-sm-4">
												<hr />
											</div>
										</div>
									</form>

									<button
										class="btn btn-outline-primary btn-block text-uppercase 										mb-2  shadow-sm w-100">
										Create an Account</button>
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