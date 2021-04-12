<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
    import="java.util.List, by.tc.task05.entity.Room" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <fmt:setLocale value="${cookie['lang'].value}" scope="session" />
            <fmt:setBundle basename="by.tc.task05.bundle.WebsiteTextBundle" />
            <!DOCTYPE html>
            <html>
            <c:import url="meta.jsp" />
            <body>
                <c:import url="header.jsp" />
                <div class="container">
                    <c:import url="messagePanel.jsp" />
                    <div class="row">
                        <c:import url="searchRoomPanel.jsp" />
                        <div class="col-sm-9">
                            <table class="rooms-table">
                                <tr>
                                    <th>
                                        <fmt:message key="roomIcon" />
                                    </th>
                                    <th>
                                        <fmt:message key="roomName" />
                                    </th>
                                    <th>
                                        <fmt:message key="location" />
                                    </th>
                                    <th>
                                        <fmt:message key="rating" />
                                    </th>
                                    <th>
                                        <fmt:message key="pricePerNight" />
                                    </th>
                                </tr>
                                <c:forEach var="room" items="${requestScope.rooms}">
                                    <tr>
                                        <td><img src="${room.icon}" /></td>
                                        <td>
                                            <c:out value="${room.name}" />
                                        </td>
                                        <td>
                                            <c:out value="${room.address}" />
                                        </td>
                                        <td>
                                            <c:out value="${room.rating}" />
                                        </td>
                                        <td>
                                            <c:out value="${room.cost}" />
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <c:import url="pagination.jsp" />
                        </div>
                    </div>
                </div>
                <c:import url="footer.jsp" />
            </body>

            </html>