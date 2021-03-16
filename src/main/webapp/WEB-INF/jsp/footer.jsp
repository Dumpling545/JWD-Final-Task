<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
    import="java.util.List, by.htp.les02.bean.News" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${cookie.locale}" scope="session" />
<div class="footer">
    <c:url value="Controller" var="languageLink">
        <c:param name="command" value="changeLanguage" />
    </c:url>
    <form class="lang-form" action="${languageLink}" method="post">
        <label for="language">
            <fmt:message key="language"/>:
        </label><br>
        <select class="lang-input" id="language" name="language">
            <option value="en" <c:if test="${cookie.locale == en}">selected</c:if>><fmt:message key="en"/></option>
            <option value="ru" <c:if test="${cookie.locale == ru}">selected</c:if>><fmt:message key="ru"/></option>
        </select>
    </form>
</div>