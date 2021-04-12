package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.CommandName;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToAddAdminPage extends AuthorizedUserCommand {
    private static final String ADD_ADMIN_JSP_LOCATION =
            "/WEB-INF/jsp/addAdmin.jsp";
    private static final String PASS_CONFIRM_KEY = "passwordConfirmation";
    private static final boolean PASS_CONFIRM_VALUE = true;

    @Override
    public CommandName getExceptionRedirectPolicy() {
        return CommandName.GOTOHOTELMANAGEMENTPAGE;
    }
    @Override
    public void executeAuthorized(int userId, HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServiceException, ServletException, IOException {
        request.setAttribute(PASS_CONFIRM_KEY, PASS_CONFIRM_VALUE);
        RequestDispatcher requestDispatcher =
                request.getRequestDispatcher(ADD_ADMIN_JSP_LOCATION);
        requestDispatcher.forward(request, response);
    }
}
