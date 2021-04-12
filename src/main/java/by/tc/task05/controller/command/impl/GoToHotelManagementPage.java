package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.CommandName;
import by.tc.task05.entity.Hotel;
import by.tc.task05.entity.PageInformation;
import by.tc.task05.entity.RoomShortView;
import by.tc.task05.entity.filter.RoomSearchServiceFilter;
import by.tc.task05.service.HotelService;
import by.tc.task05.service.RoomService;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.exception.ServiceException;
import by.tc.task05.utils.ListPart;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GoToHotelManagementPage extends AuthorizedUserCommand {
    private static final String HOTELS_ATTRIBUTE_KEY = "hotels";
    private static final String LAST_ATTRIBUTE_KEY = "last";
    private static final String HOTEL_MANAGEMENT_JSP_LOCATION =
            "/WEB-INF/jsp/hotelManagement.jsp";
    private static final String PAGE_PARAMETER_KEY = "page";
    private static final String RESULTS_PER_PAGE_PARAMETER_KEY = "resultsperpage";

    @Override
    public CommandName getExceptionRedirectPolicy() {
        return CommandName.GOTOHOTELMANAGEMENTPAGE;
    }

    @Override
    public void executeAuthorized(int userId, HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServiceException, ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        HotelService hotelService = provider.getHotelService();
        PageInformation pageInfo = new PageInformation();
        String relultsPerPage =
                request.getParameter(RESULTS_PER_PAGE_PARAMETER_KEY);
        if (relultsPerPage != null && !relultsPerPage.isBlank()) {
            pageInfo.setPageSize(Integer.parseInt(relultsPerPage));
        }
        String page = request.getParameter(PAGE_PARAMETER_KEY);
        if (page != null && !page.isBlank()) {
            pageInfo.setPage(Integer.parseInt(page));
        }
        ListPart<Hotel> hotels = hotelService.getAdministeredBy(userId, pageInfo);
        request.setAttribute(HOTELS_ATTRIBUTE_KEY, hotels.getSubList());
        request.setAttribute(LAST_ATTRIBUTE_KEY, hotels.isLast());
        RequestDispatcher requestDispatcher =
                request.getRequestDispatcher(HOTEL_MANAGEMENT_JSP_LOCATION);
        requestDispatcher.forward(request, response);
    }
}
