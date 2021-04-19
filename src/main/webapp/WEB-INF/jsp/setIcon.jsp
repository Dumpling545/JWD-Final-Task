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
                <fmt:message key="uploadNewImage" var="upload" />
                <div class="container">
                    <c:import url="messagePanel.jsp" />
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
                                    <form class="signinup-form" enctype="multipart/form-data"
                                        action="Controller?command=${requestScope.command}&id=${param['id'] == null ? 0 : param['id']}"
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
                                        <div class="form-group"><label for="avatar">
                                                <fmt:message key="avatarImage" />:
                                            </label>
                                            <fmt:bundle basename="by.tc.task05.bundle.misc">
                                                <fmt:message key="imageTypes" var="types" /><br />
                                            </fmt:bundle>
                                            <input class="form-control" type="file" id="${requestScope.image}"
                                                name="${requestScope.image}" accept="${types}" required>
                                            <span class="help-block">
                                                <fmt:message key="imageHelp" />: <c:out value="${types}" />
                                            </span>
                                        </div>
                                        <c:import url="passwordConfirmationFormGroup.jsp" />
                                        <div class="form-group">
                                            <input class="form-control btn btn-primary" type="submit" value="${upload}">
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