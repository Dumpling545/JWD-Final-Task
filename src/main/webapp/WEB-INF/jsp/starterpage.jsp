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
    <div class="content">
        <c:import url="minimalSearchRoomPanel.jsp" />
    </div>
    <c:import url="footer.jsp" />
</body>
</html>