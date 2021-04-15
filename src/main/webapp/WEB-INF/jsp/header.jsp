<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <fmt:setLocale value="${cookie['lang'].value}" scope="session" />
            <fmt:setBundle basename="by.tc.task05.bundle.WebsiteTextBundle" />
            <c:url value="Controller" var="starter">
                <c:param name="command" value="gotostarterpage" />
            </c:url>
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="${starter}">Hotel Booking System</a>
                    </div>
                    <ul class="nav navbar-nav navbar-right">
                        <c:choose>
                            <c:when test="${sessionScope.userId == null}">
                                <c:url value="Controller" var="signInLink">
                                    <c:param name="command" value="goToSignIn" />
                                </c:url>
                                <c:url value="Controller" var="registerLink">
                                    <c:param name="command" value="gotoregistration" />
                                </c:url>
                                <li><a href="${registerLink}"><span class="glyphicon glyphicon-user"></span>
                                        <fmt:message key="register" />
                                    </a></li>
                                <li><a href="${signInLink}"><span class="glyphicon glyphicon-log-in"></span>
                                        <fmt:message key="signIn" />
                                    </a></li>
                            </c:when>
                            <c:otherwise>
                                <c:url value="Controller" var="accountLink">
                                    <c:param name="command" value="account" />
                                </c:url>
                                <c:url value="Controller" var="signOutLink">
                                    <c:param name="command" value="signOut" />
                                </c:url>
                                <c:url value="Controller" var="hotelManagementLink">
                                    <c:param name="command" value="gotohotelmanagementpage" />
                                </c:url>
                                <c:url value="Controller" var="reservationsLink">
                                    <c:param name="command" value="gotouserreservationspage" />
                                </c:url>
                                <li class="dropdown">
                                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                        <span class="glyphicon glyphicon-user"></span>
                                        <fmt:message key="account" />
                                        <span class="caret"></span>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li><a href="${accountLink}">
                                                <fmt:message key="profile" />
                                            </a></li>
                                        <li><a href="${hotelManagementLink}">
                                                <fmt:message key="hotelManagement" />
                                            </a></li>
                                        <li><a href="${reservationsLink}">
                                                <fmt:message key="reservations" />
                                        </a></li>
                                    </ul>
                                </li>
                                <li><a href="${signOutLink}"><span class="glyphicon glyphicon-log-out"></span>
                                        <fmt:message key="signOut" />
                                    </a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </nav>