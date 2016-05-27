<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags/mylib"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
<link href="css/jquery-ui.min.css" rel="stylesheet" media="screen">
<link href="css/jquery-ui.theme.min.css" rel="stylesheet" media="screen">
<link href="css/jquery-ui.structure.min.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application - Computer
				Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div id="error"></div>
				<c:if test="${requestScope.display}">
					<c:if test="${requestScope.success}">
						<div class="alert alert-success" role="alert">
							${requestScope.successMessage}</div>
					</c:if>
					<c:if test="${!requestScope.success}">
						<div class="alert alert-danger" role="alert">
							${requestScope.successMessage}</div>
					</c:if>
				</c:if>
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>Add Computer</h1>
					<form action="addcomputer" method="POST">
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									type="text" class="form-control" id="computerName"
									name="computerName" placeholder="Computer name" required>
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label> <input
									class="form-control datepicker" id="introduced"
									name="introduced" placeholder="Introduced date">
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <input
									class="form-control datepicker" id="discontinued"
									name="discontinued" placeholder="Discontinued date">
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" id="companyId" name="companyId">
									<c:forEach items="${requestScope.companies}" var="item">
										<option value="${item.id}">${item.name}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Add" id="submitButton"
								class="btn btn-primary"> or <a href="dashboard"
								class="btn btn-default">Cancel</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/addComputer.js"></script>
</body>
</html>