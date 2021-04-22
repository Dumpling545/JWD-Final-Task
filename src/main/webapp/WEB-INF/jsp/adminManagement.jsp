<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
    import="by.tc.task05.entity.User, by.tc.task05.utils.ListPart" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
                            <div class="col-sm-8 col-sm-offset-2">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">
                                            <c:if test="${param['name'] != null}">
                                                <c:url value="Controller" var="hotelPage">
                                                    <c:param name="command" value="gotohotelpage" />
                                                    <c:param name="hotelid" value="${param['id']}" />
                                                </c:url>
                                                <a href="${hotelPage}" class="btn btn-default" role="button">
                                                    <strong>
                                                        <c:out value="${param['name']}" />
                                                    </strong>:
                                                </a>
                                            </c:if>
                                            <fmt:message key="adminManagement" />
                                            <c:url value="Controller" var="addAdmin">
                                                <c:param name="command" value="gotoaddadminpage" />
                                                <c:param name="id" value="${param['id']}" />
                                                <c:param name="name" value="${param['name']}" />
                                                <c:param name="returnurl" value="${sessionScope.lastUrl}" />
                                            </c:url>
                                            <a href="${addAdmin}" class="btn btn-success" role="button">
                                                <span class="glyphicon glyphicon-plus"></span>
                                                <fmt:message key="addAdmin" />
                                            </a>
                                        </h3>
                                        <c:import url="pageResizer.jsp" />
                                    </div>
                                    <div class="panel-body">
                                        <ul class="list-group">
                                            <c:forEach var="admin" items="${requestScope.admins}">
                                                <li class="list-group-item">
                                                    <div class="media">
                                                        <div class="media-left">
                                                            <fmt:bundle basename="by.tc.task05.bundle.misc">
                                                                <fmt:message key="fallbackImageUrl"
                                                                    var="fallbackImageUrl" />
                                                            </fmt:bundle>
                                                            <img src="/fileServer/images/${admin.avatar}"
                                                                onerror="this.onerror=null; this.src='${fallbackImageUrl}'"
                                                                class="media-object img-thumbnail" style="width:10vw"
                                                                alt="" />
                                                        </div>
                                                        <div class="media-body">
                                                            <ul class="list-group">
                                                                <li class="list-group-item list-group-item-info">
                                                                    <strong>
                                                                        <fmt:message key="email" />:
                                                                    </strong>
                                                                    <c:out value="${admin.email}" />
                                                                </li>
                                                                <li class="list-group-item list-group-item-info">
                                                                    <strong>
                                                                        <fmt:message key="firstName" />:
                                                                    </strong>
                                                                    <c:out value="${admin.firstName}" />
                                                                </li>
                                                                <li class="list-group-item list-group-item-info">
                                                                    <strong>
                                                                        <fmt:message key="lastName" />:
                                                                    </strong>
                                                                    <c:out value="${admin.lastName}" />
                                                                </li>
                                                                <li class="list-group-item list-group-item-info">
                                                                    <c:url value="Controller" var="deleteAdmin">
                                                                        <c:param name="command"
                                                                            value="gotodeleteadminpage" />
                                                                        <c:param name="id" value="${admin.id}" />
                                                                        <c:param name="id2" value="${param['id']}" />
                                                                        <fmt:message key="hotel" var="hotelheader" />
                                                                        <fmt:message key="admin" var="adminheader" />
                                                                        <c:param name="name" value="${param['name']}" />
                                                                        <c:param name="statickey" value="adminEmail" />
                                                                        <c:param name="staticvalue"
                                                                            value="${admin.email}" />
                                                                        <c:param name="returnurl"
                                                                            value="${sessionScope.lastUrl}" />

                                                                    </c:url>
                                                                    <a href="${deleteAdmin}" class="btn btn-danger url-adjustment-object-1 url-adjustment-target-1"
                                                                        role="button">
                                                                        <span class="glyphicon glyphicon-remove"></span>
                                                                        <fmt:message key="deleteAdmin" />
                                                                    </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                    <div class="panel-footer">
                                        <c:import url="pagination.jsp" />
                                    </div>
                                    <script>
                                        <c:import url="../js/adjustUrlsForDeletionLinksOnList.js" />
                                    </script>
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:import url="footer.jsp" />
                </body>

                </html>