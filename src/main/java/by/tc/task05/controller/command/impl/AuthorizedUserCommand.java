package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.Command;
import by.tc.task05.controller.command.CommandName;
import by.tc.task05.controller.helper.ExceptionMessageMapper;
import by.tc.task05.controller.helper.UrlHelper;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public abstract class AuthorizedUserCommand implements Command {
    private static final String USER_ID_ATTRIBUTE_KEY = "userId";
    private static final String UNAUTHORIZED_MESSAGE = "unauthorizedError";

    @Override
    public final void execute(HttpServletRequest request,
                              HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session != null) {
            Object attr = session.getAttribute(USER_ID_ATTRIBUTE_KEY);
            if (attr != null) {
                int id = (int) attr;
                try {
                    executeAuthorized(id, request, response);
                } catch (ServiceException e) {
                    onException(request, response, e);
                }
            } else {
                onUnauthorizedUser(request, response);
            }
        } else {
            onUnauthorizedUser(request, response);
        }
    }
    public void onUnauthorizedUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect(UrlHelper
                .buildUrl(CommandName.GOTOSTARTERPAGE,
                        UNAUTHORIZED_MESSAGE));
    }
    public void onException(HttpServletRequest request,
                            HttpServletResponse response,
                            ServiceException e) throws IOException {
        response.sendRedirect(getExceptionRedirectUrl(e));
    }

    public String getExceptionRedirectUrl(ServiceException e) {
        return UrlHelper
                .buildUrl(getExceptionRedirectCommand(), ExceptionMessageMapper.getKey(this, e));
    }
    public CommandName getExceptionRedirectCommand() {
        return CommandName.GOTOSTARTERPAGE;
    }

    public abstract void executeAuthorized(int userId,
                                           HttpServletRequest request,
                                           HttpServletResponse response)
            throws ServiceException, ServletException, IOException;
}
