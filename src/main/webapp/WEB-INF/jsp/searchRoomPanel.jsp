<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <fmt:setLocale value="${cookie['lang'].value}" scope="session" />
            <fmt:setBundle basename="by.tc.task05.bundle.WebsiteTextBundle" />
            <div class="col-sm-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <fmt:message key="searchFilter" />:
                    </div>
                    <div class="panel-body">
                        <form action="Controller" method="get">
                            <input type="hidden" name="command" value="gotosearchpage">

                            <div class="form-group">
                                <button class="form-control btn btn-primary" type="submit">
                                    <span class="glyphicon glyphicon-search"></span>
                                </button>
                                <button class="form-control btn btn-danger" type="reset">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </button>
                            </div>

                            <div class="form-group">
                                <label for="location">
                                    <fmt:message key="location" />:
                                </label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-globe"></i></span>
                                    <input class="form-control" type="text" id="location" name="location"
                                        value="${param['location']}">
                                </div>
                            </div>

                            <div id="dateRangeFormGroup" class="form-group hidden">
                                <label for="textdate">
                                    <fmt:message key="checkIn" /> - <fmt:message key="checkOut" />:
                                </label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                                    <input class="form-control" type="text" id="textdate" name="textdate" value="">
                                </div>
                            </div>

                            <div class="form-group" id="checkinFormGroup">
                                <label for="checkin">
                                    <fmt:message key="checkIn" />:
                                </label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                                    <input class="form-control" type="date" id="checkin" name="checkin"
                                        value="${param['checkin']}">
                                </div>
                            </div>

                            <div class="form-group" id="checkoutFormGroup">
                                <label for="checkout">
                                    <fmt:message key="checkOut" />:
                                </label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                                    <input class="form-control" type="date" id="checkout" name="checkout"
                                        value="${param['checkout']}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="numberOfBeds">
                                    <fmt:message key="numberOfBeds" />:
                                </label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-bed"></i></span>
                                    <input class="form-control" type="number" min="1" max="20" id="numberofbeds"
                                        name="numberofbeds" value="${param['numberofbeds']}">
                                </div>
                            </div>
                            <div class="form-group">
                                <p class="form-control-static">
                                    <fmt:message key="pricePerNight" />:
                                </p>
                            </div>
                            <div class="form-group">
                                <label for="fromprice">
                                    <fmt:message key="from" />:
                                </label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-usd"></i></span>
                                    <input class="form-control" type="number" id="fromprice" name="fromprice" min="0"
                                        value="${param['fromprice']}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="toprice">
                                    <fmt:message key="to" />:
                                </label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-usd"></i></span>
                                    <input class="form-control" type="number" id="toprice" name="toprice" min="0"
                                        value="${param['toprice']}">
                                </div>
                            </div>
                            <div class="form-group">
                                <p class="form-control-static">
                                    <fmt:message key="rating" />:
                                </p>
                            </div>
                            <div class="form-group">
                                <label for="fromrating">
                                    <fmt:message key="from" />:
                                </label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-star"></i></span>
                                    <input class="form-control" type="number" id="fromrating" name="fromrating" min="0"
                                        max="10" value="${param['fromrating']}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="torating">
                                    <fmt:message key="to" />:
                                </label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-star"></i></span>
                                    <input class="form-control" type="number" id="torating" name="torating" min="1"
                                        max="10" value="${param['torating']}">
                                </div>
                            </div>
                            <!--
                            <div class="form-group">
                                <label for="sortby">
                                    <fmt:message key="sortBy" />:
                                </label>
                                <select class="input" id="sortby" name="sortby">
                                    <option value="rating">
                                        <fmt:message key="rating" />
                                    </option>
                                    <option value="location">
                                        <fmt:message key="location" />
                                    </option>
                                    <option value="price">
                                        <fmt:message key="pricePerNight" />
                                    </option>
                                    <option value="reviews">
                                        <fmt:message key="numberOfReviews" />
                                    </option>
                                </select>
                            </div>-->
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
            <script>
                var start = "<c:out value='${param.checkin}'/>";
                var end="<c:out value='${param.checkout}'/>"
                <c:import url="../js/mainScript.js" />
            </script>