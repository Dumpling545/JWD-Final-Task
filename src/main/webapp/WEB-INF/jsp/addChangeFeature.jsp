<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" import="by.tc.task05.entity.Room"
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
                                        <c:when test="${param['command'] == 'gotoaddroomfeaturepage'}">
                                            <c:out value="${param['roomname']}"/>:<fmt:message key="addRoomFeatures" var="fheader" />
                                        </c:when>
                                        <c:when test="${param['command'] == 'gotochangeroomfeaturepage'}">
                                            <c:out value="${param['roomname']}"/>:<fmt:message key="changeRoomFeatures" var="fheader" />
                                        </c:when>
                                    </c:choose>
                                    <c:out value="${fheader}" />
                                </div>
                                <div class="panel-body">
                                    <form id="regForm" <c:choose>
                                        <c:when test="${param['command'] == 'gotoaddroomfeaturepage'}">
                                            action="Controller?command=addroomfeature&id=${param['id']}"
                                        </c:when>
                                        <c:when test="${param['command'] == 'gotochangeroomfeaturepage'}">
                                            action="Controller?command=changeroomfeature&id=${requestScope.feature.roomId}"
                                        </c:when>
                                        </c:choose>
                                        method="post">
                                        <div class="form-group">
                                            <label>
                                                <fmt:message key="hotel" />:
                                            </label>
                                            <p class="form-control-static">
                                                <c:out value="${param['hotelname']}" />
                                            </p>
                                        </div>
                                        <div class="form-group">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="airconditioning" value="1" 
                                                    <c:if test="${param['command'] == 'gotochangeroomfeaturepage' && requestScope.feature.hasAirconditioning}">checked</c:if>>
                                                    <fmt:message key="airconditioning" />
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="heating" value="1" 
                                                    <c:if test="${param['command'] == 'gotochangeroomfeaturepage' && requestScope.feature.hasHeating}">checked</c:if>>
                                                    <fmt:message key="heating" />
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="balcony" value="1" 
                                                    <c:if test="${param['command'] == 'gotochangeroomfeaturepage' && requestScope.feature.hasBalcony}">checked</c:if>>
                                                    <fmt:message key="balcony" />
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="tv" value="1" 
                                                    <c:if test="${param['command'] == 'gotochangeroomfeaturepage' && requestScope.feature.hasTV}">checked</c:if>>
                                                    <fmt:message key="tv" />
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="refrigerator" value="1" 
                                                    <c:if test="${param['command'] == 'gotochangeroomfeaturepage' && requestScope.feature.hasRefrigerator}">checked</c:if>>
                                                    <fmt:message key="refrigerator" />
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="kitchen" value="1" 
                                                    <c:if test="${param['command'] == 'gotochangeroomfeaturepage' && requestScope.feature.hasKitchen}">checked</c:if>>
                                                    <fmt:message key="kitchen" />
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="hairDryer" value="1" 
                                                    <c:if test="${param['command'] == 'gotochangeroomfeaturepage' && requestScope.feature.hasHairDryer}">checked</c:if>>
                                                    <fmt:message key="hairDryer" />
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="toilet" value="1" 
                                                    <c:if test="${param['command'] == 'gotochangeroomfeaturepage' && requestScope.feature.hasToilet}">checked</c:if>>
                                                    <fmt:message key="toilet" />
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="shower" value="1" 
                                                    <c:if test="${param['command'] == 'gotochangeroomfeaturepage' && requestScope.feature.hasShower}">checked</c:if>>
                                                    <fmt:message key="shower" />
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="washingMachine" value="1" 
                                                    <c:if test="${param['command'] == 'gotochangeroomfeaturepage' && requestScope.feature.hasWashingMachine}">checked</c:if>>
                                                    <fmt:message key="washingMachine" />
                                                </label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="description">
                                                <fmt:message key="description" />:
                                                <textarea class="form-control" type="text" id="description"
                                                    name="description" maxlength="2000" rows="25" cols="80"><c:if test="${param['command'] == 'gotochangeroomfeaturepage'}"><c:out value="${requestScope.feature.description}" /></c:if></textarea>
                                            </label>
                                        </div>
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