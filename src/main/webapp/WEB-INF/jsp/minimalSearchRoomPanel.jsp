<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <fmt:setLocale value="${cookie['lang'].value}" scope="session" />
            <fmt:setBundle basename="by.tc.task05.bundle.WebsiteTextBundle" />
            <div class="col-sm-4 col-sm-offset-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <fmt:message key="minimalSearchHeader" />
                    </div>
                    <div class="panel-body">
                        <form action="Controller" method="get">
                            <input type="hidden" name="command" value="gotosearchpage">
                            <div class="form-group">
                                <label for="location">
                                    <fmt:message key="location" />:
                                </label>
                                <input class="form-control" type="text" id="location" name="location"
                                    value="${param['location']}">
                            </div>
                            <div class="form-group">
                                <button class="form-control btn btn-primary" type="submit">
                                    <span class="glyphicon glyphicon-search"></span>
                                </button>
                                <button class="form-control btn btn-danger" type="reset">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>