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
    <fmt:message key="signIn" var="signIn" />
    <div class="content">
        <form class="signinup-form" action="Controller" method="post">
            <input type="hidden" name="command" value="signin">
            <label for="email"><fmt:message key="email"/>: </label><br>
            <input class="signinup" type="email" id="email" name="email" required><br>
            <label for="password"><fmt:message key="password"/>:</label><br>
            <input class="signinup" type="password" id="password" name="password"required><br>
            <input class="signinup" type="submit" value="${signIn}">
        </form>
    </div>
    <c:import url="footer.jsp" />
</body>
</html>