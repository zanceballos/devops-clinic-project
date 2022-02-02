
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Review | ClinicHub</title>
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
		<div class='container'>
			

			<form action="ReviewClinic" method="post">
				<div class="row no-gutter g-0 rounded-lg">

					<div class="col-md-12">
						<div class="login d-flex align-items-center py-5">
							<div class="container py-3 px-3">
								<div class="row text-dark">
									<div class="col-lg-6 col-xl-7 mx-auto shadow py-5 px-5 bg-light">
										<h2>
											<i class="fas fa-comments text-primary pr-2"></i><b>Create Review</b>
										</h2>

										<div class='row'>
											<div class='col-lg-12'>
												<div class="form-group mb-3">
													<input id="inputClinicID" type="text"
														placeholder="Clinic ID" required 
														value="${clinicid}"
														name="clinicid"
														class="form-control border-0 shadow-sm px-4 py-4" readonly />

												</div>

											</div>
											
										</div>


										<div class="form-group mb-3">
											<input id="inputReview" type="text"
												placeholder="Title of Review" required
												name="review_title"
												class="form-control border-0 shadow-sm px-4 py-4 " />

										</div>

										<div class="form-group mb-3">
											<input id="inputReviewText" type="text"
												placeholder="Review of clinic" required
												name="review"
												class="form-control border-0 shadow-sm px-4 py-4 " />

										</div>

										<div class="form-group mb-3">
											<input id="inputReviewScore" type="number"
												placeholder="Rating out of 5" required name="rating_score"
												class="form-control border-0 shadow-sm px-4 py-4 "
												 />

										</div>

										<c:if test="${score_error == true}">
											<div class="alert alert-danger py-3" role="alert">
												<i class="fas fa-exclamation-circle pr-2"></i><span>Enter Up to Rating Score of 5 only!</span>
											</div>
											<c:set var="score_error" value="" scope="session" />
											
										</c:if>


										<button type='submit'
											class="btn btn-primary py-3 btn-block text-uppercase mb-2 shadow-sm w-100">
											Create Review</button>
										


									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>

		</div>
	</div>


</body>
</html>