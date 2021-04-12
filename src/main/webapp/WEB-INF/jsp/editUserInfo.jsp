<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <fmt:setLocale value="${cookie['lang'].value}" scope="session" />
            <fmt:setBundle basename="by.tc.task05.bundle.WebsiteTextBundle" />
            <!DOCTYPE html>
            <html>
            <c:import url="meta.jsp" />

            <body>
                <c:import url="header.jsp" />
                <fmt:message key="applyChanges" var="apply" />
                <div class="container">
                    <c:import url="messagePanel.jsp" />
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <fmt:message key="editUserInfo" />
                                </div>
                                <div class="panel-body">
                                    <form class="signinup-form" action="Controller" method="post">
                                        <input type="hidden" name="command" value="editUserInfo">
                                        <div class="form-group"><label for="firstName">
                                                <fmt:message key="firstName" />:
                                            </label>
                                            <input class="form-control" type="text" id="firstName" name="firstName"
                                                value="${requestScope.userInfo.firstName}" required>
                                        </div>
                                        <div class="form-group"><label for="lastName">
                                                <fmt:message key="lastName" />:
                                            </label>
                                            <input class="form-control" type="text" id="lastName" name="lastName"
                                                value="${requestScope.userInfo.lastName}" required>
                                        </div>
                                        <div class="form-group">
                                            <input class="form-control btn btn-primary" type="submit" value="${apply}">
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