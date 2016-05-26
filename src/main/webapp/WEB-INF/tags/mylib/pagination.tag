<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag body-content="empty"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags/mylib"%>
<%@ attribute name="page" required="true"%>
<%@ attribute name="count" required="true"%>
<%@ attribute name="limit" required="true"%>
<ul class="pagination">
	<c:if test="${page > 1}">
		<li><mylib:link page="${page - 1}" limit="${limit}">
				<span aria-hidden="true">&laquo;</span>
			</mylib:link></li>
	</c:if>
	<c:set var="endPage" scope="request" value="${page + 5}" />
	<c:if test="${page + 5 > count}">
		<c:set var="endPage" scope="request" value="${count}" />
	</c:if>
	<c:forEach begin="${page}" end="${endPage}" varStatus="loop">
		<c:if test="${loop.index == page}">
			<li class="active"><mylib:link page="${loop.index}"
					limit="${limit}">${loop.index}</mylib:link></li>
		</c:if>
		<c:if test="${loop.index != page}">
			<li><mylib:link page="${loop.index}" limit="${limit}">${loop.index}</mylib:link></li>
		</c:if>
	</c:forEach>
	<c:if test="${page < count}">
		<li><mylib:link page="${page + 1}" limit="${limit}">
				<span aria-hidden="true">&raquo;</span>
			</mylib:link></li>
	</c:if>
</ul>
