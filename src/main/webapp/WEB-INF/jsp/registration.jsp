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
                    <fmt:message key="emailRegex" var="emailRegex" />
                    <fmt:message key="passwordRegex" var="passwordRegex" />
                </fmt:bundle>
                <c:import url="header.jsp" />
                <div class="container">
                    <c:import url="messagePanel.jsp" />
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <fmt:message key="register" var="register" />
                                    <c:out value="${register}" />
                                </div>
                                <div class="panel-body">
                                    <form id="regForm" action="Controller" onsubmit="return validateForm()"
                                        method="post">
                                        <input type="hidden" name="command" value="savenewuser">
                                        <div class="form-group"><label for="firstName">
                                                <fmt:message key="firstName" />:
                                            </label>
                                            <input class="form-control" type="text" id="firstName" name="firstName"
                                                required>
                                        </div>
                                        <div class="form-group"><label for="lastName">
                                                <fmt:message key="lastName" />:
                                            </label>
                                            <input class="form-control" type="text" id="lastName" name="lastName"
                                                required>
                                        </div>
                                        <div class="form-group"><label for="email">
                                                <fmt:message key="email" />:
                                            </label>
                                            <input class="form-control" type="email" id="email" name="email"
                                                pattern="${emailRegex}" required>
                                        </div>
                                        <div class="form-group"><label for="password">
                                                <fmt:message key="password" />:
                                            </label>
                                            <input id="pw" class="form-control" type="password" id="password"
                                                name="password" pattern="${passwordRegex}" required>
                                        </div>
                                        <div class="form-group"><label for="repeatPassword">
                                                <fmt:message key="repeatPassword" />:
                                            </label>
                                            <input id="repPw" class="form-control" type="password" id="repeatPassword"
                                                name="repeatPassword" pattern="${passwordRegex}" required>
                                        </div>
                                        <input class="form-control btn btn-primary" type="submit" value="${register}">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <c:import url="footer.jsp" />
                <script type="text/javascript">
                    var passwordRepeatError = "<fmt:message key='passwordRepeatError' />";
                    <c:import url="../js/passwordRepeatCheck.js" />
                </script>
            </body>

            </html>