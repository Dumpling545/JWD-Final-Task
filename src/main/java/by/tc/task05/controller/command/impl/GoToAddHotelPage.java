package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.CommandName;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToAddHotelPage extends AuthorizedUserCommand {
    private static final String ADD_HOTEL_JSP_LOCATION =
            "/WEB-INF/jsp/addChangeHotel.jsp";

    @Override
    public CommandName getExceptionRedirectCommand() {
        return CommandName.GOTOHOTELMANAGEMENTPAGE;
    }

    @Override
    public void executeAuthorized(int userId, HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServiceException, ServletException, IOException {
        RequestDispatcher requestDispatcher =
                request.getRequestDispatcher(ADD_HOTEL_JSP_LOCATION);
        requestDispatcher.forward(request, response);
    }
}
