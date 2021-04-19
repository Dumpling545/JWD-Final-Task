package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.CommandName;
import by.tc.task05.entity.User;
import by.tc.task05.entity.UserInfo;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.UserService;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToEditUserInfoPage extends AuthorizedUserCommand {
    private static final String EDIT_INFO_JSP_LOCATION =
            "/WEB-INF/jsp/editUserInfo.jsp";
    private static final String USER_INFO_ATTRIBUTE_KEY = "userInfo";

    @Override
    public CommandName getExceptionRedirectCommand() {
        return CommandName.GOTOSTARTERPAGE;
    }

    @Override
    public void executeAuthorized(int userId, HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServiceException, ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();
        User user = userService.getById(userId);
        UserInfo userInfo =
                new UserInfo(userId, user.getFirstName(), user.getLastName());
        request.setAttribute(USER_INFO_ATTRIBUTE_KEY, userInfo);
        RequestDispatcher requestDispatcher =
                request.getRequestDispatcher(EDIT_INFO_JSP_LOCATION);
        requestDispatcher.forward(request, response);

    }
}
