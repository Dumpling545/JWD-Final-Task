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
        <c:import url="searchRoomPanel.jsp"/><c:import url="messagePanel.jsp" /><div class="rooms">
            <table class="rooms-table">
                <tr>
                    <th><fmt:message key="roomIcon"/></th>
                    <th><fmt:message key="roomName"/></th>
                    <th><fmt:message key="location"/></th>
                    <th><fmt:message key="rating"/></th>
                    <th><fmt:message key="pricePerNight"/></th>
                </tr>
                <c:forEach var="room" items="${requestScope.rooms}">
                    <tr>
                        <td><img src="${room.icon}"/></td>
                        <td><c:out value="${room.name}" /></td>
                        <td><c:out value="${room.address}" /></td>
                        <td><c:out value="${room.rating}" /></td>
                        <td><c:out value="${room.cost}" /></td>
                    </tr>
                </c:forEach>
            </table>
            <c:import url="pagination.jsp" />
        </div>
    </div>
    <c:import url="footer.jsp" />
</body>
</html>