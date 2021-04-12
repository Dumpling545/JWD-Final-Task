<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <fmt:setLocale value="${cookie['lang'].value}" scope="session" />
            <fmt:setBundle basename="by.tc.task05.bundle.WebsiteTextBundle" />
            <div class="dropdown">
                <fmt:message key="show" />
                <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
                    <c:out value="${(param['resultsperpage'] == null) ? 10 : param['resultsperpage']}" />
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a id="perpage10" href="#">10</a></li>
                    <li><a id="perpage20" href="#">20</a></li>
                    <li><a id="perpage30" href="#">30</a></li>
                    <li><a id="perpage40" href="#">40</a></li>
                </ul>
                <fmt:message key="resultsPerPage" />
                <script type="text/javascript">
                    <c:import url="../js/addLinksToPageSizeDropdown.js" />
                </script>
            </div>