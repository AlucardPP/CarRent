<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>Rental Cars - login</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style>
body {
	padding-top: 70px;
	/* Required padding for .navbar-fixed-top. Remove if using .navbar-static-top. Change if height of navigation changes. */
}
</style>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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
							<li ><a href="CarServlet">Car</a></li>
							<li class="active"><a href="RentedServlet">Rented Cars</a></li>

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
							<li><a href="CustomerServlet">Client</a></li>
							<li><a href="CarServlet">Car</a></li>
							<li class="active"><a href="RentedServlet">Rented Cars</a></li>
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
							<li><a href="CarServlet">Car</a></li>
							<li class="active"><a href="RentedServlet">Rented Cars</a></li>
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
		<!-- /.container -->
	</nav>

	<!-- Page Content -->
	<div class="container">
		<form action="RentCar" method="post">
			<div class="row">
				<div class="menu">
					<div class="panel-group" id="accordion">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne"> +RENTED </a>
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

															<label for="employee">Employee:</label> <select class="form-control" id="employee" name="employee">
																<c:forEach items="${employeelist }" var="employee">
																	<option value="${employee.name } ${employee.surname}">${employee.name }${employee.surname}</option>
																</c:forEach>
															</select>

														</div>

													</div>
													<div class="col-md-3">

														<div class="form-group">
															<label for="fromDate">From:</label> <input type="text" class="form-control" id="from" placeholder="From:"
																name="from" required>
														</div>

													</div>
												</div>
											</div>
											<div class="bs-docs-grid">
												<div class="row show-grid">
													<div class="col-md-3">

														<div class="form-group">
															<label for="client">Client:</label> <select class="form-control" id="client" name="client">
																<c:forEach items="${clientlist }" var="client">
																	<option value="${client.name } ${client.surname}">${client.name }${client.surname }</option>
																</c:forEach>
															</select>

														</div>

													</div>
													<div class="col-md-3">

														<div class="form-group">
															<label for="tillDate">Till:</label> <input type="text" class="form-control" id="till" placeholder="Till:"
																name="till" required>
														</div>

													</div>
													<div class="col-md-3"></div>
													<div class="col-md-1">
														<input class="btn btn-md btn-primary" type="submit" value="Rent" name="rent" />
													</div>
												</div>
											</div>
											<div class="bs-docs-grid">
												<div class="row show-grid">
													<div class="col-md-6">
														<div class="form-group">
															<label for="car">Car:</label>

															<!--  <input type="hidden" value="${car.rentPerHour }"
														name="pricePerHour" />		 -->


															<select multiple class="form-control" id="car" name="cars">
																<c:forEach items="${carlist }" var="car">

																	<c:if test="${car.available eq 'yes' }">

																		<option value="${car.idCar}">${car.brand }/${car.model}/${car.engineSize}</option>

																	</c:if>
																</c:forEach>
															</select>
															<c:forEach items="${carlist }" var="car">
																<c:if test="${ param.cars == car.idCar }">
																	<input type="hidden" value="${car.rentPerHour }" name="pricePerHour" />
																</c:if>
															</c:forEach>
														</div>
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
			</div>


			<!-- /.row -->
			<table class="table table-bordered" id="thetable">

				<thead>
					<tr>
						<th>ID</th>
						<th>EMPLOYEE</th>
						<th>CLIENT</th>
						<th>CARS</th>
						<th>FROM</th>
						<th>TILL</th>
						<th>DAYS</th>
						<th>PRICE</th>
						<th>ACTIONS</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${rentedlist }" var="rented">


						<tr>
							<td id="id${rented.idRented }"><c:out value="${rented.idRented }" /></td>
							<td><c:out value="${rented.employee }" /></td>
							<td><c:out value="${rented.client }" /></td>
							<td><c:out value="${rented.cars }" /></td>
							<td><c:out value="${rented.fromDate }" /></td>
							<td><c:out value="${rented.tillDate }" /></td>
							<td><c:out value="${rented.days }" /></td>
							<td><c:out value="${rented.price }" /></td>


							<td id="buttons${rented.idRented }"><c:choose>


									<c:when test="${rented.payed == '1' }">
										<form action="Pay" method="post">

											<input class="btn btn-primary" type="submit" name="Payed" id="payed${rented.idRented }" value="Payed" /> <input
												type="hidden" name="PayedID" value="${rented.idRented }" id="${rented.idRented }" /> <input type="hidden"
												name="Car_ID" value="${rented.carId }" />
										</form>
										<form action="CancelRent" method="post">
											<input class="btn btn-md btn-primary" type="submit" id="cancel${rented.idRented }" value="Canceled"
												name="cancel" /> <input type="hidden" name="Car_ID" value="${rented.carId }" /><input type="hidden"
												name="PayedID" value="${rented.idRented }" id="${rented.idRented }" />
										</form>
									</c:when>

									<c:when test="${rented.payed == '0' && rented.rented == '1' }">
										<form action="RentedServlet" method="post">

											<input class="btn btn-md btn-primary" type="submit" value="Rented" id="rented${rented.idRented }"
												name="rented" /> <input type="hidden" name="RentedID" value="${rented.idRented }" />




										</form>
									</c:when>

									<c:when test="${rented.rented == '0' && rented.payed == '0' }">


										<input class="btn btn-md btn-primary" type="submit" value="Returned" id="returned${rented.idRented }"
											name="returned" />
										<input class="btn btn-md btn-primary" type="submit" value="Destroyed" id="destroyed${rented.idRented }"
											name="destroyed" />

									</c:when>

								</c:choose></td>


						</tr>
					</c:forEach>




				</tbody>
			</table>
		</form>
	</div>
	<!-- /.container -->

	<!-- jQuery Version 1.11.1 -->


	<script>
		/*	$(document).ready(
				function(){
					$('td[id^="buttons"]').on('click', 'input[id^="payed"]',
							function() {

								$('#thetable').find('tr').click(function() {
									var id = $(this).index();
									$.ajax({
										url: 'response.jsp',
										data: {'id':id},
										type: 'POST',
										
										success: function(data){
											$('div[id^="rentedcar"]').show();
										}
									});

								});

							});
						
					});
			
		
		
		/* $(document).ready(
					function() {
						$('div[id^="operations"]').hide();
						$('div[id^="rentedcar"]').hide();
					});
						 	$('td[id^="buttons"]').on('click', 'input[id^="payed"]',
								function() {
									$(this).parents('div[id^="choose"]').remove();

									$('#thetable').find('tr').click(function() {
										var a = $('div[id^="rentedcar"]');
										var row = $(this).index();
										$(a[row]).show();

									});

								});

					});

			/* 			
						$('div[id^="rentedcar"]').hide();
						
						$('div[id^="rentedcar"]').show();
					});
				
				
					$('input[id^="rented"]').click(function() {
						$(this).hide();
						$('div[id^="button"]').hide();
						$('div[id^="rentedcar"]').hide();
						

						$('div[id^="button"]').show();

						return false;

				
				}); */
	</script>


	<script>
		$(function() {
			$("#from").datepicker({
				dateFormat : "dd.mm.yy",
				changeYear : true,
				changeMonth : true,
				yearRange : '1900:' + (new Date).getFullYear()
			});
		});
	</script>
	<script>
		$(function() {
			$("#till").datepicker({
				dateFormat : "dd.mm.yy",
				changeYear : true,
				changeMonth : true,
				yearRange : '1900:' + (new Date).getFullYear()
			});
		});
	</script>
</body>
</html>