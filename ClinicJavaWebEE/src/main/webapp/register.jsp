
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register | Clinic Hub</title>

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
		<div class='container text-center py-5'>
			<div class="row no-gutter g-0 shadow rounded-lg">

				<div class="col-md-12 bg-light">
					<div class="login d-flex align-items-center py-5">
						<div class="container py-3 px-3">
							<div class="row text-dark">
								<div class="col-lg-10 col-xl-7 mx-auto">
									<h2>
										<b>Register</b>
									</h2>

									<p class="text-muted mb-4">Enter Your Credentials To
										Continue</p>
									<form autoComplete="off" noValidate>
										<div class="form-group mb-3">
											<input id="inputUsername" type="text" placeholder="Username"
												name="username" required=""
												class="form-control border-0 shadow-sm px-4 py-4"
												onChange={handleInputChange} />

										</div>
										<div class="form-group mb-3">
											<input id="inputFullName" type="text" placeholder="Full Name"
												required="" name="full_name"
												class="form-control border-0 shadow-sm px-4 py-4"
												onChange={handleInputChange} />

										</div>

										<div class='row'>
											<div class='col-lg-6'>
												<div class="form-group mb-3">
													<input id="inputEmail" type="text" placeholder="Email"
														required="" name="email"
														class="form-control border-0 shadow-sm px-4 py-4"
														onChange={handleInputChange} />


												</div>

											</div>
											<div class='col-lg-6'>
												<div class="form-group mb-3">
													<input id="inputContact" type="text"
														placeholder="Contact Number" required=""
														name="contact_number"
														class="form-control border-0 shadow-sm px-4 py-4"
														onChange={handleInputChange} />

												</div>
											</div>
										</div>


										<div class="form-group mb-3">
											<input id="inputPassword" type="password"
												placeholder="Password" required="" name="password"
												ref={password}
												class="form-control border-0 shadow-sm px-4 py-4 text-primary"
												onChange={handleInputChange} />

										</div>

										<div class="form-group mb-3">
											<input id="inputPassword" type="password"
												placeholder="Confirm Password" required=""
												name="cfmpassword"
												class="form-control border-0 shadow-sm px-4 py-4 text-primary" />
										</div>
										<button
											class="btn btn-primary py-3 btn-block text-uppercase mb-2 shadow-sm w-100">
											Confirm & Register</button>
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
										class="btn btn-outline-primary btn-block text-uppercase mb-2 shadow-sm w-100">
										Login to existing account</button>
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