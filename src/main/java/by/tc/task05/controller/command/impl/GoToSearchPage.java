package by.tc.task05.controller.command.impl;

import java.io.IOException;
import java.time.LocalDate;

import by.tc.task05.controller.helper.ExceptionMessageMapper;
import by.tc.task05.controller.helper.UrlHelper;
import by.tc.task05.controller.command.Command;
import by.tc.task05.controller.command.CommandName;
import by.tc.task05.entity.PageInformation;
import by.tc.task05.entity.RoomShortView;
import by.tc.task05.entity.filter.RoomSearchServiceFilter;
import by.tc.task05.service.RoomService;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.exception.*;
import by.tc.task05.utils.ListPart;
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
    private static final String ERROR_MESSAGE_ATTRIBUTE_KEY = "errorMessage";

    @Override
    public void execute(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        RoomService roomService = provider.getRoomService();
        RoomSearchServiceFilter filter = new RoomSearchServiceFilter();
        PageInformation pageInformation = new PageInformation();
        extractFilters(request, filter, pageInformation);
        try {
            ListPart<RoomShortView> rooms =
                    roomService.getViewsByFilter(filter, pageInformation);
            request.setAttribute(ROOMS_ATTRIBUTE_KEY, rooms);
            RequestDispatcher requestDispatcher =
                    request.getRequestDispatcher(MAIN_JSP_LOCATION);
            requestDispatcher.forward(request, response);

        } catch (ServiceException e) {
            /*response.sendRedirect(UrlHelper
                    .buildUrl(CommandName.GOTOSTARTERPAGE,
                            ExceptionMessageMapper.getKey(this, e)));*/
            RequestDispatcher requestDispatcher =
                    request.getRequestDispatcher(MAIN_JSP_LOCATION);
            request.setAttribute(ERROR_MESSAGE_ATTRIBUTE_KEY,
                    ExceptionMessageMapper.getKey(this, e));
            requestDispatcher.forward(request, response);
            /*UrlBuilder.sendRedirectToLastUrlWithMessage(request,
                    response, ExceptionMessageMapper.getKey(this, e));*/
        }

    }

    private void extractFilters(HttpServletRequest request,
                                RoomSearchServiceFilter searchFilter,
                                PageInformation pageInfo) {
        String checkIn = request.getParameter(CHECK_IN_PARAMETER_KEY);
        if (checkIn != null && !checkIn.isBlank()) {
            searchFilter.setCheckInDate(LocalDate.parse(checkIn));
        }
        String checkOut = request.getParameter(CHECK_OUT_PARAMETER_KEY);
        if (checkOut != null && !checkOut.isBlank()) {
            searchFilter.setCheckOutDate(LocalDate.parse(checkOut));
        }
        String fromPrice = request.getParameter(FROM_PRICE_PARAMETER_KEY);
        if (fromPrice != null && !fromPrice.isBlank()) {
            searchFilter.setCostLowBound(Double.parseDouble(fromPrice));
        }
        String toPrice = request.getParameter(TO_PRICE_PARAMETER_KEY);
        if (toPrice != null && !toPrice.isBlank()) {
            searchFilter.setCostHighBound(Double.parseDouble(toPrice));
        }
        String location = request.getParameter(LOCATION_PARAMETER_KEY);
        if (location != null && !location.isBlank()) {
            searchFilter.setLocation(location);
        }
        String numberOfBeds =
                request.getParameter(NUMBER_OF_BEDS_PARAMETER_KEY);
        if (numberOfBeds != null && !numberOfBeds.isBlank()) {
            searchFilter.setNumberOfBeds(Integer.parseInt(numberOfBeds));
        }
        String fromRating = request.getParameter(FROM_RATING_PARAMETER_KEY);
        if (fromRating != null && !fromRating.isBlank()) {
            searchFilter.setRatingLowBound(Double.parseDouble(fromRating));
        }
        String toRating = request.getParameter(TO_RATING_PARAMETER_KEY);
        if (toRating != null && !toRating.isBlank()) {
            searchFilter.setRatingHighBound(Double.parseDouble(toRating));
        }
        String roomsPerPage =
                request.getParameter(ROOMS_PER_PAGE_PARAMETER_KEY);
        if (roomsPerPage != null && !roomsPerPage.isBlank()) {
            pageInfo.setPageSize(Integer.parseInt(roomsPerPage));
        }
        String page = request.getParameter(PAGE_PARAMETER_KEY);
        if (page != null && !page.isBlank()) {
            pageInfo.setPage(Integer.parseInt(page));
        }
    }
}
