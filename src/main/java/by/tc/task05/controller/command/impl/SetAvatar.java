package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.CommandName;
import by.tc.task05.controller.helper.UrlHelper;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.UserService;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

public class SetAvatar extends AuthorizedUserCommand {
    private static final String AVATAR = "avatar";


    @Override
    public CommandName getExceptionRedirectPolicy() {
        return CommandName.GOTOSETAVATARPAGE;
    }

    @Override
    public void executeAuthorized(int userId, HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServiceException, ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();
        Part part = request.getPart(AVATAR);
        userService.setAvatar(userId, part);
        UrlHelper.sendRedirectToReturnUrl(request, response,
                UrlHelper.buildUrl(CommandName.ACCOUNT));
    }
}
