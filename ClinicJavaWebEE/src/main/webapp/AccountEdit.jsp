<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Account | Clinic Hub</title>

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
	
	<div class='fixed-top-body pb-5'>
		<div class='container mt-2'>
			<div class="row no-gutter g-0">
				<div class="col-md-12">
					<div class="d-flex align-items-center">
						<div class="container py-3 px-3">
							<div class="row text-dark">
								<div class="col-lg-6 col-xl-7 mx-auto bg-light py-5 px-5 border shadow border-rounded">
									<h2>
										<i class="fas fa-plus-square pr-2 text-primary"></i> <b>Edit Account</b>
									</h2>


								<p class="text-muted mb-4">Update Your Credentials To
										Continue</p>
									<form autoComplete="off" noValidate action=''
										method="post">
										<div class="form-group mb-3">
											<input id="inputUsername" type="text" placeholder="Username"
												name="username"
												class="form-control border-0 shadow-sm px-4 py-4" required readonly/>

										</div>
										<div class="form-group mb-3">
											<input id="inputFullName" type="text" placeholder="Full Name"
												name="full_name"
												class="form-control border-0 shadow-sm px-4 py-4" required />

										</div>

										<div class='row'>
											<div class='col-lg-6'>
												<div class="form-group mb-3">
													<input id="inputEmail" type="text" placeholder="Email"
														name="email"
														class="form-control border-0 shadow-sm px-4 py-4" required />


												</div>

											</div>
											<div class='col-lg-6'>
												<div class="form-group mb-3">
													<input id="inputContact" type="text"
														placeholder="Contact Number" name="contact_number"
														class="form-control border-0 shadow-sm px-4 py-4" required />

												</div>
											</div>
										</div>
										<div class="form-group mb-3 d-none">
											<input id="inputFullName" type="text" placeholder="User Role"
												name="role"
												class="form-control border-0 shadow-sm px-4 py-4" required readonly/>

										</div>
										
										<div class="form-group mb-3 d-none" >
											<input id="inputUsername" type="password" placeholder="Password"
												name="password"
												class="form-control border-0 shadow-sm px-4 py-4" required readonly/>

										</div>
									

										<button type='submit'
											class="btn btn-primary py-3 btn-block text-uppercase mb-2 shadow-sm w-100">
											Confirm & Update</button>
								
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