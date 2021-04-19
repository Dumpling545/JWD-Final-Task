package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.CommandName;
import by.tc.task05.entity.User;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.UserService;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToEditPasswordPage extends AuthorizedUserCommand {
    private static final String EDIT_PASSWORD_JSP_LOCATION =
            "/WEB-INF/jsp/editPassword.jsp";

    @Override
    public CommandName getExceptionRedirectCommand() {
        return CommandName.GOTOSTARTERPAGE;
    }

    @Override
    public void executeAuthorized(int userId, HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServiceException, ServletException, IOException {
        RequestDispatcher requestDispatcher =
                request.getRequestDispatcher(EDIT_PASSWORD_JSP_LOCATION);
        requestDispatcher.forward(request, response);
    }
}
