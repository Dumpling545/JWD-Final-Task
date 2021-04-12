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

public class EditPassword extends AuthorizedUserCommand {
    private static final String NEW_PASSWORD_ATTRIBUTE_KEY = "newPassword";
    private static final String REPEAT_NEW_PASSWORD_ATTRIBUTE_KEY =
            "repeatNewPassword";
    private static final String OLD_PASSWORD_ATTRIBUTE_KEY = "oldPassword";

    @Override
    public CommandName getExceptionRedirectPolicy() {
        return CommandName.GOTOEDITPASSWORDPAGE;
    }

    @Override
    public void executeAuthorized(int userId, HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServiceException, ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();
        userService.changePassword(userId,
                request.getParameter(NEW_PASSWORD_ATTRIBUTE_KEY),
                request.getParameter(REPEAT_NEW_PASSWORD_ATTRIBUTE_KEY),
                request.getParameter(OLD_PASSWORD_ATTRIBUTE_KEY));
        UrlHelper.sendRedirectToReturnUrl(request, response,
                UrlHelper.buildUrl(CommandName.ACCOUNT));
    }
}
