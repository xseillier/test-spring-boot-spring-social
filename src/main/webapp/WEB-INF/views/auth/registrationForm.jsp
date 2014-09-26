<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title></title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app/user.form.js"></script>
       <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/social-buttons-3.css"/>
   	   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap.css"/>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/font-awesome.css"/>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/style.css"/>
</head>
<body>
    <div class="page-header">
        <h1><spring:message code="label.user.registration.page.title"/></h1>
    </div>
    <sec:authorize access="isAnonymous()">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel-body">
                <form:form data-toggle="validator" action="/user/register" commandName="user" method="POST" enctype="utf8" role="form">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <c:if test="${user.signInProvider != null}">
                        <input type="hidden" name="signInProvider" value="${user.signInProvider}"/>
                    </c:if>
                    <div class="row">
                        <div id="form-group-firstName" class="form-group col-lg-4">
                            <label class="control-label" for="user-firstName"><spring:message code="label.user.firstName"/>:</label>
                            <input id="user-firstName" name="firstName" class="form-control"value="${user.firstName}" required/>
                        </div>
                    </div>
                    <div class="row">
                        <div id="form-group-lastName" class="form-group col-lg-4">
                            <label class="control-label" for="user-lastName"><spring:message code="label.user.lastName"/>:</label>
                            <input id="user-lastName" name="lastName" class="form-control" value="${user.lastName}" required/>
                       </div>
                    </div>
                    <div class="row">
                        <div id="form-group-email" class="form-group col-lg-4">
                            <label class="control-label" for="user-email"><spring:message code="label.user.email"/>:</label>
                            <input id="user-email" name="email" class="form-control" value="${user.email}" type="email" required/>
                        </div>
                    </div>
                    <c:if test="${user.signInProvider == null}">
                        <div class="row">
                            <div id="form-group-password" class="form-group col-lg-4">
                                <label class="control-label" for="user-password"><spring:message code="label.user.password"/>:</label>
                                <input type="password" id="user-password" name="password" class="form-control" required data-minlength="6" data-error="<spring:message code="error.not.long.enough"/>"/>
                                <div class="help-block with-errors"></div>
                            </div>
                        </div>
                        <div class="row">
                            <div id="form-group-passwordVerification" class="form-group col-lg-4">
                                <label class="control-label" for="user-passwordVerification"><spring:message code="label.user.passwordVerification"/>:</label>
                                <input type="password" id="user-passwordVerification" name="passwordVerification" class="form-control" data-match="#user-password"  data-match-error="<spring:message code="error.password.not.match"/> />
                                <div class="help-block with-errors"></div>
                             </div>
                        </div>
                    </c:if>
                    <button type="submit" class="btn btn-default"><spring:message code="label.user.registration.submit.button"/></button>
                </form:form>
            </div>
        </div>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <p><spring:message code="text.registration.page.authenticated.user.help"/></p>
    </sec:authorize>
    
    <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap.js"></script>
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-2.0.3.js"></script>
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/static/js/validator.js"></script>
  
</body>
</html>