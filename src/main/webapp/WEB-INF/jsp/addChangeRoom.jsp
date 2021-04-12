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
                                        <c:when test="${param['command'] == 'gotoaddroompage'}">
                                            <fmt:message key="addRoom" var="fheader" />
                                        </c:when>
                                        <c:when test="${param['command'] == 'gotochangeroompage'}">
                                            <fmt:message key="changeRoom" var="fheader" />
                                        </c:when>
                                    </c:choose>
                                    <c:out value="${fheader}" />
                                </div>
                                <div class="panel-body">
                                    <form id="regForm" <c:choose>
                                        <c:when test="${param['command'] == 'gotoaddroompage'}">
                                            action="Controller?command=addroom&hotelid=${param['hotelid']}"
                                        </c:when>
                                        <c:when test="${param['command'] == 'gotochangeroompage'}">
                                            action="Controller?command=changeroom&id=${requestScope.room.id}"
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
                                            <label for="roomName">
                                                <fmt:message key="roomName" />:
                                            </label>
                                            <input class="form-control" type="text" id="roomName" name="roomName" <c:if
                                                test="${param['command'] == 'gotochangeroompage'}">value="${requestScope.room.name}"
                                            </c:if> required>
                                        </div>
                                        <div class="form-group">
                                            <label for="numberOfBeds">
                                                <fmt:message key="numberOfBeds" />:
                                            </label>
                                            <div class="input-group">
                                                <span class="input-group-addon"><i
                                                        class="glyphicon glyphicon-bed"></i></span>
                                                <input class="form-control" type="number" min="1" max="20"
                                                    id="numberOfBeds" name="numberOfBeds" <c:if
                                                    test="${param['command'] == 'gotochangeroompage'}">value="${requestScope.room.numberOfBeds}"
                                                </c:if> required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="cost">
                                                <fmt:message key="pricePerNight" />:
                                            </label>
                                            <div class="input-group">
                                                <span class="input-group-addon"><i
                                                        class="glyphicon glyphicon-usd"></i></span>
                                                <input class="form-control" type="number" min="0" step="any" id="cost"
                                                    name="cost" <c:if
                                                    test="${param['command'] == 'gotochangeroompage'}">value="${requestScope.room.cost}"
                                                </c:if> required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="shortDescription">
                                                <fmt:message key="shortDescription" />:
                                            </label>
                                            <div class="input-group">
                                                <span class="input-group-addon"><i
                                                        class="glyphicon glyphicon-info-sign"></i></span>
                                                <textarea class="form-control" type="text" id="shortDescription"
                                                    name="shortDescription" maxlength="255" required><c:if test="${param['command'] == 'gotochangeroompage'}"><c:out value="${requestScope.room.shortDescription}" /></c:if></textarea>
                                            </div>
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