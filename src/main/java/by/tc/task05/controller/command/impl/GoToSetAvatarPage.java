package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.CommandName;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToSetAvatarPage extends AuthorizedUserCommand {
    private static final String SET_ICON_JSP_LOCATION =
            "/WEB-INF/jsp/setIcon.jsp";
    private static final String IMAGE_FIELD_KEY = "image";
    private static final String IMAGE_FIELD_VALUE = "avatar";
    private static final String FORM_HEADER_KEY = "header";
    private static final String FORM_HEADER_VALUE = "setAvatar";
    private static final String COMMAND_KEY = "command";
    private static final String COMMAND_VALUE = CommandName.SETAVATAR.name();

    @Override
    public CommandName getExceptionRedirectPolicy() {
        return CommandName.GOTOSTARTERPAGE;
    }

    @Override
    public void executeAuthorized(int userId, HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServiceException, ServletException, IOException {
        RequestDispatcher requestDispatcher =
                request.getRequestDispatcher(SET_ICON_JSP_LOCATION);
        request.setAttribute(IMAGE_FIELD_KEY, IMAGE_FIELD_VALUE);
        request.setAttribute(FORM_HEADER_KEY, FORM_HEADER_VALUE);
        request.setAttribute(COMMAND_KEY, COMMAND_VALUE);
        requestDispatcher.forward(request, response);
    }
}
