<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag body-content="scriptless"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="page" required="true"%>
<%@ attribute name="limit" required="true"%>
<%@ attribute name="classes" required="false" %>
<jsp:doBody var="bodyRes" />
<c:if test="${not empty classes}">
   <a class="${classes}" href="dashboard?page=${page}&limit=${limit}">${bodyRes}</a>
</c:if>
<c:if test="${empty classes}">
   <a href="dashboard?page=${page}&limit=${limit}">${bodyRes}</a>
</c:if>

