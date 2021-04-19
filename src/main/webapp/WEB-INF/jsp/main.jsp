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
                <fmt:bundle basename="by.tc.task05.bundle.misc">
                    <fmt:message key="fallbackImageUrl" var="fallbackImageUrl" />
                </fmt:bundle>
                <c:import url="header.jsp" />
                <div class="container">
                    <c:import url="messagePanel.jsp" />
                    <div class="row">
                        <c:import url="searchRoomPanel.jsp" />
                        <div class="col-sm-9">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title">
                                        <fmt:message key="rooms" />
                                    </h3>
                                    <c:import url="pageResizer.jsp" />
                                </div>
                                <div class="panel-body">
                                    <ul class="list-group">
                                        <c:forEach var="room" items="${requestScope.rooms}">
                                            <li class="list-group-item">
                                                <div class="media">
                                                    <div class="media-left">
                                                        <img src="/fileServer/images/${room.icon}"
                                                            onerror="this.onerror=null; this.src='${fallbackImageUrl}'"
                                                            class="media-object img-thumbnail" style="width:10vw"
                                                            alt="" />
                                                    </div>
                                                    <div class="media-body">
                                                        <h4 class="media-heading">
                                                            <c:url value="Controller" var="roomPage">
                                                                <c:param name="command" value="gotoroompage" />
                                                                <c:param name="roomid" value="${room.id}" />
                                                                <c:param name="checkin" value="${param.checkin}" />
                                                                <c:param name="checkout" value="${param.checkout}" />
                                                            </c:url>
                                                            <a target="_blank" href="${roomPage}">
                                                                <c:out value="${room.name}" />
                                                            </a>
                                                        </h4>
                                                        <ul class="list-group">
                                                            <li class="list-group-item list-group-item-info">
                                                                <strong>
                                                                    <fmt:message key="hotel" />:
                                                                </strong>
                                                                <c:url value="Controller" var="hotelPage">
                                                                    <c:param name="command" value="gotohotelpage" />
                                                                    <c:param name="hotelid" value="${room.hotelId}" />
                                                                </c:url>
                                                                <a target="_blank" href="${hotelPage}"
                                                                    class="btn btn-info" role="button">
                                                                    <c:out value="${room.hotelName}" />
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
                                                            <li class="list-group-item list-group-item-info">
                                                                <strong>
                                                                    <fmt:message key="rating" />:
                                                                </strong>
                                                                <c:out value="${room.rating}" />
                                                                <span class="glyphicon glyphicon-star"></span>
                                                            </li>
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
                            </div>
                        </div>
                    </div>
                </div>
                <c:import url="footer.jsp" />
            </body>

            </html>