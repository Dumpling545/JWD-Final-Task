package by.tc.task05.controller.command.impl;

import by.tc.task05.entity.Hotel;
import by.tc.task05.entity.Room;
import by.tc.task05.service.HotelService;
import by.tc.task05.service.RoomService;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToChangeRoomPage extends AuthorizedUserCommand {
    private static final String CHANGE_ROOM_JSP_LOCATION =
            "/WEB-INF/jsp/addChangeRoom.jsp";
    private static final String ROOM_ATTRIBUTE_KEY = "room";
    private static final String ID_KEY = "id";
    private static final String PASS_CONFIRM_KEY = "passwordConfirmation";
    private static final boolean PASS_CONFIRM_VALUE = true;

    @Override
    public void executeAuthorized(int userId, HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServiceException, ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        RoomService roomService = provider.getRoomService();
        Room room = roomService
                .getById(Integer.parseInt(request.getParameter(ID_KEY)));
        request.setAttribute(ROOM_ATTRIBUTE_KEY, room);
        RequestDispatcher requestDispatcher =
                request.getRequestDispatcher(CHANGE_ROOM_JSP_LOCATION);
        request.setAttribute(PASS_CONFIRM_KEY, PASS_CONFIRM_VALUE);
        requestDispatcher.forward(request, response);
    }
}
