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
                                            <c:if test="${param['command'] == 'gotohotelreservationspage'}">
                                                <c:url value="Controller" var="mainHotelPage">
                                                    <c:param name="command" value="gotohotelpage" />
                                                    <c:param name="hotelid" value="${param['hotelid']}" />
                                                </c:url>
                                                <td>
                                                    <a target="_blank" href="${mainHotelPage}">
                                                        <c:out value="${param['name']}" />
                                                    </a>,
                                                </td>
                                            </c:if>
                                            <c:if test="${param['command'] == 'gotoroomreservationspage'}">
                                                <c:url value="Controller" var="mainRoomPage">
                                                    <c:param name="command" value="gotoroompage" />
                                                    <c:param name="roomid" value="${param['roomid']}" />
                                                </c:url>
                                                <td>
                                                    <a target="_blank" href="${mainRoomPage}">
                                                        <c:out value="${param['name']}" />
                                                    </a>,
                                                </td>
                                            </c:if>
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
                                                        <td class="url-adjustment-object-1">
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
                                                            <div class="btn-group-vertical">
                                                                <c:if
                                                                    test="${param['command'] == 'gotouserreservationspage' && param['archived'] != true}">
                                                                    <c:url value="Controller" var="cancelReservation">
                                                                        <c:param name="command"
                                                                            value="gotocancelreservationpage" />
                                                                        <c:param name="id" value="${reservation.id}" />
                                                                        <c:param name="id2" value="2" />
                                                                        <c:param name="name"
                                                                            value="${reservation.hotelName}" />
                                                                        <c:param name="statickey" value="reservation" />
                                                                        <c:param name="staticvalue"
                                                                            value="${reservation.checkInDate} - ${reservation.checkOutDate},  ${reservation.roomName}" />
                                                                        <c:param name="returnurl"
                                                                            value="${sessionScope.lastUrl}" />

                                                                    </c:url>
                                                                    <a href="${cancelReservation}"
                                                                        class="btn btn-danger url-adjustment-target-1"
                                                                        role="button">
                                                                        <span
                                                                            class="glyphicon glyphicon-remove-sign"></span>
                                                                        <fmt:message key="cancel" />
                                                                    </a>
                                                                </c:if>
                                                                <c:if
                                                                    test="${(param['command'] == 'gotohotelreservationspage' || param['command'] == 'gotoroomreservationspage') && param['archived'] != true && reservation.status == 0}">
                                                                    <c:url value="Controller" var="acceptReservation">
                                                                        <c:param name="command"
                                                                            value="gotoacceptreservationpage" />
                                                                        <c:param name="id" value="${reservation.id}" />
                                                                        <c:param name="name"
                                                                            value="${reservation.hotelName}" />
                                                                        <c:param name="statickey" value="reservation" />
                                                                        <c:param name="staticvalue"
                                                                            value="${reservation.checkInDate} - ${reservation.checkOutDate},  ${reservation.roomName}" />
                                                                        <c:param name="returnurl"
                                                                            value="${sessionScope.lastUrl}" />
                                                                    </c:url>
                                                                    <a href="${acceptReservation}"
                                                                        class="btn btn-success" role="button">
                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                        <fmt:message key="accept" />
                                                                    </a>
                                                                </c:if>
                                                                <c:if
                                                                    test="${(param['command'] == 'gotohotelreservationspage' || param['command'] == 'gotoroomreservationspage') && param['archived'] != true && reservation.status == 1}">
                                                                    <c:url value="Controller" var="archiveReservation">
                                                                        <c:param name="command"
                                                                            value="gotoarchivereservationpage" />
                                                                        <c:param name="id" value="${reservation.id}" />
                                                                        <c:param name="id2" value="1" />
                                                                        <c:param name="name"
                                                                            value="${reservation.hotelName}" />
                                                                        <c:param name="statickey" value="reservation" />
                                                                        <c:param name="staticvalue"
                                                                            value="${reservation.checkInDate} - ${reservation.checkOutDate},  ${reservation.roomName}" />
                                                                        <c:param name="returnurl"
                                                                            value="${sessionScope.lastUrl}" />
                                                                    </c:url>
                                                                    <a href="${archiveReservation}"
                                                                        class="btn btn-success url-adjustment-target-1"
                                                                        role="button">
                                                                        <span
                                                                            class="glyphicon glyphicon-compressed"></span>
                                                                        <fmt:message key="archive" />
                                                                    </a>
                                                                </c:if>
                                                                <c:if
                                                                    test="${(param['command'] == 'gotohotelreservationspage' || param['command'] == 'gotoroomreservationspage') && param['archived'] != true}">
                                                                    <c:url value="Controller" var="rejectReservation">
                                                                        <c:param name="command"
                                                                            value="gotorejectreservationpage" />
                                                                        <c:param name="id" value="${reservation.id}" />
                                                                        <c:param name="id2" value="0" />
                                                                        <c:param name="name"
                                                                            value="${reservation.hotelName}" />
                                                                        <c:param name="statickey" value="reservation" />
                                                                        <c:param name="staticvalue"
                                                                            value="${reservation.checkInDate} - ${reservation.checkOutDate},  ${reservation.roomName}" />
                                                                        <c:param name="returnurl"
                                                                            value="${sessionScope.lastUrl}" />
                                                                    </c:url>
                                                                    <a href="${rejectReservation}"
                                                                        class="btn btn-danger url-adjustment-target-1"
                                                                        role="button">
                                                                        <span
                                                                            class="glyphicon glyphicon-remove-sign"></span>
                                                                        <fmt:message key="reject" />
                                                                    </a>
                                                                </c:if>
                                                                <c:if
                                                                    test="${param['command'] == 'gotouserreservationspage' && param['archived'] == true && reservation.status == 1}">
                                                                    <button type="button" class="btn btn-default"
                                                                        data-toggle="modal"
                                                                        data-target="#reviewModal${reservation.id}">
                                                                        <fmt:message key="writeReview" />
                                                                    </button>
                                                                    <div id="reviewModal${reservation.id}"
                                                                        class="modal fade" role="dialog">
                                                                        <div class="modal-dialog">
                                                                            <div class="modal-content">
                                                                                <div class="modal-header">
                                                                                    <button type="button" class="close"
                                                                                        data-dismiss="modal">&times;</button>
                                                                                    <h4 class="modal-title">
                                                                                        <fmt:message
                                                                                            key="writeReview" />
                                                                                    </h4>
                                                                                </div>
                                                                                <div class="modal-body">
                                                                                    <form action="Controller" method="POST"
                                                                                        class="addReviewForm"
                                                                                        role="form">
                                                                                        <input type="hidden"
                                                                                            name="command"
                                                                                            value="createreview">
                                                                                        <input type="hidden"
                                                                                            name="reservationId"
                                                                                            value="${reservation.id}">
                                                                                        <div class="form-group">
                                                                                            <label>
                                                                                                <fmt:message
                                                                                                    key="reservation" />
                                                                                                :
                                                                                            </label>
                                                                                            <p
                                                                                                class="form-control-static">
                                                                                                <fmt:message
                                                                                                    key="room" />:
                                                                                                <c:out
                                                                                                    value=" ${reservation.roomName}, " />
                                                                                                <fmt:message
                                                                                                    key="hotel" />:
                                                                                                <c:out
                                                                                                    value=" ${reservation.hotelName}, " />
                                                                                                <fmt:message
                                                                                                    key="reservationDates" />
                                                                                                :
                                                                                                <c:out
                                                                                                    value=" ${reservation.checkInDate} - ${reservation.checkOutDate}" />
                                                                                            </p>
                                                                                        </div>
                                                                                        <div class="form-group">
                                                                                            <label for="title">
                                                                                                <fmt:message
                                                                                                    key="title" />:
                                                                                            </label>
                                                                                            <input class="form-control"
                                                                                                type="text" name="title"
                                                                                                required>
                                                                                        </div>
                                                                                        <div class="form-group">
                                                                                            <label for="text">
                                                                                                <fmt:message
                                                                                                    key="text" />:
                                                                                                <textarea
                                                                                                    class="form-control"
                                                                                                    type="text"
                                                                                                    name="text"
                                                                                                    maxlength="2000"
                                                                                                    rows="25"
                                                                                                    cols="80"></textarea>
                                                                                            </label>
                                                                                        </div>
                                                                                        <div class="form-group">
                                                                                            <label for="rating">
                                                                                                <fmt:message
                                                                                                    key="rating" />:
                                                                                            </label>
                                                                                            <div class="input-group">
                                                                                                <span
                                                                                                    class="input-group-addon"><i
                                                                                                        class="glyphicon glyphicon-star"></i></span>
                                                                                                <input
                                                                                                    class="form-control"
                                                                                                    type="number"
                                                                                                    name="rating"
                                                                                                    min="1" max="10"
                                                                                                    required>
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="form-group">
                                                                                            <button type="submit"
                                                                                                class="btn btn-info">
                                                                                                <fmt:message
                                                                                                    key="send" />
                                                                                            </button>
                                                                                        </div>

                                                                                    </form>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </c:if>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="panel-footer">
                                        <c:import url="pagination.jsp" />
                                    </div>
                                    <script>
                                        <c:import url="../js/adjustUrlsForDeletionLinksOnList.js" />
                                        <c:import url="../js/addLinksToPills.js" />
                                    </script>
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:import url="footer.jsp" />
                </body>

                </html>