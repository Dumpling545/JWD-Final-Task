<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <fmt:setLocale value="${cookie['lang'].value}" scope="session" />
            <fmt:setBundle basename="by.tc.task05.bundle.WebsiteTextBundle" />
            <!DOCTYPE html>
            <html>
            <c:import url="meta.jsp" />

            <body>
                <fmt:bundle basename="by.tc.task05.bundle.misc">
                    <fmt:message key="passwordRegex" var="passwordRegex" />
                    <fmt:message key="emailRegex" var="emailRegex" />
                </fmt:bundle>
                <c:import url="header.jsp" />
                <fmt:message key="applyChanges" var="apply" />
                <div class="container">
                    <c:import url="messagePanel.jsp" />
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <fmt:message key="editEmail" />
                                </div>
                                <div class="panel-body">
                                    <form class="signinup-form" action="Controller" method="post">
                                        <input type="hidden" name="command" value="editEmail">
                                        <div class="form-group"><label for="email">
                                                <fmt:message key="email" />:
                                            </label>
                                            <input class="form-control" type="email" id="email" name="email"
                                                pattern="${emailRegex}" value="${requestScope.email}" required>
                                        </div>
                                        <div class="form-group"><label for="password">
                                                <fmt:message key="enterPasswordToVerify" />:
                                            </label>
                                            <input class="form-control" type="password" id="password"
                                                pattern="${passwordRegex}" name="password" required>
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