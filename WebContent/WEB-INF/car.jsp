<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Car</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">


<!-- Custom CSS -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style>
body {
	padding-top: 70px;
	/* Required padding for .navbar-fixed-top. Remove if using .navbar-static-top. Change if height of navigation changes. */
}
</style>



<script src="https://code.jquery.com/jquery-3.2.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


<script src="js/bootstrap.min.js"></script>
</head>

<body>

	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>

		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

			<c:choose>
				<c:when test="${not empty sessionScope.user && user.role == 'regular' }">
					<ul class="nav navbar-nav">
						<li><a href="CustomerServlet">Client</a></li>
						<li class="active"><a href="CarServlet">Car</a></li>
						<li><a href="RentedServlet">Rented Cars</a></li>

					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><span
								class="glyphicon glyphicon-user"></span> ${user.username } <span class="caret"></span> </a>
							<ul class="dropdown-menu">

								<li><a href="PasswordServlet">Change Password</a></li>
								<form class="form-signin" action="LogOutServlet" method="post">
									<li><input type="submit" class="btn btn-block" value="Log Out"></li>
								</form>
							</ul></li>

					</ul>
				</c:when>
				<c:when test="${not empty sessionScope.user && user.role == 'admin' }">
					<ul class="nav navbar-nav">
						<li><a href="CustomerServlet">Client</a></li>
						<li class="active"><a href="CarServlet">Car</a></li>
						<li><a href="RentedServlet">Rented Cars</a></li>
						<li><a href="EmployeeServlet">Employee</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><span
								class="glyphicon glyphicon-user"></span> ${user.username } <span class="caret"></span> </a>
							<ul class="dropdown-menu">

								<li><a href="PasswordServlet">Change Password</a></li>
								<form class="form-signin" action="LogOutServlet" method="post">
									<li><input type="submit" class="btn btn-block" value="Log Out"></li>
								</form>
							</ul></li>

					</ul>
				</c:when>
				<c:when test="${not empty sessionScope.user && user.role == 'manager' }">
					<ul class="nav navbar-nav">
						<li><a href="CustomerServlet">Client</a></li>
						<li class="active"><a href="CarServlet">Car</a></li>
						<li><a href="RentedServlet">Rented Cars</a></li>
						<li><a href="EmployeeServlet">Employee</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><span
								class="glyphicon glyphicon-user"></span> ${user.username } <span class="caret"></span> </a>
							<ul class="dropdown-menu">

								<li><a href="PasswordServlet">Change Password</a></li>
								<form class="form-signin" action="LogOutServlet" method="post">
									<li><input type="submit" class="btn btn-block" value="Log Out"></li>
								</form>
							</ul></li>

					</ul>
				</c:when>
				<c:otherwise>
					<ul class="nav navbar-nav">
						<li><a href="LoginServlet">Client</a></li>
						<li><a href="LoginServlet">Car</a></li>
						<li><a href="LoginServlet">Rented Cars</a></li>
						<li><a href="LoginServlet">Employee</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="LoginServlet"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
					</ul>
				</c:otherwise>
			</c:choose>

		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container --> </nav>

	<!-- Page Content -->
	<div class="container">
		<form action="AddCarServlet" method="post" enctype="multipart/form-data">

			<div class="menu">
				<div class="panel-group" id="accordion">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne"> +ADD CAR </a>
							</h4>
						</div>
						<c:choose>
							<c:when test="${not empty sessionScope.user && user.role =='admin' }">
							</c:when>
							<c:otherwise>
								<div id="collapseOne" class="panel-collapse collapse">
									<div class="panel-body">
										<div class="bs-docs-grid">
											<div class="row show-grid">
												<div class="col-md-3">

													<div class="form-group">
														<label for="brand">Car Brand:</label> <input type="text" class="form-control" id="brand"
															placeholder="Brand" name="brand" required>
													</div>

												</div>
												<div class="col-md-3">

													<div class="form-group">
														<label for="model">Car model:</label> <input type="text" class="form-control" id="model"
															placeholder="Model" name="model" required>
													</div>

												</div>
												<div class="col-md-3">
													<div class="form-group">
														<div class="form-group">
															<label for="plate">Car plate:</label> <input type="text" class="form-control" id="plate"
																placeholder="Car Plate" name="plate" required>
														</div>
													</div>
												</div>
												<div class="col-md-2">
													<div class="form-group">

														<input type="file" name="files" multiple="true" />

													</div>
												</div>


											</div>
										</div>
										<br>
										<div class="bs-docs-grid">
											<div class="row show-grid">
												<div class="col-md-3">

													<div class="form-group">
														<label for="produced">Produced:</label> <input type="text" class="form-control" id="produced"
															placeholder="Produced year" name="produced" required />
													</div>

												</div>
												<div class="col-md-3">

													<div class="form-group">
														<label for="firstRegistration">First Registration:</label> <input type="text" class="form-control"
															id="firstRegistration" placeholder="First Registration Date" name="firstregistration" required>
													</div>

												</div>
												<div class="col-md-3"></div>
												<div class="col-md-2"></div>
											</div>
										</div>
										<br>
										<div class="bs-docs-grid">
											<div class="row show-grid">
												<div class="col-md-3">

													<div class="form-group">
														<label for="engine">Engine Size:</label> <input type="text" class="form-control" id="engine"
															placeholder="Engine Size" name="engine" required>
													</div>

												</div>
												<div class="col-md-3">

													<div class="form-group">
														<label for="value">Value:</label> <input type="text" class="form-control" id="value" placeholder="Value"
															name="value" required>
													</div>

												</div>
												<div class="col-md-3"></div>
												<div class="col-md-2"></div>
											</div>
										</div>
										<br>
										<div class="bs-docs-grid">
											<div class="row show-grid">
												<div class="col-md-3">

													<div class="form-group">
														<label for="rentPerHour">Rent Price Per Hour:</label> <input type="text" class="form-control"
															id="rentperhour" placeholder="Rent Price Per Hour" name="rentperhour" required>
													</div>

												</div>
												<div class="col-md-3">

													<div class="form-group">
														<label for="distance">Distance mode:</label> <input type="text" class="form-control" id="distance"
															placeholder="Distance mode" name="distance" required>
													</div>

												</div>
												<div class="col-md-3"></div>
												<div class="col-md-2"></div>
											</div>
										</div>
										<br>
										<div class="bs-docs-grid">
											<div class="row show-grid">
												<div class="col-md-3">
													<div class="checkbox">
														<label> <input type="checkbox" id="visible" value="yes" name="available" />Available <input
															type="hidden" id="notvisible" value="no" name="available" required />

														</label>
													</div>
												</div>
												<div class="col-md-3"></div>
												<div class="col-md-3"></div>
												<div class="col-md-2"></div>
											</div>
										</div>
										<br>
										<div class="bs-docs-grid">
											<div class="row show-grid">
												<div class="col-md-9"></div>
												<div class="col-md-1">
													<button class="btn btn-md btn-primary" type="reset" name="cancel" data-toggle="collapse"
														data-target="#collapseOne">Cancel</button>

												</div>
												<div class="col-md-1">
													<form action="AddCarServlet" method="post" enctype="multipart/form-data">
														<input class="btn btn-md btn-primary" type="submit" value="Save" name="save" />
													</form>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>

		</form>
		<!-- /.row -->
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>ID</th>
					<th>BRAND</th>
					<th>MODEL</th>
					<th>PRICE PER HOUR</th>
					<th>AVAILABLE</th>
					<th>DOCUMENT</th>
					<th>ACTION</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${carlist }" var="list">
					<tr>

						<td><c:out value="${list.idCar}" /></td>
						<td><c:out value="${list.brand }" /></td>
						<td><c:out value="${list.model }" /></td>
						<td><c:out value="${list.rentPerHour }" /></td>
						<td><c:out value="${list.available }" /></td>
						<c:choose>
							<c:when test="${not empty sessionScope.user && user.role =='admin' }">
							</c:when>
							<c:otherwise>
								<form name="frm" action="download" method="post" enctype="multipart/form-data">
									<td><input class="btn btn-md btn-primary" type="submit" value="Download" name="download" /><input
										type="hidden" name="carPlate" value="${list.plate }" /></td>
									<td>
								</form>
								<form name="frm" action="DeleteCarServlet" method="post" enctype="multipart/form-data">
									<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#${ list.idCar}"
										aria-expanded="false" aria-controls="id" name="edit" value="${list.idCar }">Edit</button>
									<input type="hidden" name="carID" value="${list.idCar }" /> <input class="btn btn-md btn-primary"
										type="submit" value="Delete" name="delete" />
								</form>
								</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:forEach items="${carlist }" var="car">
			<div class="collapse" id="${car.idCar }">
				<div class="well">
					<form action="UpdateCarServlet" method="post" enctype="multipart/form-data">
						<div class="bs-docs-grid">

							<div class="row show-grid">

								<div class="col-md-3">

									<div class="form-group">
										<label for="upbrand">Car Brand:</label> <input type="text" class="form-control" id="upbrand"
											value="${car.brand }" name="upbrand" required>
									</div>
								</div>
								<div class="col-md-3">

									<div class="form-group">
										<label for="upmodel">Car model:</label> <input type="text" class="form-control" id="upmodel"
											value="${car.model }" name="upmodel" required>
									</div>

								</div>
								<div class="col-md-3">
									<div class="form-group">
										<div class="form-group">
											<label for="upplate">Car plate:</label> <input type="text" class="form-control" id="upplate"
												value="${car.plate }" name="upplate" required>
										</div>
									</div>
								</div>
								<div class="col-md-2">
									<div class="form-group">

										<input type="file" name="upfiles" multiple="true" />

									</div>
								</div>
							</div>
						</div>
						<br>
						<div class="bs-docs-grid">
							<div class="row show-grid">
								<div class="col-md-3">

									<div class="form-group">
										<label for="upproduced">Produced:</label> <input type="text" class="form-control" id="upproduced"
											value="${car.produced }" name="upproduced" required />
									</div>

								</div>
								<div class="col-md-3">

									<div class="form-group">
										<label for="upfirstRegistration">First Registration:</label> <input type="text" class="form-control"
											id="upfirstRegistration" value="${car.firstRegistration }" name="upfirstregistration" required>
									</div>

								</div>
								<div class="col-md-3"></div>
								<div class="col-md-2"></div>
							</div>
						</div>
						<br>
						<div class="bs-docs-grid">
							<div class="row show-grid">
								<div class="col-md-3">

									<div class="form-group">
										<label for="upengine">Engine Size:</label> <input type="text" class="form-control" id="upengine"
											value="${car.engineSize }" name="upengine" required>
									</div>

								</div>
								<div class="col-md-3">

									<div class="form-group">
										<label for="upvalue">Value:</label> <input type="text" class="form-control" id="upvalue" value="${car.value }"
											name="upvalue" required>
									</div>

								</div>
								<div class="col-md-3"></div>
								<div class="col-md-2"></div>
							</div>
						</div>
						<br>
						<div class="bs-docs-grid">
							<div class="row show-grid">
								<div class="col-md-3">

									<div class="form-group">
										<label for="uprentPerHour">Rent Price Per Hour:</label> <input type="text" class="form-control"
											id="uprentperhour" value="${car.rentPerHour }" name="uprentperhour" required>
									</div>

								</div>
								<div class="col-md-3">

									<div class="form-group">
										<label for="updistance">Distance mode:</label> <input type="text" class="form-control" id="updistance"
											value="${car.distance }" name="updistance" required>
									</div>

								</div>
								<div class="col-md-3"></div>
								<div class="col-md-2"></div>
							</div>
						</div>
						<br>
						<div class="bs-docs-grid">
							<div class="row show-grid">
								<div class="col-md-3">
									<div class="checkbox">
										<label> <c:choose>
												<c:when test="${car.available eq 'yes' }">
													<input type="checkbox" id="visible" value="yes" name="upavailable" checked />Available
												
											</c:when>
												<c:otherwise>
													<input type="checkbox" id="visible" value="yes" name="upavailable" />Available
												
												
											</c:otherwise>
											</c:choose> <input type="hidden" id="notvisible" value="no" name="upavailable" />
										</label>
									</div>
								</div>
								<div class="col-md-3"></div>
								<div class="col-md-3"></div>
								<div class="col-md-2"></div>
							</div>
						</div>
						<br>
						<div class="bs-docs-grid">
							<div class="row show-grid">
								<div class="col-md-9"></div>
								<div class="col-md-1">

									<button class="btn btn-md btn-primary" type="reset" name="cancel" data-toggle="collapse"
										data-target="#${car.idCar }">Cancel</button>

								</div>
								<div class="col-md-1">
									<input type="hidden" name="IDcar" value="${car.idCar }" /> <input class="btn btn-md btn-primary" type="submit"
										value="Update" name="update" />
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</c:forEach>
	</div>
	<!-- /.container -->


	<script>
		if (document.getElementById("visible").checked) {
			document.getElementById('notvisible').disabled = true;
		}
	</script>


</body>
</html>