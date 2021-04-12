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
                <fmt:message key="delete" var="delete" />
                <div class="container">
                    <c:import url="messagePanel.jsp" />
                    <fmt:bundle basename="by.tc.task05.bundle.misc">
                        <fmt:message key="passwordRegex" var="passwordRegex" />
                    </fmt:bundle>
                    <div class="alert alert-danger">
                        <fmt:message key="deleteWarning" />
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <c:if test="${param['name'] != null}">
                                        <strong>
                                            <c:out value="${param['name']}" />
                                        </strong>:
                                    </c:if>
                                    <fmt:message key="${requestScope.header}" />
                                </div>
                                <div class="panel-body">
                                    <form class="signinup-form"
                                        action="Controller?command=${requestScope.command}&id=${param['id'] == null ? 0 : param['id']}&id2=${param['id2'] == null ? 0 : param['id2']}"
                                        method="post">
                                        <c:if test="${param['statickey'] != null && param['staticvalue'] != null}">
                                            <div class="form-group">
                                                <label><fmt:message key="${param['statickey']}" />:</label>
                                                <p class="form-control-static"><c:out value="${param['staticvalue']}" /></p>
                                            </div>
                                        </c:if>
                                        <c:import url="passwordConfirmationFormGroup.jsp" />
                                        <div class="form-group">
                                            <input class="form-control btn btn-danger" type="submit" value="${delete}">
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