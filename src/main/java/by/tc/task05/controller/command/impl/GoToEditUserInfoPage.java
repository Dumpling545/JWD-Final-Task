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

public class GoToEditUserInfoPage implements Command {
    private static final String USER_ID_ATTRIBUTE_KEY = "userId";
    private static final String EDIT_INFO_JSP_LOCATION = "/WEB-INF/jsp/editUserInfo.jsp";
    private static final String USER_INFO_ATTRIBUTE_KEY = "userInfo";
    private static final String UNAUTHORIZED_MESSAGE ="unauthorizedError";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();
        try {
            HttpSession session = request.getSession();
            UserInfo userInfo = null;
            if (session != null) {
                Object attr = session.getAttribute(USER_ID_ATTRIBUTE_KEY);
                if(attr != null){
                    int id = (int) attr;
                    User user = userService.getById(id);
                    userInfo = new UserInfo(id, user.getFirstName(), user.getLastName());
                    request.setAttribute(USER_INFO_ATTRIBUTE_KEY, userInfo);
                    RequestDispatcher requestDispatcher =
                            request.getRequestDispatcher(EDIT_INFO_JSP_LOCATION);
                    requestDispatcher.forward(request, response);
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
            /*UrlBuilder.sendRedirectToLastUrlWithMessage(request,
                    response, ExceptionMessageMapper.getKey(this, e));*/
        }
    }
}
