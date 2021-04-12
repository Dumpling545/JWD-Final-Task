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

public class GoToEditEmailPage extends AuthorizedUserCommand {
    private static final String EDIT_EMAIL_JSP_LOCATION =
            "/WEB-INF/jsp/editEmail.jsp";
    private static final String EMAIL_ATTRIBUTE_KEY = "email";

    @Override
    public CommandName getExceptionRedirectPolicy() {
        return CommandName.GOTOSTARTERPAGE;
    }

    @Override
    public void executeAuthorized(int userId, HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServiceException, ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();
        User user = userService.getById(userId);
        request.setAttribute(EMAIL_ATTRIBUTE_KEY, user.getEmail());
        RequestDispatcher requestDispatcher =
                request.getRequestDispatcher(EDIT_EMAIL_JSP_LOCATION);
        requestDispatcher.forward(request, response);

    }
}
