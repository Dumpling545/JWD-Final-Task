<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <fmt:setLocale value="${cookie['lang'].value}" scope="session" />
            <fmt:setBundle basename="by.tc.task05.bundle.WebsiteTextBundle" />
            <!DOCTYPE html>
            <html>
            <c:import url="meta.jsp" />
            <fmt:bundle basename="by.tc.task05.bundle.misc">
                <fmt:message key="emailRegex" var="emailRegex" />
            </fmt:bundle>

            <body>
                <c:import url="header.jsp" />
                <div class="container">
                    <c:import url="messagePanel.jsp" />
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <strong>
                                        <c:out value="${param['name']}" />
                                    </strong>:
                                    <fmt:message key="addAdmin" var="addAdmin" />
                                    <c:out value="${addAdmin}" />
                                </div>
                                <div class="panel-body">
                                    <form id="regForm"
                                        action="Controller?command=addadmin&id=${param['id'] == null ? 0 : param['id']}&name=${param['name']}"
                                        method="post">
                                        <div class="form-group"><label for="email">
                                                <fmt:message key="emailOfNewAdmin" />:
                                            </label>
                                            <input class="form-control" type="email" id="email" name="email"
                                                pattern="${emailRegex}" required>
                                        </div>
                                        <c:import url="passwordConfirmationFormGroup.jsp" />
                                        <div class="form-group">
                                            <input class="form-control btn btn-primary" type="submit"
                                                value="${addAdmin}">
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