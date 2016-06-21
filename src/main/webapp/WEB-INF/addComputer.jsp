<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags/mylib"%>
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
<spring:url value="/resources/css/jquery-ui.min.css" var="jqueryUiCss" />
<spring:url value="/resources/css/jquery-ui.theme.min.css"
	var="jqueryUiThemeCss" />
<spring:url value="/resources/css/jquery-ui.structure.min.css"
	var="jqueryUiStructureCss" />

<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${fontCss}" rel="stylesheet" />
<link href="${mainCss}" rel="stylesheet" />
<link href="${jqueryUiCss}" rel="stylesheet" />
<link href="${jqueryUiThemeCss}" rel="stylesheet" />
<link href="${jqueryUiStructureCss}" rel="stylesheet" />

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
				<div id="information">
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
				</div>
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>Add Computer</h1>
					<form:form commandName="computerDTO" method="POST">
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label>
								<form:input type="text" class="form-control" id="computerName"
									name="computerName" placeholder="Computer name" path="name" />
								<form:errors cssClass="error" path="name" />
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label>
								<form:input class="form-control datepicker" id="introduced"
									name="introduced" placeholder="Introduced date"
									path="introduced" />
								<form:errors path="introduced" />
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label>
								<form:input class="form-control datepicker" id="discontinued"
									name="discontinued" placeholder="Discontinued date"
									path="discontinued" />
								<form:errors path="discontinued" />
							</div>
							<div class="form-group">
								<label for="companyId">Company</label>
								<form:select class="form-control" id="companyId"
									name="companyId" path="companyId">
									<option selected disabled>Choose the brand of the
										computer</option>
									<c:forEach items="${requestScope.companies}" var="item">
										<option value="${item.id}">${item.name}</option>
									</c:forEach>
								</form:select>
								<form:errors path="companyId" />
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Add" id="submitButton"
								class="btn btn-primary"> or <a href="dashboard"
								class="btn btn-default">Cancel</a>
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
	<%-- 	<script src="${addComputerJs}"></script> --%>
	<script src="${bootstrapJs}"></script>
	<script src="${jqueryUiJs}"></script>
</body>
</html>