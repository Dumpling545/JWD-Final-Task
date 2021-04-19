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
                <c:choose>
                    <c:when test="${requestScope.buttonTextBundleKey != null}">
                        <fmt:message key="${requestScope.buttonTextBundleKey}" var="buttonText" />
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="delete" var="buttonText" />
                    </c:otherwise>
                </c:choose>
                <div class="container">
                    <c:import url="messagePanel.jsp" />
                    <fmt:bundle basename="by.tc.task05.bundle.misc">
                        <fmt:message key="passwordRegex" var="passwordRegex" />
                    </fmt:bundle>
                    <c:set var="subclass" value="primary" scope="page"/>
                    <c:if test="${requestScope.dangerous != false}">
                        <div class="alert alert-danger">
                            <fmt:message key="dangerousWarning" />
                        </div>
                        <c:set var="subclass" value="danger" scope="page"/>
                    </c:if>
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
                                                <label>
                                                    <fmt:message key="${param['statickey']}" />:
                                                </label>
                                                <p class="form-control-static">
                                                    <c:out value="${param['staticvalue']}" />
                                                </p>
                                            </div>
                                        </c:if>
                                        <c:import url="passwordConfirmationFormGroup.jsp" />
                                        <div class="form-group">
                                            <input class="form-control btn btn-${subclass}" type="submit"
                                                value="${buttonText}">
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