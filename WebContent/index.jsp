<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Car Rent</title>

<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style>
body {
	padding-top: 70px;
	/* Required padding for .navbar-fixed-top. Remove if using .navbar-static-top. Change if height of navigation changes. */
}
</style>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
					aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>

			</div>

			<div id="navbar" class="collapse navbar-collapse">

				<c:choose>
					<c:when test="${not empty sessionScope.user && user.role == 'regular' }">
						<ul class="nav navbar-nav">
							<li><a href="index.jsp">Client</a></li>
							<li class="active"><a href="car.jsp">Car</a></li>
							<li><a href="rented.jsp">Rented Cars</a></li>

						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><span
									class="glyphicon glyphicon-user"></span> ${user.username } <span class="caret"></span> </a>
								<ul class="dropdown-menu">

									<li><a href="password.jsp">Change Password</a></li>
									<form class="form-signin" action="LogOutServlet" method="post">
										<li><input type="submit" class="btn btn-block" value="Log Out"></li>
									</form>
								</ul></li>

						</ul>
					</c:when>
					<c:when test="${not empty sessionScope.user && user.role == 'admin' }">
						<ul class="nav navbar-nav">
							<li class="active"><a href="index.jsp">Client</a></li>
							<li><a href="car.jsp">Car</a></li>
							<li><a href="rented.jsp">Rented Cars</a></li>
							<li><a href="employee.jsp">Employee</a></li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><span
									class="glyphicon glyphicon-user"></span> ${user.username } <span class="caret"></span> </a>
								<ul class="dropdown-menu">

									<li><a href="password.jsp">Change Password</a></li>
									<form class="form-signin" action="LogOutServlet" method="post">
										<li><input type="submit" class="btn btn-block" value="Log Out"></li>
									</form>
								</ul></li>

						</ul>
					</c:when>
					<c:when test="${not empty sessionScope.user && user.role == 'manager' }">
						<ul class="nav navbar-nav">
							<li class="active"><a href="index.jsp">Client</a></li>
							<li><a href="car.jsp">Car</a></li>
							<li><a href="rented.jsp">Rented Cars</a></li>
							<li><a href="employee.jsp">Employee</a></li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><span
									class="glyphicon glyphicon-user"></span> ${user.username } <span class="caret"></span> </a>
								<ul class="dropdown-menu">

									<li><a href="password.jsp">Change Password</a></li>
									<form class="form-signin" action="LogOutServlet" method="post">
										<li><input type="submit" class="btn btn-block" value="Log Out"></li>
									</form>
								</ul></li>

						</ul>
					</c:when>
					<c:otherwise>
						<ul class="nav navbar-nav">
							<li><a href="login.jsp">Client</a></li>
							<li><a href="login.jsp">Car</a></li>
							<li><a href="login.jsp">Rented Cars</a></li>
							<li><a href="login.jsp">Employee</a></li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
						</ul>
					</c:otherwise>
				</c:choose>

			</div>
			<!--/.nav-collapse -->
		</div>
		</nav>
		<form action="CustomerServlet" method="post">
			<div class="menu">
				<div class="panel-group" id="accordion">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne"> +ADD CLIENT </a>
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
														<label for="clientName">Client Name:</label> <input type="text" class="form-control" id="clientName"
															placeholder="Client Name" name="name" required>
													</div>

												</div>
												<div class="col-md-3">

													<div class="form-group">
														<label for="clientSurname">Client Surname:</label> <input type="text" class="form-control"
															id="clientSurname" placeholder="Client Surname" name="surname" required>
													</div>

												</div>
												<div class="col-md-3"></div>
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
														<label for="born">Born:</label> <input type="text" class="form-control" id="born" placeholder="Born"
															name="born" required>
													</div>

												</div>
												<div class="col-md-3">

													<div class="form-group">
														<label for="idCardNumber">ID Card Number:</label> <input type="text" class="form-control"
															id="idCardNumber" placeholder="ID Card Number" name="idcardnumber" required>
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
														<label for="street">Street:</label> <input type="text" class="form-control" id="street"
															placeholder="Street" name="street" required>
													</div>

												</div>
												<div class="col-md-3">

													<div class="form-group">
														<label for="house">House Number:</label> <input type="text" class="form-control" id="house"
															placeholder="House Number" name="housenumber" required>
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
														<label for="city">City:</label> <input type="text" class="form-control" id="city" placeholder="City"
															name="city" required>
													</div>

												</div>
												<div class="col-md-3">

													<label for="country">Country code:</label> <select class="form-control" name="country">
														<c:forEach items="${countries }" var="country">
															<option value="${country.key }">${country.value }</option>
														</c:forEach>

													</select>

												</div>
												<div class="col-md-3"></div>
												<div class="col-md-2"></div>
											</div>
										</div>
										<br>
										<div class="bs-docs-grid">
											<div class="row show-grid">
												<div class="col-md-3">
													<label for="gender">Gender:</label> <select class="form-control" name="gender">
														<option value="male">Male</option>
														<option value="female">Female</option>

													</select>
												</div>
												<div class="col-md-3">

													<div class="form-group">
														<label for="telephone">Telephone:</label> <input type="text" class="form-control" id="Telephone"
															placeholder="Telephone" name="telephone" required>
													</div>

												</div>
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
													<input class="btn btn-md btn-primary" type="submit" value="Save" name="save" />
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
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>NAME</th>
						<th>SURNAME</th>
						<th>TELEPHONE</th>
						<th>CREATED</th>
						<th>EDITED</th>
						<th>DOCUMENT</th>
						<th>ACTION</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${clientlist }" var="list">
						<tr>

							<td><c:out value="${list.idCustomer}" /></td>
							<td><c:out value="${list.name }" /></td>
							<td><c:out value="${list.surname }" /></td>
							<td><c:out value="${list.telephone }" /></td>
							<td><c:out value="${list.createDate }" /></td>
							<td><c:out value="${list.edited }" /></td>
							<td>
								<form name="frm" action="CustomerServlet" method="post">
									<input class="btn btn-md btn-primary" type="submit" value="Download" name="download" /> <input type="hidden"
										name="id" value="${list.idCustomer }" />
							</td>
							<td>


								<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#${ list.idCustomer}"
									aria-expanded="false" aria-controls="id" name="edit" value="${list.idCustomer }">Edit</button> <input
								type="hidden" name="clientID" value="${list.idCustomer }" /> <input class="btn btn-md btn-primary"
								type="submit" value="Delete" name="delete" />

								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
			<c:forEach items="${clientlist }" var="client">

				<div class="collapse" id="${client.idCustomer }">


					<div class="well">



						<div class="bs-docs-grid">
							<div class="row show-grid">
								<div class="col-md-3">
									<div class="form-group">
										<form action="CustomerServlet" method="post">
											<label for="clientName">Client Name:</label> <input type="text" class="form-control" id="upclientName"
												name="upname" value="${client.name }" required>
									</div>

								</div>
								<div class="col-md-3">

									<div class="form-group">
										<label for="clientSurname">Client Surname:</label> <input type="text" class="form-control"
											id="upclientSurname" value="${client.surname }" name="upsurname" required>
									</div>

								</div>
								<div class="col-md-3"></div>
								<div class="col-md-2">
									<div class="form-group">
										<label for="uploadFile">Upload File</label> <input type="file" id="uploadFile">
									</div>
								</div>
							</div>
						</div>
						<br>
						<div class="bs-docs-grid">
							<div class="row show-grid">
								<div class="col-md-3">

									<div class="form-group">
										<label for="born">Born:</label> <input type="text" class="form-control" id="upborn" value="${client.born }"
											name="upborn" required>
									</div>

								</div>
								<div class="col-md-3">

									<div class="form-group">
										<label for="idCardNumber">ID Card Number:</label> <input type="text" class="form-control" id="upidCardNumber"
											value="${client.idCardNumber }" name="upidcardnumber" required>
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
										<label for="street">Street:</label> <input type="text" class="form-control" id="upstreet"
											value="${client.street }" name="upstreet" required>
									</div>

								</div>
								<div class="col-md-3">

									<div class="form-group">
										<label for="house">House Number:</label> <input type="text" class="form-control" id="uphouse"
											value="${client.houseNumber }" name="uphousenumber" required>
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
										<label for="city">City:</label> <input type="text" class="form-control" id="upcity" value="${client.city }"
											name="upcity" required>
									</div>

								</div>
								<div class="col-md-3">

									<label for="upcountry">Country code:</label> <select class="form-control" name="upcountry">

										<c:forEach items="${countries }" var="country">
											<c:if test="${client.country eq country.key }">
												<option value="${country.key }">${country.value }</option>
											</c:if>
										</c:forEach>
										<c:forEach items="${countries }" var="country">
											<option value="${country.key }">${country.value }</option>
										</c:forEach>





									</select>

								</div>
								<div class="col-md-3"></div>
								<div class="col-md-2"></div>
							</div>
						</div>
						<br>
						<div class="bs-docs-grid">
							<div class="row show-grid">
								<div class="col-md-3">
									<label for="upgender">Gender:</label> <select class="form-control" name="upgender">
										<c:choose>
											<c:when test="${client.gender eq 'male' }">
												<option selected value="male">Male</option>
											</c:when>
											<c:otherwise>
												<option value="female">Female</option>
											</c:otherwise>

										</c:choose>
									</select>
								</div>
								<div class="col-md-3">

									<div class="form-group">
										<label for="uptelephone">Telephone:</label> <input type="text" class="form-control" id="upTelephone"
											value="${client.telephone }" name="uptelephone" required>
									</div>

								</div>
								<div class="col-md-3"></div>
								<div class="col-md-2"></div>
							</div>
						</div>
						<br>
						<div class="bs-docs-grid">
							<div class="row show-grid">
								<div class="col-md-9"></div>
								<div class="col-md-1">
									<button class="btn btn-md btn-primary" type="reset" name="upcancel" data-toggle="collapse"
										data-target="#${client.idCustomer }">Cancel</button>
								</div>
								<div class="col-md-1">

									<input type="hidden" name="idClient123" value="${client.idCustomer }" /> <input class="btn btn-md btn-primary"
										type="submit" value="Update" name="update" />
		</form>
	</div>


	</div>
	</div>
	</div>
	</div>

	</c:forEach>
	</form>




	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<!-- <script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> -->
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"
		integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script>
		$(function() {
			$("#born").datepicker({
				dateFormat : "dd.mm.yy",
				changeYear : true,
				changeMonth : true,
				yearRange : '1900:' + (new Date).getFullYear()
			});
		});
	</script>

</body>
</html>