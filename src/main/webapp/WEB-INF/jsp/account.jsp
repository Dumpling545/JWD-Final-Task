<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" import="by.tc.task05.entity.User"
    %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <fmt:setLocale value="${cookie['lang'].value}" scope="session" />
            <fmt:setBundle basename="by.tc.task05.bundle.WebsiteTextBundle" />
            <!DOCTYPE html>
            <html>
            <c:import url="meta.jsp" />
            <fmt:message key="avatarImage" var="avatarAlt" />

            <body>
                <c:import url="header.jsp" />
                <div class="container">
                    <c:import url="messagePanel.jsp" />
                    <div class="media">
                        <div class="media-left">
                            <div class="hovereffect">
                                <fmt:bundle basename="by.tc.task05.bundle.misc">
                                    <fmt:message key="fallbackImageUrl" var="fallbackImageUrl" />
                                </fmt:bundle>
                                <img src="/fileServer/images/${requestScope.user.avatar}"
                                    onerror="this.onerror=null; this.src='${fallbackImageUrl}'"
                                    class="media-object img-thumbnail" alt="${avatarAlt}" />
                                <div class="overlay">
                                    <c:url value="Controller" var="setAvatar">
                                        <c:param name="command" value="gotosetavatarpage" />
                                    </c:url>
                                    <a class="info" href="${setAvatar}">
                                        <fmt:message key="setAvatar" />
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading">
                                <fmt:message key="accountInfoHeader" />
                            </h4>
                            <ul class="list-group">
                                <li class="list-group-item list-group-item-info">
                                    <strong>
                                        <fmt:message key="email" />:
                                    </strong>
                                    <c:out value="${requestScope.user.email}" />
                                    <c:url value="Controller" var="editEmail">
                                        <c:param name="command" value="gotoeditemailpage" />
                                    </c:url>
                                    <a class="label label-success" href="${editEmail}">
                                        <span class="glyphicon glyphicon-pencil"></span>
                                        <fmt:message key="editEmail" />
                                    </a>
                                </li>
                                <li class="list-group-item list-group-item-info">
                                    <strong>
                                        <fmt:message key="firstName" />:
                                    </strong>
                                    <c:out value="${requestScope.user.firstName}" />
                                    <c:url value="Controller" var="editInfo">
                                        <c:param name="command" value="gotoedituserinfopage" />
                                    </c:url>
                                    <a class="label label-success" href="${editInfo}">
                                        <span class="glyphicon glyphicon-pencil"></span>
                                        <fmt:message key="editUserInfo" />
                                    </a>
                                </li>
                                <li class="list-group-item list-group-item-info">
                                    <strong>
                                        <fmt:message key="lastName" />:
                                    </strong>
                                    <c:out value="${requestScope.user.lastName}" />
                                    <c:url value="Controller" var="editInfo">
                                        <c:param name="command" value="gotoedituserinfopage" />
                                    </c:url>
                                    <a class="label label-success" href="${editInfo}">
                                        <span class="glyphicon glyphicon-pencil"></span>
                                        <fmt:message key="editUserInfo" />
                                    </a>
                                </li>
                                <li class="list-group-item list-group-item-info">
                                    <strong>
                                        <fmt:message key="userProfileManagement" />:
                                    </strong>
                                    <c:url value="Controller" var="editPassword">
                                        <c:param name="command" value="gotoeditpasswordpage" />
                                    </c:url>
                                    <a href="${editPassword}" class="btn btn-success" role="button">
                                        <span class="glyphicon glyphicon-pencil"></span>
                                        <fmt:message key="editPassword" />
                                    </a>
                                    <c:url value="Controller" var="deleteAccount">
                                        <c:param name="command" value="gotodeleteaccountpage" />
                                    </c:url>
                                    <a href="${deleteAccount}" class="btn btn-danger" role="button">
                                        <span class="glyphicon glyphicon-remove"></span>
                                        <fmt:message key="deleteAccount" />
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <c:import url="footer.jsp" />
                <style>
                    <c:import url="../css/changeImage.css"/>
                </style>
            </body>

            </html>