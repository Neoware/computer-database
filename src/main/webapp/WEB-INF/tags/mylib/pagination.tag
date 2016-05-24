<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag body-content="empty"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="page" required="true"%>
<%@ attribute name="count" required="true"%>
<ul class="pagination">
	<c:if test="${page > 1}" >
	<li><a href="dashboard?page=${page - 1}" aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a></li>
	</c:if>
	<c:set var="endPage" scope="request" value="${page + 5}"/> 
	<c:if test="${page + 5 > count}">
		<c:set var="endPage" scope="request" value="${count}"/> 
	</c:if>
	<c:forEach begin="${page}" end="${endPage}" varStatus="loop">
		<c:if test="${loop.index == page}">
		 <li class="active"><a href="dashboard?page=${loop.index}">${loop.index}</a></li>
		</c:if>
		<c:if test="${loop.index != page}">
		<li><a href="dashboard?page=${loop.index}">${loop.index}</a></li>
		</c:if>	
	</c:forEach>
	<c:if test="${page < count}">
	<li><a href="dashboard?page=${page + 1}" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a></li>
	</c:if>
</ul>
