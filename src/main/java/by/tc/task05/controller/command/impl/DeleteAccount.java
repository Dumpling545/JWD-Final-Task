package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.CommandName;
import by.tc.task05.controller.helper.UrlHelper;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.UserService;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class DeleteAccount extends AuthorizedUserCommand {
    private static final String PASSWORD_ATTRIBUTE_KEY = "password";
    private static final String USER_ID_ATTRIBUTE_KEY = "userId";

    @Override
    public CommandName getExceptionRedirectPolicy() {
        return CommandName.GOTODELETEACCOUNTPAGE;
    }

    @Override
    public void executeAuthorized(int userId, HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServiceException, ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();
        userService
                .remove(userId, request.getParameter(PASSWORD_ATTRIBUTE_KEY));
        HttpSession session = request.getSession();
        if (session != null) {
            session.removeAttribute(USER_ID_ATTRIBUTE_KEY);
        }
        response.sendRedirect(UrlHelper.buildUrl(CommandName.GOTOSTARTERPAGE));
    }
}
