package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.CommandName;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToCancelReservationPage extends AuthorizedUserCommand {
    private static final String DELETE_ROOM_JSP_LOCATION =
            "/WEB-INF/jsp/passwordVerification.jsp";
    private static final String FORM_HEADER_KEY = "header";
    private static final String FORM_HEADER_VALUE = "cancelReservation";
    private static final String BUTTON_TEXT_KEY = "buttonTextBundleKey";
    private static final String BUTTON_TEXT_VALUE = "cancel";
    private static final String COMMAND_KEY = "command";
    private static final String COMMAND_VALUE = CommandName.ARCHIVERESERVATION.name();
    private static final String PASS_CONFIRM_KEY = "passwordConfirmation";
    private static final boolean PASS_CONFIRM_VALUE = true;
    @Override
    public void executeAuthorized(int userId, HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServiceException, ServletException, IOException {
        RequestDispatcher requestDispatcher =
                request.getRequestDispatcher(DELETE_ROOM_JSP_LOCATION);
        request.setAttribute(FORM_HEADER_KEY, FORM_HEADER_VALUE);
        request.setAttribute(COMMAND_KEY, COMMAND_VALUE);
        request.setAttribute(PASS_CONFIRM_KEY, PASS_CONFIRM_VALUE);
        request.setAttribute(BUTTON_TEXT_KEY, BUTTON_TEXT_VALUE);
        requestDispatcher.forward(request, response);
    }
}
