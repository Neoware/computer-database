<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags/mylib"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
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
			<c:url var="logoutUrl" value="/login?logout" />
			<a class="navbar-brand pull-right" href="${logoutUrl}"> Logout </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${requestScope.page.count}
				<spring:message code="dashboard.found" />
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="dashboard" method="GET"
						class="form-inline">
						<spring:message code="dashboard.search" var="searchPlaceholder" />
						<spring:message code="dashboard.filterBy" var="filterBy" />
						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="${searchPlaceholder}" /> <input
							type="submit" id="searchsubmit" value="${filterBy}"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">

					<a class="btn btn-success" id="addComputer" href="addcomputer"><spring:message
							code="dashboard.button.add" /></a> <a class="btn btn-default"
						id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message
							code="dashboard.button.select" /></a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="dashboard" method="POST">
			<input type="hidden" name="selection" value="">
		</form>
		<c:if test="${requestScope.page.order == 'ASC'}">
			<c:set var="futureOrder" scope="request" value="DESC" />
		</c:if>
		<c:if test="${requestScope.page.order == 'DESC'}">
			<c:set var="futureOrder" scope="request" value="ASC" />
		</c:if>
		<c:if test="${empty requestScope.page.order}">
			<c:set var="futureOrder" scope="request" value="ASC" />
		</c:if>
		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th><spring:message code="dashboard.tableColumn.name" /> <mylib:link
								page="${requestScope.page.current}"
								limit="${requestScope.page.limit}"
								search="${requestScope.page.search}" sort="name"
								order="${futureOrder}">
								<span class="glyphicon glyphicon-sort" aria-hidden="true"></span>
							</mylib:link></th>
						<th><spring:message code="dashboard.tableColumn.introduced" />
							<mylib:link page="${requestScope.page.current}"
								limit="${requestScope.page.limit}"
								search="${requestScope.page.search}" sort="introduced"
								order="${futureOrder}">
								<span class="glyphicon glyphicon-sort" aria-hidden="true"></span>
							</mylib:link></th>
						<th><spring:message code="dashboard.tableColumn.discontinued" />
							<mylib:link page="${requestScope.page.current}"
								limit="${requestScope.page.limit}"
								search="${requestScope.page.search}" sort="discontinued"
								order="${futureOrder}">
								<span class="glyphicon glyphicon-sort" aria-hidden="true"></span>
							</mylib:link></th>
						<th><spring:message code="dashboard.tableColumn.company" />
							<mylib:link page="${requestScope.page.current}"
								limit="${requestScope.page.limit}"
								search="${requestScope.page.search}" sort="companyName"
								order="${futureOrder}">
								<span class="glyphicon glyphicon-sort" aria-hidden="true"></span>
							</mylib:link></th>
					</tr>
				</thead>
				<tbody id="results">
					<c:forEach items="${requestScope.page.currentPageElements}"
						var="item">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${item.id}"></td>
							<td><a href="editcomputer?id=${item.id}">${item.name}</a></td>
							<td>${item.introduced}</td>
							<td>${item.discontinued}</td>
							<td>${item.companyName}</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">

			<mylib:pagination page="${requestScope.page.current}"
				count="${requestScope.page.totalPage}"
				limit="${requestScope.page.limit}"
				search="${requestScope.page.search}"
				sort="${requestScope.page.sort}" order="${requestScope.page.order}" />
			<div class="btn-group btn-group-sm pull-right" role="group">
				<mylib:link page="1" limit="10" classes="btn btn-default">10</mylib:link>
				<mylib:link page="1" limit="50" classes="btn btn-lg btn-default">50</mylib:link>
				<mylib:link page="1" limit="100" classes="btn btn-lg btn-default">100</mylib:link>
			</div>
		</div>
	</footer>
	<spring:url value="/resources/js/jquery.min.js" var="jqueryJs" />
	<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />
	<spring:url value="/resources/js/dashboard.js" var="dashboardJs" />
	<script src="${jqueryJs}"></script>
	<script src="${dashboardJs}"></script>
	<script src="${bootstrapJs}"></script>
</body>
</html>