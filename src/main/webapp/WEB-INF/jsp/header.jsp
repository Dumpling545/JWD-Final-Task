<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${cookie['lang'].value}" scope="session" />
<fmt:setBundle basename = "by.tc.task05.bundle.WebsiteTextBundle"/>
<div class="header">
    <div class="header-title">
                    <c:url value="Controller" var="starter">
                        <c:param name="command" value="gotostarterpage" />
                    </c:url>
        <a href="${starter}">
            <h1>
                Hotel Booking System
            </h1>
        </a>
    </div>
    <div class="header-options">
        <c:choose>
            <c:when test="${sessionScope.userId == null}">
                <c:url value="Controller" var="signInLink">
                    <c:param name="command" value="goToSignIn" />
                </c:url>
                <c:url value="Controller" var="registerLink">
                     <c:param name="command" value="gotoregistration" />
                </c:url>
                <a href="${signInLink}"><fmt:message key="signIn" /></a><a href="${registerLink}"><fmt:message key="register" /></a>
            </c:when>
            <c:otherwise>
                <c:url value="Controller" var="accountLink">
                    <c:param name="command" value="account" />
                </c:url>
                <c:url value="Controller" var="signOutLink">
                   <c:param name="command" value="signOut" />
                </c:url>
                <a href="${accountLink}"><fmt:message key="account" /></a><a href="${signOutLink}"><fmt:message key="signOut"  /></a>
            </c:otherwise>
        </c:choose>
    </div><c:import url="messagePanel.jsp" />
</div>