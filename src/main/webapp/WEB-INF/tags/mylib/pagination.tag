<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag body-content="empty"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="page" required="true"%>
<%@ attribute name="count" required="true"%>
<ul class="pagination">
	<c:if test="${page > 1}" >
	<li><a href="dashboard?page=${page - 1}" aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a></li>
	</c:if>
	<c:forEach begin="${page}" end="${page + 5}" varStatus="loop">
		<c:if test="${loop.index == page}">
		 <li class="active">
		</c:if>
		<c:if test="${loop.index != page}">
		<li>
		</c:if>
		<a href="dashboard?page=${loop.index}">${loop.index}</a></li>
	</c:forEach>
	<c:if test="${page < count}">
	<li><a href="dashboard?page=${page + 1}" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a></li>
	</c:if>
</ul>
