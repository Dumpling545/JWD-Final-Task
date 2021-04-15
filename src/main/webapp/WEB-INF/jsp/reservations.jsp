<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
    import="java.util.List, by.tc.task05.entity.ExtendedReservation" %>
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
                        <div class="col-sm-10 col-sm-offset-1">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h2>
                                        <fmt:message key="reservations" />:
                                    </h2>
                                    <ul class="nav nav-pills">
                                        <li <c:if test="${param['archived'] != true}">class="active"</c:if>><a
                                                id="activeLink" href="#">
                                                <fmt:message key="active" />
                                            </a></li>
                                        <li <c:if test="${param['archived'] == true}">class="active"</c:if>><a
                                                id="archivedLink" href="#">
                                                <fmt:message key="archived" />
                                            </a></li>
                                    </ul>
                                    <script>
                                        <c:import url="../js/addLinksToPills.js" />
                                    </script>
                                    <c:import url="pageResizer.jsp" />
                                </div>
                                <div class="panel-body">
                                    <table class="table table-bordered">
                                        <thead>
                                            <tr>
                                                <c:if test="${param['command'] != gotouserreservationspage}">
                                                    <th>
                                                        <fmt:message key="clientEmail" />
                                                    </th>
                                                </c:if>
                                                <c:if test="${param['command'] != gotoroomreservationspage}">
                                                    <th>
                                                        <fmt:message key="roomName" />
                                                    </th>
                                                </c:if>
                                                <c:if test="${param['command'] != gotohotelreservationspage}">
                                                    <th>
                                                        <fmt:message key="hotelName" />
                                                    </th>
                                                </c:if>
                                                <th>
                                                    <fmt:message key="checkIn" />
                                                </th>
                                                <th>
                                                    <fmt:message key="checkOut" />
                                                </th>
                                                <th>
                                                    <fmt:message key="paymentAmount" />
                                                </th>
                                                <th>
                                                    <fmt:message key="paymentToken" />
                                                </th>
                                                <th>
                                                    <fmt:message key="status" />
                                                </th>
                                                <th>
                                                    <fmt:message key="management" />
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="reservation" items="${requestScope.reservations}">
                                                <tr>
                                                    <c:if test="${param['command'] != gotouserreservationspage}">
                                                        <td>
                                                            <c:out value="${reservation.userEmail}" />
                                                        </td>
                                                    </c:if>
                                                    <c:if test="${param['command'] != gotoroomreservationspage}">
                                                        <c:url value="Controller" var="roomPage">
                                                            <c:param name="command" value="gotoroompage" />
                                                            <c:param name="roomid" value="${reservation.roomId}" />
                                                        </c:url>
                                                        <td>
                                                            <a href="${roomPage}">
                                                                <c:out value="${reservation.roomName}" />
                                                            </a>
                                                        </td>
                                                    </c:if>
                                                    <c:if test="${param['command'] != gotohotelreservationspage}">
                                                        <c:url value="Controller" var="hotelPage">
                                                            <c:param name="command" value="gotohotelpage" />
                                                            <c:param name="roomid" value="${reservation.hotelId}" />
                                                        </c:url>
                                                        <td>
                                                            <a href="${hotelPage}">
                                                                <c:out value="${reservation.hotelName}" />
                                                            </a>
                                                        </td>
                                                    </c:if>
                                                    <td>
                                                        <c:out value="${reservation.checkInDate}" />
                                                    </td>
                                                    <td>
                                                        <c:out value="${reservation.checkOutDate}" />
                                                    </td>
                                                    <td>
                                                        <c:out value="${reservation.paymentAmount}" />$
                                                    </td>
                                                    <td>
                                                        <c:out value="${reservation.paymentToken}" />
                                                    </td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${param['archived'] == true}">
                                                                <c:if test="${reservation.status == 0}">
                                                                    <p class="text-danger">
                                                                        <fmt:message key="rejected" />
                                                                    </p>
                                                                </c:if>
                                                                <c:if test="${reservation.status == 1}">
                                                                    <p class="text-success">
                                                                        <fmt:message key="endedSuccessfully" />
                                                                    </p>
                                                                </c:if>
                                                                <c:if test="${reservation.status == 2}">
                                                                    <p class="text-danger">
                                                                        <fmt:message key="cancelled" />
                                                                    </p>
                                                                </c:if>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:if test="${reservation.status == 0}">
                                                                    <p class="text-info">
                                                                        <fmt:message key="processing" />
                                                                    </p>
                                                                </c:if>
                                                                <c:if test="${reservation.status == 1}">
                                                                    <p class="text-success">
                                                                        <fmt:message key="accepted" />
                                                                    </p>
                                                                </c:if>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td>
                                                        ...management
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="panel-footer">
                                    <c:import url="pagination.jsp" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <c:import url="footer.jsp" />
            </body>

            </html>