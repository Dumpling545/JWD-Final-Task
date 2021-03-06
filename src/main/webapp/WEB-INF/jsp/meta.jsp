<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
    import="java.util.List, by.tc.task05.entity.Room" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <fmt:setLocale value="${cookie['lang'].value}" scope="session" />
            <fmt:setBundle basename="by.tc.task05.bundle.WebsiteTextBundle" />
            <head>
                <title>HBS</title>
                <meta charset="utf-8">
                <meta name="viewport" content="width=device-width, initial-scale=1">
                <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
                <link href="https://leafletjs-cdn.s3.amazonaws.com/content/leaflet/master/leaflet.css" rel="stylesheet"
                    type="text/css" />
                <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
                <script type="text/javascript">
                    <c:if test="${cookie['lang'] != null}">
                            moment.locale("<c:out value='${cookie.lang.value}'/>");
                    </c:if>
                    <c:import url="../js/daterangepicker.js" />
                </script>
                <link rel="stylesheet" type="text/css"
                    href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
                <style type="text/css">
                    #map {
                        height: 40vh;
                    }
                </style>
            </head>