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
    <fmt:message key="register" var="register" />
    <div class="content">
        <form class="signinup-form" action="Controller" method="post">
            <input type="hidden" name="command" value="savenewuser">
            <label for="firstName"><fmt:message key="firstName"/>: </label><br>
            <input class="signinup" type="text" id="firstName" name="firstName" required><br>
            <label for="lastName"><fmt:message key="lastName"/>: </label><br>
            <input class="signinup" type="text" id="lastName" name="lastName" required><br>
            <label for="email"><fmt:message key="email"/>: </label><br>
            <input class="signinup" type="email" id="email" name="email" required><br>
            <label for="password"><fmt:message key="password"/>:</label><br>
            <input class="signinup" type="password" id="password" name="password" required><br>
            <label for="repeatPassword"><fmt:message key="repeatPassword"/>:</label><br>
            <input class="signinup" type="password" id="repeatPassword" name="repeatPassword" required><br>
            <label for="avatar"><fmt:message key="avatarImage"/>:</label><br>
            <input class="signinup" type="file" id="avatar" name="avatar"><br>
            <input class="signinup" type="submit" value="${register}">
        </form>
    </div>
    <c:import url="footer.jsp" />
</body>
</html>