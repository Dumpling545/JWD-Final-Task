<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
    import="by.tc.task05.entity.User" %>
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
    <div class="content">
        <h4>
            <fmt:message key="accountInfoHeader"/>
        </h4>
        <p>
            <fmt:message key="email"/> : <c:out value="${requestScope.user.email}" />
        </p>
        <p>
            <fmt:message key="firstName"/> : <c:out value="${requestScope.user.firstName}" />
        </p>
        <p>
            <fmt:message key="lastName"/> : <c:out value="${requestScope.user.lastName}" />
        </p>
        <c:url value="Controller" var="editInfo">
            <c:param name="command" value="gotoedituserinfopage" />
        </c:url>
        <a href="${editInfo}"><fmt:message key="editUserInfo" /></a>

    </div>
    <c:import url="footer.jsp" />
</body>
</html>