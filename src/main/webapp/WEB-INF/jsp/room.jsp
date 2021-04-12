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
                    <c:import url="header.jsp" />
                    <div class="container">
                        <c:import url="messagePanel.jsp" />
                        <div class="row">
                            <div class="col-sm-8 col-sm-offset-2">
                                <div class="panel-group">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h3 class="panel-title">
                                                <fmt:message key="roomPage" />
                                            </h3>
                                        </div>
                                        <div class="panel-body">
                                            <div class="media">
                                                <div class="media-left">
                                                    <c:choose>
                                                        <c:when test="${requestScope.isAdmin}">
                                                            <div class="hovereffect">
                                                                <img src="/fileServer/images/${requestScope.room.icon}"
                                                                    onerror="this.onerror=null; this.src='https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/1024px-No_image_available.svg.png'"
                                                                    class="media-object img-thumbnail"
                                                                    style="width:10vw" alt="" />
                                                                <div class="overlay">
                                                                    <c:url value="Controller" var="setIcon">
                                                                        <c:param name="command"
                                                                            value="gotosetroomiconpage" />
                                                                        <c:param name="id"
                                                                            value="${requestScope.room.id}" />
                                                                        <c:param name="name"
                                                                            value="${requestScope.room.name}" />
                                                                        <c:param name="returnurl"
                                                                            value="${sessionScope.lastUrl}" />
                                                                        <c:param name="statickey" value="hotel" />
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
                                                            <img src="/fileServer/images/${requestScope.room.icon}"
                                                                onerror="this.onerror=null; this.src='https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/1024px-No_image_available.svg.png'"
                                                                class="media-object img-thumbnail" style="width:10vw"
                                                                alt="" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                                <div class="media-body">
                                                    <h4 class="media-heading">
                                                        <c:out value="${requestScope.room.name}" />
                                                    </h4>
                                                    <ul class="list-group">
                                                        <li class="list-group-item list-group-item-info">
                                                            <strong>
                                                                <fmt:message key="numberOfBeds" />:
                                                            </strong>
                                                            <c:out value="${requestScope.room.numberOfBeds}" />
                                                            <span class="glyphicon glyphicon-bed"></span>
                                                        </li>
                                                        <li class="list-group-item list-group-item-info">
                                                            <strong>
                                                                <fmt:message key="pricePerNight" />:
                                                            </strong>
                                                            <c:out value="${requestScope.room.cost}" />$
                                                        </li>
                                                        <li class="list-group-item list-group-item-info">
                                                            <strong>
                                                                <fmt:message key="shortDescription" />:
                                                            </strong>
                                                            <c:out value="${requestScope.room.shortDescription}" />
                                                        </li>
                                                        <li class="list-group-item list-group-item-info">
                                                            <strong>
                                                                <fmt:message key="hotel" />:
                                                            </strong>
                                                            <c:url value="Controller" var="hotelPage">
                                                                <c:param name="command" value="gotohotelpage" />
                                                                <c:param name="hotelid"
                                                                    value="${requestScope.hotel.id}" />
                                                            </c:url>
                                                            <a href="${hotelPage}" class="btn btn-info" role="button">
                                                                <c:out value="${requestScope.hotel.name}" />
                                                            </a>
                                                        </li>
                                                        <li class="list-group-item list-group-item-info">
                                                            <strong>
                                                                <fmt:message key="location" />:
                                                            </strong>
                                                            <c:out value="${requestScope.hotel.cachedAddress}" />
                                                        </li>
                                                        <li class="list-group-item list-group-item-info">
                                                            <strong>
                                                                <fmt:message key="locationOnMap" />:
                                                            </strong>
                                                            <button class="btn btn-info" data-toggle="modal"
                                                                data-target="#mapModal">
                                                                <span class="glyphicon glyphicon-eye-open"></span>
                                                                <fmt:message key="show" />
                                                            </button>
                                                        </li>
                                                        <div class="modal fade" id="mapModal" role="dialog">
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
                                                        <script type="text/javascript"
                                                            src="https://leafletjs-cdn.s3.amazonaws.com/content/leaflet/master/leaflet.js"></script>
                                                        <script type="text/javascript"
                                                            src='https://tiles.locationiq.com/v3/js/liq-styles-ctrl-leaflet.js?v=0.1.8'></script>
                                                        <script type="text/javascript">
                                                            var latitude = <c:out value="${requestScope.hotel.latitudeAddress}" />;
                                                            var longtitude = <c:out value="${requestScope.hotel.longtitudeAddress}" />;
                                                            <c:import url="../js/roomMap.js" />
                                                        </script>
                                                        <c:if test="${requestScope.isAdmin}">
                                                            <li class="list-group-item list-group-item-info">
                                                                <c:url value="Controller" var="changeRoom">
                                                                    <c:param name="hotelname"
                                                                        value="${requestScope.hotel.name}" />
                                                                    <c:param name="command"
                                                                        value="gotochangeroompage" />
                                                                    <c:param name="id"
                                                                        value="${requestScope.room.id}" />
                                                                    <c:param name="returnurl"
                                                                        value="${sessionScope.lastUrl}" />
                                                                </c:url>
                                                                <a href="${changeRoom}" class="btn btn-success"
                                                                    role="button">
                                                                    <span class="glyphicon glyphicon-pencil"></span>
                                                                    <fmt:message key="changeRoom" />
                                                                </a>
                                                                <c:url value="Controller" var="deleteRoom">
                                                                    <c:param name="command"
                                                                        value="gotodeleteroompage" />
                                                                    <c:param name="id"
                                                                        value="${requestScope.room.id}" />
                                                                    <c:param name="name"
                                                                        value="${requestScope.room.name}" />
                                                                    <c:param name="statickey" value="hotel" />
                                                                    <c:param name="staticvalue"
                                                                        value="${requestScope.hotel.name}" />
                                                                    <c:param name="returnurl" value="${hotelPage}" />
                                                                    <c:param name="cancelurl"
                                                                        value="${sessionScope.lastUrl}" />
                                                                </c:url>
                                                                <a href="${deleteRoom}" class="btn btn-danger"
                                                                    role="button">
                                                                    <span class="glyphicon glyphicon-remove"></span>
                                                                    <fmt:message key="deleteRoom" />
                                                                </a>
                                                            </li>
                                                        </c:if>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h3 class="panel-title">
                                                <fmt:message key="roomFeatures" />
                                                <c:if test="${requestScope.isAdmin}">
                                                    <c:url value="Controller" var="addRoomFeature">
                                                        <c:param name="command" value="gotoaddroomfeaturepage" />
                                                        <c:param name="id" value="${requestScope.room.id}" />
                                                        <c:param name="hotelname" value="${requestScope.hotel.name}" />
                                                        <c:param name="returnurl" value="${sessionScope.lastUrl}" />
                                                    </c:url>
                                                    <a href="${addRoomFeature}" class="btn btn-success" role="button">
                                                        <span class="glyphicon glyphicon-plus"></span>
                                                        <fmt:message key="addRoomFeatures" />
                                                    </a>
                                                </c:if>
                                            </h3>
                                        </div>
                                        <div class="panel-body">
                                            <c:choose>
                                                <c:when test="${requestScope.roomFeature != null}">
                                                    <ul class="list-group">
                                                        <c:choose>
                                                            <c:when
                                                                test="${requestScope.roomFeature.hasAirconditioning}">
                                                                <li class="list-group-item list-group-item-default">
                                                                    <p class="text-success">
                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                        <fmt:message key="airconditioning" />
                                                                    </p>
                                                                </li>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <li class="list-group-item list-group-item-default">
                                                                    <p class="text-danger">
                                                                        <span class="glyphicon glyphicon-remove"></span>
                                                                        <fmt:message key="noAirconditioning" />
                                                                    </p>
                                                                </li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <c:choose>
                                                            <c:when
                                                                test="${requestScope.roomFeature.hasHeating}">
                                                                <li class="list-group-item list-group-item-default">
                                                                    <p class="text-success">
                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                        <fmt:message key="heating" />
                                                                    </p>
                                                                </li>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <li class="list-group-item list-group-item-default">
                                                                    <p class="text-danger">
                                                                        <span class="glyphicon glyphicon-remove"></span>
                                                                        <fmt:message key="noHeating" />
                                                                    </p>
                                                                </li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <c:choose>
                                                            <c:when
                                                                test="${requestScope.roomFeature.hasBalcony}">
                                                                <li class="list-group-item list-group-item-default">
                                                                    <p class="text-success">
                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                        <fmt:message key="balcony" />
                                                                    </p>
                                                                </li>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <li class="list-group-item list-group-item-default">
                                                                    <p class="text-danger">
                                                                        <span class="glyphicon glyphicon-remove"></span>
                                                                        <fmt:message key="noBalcony" />
                                                                    </p>
                                                                </li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <c:choose>
                                                            <c:when
                                                                test="${requestScope.roomFeature.hasTV}">
                                                                <li class="list-group-item list-group-item-default">
                                                                    <p class="text-success">
                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                        <fmt:message key="tv" />
                                                                    </p>
                                                                </li>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <li class="list-group-item list-group-item-default">
                                                                    <p class="text-danger">
                                                                        <span class="glyphicon glyphicon-remove"></span>
                                                                        <fmt:message key="noTv" />
                                                                    </p>
                                                                </li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <c:choose>
                                                            <c:when
                                                                test="${requestScope.roomFeature.hasRefrigerator}">
                                                                <li class="list-group-item list-group-item-default">
                                                                    <p class="text-success">
                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                        <fmt:message key="refrigerator" />
                                                                    </p>
                                                                </li>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <li class="list-group-item list-group-item-default">
                                                                    <p class="text-danger">
                                                                        <span class="glyphicon glyphicon-remove"></span>
                                                                        <fmt:message key="noRefrigerator" />
                                                                    </p>
                                                                </li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <c:choose>
                                                            <c:when
                                                                test="${requestScope.roomFeature.hasKitchen}">
                                                                <li class="list-group-item list-group-item-default">
                                                                    <p class="text-success">
                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                        <fmt:message key="kitchen" />
                                                                    </p>
                                                                </li>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <li class="list-group-item list-group-item-default">
                                                                    <p class="text-danger">
                                                                        <span class="glyphicon glyphicon-remove"></span>
                                                                        <fmt:message key="noKitchen" />
                                                                    </p>
                                                                </li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <c:choose>
                                                            <c:when
                                                                test="${requestScope.roomFeature.hasHairDryer}">
                                                                <li class="list-group-item list-group-item-default">
                                                                    <p class="text-success">
                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                        <fmt:message key="hairDryer" />
                                                                    </p>
                                                                </li>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <li class="list-group-item list-group-item-default">
                                                                    <p class="text-danger">
                                                                        <span class="glyphicon glyphicon-remove"></span>
                                                                        <fmt:message key="noHairDryer" />
                                                                    </p>
                                                                </li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <c:choose>
                                                            <c:when
                                                                test="${requestScope.roomFeature.hasToilet}">
                                                                <li class="list-group-item list-group-item-default">
                                                                    <p class="text-success">
                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                        <fmt:message key="toilet" />
                                                                    </p>
                                                                </li>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <li class="list-group-item list-group-item-default">
                                                                    <p class="text-danger">
                                                                        <span class="glyphicon glyphicon-remove"></span>
                                                                        <fmt:message key="noToilet" />
                                                                    </p>
                                                                </li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <c:choose>
                                                            <c:when
                                                                test="${requestScope.roomFeature.hasShower}">
                                                                <li class="list-group-item list-group-item-default">
                                                                    <p class="text-success">
                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                        <fmt:message key="shower" />
                                                                    </p>
                                                                </li>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <li class="list-group-item list-group-item-default">
                                                                    <p class="text-danger">
                                                                        <span class="glyphicon glyphicon-remove"></span>
                                                                        <fmt:message key="noShower" />
                                                                    </p>
                                                                </li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <c:choose>
                                                            <c:when
                                                                test="${requestScope.roomFeature.hasWashingMachine}">
                                                                <li class="list-group-item list-group-item-default">
                                                                    <p class="text-success">
                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                        <fmt:message key="washingMachine" />
                                                                    </p>
                                                                </li>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <li class="list-group-item list-group-item-default">
                                                                    <p class="text-danger">
                                                                        <span class="glyphicon glyphicon-remove"></span>
                                                                        <fmt:message key="noWashingMachine" />
                                                                    </p>
                                                                </li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </ul>
                                                </c:when>
                                                <c:otherwise>
                                                    <fmt:message key="noRoomFeatures" />
                                                </c:otherwise>
                                            </c:choose>
                                            <c:if test="${requestScope.roomFeature != null}">

                                            </c:if>
                                        </div>
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