<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag body-content="scriptless"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="page" required="true"%>
<%@ attribute name="limit" required="true"%>
<jsp:doBody var="bodyRes" />
<a href="dashboard?page=${page}&limit=${limit}">${bodyRes}</a>
