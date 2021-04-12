package by.tc.task05.controller.command.impl;

import by.tc.task05.entity.Hotel;
import by.tc.task05.entity.User;
import by.tc.task05.entity.UserInfo;
import by.tc.task05.service.HotelService;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.UserService;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToChangeHotelPage extends AuthorizedUserCommand {
    private static final String CHANGE_HOTEL_JSP_LOCATION =
            "/WEB-INF/jsp/addChangeHotel.jsp";
    private static final String HOTEL_ATTRIBUTE_KEY = "hotel";
    private static final String ID_KEY = "id";
    private static final String PASS_CONFIRM_KEY = "passwordConfirmation";
    private static final boolean PASS_CONFIRM_VALUE = true;

    @Override
    public void executeAuthorized(int userId, HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServiceException, ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        HotelService hotelService = provider.getHotelService();
        Hotel hotel = hotelService
                .getById(Integer.parseInt(request.getParameter(ID_KEY)));
        request.setAttribute(HOTEL_ATTRIBUTE_KEY, hotel);
        RequestDispatcher requestDispatcher =
                request.getRequestDispatcher(CHANGE_HOTEL_JSP_LOCATION);
        request.setAttribute(PASS_CONFIRM_KEY, PASS_CONFIRM_VALUE);
        requestDispatcher.forward(request, response);
    }
}
