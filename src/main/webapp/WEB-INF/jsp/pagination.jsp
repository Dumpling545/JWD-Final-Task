<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
    import="java.util.List, by.htp.les02.bean.News" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${cookie.locale}" scope="session" />
<div class="rooms-pagination">
    <c:if test="${param['page'] > 1}">
        <c:url value="Controller" var="previousPageLink">
            <c:param name="command" value="rooms" />
            <t:urlReplaceParam name="page" value="${param['page'] - 1}" />
        </c:url>
        <a href="${previousPageLink}"><fmt:message key="previousPage"/></a>
    </c:if>
    <c:if test="${param['last'] == false}">
        <c:url value="Controller" var="nextPageLink">
            <c:param name="command" value="rooms" />
            <t:urlReplaceParam name="page" value="${param['page'] + 1}" />
        </c:url>
        <a href="${nextPageLink}"><fmt:message key="nextPage"/></a>
    </c:if>
</div>