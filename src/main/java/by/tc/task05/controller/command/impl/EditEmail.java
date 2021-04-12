package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.CommandName;
import by.tc.task05.controller.helper.UrlHelper;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.UserService;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class EditEmail extends AuthorizedUserCommand {
    private static final String EMAIL_ATTRIBUTE_KEY = "email";
    private static final String PASSWORD_ATTRIBUTE_KEY = "password";

    @Override
    public CommandName getExceptionRedirectPolicy() {
        return CommandName.GOTOEDITEMAILPAGE;
    }

    @Override
    public void executeAuthorized(int userId, HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServiceException, ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();
        userService
                .changeEmail(userId, request.getParameter(EMAIL_ATTRIBUTE_KEY),
                        request.getParameter(PASSWORD_ATTRIBUTE_KEY));
        UrlHelper.sendRedirectToReturnUrl(request, response,
                UrlHelper.buildUrl(CommandName.ACCOUNT));
    }
}
