package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.Command;
import by.tc.task05.controller.command.CommandName;
import by.tc.task05.controller.helper.ExceptionMessageMapper;
import by.tc.task05.controller.helper.UrlHelper;
import by.tc.task05.entity.Hotel;
import by.tc.task05.entity.PageInformation;
import by.tc.task05.entity.Room;
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

public class GoToRoomPage implements Command {
    private static final String USER_ID_ATTRIBUTE_KEY = "userId";
    private static final String ROOM_ATTRIBUTE_KEY = "room";
    private static final String ROOM_JSP_LOCATION =
            "/WEB-INF/jsp/room.jsp";
    private static final String ROOM_ID_KEY = "roomid";
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
        int roomId = Integer.parseInt(request.getParameter(ROOM_ID_KEY));
        if (session != null) {
            Object attr = session.getAttribute(USER_ID_ATTRIBUTE_KEY);
            if (attr != null) {
                int userId = (int) attr;
                try {
                    request.setAttribute(IS_ADMIN_ATTRIBUTE_KEY,
                            roomService.isRoomAdministrator(userId, roomId));
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
        try {
            Room room = roomService.getById(roomId);
            request.setAttribute(ROOM_ATTRIBUTE_KEY, room);
            Hotel hotel = hotelService.getById(room.getHotelId());
            request.setAttribute(HOTEL_ATTRIBUTE_KEY, hotel);
            RequestDispatcher requestDispatcher =
                    request.getRequestDispatcher(ROOM_JSP_LOCATION);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            response.sendRedirect(UrlHelper
                    .buildUrl(CommandName.GOTOSTARTERPAGE,
                            ExceptionMessageMapper.getKey(this, e)));
        }
    }
}
