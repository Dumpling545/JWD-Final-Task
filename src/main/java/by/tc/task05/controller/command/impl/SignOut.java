package by.tc.task05.controller.command.impl;

import java.io.IOException;

import by.tc.task05.controller.command.CommandName;
import by.tc.task05.controller.helper.UrlHelper;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SignOut extends AuthorizedUserCommand {
    private static final String USER_ID_ATTRIBUTE_KEY = "userId";
    private static final String LOGOUT_OK_MSG = "logoutOk";

    @Override
    public CommandName getExceptionRedirectPolicy() {
        return CommandName.GOTOSTARTERPAGE;
    }

    @Override
    public void executeAuthorized(int userId, HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServiceException, ServletException, IOException {
        HttpSession session = request.getSession();
        if (session != null) {
            session.removeAttribute(USER_ID_ATTRIBUTE_KEY);
        }
        response.sendRedirect(UrlHelper
                .buildUrl(CommandName.GOTOSTARTERPAGE, LOGOUT_OK_MSG));
    }

}
