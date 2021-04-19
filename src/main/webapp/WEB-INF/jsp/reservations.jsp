<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
    import="java.util.List, by.tc.task05.entity.ExtendedReservation" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
                                                    <c:if test="${param['command'] != 'gotouserreservationspage'}">
                                                        <th>
                                                            <fmt:message key="clientEmail" />
                                                        </th>
                                                    </c:if>
                                                    <c:if test="${param['command'] != 'gotoroomreservationspage'}">
                                                        <th>
                                                            <fmt:message key="roomName" />
                                                        </th>
                                                    </c:if>
                                                    <c:if test="${param['command'] != 'gotohotelreservationspage'}">
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
                                                        <c:if test="${param['command'] != 'gotouserreservationspage'}">
                                                            <td>
                                                                <c:out value="${reservation.userEmail}" />
                                                            </td>
                                                        </c:if>
                                                        <c:if test="${param['command'] != 'gotoroomreservationspage'}">
                                                            <c:url value="Controller" var="roomPage">
                                                                <c:param name="command" value="gotoroompage" />
                                                                <c:param name="roomid" value="${reservation.roomId}" />
                                                            </c:url>
                                                            <td>
                                                                <a target="_blank" href="${roomPage}">
                                                                    <c:out value="${reservation.roomName}" />
                                                                </a>
                                                            </td>
                                                        </c:if>
                                                        <c:if test="${param['command'] != 'gotohotelreservationspage'}">
                                                            <c:url value="Controller" var="hotelPage">
                                                                <c:param name="command" value="gotohotelpage" />
                                                                <c:param name="hotelid"
                                                                    value="${reservation.hotelId}" />
                                                            </c:url>
                                                            <td>
                                                                <a target="_blank" href="${hotelPage}">
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
                                                            <c:if
                                                                test="${param['command'] == 'gotouserreservationspage' && param['archived'] != true}">
                                                                <c:url value="Controller" var="deleteReservation">
                                                                    <c:param name="command"
                                                                        value="gotocancelreservationpage" />
                                                                    <c:param name="id" value="${reservation.id}" />
                                                                    <c:param name="id2" value="2" />
                                                                    <c:param name="name"
                                                                        value="${reservation.hotelName}" />
                                                                    <c:param name="statickey" value="reservation" />
                                                                    <c:param name="staticvalue"
                                                                        value="${reservation.checkInDate} - ${reservation.checkOutDate},  ${reservation.roomName}" />
                                                                    <c:choose>
                                                                        <c:when
                                                                            test="${param['page'] != null && param['page'] > 1 && fn:length(requestScope.reservations) == 1}">
                                                                            <c:param name="cancelurl"
                                                                                value="${sessionScope.lastUrl}" />
                                                                            <c:url value="Controller"
                                                                                var="previousPageLink">
                                                                                <c:forEach var="entry" items="${param}">
                                                                                    <c:choose>
                                                                                        <c:when
                                                                                            test="${entry.key=='page'}">
                                                                                            <c:param name="${entry.key}"
                                                                                                value="${entry.value - 1}" />
                                                                                        </c:when>
                                                                                        <c:otherwise>
                                                                                            <c:param name="${entry.key}"
                                                                                                value="${entry.value}" />
                                                                                        </c:otherwise>
                                                                                    </c:choose>
                                                                                </c:forEach>
                                                                            </c:url>
                                                                            <c:param name="returnurl"
                                                                                value="${previousPageLink}" />
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <c:param name="returnurl"
                                                                                value="${sessionScope.lastUrl}" />
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </c:url>
                                                                <a href="${deleteReservation}" class="btn btn-danger"
                                                                    role="button">
                                                                    <span
                                                                        class="glyphicon glyphicon-remove-sign"></span>
                                                                    <fmt:message key="cancel" />
                                                                </a>
                                                                <div class="modal fade" id="cancelModal" role="dialog">
                                                                    <div class="modal-dialog">
                                                                        <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <button type="button" class="close"
                                                                                    data-dismiss="modal">&times;</button>
                                                                                <h4 class="modal-title">
                                                                                    <fmt:message key="location" />
                                                                                </h4>
                                                                            </div>
                                                                            <div class="modal-body">
                                                                                <div id='map'></div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:if>
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