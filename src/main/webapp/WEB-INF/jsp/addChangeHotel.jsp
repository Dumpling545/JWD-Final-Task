<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" import="by.tc.task05.entity.Hotel"
    %>
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
                        <div class="col-sm-6 col-sm-offset-3">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <c:choose>
                                        <c:when test="${param['command'] == 'gotoaddhotelpage'}">
                                            <fmt:message key="addHotel" var="fheader" />
                                        </c:when>
                                        <c:when test="${param['command'] == 'gotochangehotelpage'}">
                                            <fmt:message key="changeHotel" var="fheader" />
                                        </c:when>
                                    </c:choose>
                                    <c:out value="${fheader}" />
                                </div>
                                <div class="panel-body">
                                    <form id="regForm" <c:choose>
                                        <c:when test="${param['command'] == 'gotoaddhotelpage'}">
                                            action="Controller?command=addhotel"
                                        </c:when>
                                        <c:when test="${param['command'] == 'gotochangehotelpage'}">
                                            action="Controller?command=changehotel&id=${requestScope.hotel.id}"
                                        </c:when>
                                        </c:choose>
                                        method="post">
                                        <c:choose>
                                            <c:when test="${param['command'] == 'gotoaddhotelpage'}">
                                                <input id="lat" type="hidden" name="latitude" value="17.4206485">
                                                <input id="lng" type="hidden" name="longitude" value="78.4008997">
                                            </c:when>
                                            <c:when test="${param['command'] == 'gotochangehotelpage'}">
                                                <input id="lat" type="hidden" name="latitude"
                                                    value="${requestScope.hotel.latitudeAddress}">
                                                <input id="lng" type="hidden" name="longitude"
                                                    value="${requestScope.hotel.longitudeAddress}">
                                            </c:when>
                                        </c:choose>
                                        <div class="form-group"><label for="hotelName">
                                                <fmt:message key="hotelName" />:
                                            </label>
                                            <input class="form-control" type="text" id="hotelName" name="hotelName"
                                                <c:if
                                                test="${param['command'] == 'gotochangehotelpage'}">value="${requestScope.hotel.name}"
                                            </c:if> required>
                                        </div>
                                        <div class="form-group"><label for="bankAccount">
                                                <fmt:message key="bankAccount" />:
                                            </label>
                                            <input class="form-control" type="text" id="bankAccount" name="bankAccount"
                                                <c:if
                                                test="${param['command'] == 'gotochangehotelpage'}">value="${requestScope.hotel.bankAccount}"
                                            </c:if> required>
                                        </div>
                                        <div class="form-group"><label for="cachedAddress">
                                                <fmt:message key="cachedAddress" />
                                                <a id="getAddress" class="label label-info" href="#">
                                                    <fmt:message key="loadMarkerLocation" />
                                                </a>:
                                            </label>
                                            <input id="cachedAddress" class="form-control" type="text"
                                                id="cachedAddress" name="cachedAddress" <c:if
                                                test="${param['command'] == 'gotochangehotelpage'}">value="${requestScope.hotel.cachedAddress}"
                                            </c:if> required>
                                        </div>
                                        <div id='map'></div>
                                        <script type="text/javascript"
                                            src="https://leafletjs-cdn.s3.amazonaws.com/content/leaflet/master/leaflet.js"></script>
                                        <script type="text/javascript"
                                            src='https://tiles.locationiq.com/v3/js/liq-styles-ctrl-leaflet.js?v=0.1.8'></script>
                                        <script type="text/javascript">
                                            var locationServicesError = "<fmt:message key='locationServicesError' />";
                                            <c:import url="../js/mapWithMarkerScript.js" />
                                        </script>
                                        <c:import url="passwordConfirmationFormGroup.jsp" />
                                        <div class="form-group">
                                            <input class="form-control btn btn-primary" type="submit"
                                                value="${fheader}">
                                            <c:import url="returnButton.jsp" />
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <c:import url="footer.jsp" />
            </body>

            </html>