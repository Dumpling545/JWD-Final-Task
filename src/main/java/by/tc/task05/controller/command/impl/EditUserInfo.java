package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.CommandName;
import by.tc.task05.controller.helper.UrlHelper;
import by.tc.task05.entity.UserInfo;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.UserService;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class EditUserInfo extends AuthorizedUserCommand {
    private static final String FIRST_NAME_ATTRIBUTE_KEY = "firstName";
    private static final String LAST_NAME_ATTRIBUTE_KEY = "lastName";

    @Override
    public CommandName getExceptionRedirectPolicy() {
        return CommandName.GOTOEDITUSERINFOPAGE;
    }

    @Override
    public void executeAuthorized(int userId, HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServiceException, ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();
        UserInfo info = new UserInfo(userId,
                request.getParameter(FIRST_NAME_ATTRIBUTE_KEY),
                request.getParameter(LAST_NAME_ATTRIBUTE_KEY));
        userService.changeUserInfo(info);
        UrlHelper.sendRedirectToReturnUrl(request, response,
                UrlHelper.buildUrl(CommandName.ACCOUNT));
    }
}
