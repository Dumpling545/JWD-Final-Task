<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
    import="java.util.List, by.htp.les02.bean.News" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${cookie.locale}" scope="session" />
<div class="search-panel">
    <h3 class="filter-search-header">
        <fmt:message key="searchFilter" />:
    </h3>
    <c:url value="Controller" var="searchRoomsLink">
        <c:param name="command" value="gotosearchpage" />
    </c:url>
    <form class="search-form" action="${searchRoomsLink}" method="get">
        <input class="submit-button" type="submit" value="&#x1F50D">
        <input class="reset-button" type="reset" value="&#10062">
        <label for="location"><fmt:message key="location" />:</label><br>
        <input class="input" type="text" id="location" name="location"><br>
        <hr>
        <label for="checkin"><fmt:message key="checkIn"/>:</label><br>
        <input class="input" type="date" id="checkin" name="checkin"><br>
        <label for="checkout"><fmt:message key="checkOut"/>:</label><br>
        <input class="input" type="date" id="checkout" name="checkout"><br>
        <hr>
        <label for="numberofbeds"><fmt:message key="numberOfBeds"/>:</label><br>
        <input class="input" type="range" id="numberofbeds" name="numberofbeds" min="0" max="20" step="1"><br>
        <hr>
        <label class="form-subheader"><fmt:message key="pricePerNight"/>:</label><br>
        <label for="fromprice"><fmt:message key="from"/>:</label><br>
        <input class="input" type="number" id="fromprice" name="fromprice" min="0"><br>
        <label for="toprice"><fmt:message key="to"/>:</label><br>
        <input class="input" type="number" id="toprice" name="toprice" min="0"><br>
        <hr>
        <label class="form-subheader"><fmt:message key="rating"/>:</label><br>
        <label for="fromrating"><fmt:message key="from"/>: </label><br>
        <input class="input" type="number" id="fromrating" name="fromrating" min="0" max="10"><br>
        <label for="torating"><fmt:message key="to"/>: </label><br>
        <input class="input" type="number" id="torating" name="torating" min="0" max="10"><br>
        <hr>
        <label class="form-subheader"><fmt:message key="numberOfReviews"/>:</label><br>
        <label for="atleastreviews"><fmt:message key="atLeast"/>: </label><br>
        <input class="input" type="number" id="atleastreviews" name="atleastreviews" min="0"><br>
        <hr>
        <label for="sortby"><fmt:message key="sortBy"/>: </label><br>
        <select class="input" id="sortby" name="sortby">
            <option value="rating"><fmt:message key="rating"/></option>
            <option value="location"><fmt:message key="location" /></option>
            <option value="price"><fmt:message key="pricePerNight"/></option>
            <option value="reviews"><fmt:message key="numberOfReviews"/></option>
        </select><br>
        <hr>
        <label for="roomsperpage"><fmt:message key="roomsPerPage"/>:</label><br>
        <select class="input" id="roomsperpage" name="roomsperpage">
            <option value="10">10</option>
            <option value="20">20</option>
            <option value="30">30</option>
            <option value="40">40</option>
        </select><br>
        <br><br>
        <input class="submit-button" type="submit" value="&#x1F50D">
        <input class="reset-button" type="reset" value="&#10062">
    </form>
</div>