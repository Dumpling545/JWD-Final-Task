<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${cookie['lang'].value}" scope="session" />
<fmt:setBundle basename = "by.tc.task05.bundle.WebsiteTextBundle"/>
<!DOCTYPE html>
<html>
<head>
<title>HBS</title>
<meta charset="utf-8">
<style>
<jsp:include page="../css/main.css"/>
</style>
</head>
<body>
<c:import url="header.jsp" />
<fmt:message key="applyChanges" var="apply" />
<div class="content">
<h4>
    <fmt:message key="editUserInfo" />
</h4>
<form class="signinup-form" action="Controller" method="post">
<input type="hidden" name="command" value="editUserInfo">
<label for="firstName"><fmt:message key="firstName"/>: </label><br>
<input class="signinup" type="text" id="firstName" name="firstName" value="${requestScope.userInfo.firstName}" required><br>
<label for="lastName"><fmt:message key="lastName"/>: </label><br>
<input class="signinup" type="text" id="lastName" name="lastName" value="${requestScope.userInfo.lastName}" required><br>
<input class="signinup" type="file" id="avatar" name="avatar"><br>
<input class="signinup" type="submit" value="${apply}">
</form>
</div>
<c:import url="footer.jsp" />
</body>
</html>