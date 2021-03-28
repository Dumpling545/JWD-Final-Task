<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${cookie['lang'].value}" scope="session" />
<fmt:setBundle basename = "by.tc.task05.bundle.WebsiteTextBundle"/>
<div class="minimal-search-panel">
    <h3 class="filter-search-header">
        <fmt:message key="minimalSearchHeader" />
    </h3>
    <form class="search-form" action="Controller" method="get">
        <input type="hidden" name="command" value="gotosearchpage">
        <input class="submit-button" type="submit" value="&#x1F50D">
        <input class="reset-button" type="reset" value="&#10062">
        <label for="location"><fmt:message key="location" />:</label><br>
        <input class="input" type="text" id="location" name="location"><br>
    </form>
</div>