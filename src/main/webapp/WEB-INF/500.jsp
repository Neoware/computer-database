<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
			<a class="navbar-brand" href="dashboard"> Application - Computer
				Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="alert alert-danger">
				Error 500: An error has occured! <br />
				<!-- stacktrace -->
			</div>
		</div>
	</section>

	<spring:url value="/resources/js/jquery.min.js" var="jqueryJs" />
	<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />
	<spring:url value="/resources/js/dashboard.js" var="dashboardJs" />
	<script src="${jqueryJs}"></script>
	<script src="${dashboardJs}"></script>
	<script src="${bootstrapJs}"></script>

</body>
</html>