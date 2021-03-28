package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.Command;
import by.tc.task05.controller.command.CommandName;
import by.tc.task05.controller.helper.ExceptionMessageMapper;
import by.tc.task05.controller.helper.UrlBuilder;
import by.tc.task05.entity.User;
import by.tc.task05.entity.UserInfo;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.UserService;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class EditUserInfo implements Command {
    private static final String FIRST_NAME_ATTRIBUTE_KEY = "firstName";
    private static final String LAST_NAME_ATTRIBUTE_KEY = "lastName";
    private static final String USER_ID_ATTRIBUTE_KEY = "userId";
    private static final String UNAUTHORIZED_MESSAGE = "unauthorizedError";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();
        try {
            HttpSession session = request.getSession();
            User user = null;
            if (session != null) {
                Object attr = session.getAttribute(USER_ID_ATTRIBUTE_KEY);
                if (attr != null) {
                    int id = (int) attr;
                    UserInfo info = new UserInfo(id,
                            request.getParameter(FIRST_NAME_ATTRIBUTE_KEY),
                            request.getParameter(LAST_NAME_ATTRIBUTE_KEY));
                    userService.changeUserInfo(info);
                    response.sendRedirect(UrlBuilder.buildUrl(CommandName.ACCOUNT));
                } else {
                    response.sendRedirect(UrlBuilder.buildUrl(CommandName.GOTOSTARTERPAGE,
                            UNAUTHORIZED_MESSAGE));
                }
            } else {
                response.sendRedirect(UrlBuilder.buildUrl(CommandName.GOTOSTARTERPAGE,
                        UNAUTHORIZED_MESSAGE));
            }

        } catch (ServiceException e) {
            response.sendRedirect(UrlBuilder.buildUrl(CommandName.GOTOSTARTERPAGE,
                    ExceptionMessageMapper.getKey(this, e)));
        }
    }
}
