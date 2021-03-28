package by.tc.task05.controller.command.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.*;

import by.tc.task05.controller.helper.ExceptionMessageMapper;
import by.tc.task05.controller.helper.UrlBuilder;
import by.tc.task05.controller.command.Command;
import by.tc.task05.controller.command.CommandName;
import by.tc.task05.entity.RoomShortView;
import by.tc.task05.entity.filter.RoomSearchServiceFilter;
import by.tc.task05.service.RoomService;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.exception.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToSearchPage implements Command {
    private static final String ROOMS_ATTRIBUTE_KEY = "rooms";
    private static final String MAIN_JSP_LOCATION = "/WEB-INF/jsp/main.jsp";
    private static final String CHECK_IN_PARAMETER_KEY = "checkin";
    private static final String CHECK_OUT_PARAMETER_KEY = "checkout";
    private static final String FROM_PRICE_PARAMETER_KEY = "fromprice";
    private static final String TO_PRICE_PARAMETER_KEY = "toprice";
    private static final String LOCATION_PARAMETER_KEY = "location";
    private static final String NUMBER_OF_BEDS_PARAMETER_KEY = "numberofbeds";
    private static final String FROM_RATING_PARAMETER_KEY = "fromrating";
    private static final String TO_RATING_PARAMETER_KEY = "torating";
    private static final String ROOMS_PER_PAGE_PARAMETER_KEY = "roomsperpage";
    private static final String PAGE_PARAMETER_KEY = "page";

    @Override
    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        RoomService roomService = provider.getRoomService();
        RoomSearchServiceFilter filter = extractFilter(request);
        try {
            List<RoomShortView> rooms = roomService.getRoomListPage(filter);
            request.setAttribute(ROOMS_ATTRIBUTE_KEY, rooms);
            RequestDispatcher requestDispatcher =
                    request.getRequestDispatcher(MAIN_JSP_LOCATION);
            requestDispatcher.forward(request, response);

        } catch (ServiceException e) {
            response.sendRedirect(UrlBuilder.buildUrl(CommandName.GOTOSTARTERPAGE,
                    ExceptionMessageMapper.getKey(this, e)));
            /*UrlBuilder.sendRedirectToLastUrlWithMessage(request,
                    response, ExceptionMessageMapper.getKey(this, e));*/
        }

    }

    private RoomSearchServiceFilter extractFilter(HttpServletRequest request) {
        RoomSearchServiceFilter filter = new RoomSearchServiceFilter();
        String checkIn = request.getParameter(CHECK_IN_PARAMETER_KEY);
        if (checkIn != null && !checkIn.isBlank()) {
            filter.setCheckInDate(LocalDate
                    .parse(checkIn));
        }
        String checkOut = request.getParameter(CHECK_OUT_PARAMETER_KEY);
        if (checkOut != null && !checkOut.isBlank()) {
            filter.setCheckOutDate(LocalDate
                    .parse(checkOut));
        }
        String fromPrice = request.getParameter(FROM_PRICE_PARAMETER_KEY);
        if (fromPrice != null && !fromPrice.isBlank()) {
            filter.setCostLowBound(Double.parseDouble(fromPrice));
        }
        String toPrice = request.getParameter(TO_PRICE_PARAMETER_KEY);
        if (toPrice != null && !toPrice.isBlank()) {
            filter.setCostHighBound(Double.parseDouble(toPrice));
        }
        String location = request.getParameter(LOCATION_PARAMETER_KEY);
        if (location != null && !location.isBlank()) {
            filter.setLocation(location);
        }
        String numberOfBeds = request.getParameter(NUMBER_OF_BEDS_PARAMETER_KEY);
        if (numberOfBeds != null && !numberOfBeds.isBlank()) {
            filter.setNumberOfBeds(Integer.parseInt(
                    numberOfBeds));
        }
        String fromRating = request.getParameter(FROM_RATING_PARAMETER_KEY);
        if (fromRating != null && !fromRating.isBlank()) {
            filter.setRatingLowBound(Double.parseDouble(
                    fromRating));
        }
        String toRating = request.getParameter(TO_RATING_PARAMETER_KEY);
        if (toRating != null && !toRating.isBlank()) {
            filter.setRatingHighBound(Double.parseDouble(
                    toRating));
        }
        String roomsPerPage = request.getParameter(ROOMS_PER_PAGE_PARAMETER_KEY);
        if (roomsPerPage != null && !roomsPerPage.isBlank())  {
            filter.setPageSize(Integer.parseInt(
                    roomsPerPage));
        }
        String page = request.getParameter(PAGE_PARAMETER_KEY);
        if (page != null && !page.isBlank()) {
            filter.setPage(Integer
                    .parseInt(page));
        }
        return filter;
    }
}
