<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags/mylib"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
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
			<h1 id="homeTitle">${requestScope.page.count}&nbsp;Computers&nbsp;found</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="dashboard" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addcomputer">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="dashboard" method="POST">
			<input type="hidden" name="selection" value="">
		</form>
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
						<th>Computer name</th>
						<th>Introduced date</th>
						<th>Discontinued date</th>
						<th>Company</th>
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
				paginationEnd="${requestScope.paginationEnd}"
				paginationStart="${requestScope.paginationStart}" />
			<div class="btn-group btn-group-sm pull-right" role="group">
				<mylib:link page="1" limit="10" classes="btn btn-default">10</mylib:link>
				<mylib:link page="1" limit="50" classes="btn btn-lg btn-default">50</mylib:link>
				<mylib:link page="1" limit="100" classes="btn btn-lg btn-default">100</mylib:link>
			</div>
		</div>
	</footer>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>

</body>
</html>