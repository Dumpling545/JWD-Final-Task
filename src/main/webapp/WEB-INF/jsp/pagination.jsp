<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
                <fmt:setLocale value="${cookie['lang'].value}" scope="session" />
                <fmt:setBundle basename="by.tc.task05.bundle.WebsiteTextBundle" />
                <ul class="pager">
                    <c:if test="${param['page'] != null && param['page'] > 1}">
                        <c:url value="Controller" var="previousPageLink">
                            <c:forEach var="entry" items="${param}">
                                <c:choose>
                                    <c:when test="${entry.key=='page'}">
                                        <c:param name="${entry.key}" value="${entry.value - 1}" />
                                    </c:when>
                                    <c:otherwise>
                                        <c:param name="${entry.key}" value="${entry.value}" />
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:url>
                        <li>
                            <a id="previousPage" href="${previousPageLink}">
                                <fmt:message key="previousPage" />
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${param['page'] != null && requestScope.last == false}">
                        <c:url value="Controller" var="nextPageLink">
                            <c:forEach var="entry" items="${param}">
                                <c:choose>
                                    <c:when test="${entry.key=='page'}">
                                        <c:param name="${entry.key}" value="${entry.value + 1}" />
                                    </c:when>
                                    <c:otherwise>
                                        <c:param name="${entry.key}" value="${entry.value}" />
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:url>
                        <li>
                            <a id="nextPage" href="${nextPageLink}">
                                <fmt:message key="nextPage" />
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${param['page'] == null && requestScope.last == false}">
                        <c:url value="Controller" var="secondPageLink">
                            <c:forEach var="entry" items="${param}">
                                <c:param name="${entry.key}" value="${entry.value}" />
                            </c:forEach>
                            <c:param name="page" value="2" />
                        </c:url>
                        <li>
                            <a id="nextPage" href="${secondPageLink}">
                                <fmt:message key="nextPage" />
                            </a>
                        </li>
                    </c:if>
                </ul>