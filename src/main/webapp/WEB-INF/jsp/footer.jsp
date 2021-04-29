<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <fmt:setLocale value="${cookie['lang'].value}" scope="session" />
            <fmt:setBundle basename="by.tc.task05.bundle.WebsiteTextBundle" />
            <footer class="container-fluid">
                <div class="text-center">
                    <form id="lang-form" action="Controller" method="post">
                        <input type="hidden" name="command" value="CHANGELANGUAGE">
                        <label for="language">
                            <fmt:message key="language" />:
                        </label><br>
                        <select class="lang-input" id="language" name="language" onchange="submitForm();">
                            <option value="en" <c:if test="${cookie['lang'].value == 'en'}">${'selected'}</c:if>>
                                <fmt:message key="en" />
                            </option>
                            <option value="ru" <c:if test="${cookie['lang'].value == 'ru'}">${'selected'}</c:if>>
                                <fmt:message key="ru" />
                            </option>
                        </select>
                    </form>
                </div>
            </footer>
            <script type="text/javascript">
                function submitForm() {
                    document.getElementById("lang-form").submit();
                }
            </script>