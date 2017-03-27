<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

    <title>Employee</title>

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

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
               
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="index.jsp">Client</a>
                    </li>
                    <li>
                        <a href="car.jsp">Car</a>
                    </li>
                    <li>
                        <a href="rented.jsp">Rented Cars</a>
                    </li>
                     <li class="active">
                        <a href="employee.jsp">Employee</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Content -->
    <div class="container">

       <form action="EmployeeServlet" method="post">
		<div class="menu">
			<div class="panel-group" id="accordion">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne"> +ADD EMPLOYEE </a>
						</h4>
					</div>

					<div id="collapseOne" class="panel-collapse collapse">
						<div class="panel-body">
							<div class="bs-docs-grid">
								<div class="row show-grid">
									<div class="col-md-3">

										<div class="form-group">
											<label for="employeeName">Employee Name:</label> <input
												type="text" class="form-control" id="employeeName"
												placeholder="Employee Name" name="name">
										</div>

									</div>
									<div class="col-md-3">

										<div class="form-group">
											<label for="employeeSurname">Employee Surname:</label> <input
												type="text" class="form-control" id="employeeSurname"
												placeholder="Employee Surname" name="surname">
										</div>

									</div>
									<div class="col-md-3">
									<div class="form-group">
											<label for="education">Education:</label> 
											<select class="form-control" name="education">
											<option value="basic">Basic</option>
											<option value="high school">High School</option>
											<option value="bachelor">Bachelor</option>
											<option value="engineer">Engineer</option>
											<option value="master degree">Master degree</option>
											<oprion value="none">None</oprion>
											</select>
										</div>
										</div>
									<div class="col-md-2">
										<div class="form-group">
										
											<input type="file" name="files" multiple="true"/>
										
										</div>
									</div>
								</div>
							</div>
							<br>
							<div class="bs-docs-grid">
								<div class="row show-grid">
									<div class="col-md-3">

										<div class="form-group">
											<label for="born">Born:</label> <input type="text"
												class="form-control" id="born" placeholder="Pick a date"
												name="born"/>
										</div>

									</div>
									<div class="col-md-3">

										<div class="form-group">
											<label for="idCardNumber">ID Card Number:</label> <input
												type="text" class="form-control" id="idCardNumber"
												placeholder="ID Card Number" name="idcardnumber">
										</div>

									</div>
									<div class="col-md-3">
									<div class="form-group">
											<label for="salary">Salary:</label> <input
												type="text" class="form-control" id="salary"
												placeholder="Salary" name="salary">
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
											<label for="street">Street:</label> <input type="text"
												class="form-control" id="street" placeholder="Street"
												name="street">
										</div>

									</div>
									<div class="col-md-3">

										<div class="form-group">
											<label for="house">House Number:</label> <input type="text"
												class="form-control" id="house" placeholder="House Number"
												name="housenumber">
										</div>

									</div>
									<div class="col-md-3">
									<div class="form-group">
											<label for="role">Role:</label> 
											<select class="form-control" name="role">
											<option value="admin">Admin</option>
											<option value="regular">Regular</option>
											<option value="mannager">Mannager</option>
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
											<label for="city">City:</label> <input type="text"
												class="form-control" id="city" placeholder="City"
												name="city">
										</div>

									</div>
									<div class="col-md-3">

										<label for="country">Country code:</label> <select
											class="form-control" name="country">
											<c:forEach items="${countries }" var="country">
												<option value="${country.key }">${country.value }</option>
											</c:forEach>

										</select>

									</div>
									<div class="col-md-3">
									<div class="form-group">
											<label for="email">Email:</label> <input type="text"
												class="form-control" id="email" placeholder="Email"
												name="email">
										</div>
									</div>
									<div class="col-md-2"></div>
								</div>
							</div>
							<br>
							<div class="bs-docs-grid">
								<div class="row show-grid">
									<div class="col-md-3">
										<label for="gender">Gender:</label> <select
											class="form-control" name="gender">
											<option value="male">Male</option>
											<option value="female">Female</option>

										</select>
									</div>
									<div class="col-md-3">

										<div class="form-group">
											<label for="telephone">Telephone:</label> <input type="text"
												class="form-control" id="Telephone" placeholder="Telephone"
												name="telephone">
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
										<button class="btn btn-md btn-primary" type="reset"
											 name="cancel" data-toggle="collapse" data-target="#collapseOne"> Cancel </button>
											
									</div>
									<div class="col-md-1">
										<input class="btn btn-md btn-primary" type="submit"
											value="Save" name="save" />
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
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

     <td><c:out value="${list.idEmployee}"/></td>
     <td><c:out value="${list.name }"/></td>
     <td><c:out value="${list.surname }"/></td>
     <td><c:out value="${list.telephone }"/></td>
     <td><c:out value="${list.createDate }" /></td>
     <td><c:out value="${list.edited }"/></td>
     <td><input class="btn btn-md btn-primary" type="submit" value="Download" name="download" /></td>
     <td>
     
			<form name="frm" action="EmployeeServlet" method="post">
     	<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#${ list.idEmployee}" aria-expanded="false" aria-controls="id" name="edit" value="${list.idEmployee }"    >
 Edit
  
</button>
 
     
			<input type="hidden" name="employeeID" value="${list.idEmployee }" />	
<input class="btn btn-md btn-primary" type="submit" value="Delete" name="delete" />
    	  
     </form>	 
     </td>  
    </tr>
</c:forEach>
  </tbody>
  
</table>
</form>
    </div>
    
    
    <!-- /.container -->

    <!-- jQuery Version 1.11.1 -->
   
    <script
			  src="https://code.jquery.com/jquery-3.2.1.min.js"
			  integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
			  crossorigin="anonymous"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
     <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>   
  $( function() {
    $( "#born" ).datepicker({
    	dateFormat: "dd.mm.yy",
    	changeYear: true,
    	changeMonth: true,
    	yearRange: '1900:'+(new Date).getFullYear()
    });   
  } );
 
  </script>
</body>
</html>