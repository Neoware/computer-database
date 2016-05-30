<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag body-content="empty"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags/mylib"%>
<%@ attribute name="page" required="true" type="java.lang.Integer"%>
<%@ attribute name="count" required="true" type="java.lang.Integer"%>
<%@ attribute name="limit" required="true" type="java.lang.Integer"%>
<%@ attribute name="paginationStart" required="true" type="java.lang.Integer"%>
<%@ attribute name="paginationEnd" required="true" type="java.lang.Integer" %>
<ul class="pagination">

	<li><mylib:link page="1" limit="${limit}">
			<span aria-hidden="true">First</span>
		</mylib:link></li>
	<c:if test="${page > 1}">
		<li><mylib:link page="${page - 1}" limit="${limit}">
				<span aria-hidden="true">&laquo;</span>
			</mylib:link></li>
	</c:if>

	<c:forEach begin="${paginationStart}" end="${paginationEnd}" varStatus="loop">
		<c:if test="${loop.index == page}">
			<li class="active"><mylib:link page="${loop.index}"
					limit="${limit}">${loop.index}</mylib:link></li>
		</c:if>
		<c:if test="${loop.index != page}">
			<li><mylib:link page="${loop.index}" limit="${limit}">${loop.index}</mylib:link></li>
		</c:if>
	</c:forEach>
	<c:if test="${page lt count}">
	</c:if>
	<c:if test="${page < count}">
		<li><mylib:link page="${page + 1}" limit="${limit}">
				<span aria-hidden="true">&raquo;</span>
			</mylib:link></li>
	</c:if>
	<li><mylib:link page="${count}" limit="${limit}">
			<span aria-hidden="true">Last</span>
		</mylib:link></li>
</ul>
