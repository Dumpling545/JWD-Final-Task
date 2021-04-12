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
                                <input class="form-control" type="text" id="location" name="location"
                                    value="${param['location']}">
                            </div>

                            <div class="form-group">
                                <label for="checkin">
                                    <fmt:message key="checkIn" />:
                                </label>
                                <input class="form-control" type="date" id="checkin" name="checkin">
                            </div>

                            <div class="form-group">
                                <label for="checkout">
                                    <fmt:message key="checkOut" />:
                                </label>
                                <input class="form-control" type="date" id="checkout" name="checkout">
                            </div>

                            <div class="form-group">
                                <label for="numberofbeds">
                                    <fmt:message key="numberOfBeds" />:
                                </label>
                                <input class="form-control" type="range" id="numberofbeds" name="numberofbeds" min="0"
                                    max="20" step="1">
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
                                <input class="form-control" type="number" id="fromprice" name="fromprice" min="0">
                            </div>
                            <div class="form-group">
                                <label for="toprice">
                                    <fmt:message key="to" />:
                                </label>
                                <input class="form-control" type="number" id="toprice" name="toprice" min="0">
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
                                <input class="form-control" type="number" id="fromrating" name="fromrating" min="0"
                                    max="10">
                            </div>
                            <div class="form-group">
                                <label for="torating">
                                    <fmt:message key="to" />:
                                </label>
                                <input class="form-control" type="number" id="torating" name="torating" min="0"
                                    max="10">
                            </div>
                            <div class="form-group">
                                <p class="form-control-static">
                                    <fmt:message key="numberOfReviews" />:
                                </p>
                            </div> 
                            <div class="form-group">
                                <label for="atleastreviews">
                                    <fmt:message key="atLeast" />:
                                </label>
                                <input class="form-control" type="number" id="atleastreviews" name="atleastreviews"
                                    min="0">
                            </div>

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

                                <label for="roomsperpage">
                                    <fmt:message key="roomsPerPage" />:
                                </label>
                                <select class="input" id="roomsperpage" name="roomsperpage">
                                    <option value="10">10</option>
                                    <option value="20">20</option>
                                    <option value="30">30</option>
                                    <option value="40">40</option>
                                </select>
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