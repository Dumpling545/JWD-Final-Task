<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <fmt:setLocale value="${cookie['lang'].value}" scope="session" />
            <fmt:setBundle basename="by.tc.task05.bundle.WebsiteTextBundle" />
            <c:if test="${param['returnurl'] != null}">
                <input id="returnurl" type="hidden" name="returnurl" value="${param['returnurl']}">
                <c:choose>
                    <c:when test="${param['cancelurl'] != null}">
                        <a href="${param['cancelurl']}" class="form-control btn btn-default" role="button">
                            <fmt:message key="returnBack" />
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="${param['returnurl']}" class="form-control btn btn-default" role="button">
                            <fmt:message key="returnBack" />
                        </a>
                    </c:otherwise>
                </c:choose>
            </c:if>