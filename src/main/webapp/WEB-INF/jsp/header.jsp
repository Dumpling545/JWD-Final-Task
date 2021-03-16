<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
    import="java.util.List, by.htp.les02.bean.News" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="header">
    <div class="header-title">
        <a href="">
            <h1>
                Hotel Booking System
            </h1>
        </a>
    </div>
    <div class="header-options">
        <fmt:setLocale value="${cookie.locale}" scope="session" />
        <c:choose>
            <c:when test="${sessionScope.userId == null}">
                <c:url value="Controller" var="signInLink">
                    <c:param name="command" value="goToSignIn" />
                </c:url>
                <a href="${signInLink}">
                    <fmt:message key="signIn" />
                </a>
                <c:url value="Controller" var="registerLink">
                    <c:param name="command" value="register" />
                </c:url>
                <a href="${registerLink}">
                    <fmt:message key="register" />
                </a>
            </c:when>
            <c:otherwise>
                <c:url value="Controller" var="accountLink">
                    <c:param name="command" value="account" />
                </c:url>
                <a href="${accountLink}">
                    <fmt:message key="account" />
                </a>
                <c:url value="Controller" var="signOutLink">
                    <c:param name="command" value="signOut" />
                </c:url>
                <a href="${signOutLink}">
                    <fmt:message key="signOut" />
                </a>
            </c:otherwise>
        </c:choose>
    </div>
</div>