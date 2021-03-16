<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
    import="java.util.List, by.htp.les02.bean.News" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>HBS</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
    <c:import url="header.jsp" />
    <div class="content">
        <c:import url="messagePanel.jsp" />
        <c:import url="minimalSearchRoomPanel.jsp" />
    </div>
    <c:import url="footer.jsp" />
</body>
</html>