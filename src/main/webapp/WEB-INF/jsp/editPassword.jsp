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
                <fmt:bundle basename="by.tc.task05.bundle.misc">
                    <fmt:message key="passwordRegex" var="passwordRegex" />
                </fmt:bundle>
                <fmt:message key="applyChanges" var="apply" />
                <div class="container">
                    <c:import url="messagePanel.jsp" />
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <fmt:message key="editPassword" />
                                </div>
                                <div class="panel-body">
                                    <form onsubmit="return validateForm()" class="signinup-form" action="Controller"
                                        method="post">
                                        <input type="hidden" name="command" value="editPassword">
                                        <div class="form-group"><label for="newPassword">
                                                <fmt:message key="newPassword" />:
                                            </label>
                                            <input class="form-control" type="password" id="pw"
                                                pattern="${passwordRegex}" name="newPassword" required>
                                        </div>
                                        <div class="form-group"><label for="repeatNewPassword">
                                                <fmt:message key="repeatNewPassword" />:
                                            </label>
                                            <input class="form-control" type="password" id="repPw"
                                                pattern="${passwordRegex}" name="repeatNewPassword" required>
                                        </div>
                                        <div class="form-group"><label for="oldPassword">
                                                <fmt:message key="oldPassword" />:
                                            </label>
                                            <input class="form-control" type="password" id="oldPassword"
                                                pattern="${passwordRegex}" name="oldPassword" required>
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
                <script type="text/javascript">
                    var passwordRepeatError = "<fmt:message key='passwordRepeatError' />";
                    <c:import url="../js/passwordRepeatCheck.js" />
                </script>
            </body>

            </html>