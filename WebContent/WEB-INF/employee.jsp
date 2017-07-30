<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Employee</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style>
body {
	padding-top: 70px;
	/* Required padding for .navbar-fixed-top. Remove if using .navbar-static-top. Change if height of navigation changes. */
}
</style>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


</head>

<body>
	<div class="container">
		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse">

				<c:choose>
					<c:when test="${not empty sessionScope.user }">
						<ul class="nav navbar-nav">
							<li><a href="CustomerServlet">Client</a></li>
							<li><a href="CarServlet">Car</a></li>
							<li><a href="RentedServlet">Rented Cars</a></li>
							<li class="active"><a href="EmployeeServlet">Employee</a></li>
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

			<form action="AddEmployee" method="post" enctype="multipart/form-data">
				<div class="menu">
					<div class="panel-group" id="accordion">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion" href="#addEmployee"> +ADD EMPLOYEE </a>
								</h4>
							</div>

							<div id="addEmployee" class="panel-collapse collapse">
								<div class="panel-body">
									<div class="bs-docs-grid">
										<div class="row show-grid">
											<div class="col-md-3">

												<div class="form-group">
													<label for="employeeName">Employee Name:</label> <input type="text" class="form-control" id="employeeName"
														placeholder="Employee Name" name="name" required>
												</div>

											</div>
											<div class="col-md-3">

												<div class="form-group">
													<label for="employeeSurname">Employee Surname:</label> <input type="text" class="form-control"
														id="employeeSurname" placeholder="Employee Surname" name="surname" required>
												</div>

											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label for="education">Education:</label> <select class="form-control" name="education">
														<option value="basic">Basic</option>
														<option value="high school">High School</option>
														<option value="bachelor">Bachelor</option>
														<option value="engineer">Engineer</option>
														<option value="master degree">Master degree</option>
														<option value="none">None</option>
													</select>
												</div>
											</div>
											<div class="col-md-2">
												<div class="form-group">
													<label for="exampleInputFile">File input</label> <input type="file" id="exampleInputFile" name="file">
												</div>
											</div>
										</div>
										<br>
										<div class="bs-docs-grid">
											<div class="row show-grid">
												<div class="col-md-3">

													<div class="form-group">
														<label for="born">Born:</label> <input type="text" class="form-control" id="born"
															placeholder="Pick a date" name="born" required />
													</div>

												</div>
												<div class="col-md-3">

													<div class="form-group">
														<label for="idCardNumber">ID Card Number:</label> <input type="text" class="form-control"
															id="idCardNumber" placeholder="ID Card Number" name="idcardnumber" required>
													</div>

												</div>
												<div class="col-md-3">
													<div class="form-group">
														<label for="salary">Salary:</label> <input type="text" class="form-control" id="salary"
															placeholder="Salary" name="salary" required>
													</div>
												</div>
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
												<div class="col-md-3">
													<div class="form-group">
														<label for="role">Role:</label> <select class="form-control" name="role">

															<c:if test="${user.role == 'manager' }">
																<option value="regular">regular</option>
															</c:if>
															<c:if test="${user.role == 'admin' }">

																<option value="regular">regular</option>
																<option value="manager">manager</option>
															</c:if>
														</select>
													</div>
												</div>
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
												<div class="col-md-3">
													<div class="form-group">
														<label for="email">Email:</label> <input type="text" class="form-control" id="email" placeholder="Email"
															name="email" required>
													</div>
												</div>
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
														data-target="#addEmployee">Cancel</button>

												</div>
												<div class="col-md-1">
													<input class="btn btn-md btn-primary" type="submit" value="Save" name="save" />
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
			</form>
			<!-- /.row -->
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
					<c:forEach items="${employeelist }" var="list">
						<tr>

							<td><c:out value="${list.idEmployee}" /></td>
							<td><c:out value="${list.name }" /></td>
							<td><c:out value="${list.surname }" /></td>
							<td><c:out value="${list.telephone }" /></td>
							<td><c:out value="${list.createDate }" /></td>
							<td><c:out value="${list.edited }" /></td>
							<td>
								<form name="frm" action="DownloadEmployeeFile" method="post" enctype="multipart/form-data">
									<input class="btn btn-md btn-primary" type="submit" value="Download" name="download" /><input type="hidden"
										name="id" value="${list.idEmployee }" />
							</td>
							<td>
								</form>
								<form name="frm" action="DeleteEmployee" method="post" enctype="multipart/form-data">
									<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#${ list.idEmployee}"
										aria-expanded="false" aria-controls="id" name="edit" value="${list.idEmployee }">Edit</button>
									<input type="hidden" name="employeeID" value="${list.idEmployee }" /> <input class="btn btn-md btn-primary"
										type="submit" value="Delete" name="delete" />

								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
			<c:forEach items="${employeelist }" var="employee">
				<div class="collapse" id="${employee.idEmployee }">
					<div class="well">
						<div class="bs-docs-grid">
							<div class="row show-grid">
								<div class="col-md-3">

									<div class="form-group">
										<form action="UpdateEmployee" method="post" enctype="multipart/form-data">
											<label for="employeeName">Employee Name:</label> <input type="text" class="form-control" id="upemployeeName"
												value="${employee.name }" name="upname" required>
									</div>

								</div>
								<div class="col-md-3">

									<div class="form-group">
										<label for="employeeSurname">Employee Surname:</label> <input type="text" class="form-control"
											id="upemployeeSurname" value="${employee.surname }" name="upsurname" required>
									</div>

								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label for="upeducation">Education:</label> <select class="form-control" name="upeducation">
											<c:choose>
												<c:when test="${employee.education  == 'basic'}">
													<option selected value="basic">Basic</option>
												</c:when>
												<c:otherwise>
													<option value="basic">Basic</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${employee.education  == 'high school'}">
													<option selected value="high school">High School</option>
												</c:when>
												<c:otherwise>
													<option value="high school">High School</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${employee.education  == 'bachelor'}">
													<option selected value="bachelor">Bachelor</option>
												</c:when>
												<c:otherwise>
													<option value="bachelor">Bachelor</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${employee.education  == 'engineer'}">
													<option selected value="engineer">Engineer</option>
												</c:when>
												<c:otherwise>
													<option value="engineer">Engineer</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${employee.education  == 'master degree'}">
													<option selected value="master degree">Master degree</option>
												</c:when>
												<c:otherwise>
													<option value="master degree">Master degree</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${employee.education  == 'none'}">
													<option selected value="none">None</option>
												</c:when>
												<c:otherwise>
													<option value="none">None</option>
												</c:otherwise>
											</c:choose>


										</select>
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
										<label for="upborn">Born:</label> <input type="text" class="form-control" id="upborn"
											value="${employee.born }" name="upborn" required />
									</div>

								</div>
								<div class="col-md-3">

									<div class="form-group">
										<label for="idCardNumber">ID Card Number:</label> <input type="text" class="form-control" id="upidCardNumber"
											value="${employee.idCardNumber }" name="upidcardnumber" required>
									</div>

								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label for="salary">Salary:</label> <input type="text" class="form-control" id="upsalary"
											value="${employee.salary }" name="upsalary" required>
									</div>
								</div>
								<div class="col-md-2"></div>
							</div>
						</div>
						<br>
						<div class="bs-docs-grid">
							<div class="row show-grid">
								<div class="col-md-3">

									<div class="form-group">
										<label for="street">Street:</label> <input type="text" class="form-control" id="upstreet"
											value="${employee.street }" name="upstreet" required>
									</div>

								</div>
								<div class="col-md-3">

									<div class="form-group">
										<label for="house">House Number:</label> <input type="text" class="form-control" id="uphouse"
											value="${employee.houseNumber }" name="uphousenumber" required>
									</div>

								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label for="uprole">Role:</label> <select class="form-control" name="uprole">
											<c:choose>
												<c:when test="${employee.role  == 'admin'}">
													<option selected value="admin">admin</option>
												</c:when>
												<c:otherwise>
													<option value="admin">admin</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${employee.role  == 'regular'}">
													<option selected value="regular">regular</option>
												</c:when>
												<c:otherwise>
													<option value="regular">regular</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${employee.role  == 'manager'}">
													<option selected value="manager">manager</option>
												</c:when>
												<c:otherwise>
													<option value="manager">manager</option>
												</c:otherwise>
											</c:choose>
										</select>
									</div>
								</div>
								<div class="col-md-2"></div>
							</div>
						</div>
						<br>
						<div class="bs-docs-grid">
							<div class="row show-grid">
								<div class="col-md-3">

									<div class="form-group">
										<label for="city">City:</label> <input type="text" class="form-control" id="upcity" value="${employee.city }"
											name="upcity" required>
									</div>

								</div>
								<div class="col-md-3">

									<label for="country">Country code:</label> <select class="form-control" name="upcountry">
										<c:forEach items="${countries }" var="country">
											<c:if test="${employee.country eq country.key }">
												<option value="${country.key }">${country.value }</option>
											</c:if>
										</c:forEach>
										<c:forEach items="${countries }" var="country">
											<option value="${country.key }">${country.value }</option>
										</c:forEach>

									</select>

								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label for="upemail">Email:</label> <input type="text" class="form-control" id="upemail"
											value="${employee.email }" name="upemail" required>
									</div>
								</div>
								<div class="col-md-2"></div>
							</div>
						</div>
						<br>
						<div class="bs-docs-grid">
							<div class="row show-grid">
								<div class="col-md-3">
									<label for="gender">Gender:</label> <select class="form-control" name="upgender">
										<c:choose>
											<c:when test="${employee.gender eq 'male' }">
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
										<label for="uptelephone">Telephone:</label> <input type="text" class="form-control" id="uptelephone"
											value="${employee.telephone }" name="uptelephone" required>
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
										data-target="#${employee.idEmployee }">Cancel</button>

								</div>
								<div class="col-md-1">
									<input type="hidden" name="IDemployee" value="${employee.idEmployee }" /> <input
										class="btn btn-md btn-primary" type="submit" value="Update" name="update" />
								</div>

							</div>
						</div>
					</div>
				</div>

			</c:forEach>
			</form>
		</div>
	</div>



	<!-- /.container -->

	<!-- jQuery Version 1.11.1 -->


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