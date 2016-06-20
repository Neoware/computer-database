<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />
<spring:url value="/resources/css/font-awesome.css" var="fontCss" />
<spring:url value="/resources/css/main.css" var="mainCss" />

<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${fontCss}" rel="stylesheet" />
<link href="${mainCss}" rel="stylesheet" />
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard  "> Application -
				Computer Database </a>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id:
						${computerDTO.id}</div>
					<h1>Edit Computer</h1>

					<form:form commandName="computerDTO" method="POST">
						<input type="hidden" value="${computerDTO.id}" id="id"
							name="computerId" />
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <form:input
									type="text" class="form-control" id="computerName"
									placeholder="Computer name" path="name" name="computerName"/>
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label> <form:input
									type="date" class="form-control" id="introduced"
									placeholder="Introduced date" path="introduced" name="introduced"/>
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <form:input
									type="date" class="form-control" id="discontinued"
									placeholder="Discontinued date" path="discontinued" name="discontinued"/>
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <form:select
									class="form-control" id="companyId" path="companyId" name="companyId">
									<c:if test="${computer.companyId == 0}">

										<option selected disabled>Choose the brand of the
											computer</option>
									</c:if>
									<c:forEach items="${requestScope.companies}" var="item">
										<c:if test="${item.id eq computerDTO.companyId}">
											<option selected value="${item.id}">${item.name}</option>
										</c:if>
										<c:if test="${item.id ne computerDTO.companyId}">
											<option value="${item.id}">${item.name}</option>
										</c:if>
									</c:forEach>
								</form:select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Edit" class="btn btn-primary">
							or <a href="dashboard" class="btn btn-default">Cancel</a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</section>
	<spring:url value="/resources/js/jquery.min.js" var="jqueryJs" />
	<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />
	<spring:url value="/resources/js/addComputer.js" var="addComputerJs" />
	<spring:url value="/resources/js/jquery-ui.min.js" var="jqueryUiJs" />
	<script src="${jqueryJs}"></script>
	<script src="${addComputerJs}"></script>
	<script src="${bootstrapJs}"></script>
	<script src="${jqueryUiJs}"></script>
</body>
</html>