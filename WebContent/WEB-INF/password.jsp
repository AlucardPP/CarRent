<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Rental Car</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style>
body {
	padding-top: 70px;
}
</style>
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
							<li><a href="CustomerServlet">Client</a></li>
							<li><a href="CarServlet">Car</a></li>
							<li><a href="RentedServlet">Rented Cars</a></li>

						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><span
									class="glyphicon glyphicon-user"></span> ${user.username } <span class="caret"></span> </a>
								<ul class="dropdown-menu">

									<li><a href="#">Change Password</a></li>
									<form class="form-signin" action="LogOutServlet" method="post">
										<li><input type="submit" class="btn btn-block" value="Log Out"></li>
									</form>
								</ul></li>

						</ul>
					</c:when>
					<c:when test="${not empty sessionScope.user && user.role == 'admin' }">
						<ul class="nav navbar-nav">
							<li><a href="CustomerServlet">Client</a></li>
							<li><a href="CarServlet">Car</a></li>
							<li><a href="RentedServlet">Rented Cars</a></li>
							<li><a href="EmployeeServlet">Employee</a></li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><span
									class="glyphicon glyphicon-user"></span> ${user.username } <span class="caret"></span> </a>
								<ul class="dropdown-menu">

									<li><a href="#">Change Password</a></li>
									<form class="form-signin" action="LogOutServlet" method="post">
										<li><input type="submit" class="btn btn-block" value="Log Out"></li>
									</form>
								</ul></li>

						</ul>
					</c:when>
					<c:when test="${not empty sessionScope.user && user.role == 'manager' }">
						<ul class="nav navbar-nav">
							<li><a href="CustomerServlet">Client</a></li>
							<li><a href="CarServlet">Car</a></li>
							<li><a href="RentedServlet">Rented Cars</a></li>
							<li><a href="EmployeeServlet">Employee</a></li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><span
									class="glyphicon glyphicon-user"></span> ${user.username } <span class="caret"></span> </a>
								<ul class="dropdown-menu">

									<li><a href="#">Change Password</a></li>
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
		</div>
		</nav>

		<div class="container">
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<form class="form-signin" action="PasswordServlet" id="changePassword" method="post">
					<h2 class="form-signin-heading">Change Password</h2>
					<input name="old_password" type="password" class="form-control" placeholder="Old Password" id="old" required
						autofocus> <input name="new_password" type="password" class="form-control" placeholder="New Password"
						id="newPassword" required> <input name="repeat_password" type="password" class="form-control"
						placeholder="Repeat Password" id="repeat" equalTo= "#newPassword" required>
					<div class="btn-group btn-group-justified">
						<div class="btn-group">

							<input class="btn btn-primary" type="submit" value="Save" name="save" /> <input type="hidden" name="id"
								value="${ user.user_id}" />

						</div>
						<div class="btn-group">
							<input class="btn btn-primary " type="submit" value="Cancel" name="cancel" />
						</div>
					</div>
				</form>
			</div>
		</div>



		<script src="https://code.jquery.com/jquery-3.2.1.min.js"
			integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/jquery.validate.js"></script>
		<script>
			$(document).ready(function() {
				$("#changePassword").validate({
					rules : {
						old : "required",
						newPassword : {
							required : true
						},
						repeat : {
							requeired : true,
							equalTo : "#newPassword"
						}
					},
					messages : {
						old : "Please enter you password",
						password : "Please provide password",
						repeat : {
							required : "Please provide password",
							equalTo : "Please enter the same password as above"
						}
					}

				});
			});
		</script>
</body>

</html>