<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
    import="by.tc.task05.entity.Hotel, by.tc.task05.entity.Room, by.tc.task05.utils.ListPart" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
                <fmt:setLocale value="${cookie['lang'].value}" scope="session" />
                <fmt:setBundle basename="by.tc.task05.bundle.WebsiteTextBundle" />
                <!DOCTYPE html>
                <html>
                <c:import url="meta.jsp" />

                <body>
                    <fmt:bundle basename="by.tc.task05.bundle.misc">
                        <fmt:message key="fallbackImageUrl" var="fallbackImageUrl" />
                    </fmt:bundle>
                    <c:import url="header.jsp" />
                    <div class="container">
                        <c:import url="messagePanel.jsp" />
                        <div class="row">
                            <div class="col-sm-8 col-sm-offset-2">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">
                                            <fmt:message key="hotelPage" />
                                        </h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class="media">
                                            <div class="media-left">
                                                <c:choose>
                                                    <c:when test="${requestScope.isAdmin}">
                                                        <div class="hovereffect">
                                                            <img src="/fileServer/images/${requestScope.hotel.icon}"
                                                                onerror="this.onerror=null; this.src='${fallbackImageUrl}'"
                                                                class="media-object img-thumbnail" style="width:10vw"
                                                                alt="" />
                                                            <div class="overlay">
                                                                <c:url value="Controller" var="setHotelIcon">
                                                                    <c:param name="command"
                                                                        value="gotosethoteliconpage" />
                                                                    <c:param name="id"
                                                                        value="${requestScope.hotel.id}" />
                                                                    <c:param name="name"
                                                                        value="${requestScope.hotel.name}" />
                                                                    <c:param name="returnurl"
                                                                        value="${sessionScope.lastUrl}" />
                                                                </c:url>
                                                                <a class="info" href="${setHotelIcon}">
                                                                    <fmt:message key="setIcon" />
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img src="/fileServer/images/${requestScope.hotel.icon}"
                                                            onerror="this.onerror=null; this.src='${fallbackImageUrl}'"
                                                            class="media-object img-thumbnail" style="width:10vw"
                                                            alt="" />
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                            <div class="media-body">
                                                <h4 class="media-heading">
                                                    <c:out value="${requestScope.hotel.name}" />
                                                </h4>
                                                <ul class="list-group">
                                                    <li class="list-group-item list-group-item-info">
                                                        <strong>
                                                            <fmt:message key="location" />:
                                                        </strong>
                                                        <c:out value="${requestScope.hotel.cachedAddress}" />
                                                    </li>
                                                    <li class="list-group-item list-group-item-info">
                                                        <strong>
                                                            <fmt:message key="bankAccount" />:
                                                        </strong>
                                                        <c:out value="${requestScope.hotel.bankAccount}" />
                                                    </li>
                                                    <li class="list-group-item list-group-item-info">
                                                        <strong>
                                                            <fmt:message key="locationOnMap" />:
                                                        </strong>
                                                        <button href="#" class="btn btn-info" data-toggle="collapse"
                                                            data-target="#map${requestScope.hotel.id}">
                                                            <span class="glyphicon glyphicon-eye-open"></span>
                                                            <fmt:message key="show" />
                                                        </button>
                                                        <div id="map${requestScope.hotel.id}" class="collapse">
                                                            <img
                                                                src='https://maps.locationiq.com/v3/staticmap?key=pk.c46d63a472dc505f81781cfbd989a154&center=${requestScope.hotel.latitudeAddress},${requestScope.hotel.longitudeAddress}&zoom=12&size=280x280&markers=icon:large-blue-cutout|${requestScope.hotel.latitudeAddress},${requestScope.hotel.longitudeAddress}'>
                                                        </div>
                                                    </li>
                                                    <c:if test="${requestScope.isAdmin}">
                                                        <li class="list-group-item list-group-item-info">
                                                            <c:url value="Controller" var="changeHotel">
                                                                <c:param name="command" value="gotochangehotelpage" />
                                                                <c:param name="id" value="${requestScope.hotel.id}" />
                                                                <c:param name="returnurl"
                                                                    value="${sessionScope.lastUrl}" />
                                                            </c:url>
                                                            <a href="${changeHotel}" class="btn btn-success"
                                                                role="button">
                                                                <span class="glyphicon glyphicon-pencil"></span>
                                                                <fmt:message key="changeHotel" />
                                                            </a>
                                                            <c:url value="Controller" var="admins">
                                                                <c:param name="command" value="gotoadminspage" />
                                                                <c:param name="id" value="${requestScope.hotel.id}" />
                                                                <c:param name="name"
                                                                    value="${requestScope.hotel.name}" />
                                                            </c:url>
                                                            <a target="_blank" href="${admins}" class="btn btn-info"
                                                                role="button">
                                                                <span class="glyphicon glyphicon-user"></span>
                                                                <fmt:message key="admins" />
                                                            </a>
                                                            <c:url value="Controller" var="reservations">
                                                                <c:param name="command"
                                                                    value="gotohotelreservationspage" />
                                                                <c:param name="hotelid" value="${hotel.id}" />
                                                                <c:param name="name" value="${hotel.name}" />
                                                            </c:url>
                                                            <a target="_blank" href="${reservations}"
                                                                class="btn btn-info" role="button">
                                                                <fmt:message key="reservations" />
                                                            </a>
                                                            <c:url value="Controller" var="hotelManagementLink">
                                                                <c:param name="command"
                                                                    value="gotohotelmanagementpage" />
                                                            </c:url>
                                                            <c:url value="Controller" var="deleteHotel">
                                                                <c:param name="command" value="gotodeletehotelpage" />
                                                                <c:param name="id" value="${requestScope.hotel.id}" />
                                                                <c:param name="name"
                                                                    value="${requestScope.hotel.name}" />
                                                                <c:param name="returnurl"
                                                                    value="${hotelManagementLink}" />
                                                                <c:param name="cancelurl"
                                                                    value="${sessionScope.lastUrl}" />
                                                            </c:url>
                                                            <a href="${deleteHotel}" class="btn btn-danger"
                                                                role="button">
                                                                <span class="glyphicon glyphicon-remove"></span>
                                                                <fmt:message key="deleteHotel" />
                                                            </a>
                                                        </li>
                                                    </c:if>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-8 col-sm-offset-2">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">
                                            <fmt:message key="rooms" />
                                            <c:if test="${requestScope.isAdmin}">
                                                <c:url value="Controller" var="addRoom">
                                                    <c:param name="command" value="gotoaddroompage" />
                                                    <c:param name="hotelid" value="${requestScope.hotel.id}" />
                                                    <c:param name="hotelname" value="${requestScope.hotel.name}" />
                                                    <c:param name="returnurl" value="${sessionScope.lastUrl}" />
                                                </c:url>
                                                <a href="${addRoom}" class="btn btn-success" role="button">
                                                    <span class="glyphicon glyphicon-plus"></span>
                                                    <fmt:message key="addRoom" />
                                                </a>
                                            </c:if>
                                        </h3>
                                        <c:import url="pageResizer.jsp" />
                                    </div>
                                    <div class="panel-body">
                                        <ul class="list-group">
                                            <c:forEach var="room" items="${requestScope.rooms}">
                                                <li class="list-group-item">
                                                    <div class="media">
                                                        <div class="media-left">
                                                            <c:choose>
                                                                <c:when test="${requestScope.isAdmin}">
                                                                    <div class="hovereffect">
                                                                        <img src="/fileServer/images/${room.icon}"
                                                                            onerror="this.onerror=null; this.src='${fallbackImageUrl}'"
                                                                            class="media-object img-thumbnail"
                                                                            style="width:10vw" alt="" />
                                                                        <div class="overlay">
                                                                            <c:url value="Controller" var="setIcon">
                                                                                <c:param name="command"
                                                                                    value="gotosetroomiconpage" />
                                                                                <c:param name="id" value="${room.id}" />
                                                                                <c:param name="name"
                                                                                    value="${room.name}" />
                                                                                <c:param name="returnurl"
                                                                                    value="${sessionScope.lastUrl}" />
                                                                                <c:param name="statickey"
                                                                                    value="hotel" />
                                                                                <c:param name="staticvalue"
                                                                                    value="${requestScope.hotel.name}" />
                                                                            </c:url>
                                                                            <a class="info" href="${setIcon}">
                                                                                <fmt:message key="setIcon" />
                                                                            </a>
                                                                        </div>
                                                                    </div>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <img src="/fileServer/images/${room.icon}"
                                                                        onerror="this.onerror=null; this.src='${fallbackImageUrl}'"
                                                                        class="media-object img-thumbnail"
                                                                        style="width:10vw" alt="" />
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </div>

                                                        <div class="media-body">
                                                            <h4 class="media-heading">
                                                                <c:out value="${room.name}" />
                                                            </h4>
                                                            <ul class="list-group">
                                                                <li class="list-group-item list-group-item-info">
                                                                    <c:url value="Controller" var="roomPage">
                                                                        <c:param name="command" value="gotoroompage" />
                                                                        <c:param name="roomid" value="${room.id}" />
                                                                    </c:url>
                                                                    <a target="_blank" href="${roomPage}"
                                                                        class="btn btn-info" role="button">
                                                                        <fmt:message key="goToRoomPage" />
                                                                    </a>
                                                                </li>
                                                                <li class="list-group-item list-group-item-info">
                                                                    <strong>
                                                                        <fmt:message key="numberOfBeds" />:
                                                                    </strong>
                                                                    <c:out value="${room.numberOfBeds}" />
                                                                    <span class="glyphicon glyphicon-bed"></span>
                                                                </li>
                                                                <li class="list-group-item list-group-item-info">
                                                                    <strong>
                                                                        <fmt:message key="pricePerNight" />:
                                                                    </strong>
                                                                    <c:out value="${room.cost}" />$
                                                                </li>
                                                                <li class="list-group-item list-group-item-info">
                                                                    <strong>
                                                                        <fmt:message key="shortDescription" />:
                                                                    </strong>
                                                                    <c:out value="${room.shortDescription}" />
                                                                </li>
                                                                <c:if test="${requestScope.isAdmin}">
                                                                    <li class="list-group-item list-group-item-info">
                                                                        <c:url value="Controller" var="changeRoom">
                                                                            <c:param name="hotelname"
                                                                                value="${requestScope.hotel.name}" />
                                                                            <c:param name="command"
                                                                                value="gotochangeroompage" />
                                                                            <c:param name="id" value="${room.id}" />
                                                                            <c:param name="returnurl"
                                                                                value="${sessionScope.lastUrl}" />
                                                                        </c:url>
                                                                        <a href="${changeRoom}" class="btn btn-success"
                                                                            role="button">
                                                                            <span
                                                                                class="glyphicon glyphicon-pencil"></span>
                                                                            <fmt:message key="changeRoom" />
                                                                        </a>
                                                                        <c:url value="Controller" var="reservations">
                                                                            <c:param name="command"
                                                                                value="gotoroomreservationspage" />
                                                                            <c:param name="roomid" value="${room.id}" />
                                                                            <c:param name="name" value="${room.name}" />
                                                                        </c:url>
                                                                        <a target="_blank" href="${reservations}"
                                                                            class="btn btn-info" role="button">
                                                                            <fmt:message key="reservations" />
                                                                        </a>
                                                                        <c:url value="Controller" var="deleteRoom">
                                                                            <c:param name="command"
                                                                                value="gotodeleteroompage" />
                                                                            <c:param name="id" value="${room.id}" />
                                                                            <c:param name="name" value="${room.name}" />
                                                                            <c:param name="statickey" value="hotel" />
                                                                            <c:param name="staticvalue"
                                                                                value="${requestScope.hotel.name}" />
                                                                            <c:param name="returnurl"
                                                                                value="${sessionScope.lastUrl}" />
                                                                        </c:url>
                                                                        <a href="${deleteRoom}" class="btn btn-danger url-adjustment-object-1 url-adjustment-target-1"
                                                                            role="button">
                                                                            <span
                                                                                class="glyphicon glyphicon-remove"></span>
                                                                            <fmt:message key="deleteRoom" />
                                                                        </a>
                                                                    </li>
                                                                </c:if>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                    <div class="panel-footer">
                                        <c:import url="pagination.jsp" />
                                    </div>
                                    <script>
                                        <c:import url="../js/adjustUrlsForDeletionLinksOnList.js" />
                                    </script>
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:import url="footer.jsp" />
                    <style>
                        <c:import url="../css/changeImage.css"/>
                    </style>
                </body>

                </html>