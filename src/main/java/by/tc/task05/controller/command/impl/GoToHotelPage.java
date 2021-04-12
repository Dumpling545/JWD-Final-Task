package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.Command;
import by.tc.task05.controller.command.CommandName;
import by.tc.task05.controller.helper.ExceptionMessageMapper;
import by.tc.task05.controller.helper.UrlHelper;
import by.tc.task05.entity.Hotel;
import by.tc.task05.entity.PageInformation;
import by.tc.task05.entity.Room;
import by.tc.task05.entity.User;
import by.tc.task05.service.HotelService;
import by.tc.task05.service.RoomService;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.exception.ServiceException;
import by.tc.task05.utils.ListPart;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class GoToHotelPage implements Command {
    private static final String USER_ID_ATTRIBUTE_KEY = "userId";
    private static final String ROOMS_ATTRIBUTE_KEY = "rooms";
    private static final String LAST_ATTRIBUTE_KEY = "last";
    private static final String HOTEL_JSP_LOCATION =
            "/WEB-INF/jsp/hotel.jsp";
    private static final String PAGE_PARAMETER_KEY = "page";
    private static final String RESULTS_PER_PAGE_PARAMETER_KEY =
            "resultsperpage";
    private static final String HOTEL_ID_KEY = "hotelid";
    private static final String IS_ADMIN_ATTRIBUTE_KEY = "isAdmin";
    private static final String HOTEL_ATTRIBUTE_KEY = "hotel";

    @Override
    public void execute(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServiceProvider provider = ServiceProvider.getInstance();
        HotelService hotelService = provider.getHotelService();
        RoomService roomService = provider.getRoomService();
        int hotelId = Integer.parseInt(request.getParameter(HOTEL_ID_KEY));
        if (session != null) {
            Object attr = session.getAttribute(USER_ID_ATTRIBUTE_KEY);
            if (attr != null) {
                int userId = (int) attr;
                try {
                    request.setAttribute(IS_ADMIN_ATTRIBUTE_KEY,
                            hotelService.isHotelAdministrator(userId, hotelId));
                } catch (ServiceException e) {
                    response.sendRedirect(UrlHelper
                            .buildUrl(CommandName.GOTOSTARTERPAGE,
                                    ExceptionMessageMapper.getKey(this, e)));
                }
            } else {
                request.setAttribute(IS_ADMIN_ATTRIBUTE_KEY, false);
            }
        } else {
            request.setAttribute(IS_ADMIN_ATTRIBUTE_KEY, false);
        }
        PageInformation pageInfo = new PageInformation();
        String resultsPerPage =
                request.getParameter(RESULTS_PER_PAGE_PARAMETER_KEY);
        if (resultsPerPage != null && !resultsPerPage.isBlank()) {
            pageInfo.setPageSize(Integer.parseInt(resultsPerPage));
        }
        String page = request.getParameter(PAGE_PARAMETER_KEY);
        if (page != null && !page.isBlank()) {
            pageInfo.setPage(Integer.parseInt(page));
        }
        try {
            Hotel hotel = hotelService.getById(hotelId);
            request.setAttribute(HOTEL_ATTRIBUTE_KEY, hotel);
            ListPart<Room> rooms =
                    roomService.getByHotel(hotelId, pageInfo);
            request.setAttribute(ROOMS_ATTRIBUTE_KEY, rooms.getSubList());
            request.setAttribute(LAST_ATTRIBUTE_KEY, rooms.isLast());
            RequestDispatcher requestDispatcher =
                    request.getRequestDispatcher(HOTEL_JSP_LOCATION);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            response.sendRedirect(UrlHelper
                    .buildUrl(CommandName.GOTOSTARTERPAGE,
                            ExceptionMessageMapper.getKey(this, e)));
        }
    }
}
