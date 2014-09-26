<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <title>Private Zone</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap.css"/>    
</head>

<body>
	
	<sec:authorize access="isAuthenticated()">
	 <div class="panel panel-default">
        <div class="panel-body">
        
        	<table>
        		<tr>
        			<td><strong><spring:message code="label.user.home.social.provider"/></strong></td><td>${socialProvider}</td>
        		</tr>
        		<tr>
        			<td><strong><spring:message code="label.user.home.username"/></strong></td><td>${user}</td>
        		</tr>
        		
        		<c:if test="${imageUrl != null && not empty imageUrl }">
	        		<tr>
	        			<td><strong><spring:message code="label.user.home.image.profile"/></strong></td><td><img src="${imageUrl}"/></td>
	        		</tr>
        		</c:if>
        	</table>     
        </div>
        
        <a href="/logout" >Logout</a>
     </div>
	</sec:authorize>
	
	<sec:authorize access="isAnonymous()">
	
	</sec:authorize>
</body>
</html>