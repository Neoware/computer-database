<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag body-content="scriptless"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="page" required="true"%>
<%@ attribute name="limit" required="true"%>
<%@ attribute name="search" required="false"%>
<%@ attribute name="sort" required="false"%>
<%@ attribute name="classes" required="false" %>
<jsp:doBody var="bodyRes" />
<c:set var="linkBase" scope="request" value="dashboard?page=${page}&limit=${limit}" />

<c:if test="${not empty search}">
    <c:set var="searchUrl" value="&search=${search}" />
</c:if>

<c:if test="${not empty sort}">
    <c:set var="sortUrl" value="&sort=${sort}" />
</c:if>

<c:set var="trueLink" value="${linkBase}${searchUrl}${sortUrl}" />

<c:if test="${not empty classes}">
   <a class="${classes}" href="${trueLink}">${bodyRes}</a>
</c:if>
<c:if test="${empty classes}">
   <a href="${trueLink}">${bodyRes}</a>
</c:if>

