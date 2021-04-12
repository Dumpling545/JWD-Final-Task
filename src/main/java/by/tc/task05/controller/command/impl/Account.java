package by.tc.task05.controller.command.impl;

import by.tc.task05.entity.User;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.UserService;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Account extends AuthorizedUserCommand {
    private static final String USER_ATTRIBUTE_KEY = "user";
    private static final String ACCOUNT_JSP_LOCATION =
            "/WEB-INF/jsp/account.jsp";

    @Override
    public void executeAuthorized(int userId, HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServiceException, ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();
        User user = userService.getById(userId);
        request.setAttribute(USER_ATTRIBUTE_KEY, user);
        RequestDispatcher requestDispatcher =
                request.getRequestDispatcher(ACCOUNT_JSP_LOCATION);
        requestDispatcher.forward(request, response);
    }
}
