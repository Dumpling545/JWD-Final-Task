package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.CommandName;
import by.tc.task05.controller.helper.ExceptionMessageMapper;
import by.tc.task05.controller.helper.UrlHelper;
import by.tc.task05.entity.Room;
import by.tc.task05.service.HotelService;
import by.tc.task05.service.RoomService;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AddRoom extends AuthorizedUserCommand {
    private static final String HOTEL_ID_KEY = "hotelid";
    private static final String ROOM_NAME_ATTRIBUTE_KEY = "roomName";
    private static final String NUMBER_OF_BEDS_ATTRIBUTE_KEY = "numberOfBeds";
    private static final String PRICE_PER_NIGHT_ATTRIBUTE_KEY = "cost";
    private static final String SHORT_DESCRIPTION_ATTRIBUTE_KEY =
            "shortDescription";
    private static final String PASSWORD_ATTRIBUTE_KEY = "password";

    @Override
    public void redirectOnException(HttpServletRequest request,
                                    HttpServletResponse response,
                                    ServiceException e) throws IOException {
        UrlHelper.sendRedirectToLastUrlWithMessage(request, response,
                ExceptionMessageMapper.getKey(this, e));
    }

    @Override
    public void executeAuthorized(int userId, HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServiceException, ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        RoomService roomService = provider.getRoomService();
        int hotelId = Integer.parseInt(request.getParameter(HOTEL_ID_KEY));
        String name = request.getParameter(ROOM_NAME_ATTRIBUTE_KEY);
        int numberOfBeds = Integer.parseInt(
                request.getParameter(NUMBER_OF_BEDS_ATTRIBUTE_KEY));
        double price = Double.parseDouble(
                request.getParameter(PRICE_PER_NIGHT_ATTRIBUTE_KEY));
        String shortDescription =
                request.getParameter(SHORT_DESCRIPTION_ATTRIBUTE_KEY);

        Room room = new Room(0, hotelId, name, numberOfBeds, shortDescription,
                price, null);
        roomService.add(userId, room,
                request.getParameter(PASSWORD_ATTRIBUTE_KEY));
        UrlHelper.sendRedirectToReturnUrl(request, response,
                UrlHelper.buildUrl(CommandName.GOTOHOTELMANAGEMENTPAGE));
    }
}
