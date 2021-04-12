<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <fmt:setLocale value="${cookie['lang'].value}" scope="session" />
            <fmt:setBundle basename="by.tc.task05.bundle.WebsiteTextBundle" />
            <fmt:bundle basename="by.tc.task05.bundle.misc">
                <fmt:message key="passwordRegex" var="passwordRegex" />
            </fmt:bundle>
            <c:if test="${requestScope.passwordConfirmation == true}">
                <div class="form-group"><label for="password">
                        <fmt:message key="enterPasswordToVerify" />:
                    </label>
                    <input class="form-control" type="password" id="password" pattern="${passwordRegex}" name="password"
                        required>
                </div>
            </c:if>