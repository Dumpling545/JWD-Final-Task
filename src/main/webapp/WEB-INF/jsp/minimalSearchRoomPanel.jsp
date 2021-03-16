<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
    import="java.util.List, by.htp.les02.bean.News" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${cookie.locale}" scope="session" />
<div class="minimal-search-panel">
    <h3 class="filter-search-header">
        <fmt:message key="minimalSearchHeader" />
    </h3>
    <c:url value="Controller" var="searchRoomsLink">
        <c:param name="command" value="gotosearchpage" />
    </c:url>
    <form class="search-form" action="${searchRoomsLink}" method="get">
        <input class="submit-button" type="submit" value="&#x1F50D">
        <input class="reset-button" type="reset" value="&#10062">
        <label for="location"><fmt:message key="location" />:</label><br>
        <input class="input" type="text" id="location" name="location"><br>
    </form>
</div>