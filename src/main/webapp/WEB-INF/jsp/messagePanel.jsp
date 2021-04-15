<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <fmt:setLocale value="${cookie['lang'].value}" scope="session" />
            <fmt:setBundle basename="by.tc.task05.bundle.WebsiteTextBundle" />
            <c:choose>
                <c:when test="${param['message'] != null}">
                    <div class="alert alert-warning fade in">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <fmt:message key="${param['message']}" />
                    </div>
                </c:when>
                <c:when test="${requestScope.errorMessage != null}">
                    <div class="alert alert-warning fade in">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <fmt:message key="${requestScope.errorMessage}" />
                    </div>
                </c:when>
                <c:otherwise></c:otherwise>
            </c:choose>