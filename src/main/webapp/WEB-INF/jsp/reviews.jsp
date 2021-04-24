<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
    import="by.tc.task05.entity.ExtendedReview, by.tc.task05.utils.ListPart" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
                <fmt:setLocale value="${cookie['lang'].value}" scope="session" />
                <fmt:setBundle basename="by.tc.task05.bundle.WebsiteTextBundle" />
                <!DOCTYPE html>
                <html>
                <c:import url="meta.jsp" />

                <body>
                    <c:import url="header.jsp" />
                    <div class="container">
                        <c:import url="messagePanel.jsp" />
                        <div class="row">
                            <div class="col-sm-10 col-sm-offset-1">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">
                                            <c:url value="Controller" var="roomPage">
                                                <c:param name="command" value="gotoroompage" />
                                                <c:param name="roomid" value="${param.roomid}" />
                                            </c:url>
                                            <a href="${roomPage}">
                                                <c:out value="${param.roomname}" />
                                            </a>:
                                            <fmt:message key="reviews2" />
                                        </h3>
                                        <c:import url="pageResizer.jsp" />
                                    </div>
                                    <div class="panel-body">
                                        <ul class="list-group">
                                            <c:forEach var="review" items="${requestScope.reviews}">
                                                <li class="list-group-item">
                                                    <div class="media">
                                                        <div class="media-left">
                                                            <img src="/fileServer/images/${review.userAvatar}"
                                                                onerror="this.onerror=null; this.src='${fallbackImageUrl}'"
                                                                class="media-object img-thumbnail" style="width:15vw"
                                                                alt="" />
                                                            <div class="caption">
                                                                <p>
                                                                    <c:out value="${review.userEmail}" />
                                                                </p>
                                                            </div>
                                                        </div>
                                                        <div class="media-body">
                                                            <h4 class="media-heading">
                                                                <c:out value="${review.title}" />
                                                                <small>
                                                                    <i>
                                                                        <c:out value="${review.date}" />
                                                                    </i>
                                                                </small>
                                                            </h4>
                                                            <p>
                                                                <c:out value="${review.text}" />
                                                            </p>
                                                            <p>
                                                                <strong>
                                                                    <fmt:message key="rating" />:
                                                                </strong>
                                                                <c:out value=" ${review.rating}/10" />
                                                                <span class="glyphicon glyphicon-star"></span>
                                                            </p>
                                                        </div>
                                                    </div>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                    <div class="panel-footer">
                                        <c:import url="pagination.jsp" />
                                    </div>
                                    <script>
                                        <c:import url="../js/adjustUrlsForDeletionLinksOnList.js" />
                                    </script>
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:import url="footer.jsp" />
                    <style>
                        <c:import url="../css/changeImage.css"/>
                    </style>
                </body>

                </html>