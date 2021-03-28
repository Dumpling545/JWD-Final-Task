package by.tc.task05.controller.command.impl;

import java.io.IOException;

import by.tc.task05.controller.helper.ExceptionMessageMapper;
import by.tc.task05.controller.helper.UrlBuilder;
import by.tc.task05.controller.command.Command;
import by.tc.task05.controller.command.CommandName;
import by.tc.task05.entity.User;
import by.tc.task05.service.exception.CredentialValidationException;
import by.tc.task05.service.exception.ServiceException;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SaveNewUser implements Command {
    private static final String REGISTRATION_JSP_LOCATION =
            "/WEB-INF/jsp/registration.jsp";
    private static final String EMAIL_ATTRIBUTE_KEY = "email";
    private static final String FIRST_NAME_ATTRIBUTE_KEY = "firstName";
    private static final String LAST_NAME_ATTRIBUTE_KEY = "lastName";
    private static final String PASSWORD_ATTRIBUTE_KEY = "password";
    private static final String MESSAGE_ATTRIBUTE_KEY = "message";

    @Override
    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setEmail(request.getParameter(EMAIL_ATTRIBUTE_KEY));
        user.setFirstName(request.getParameter(FIRST_NAME_ATTRIBUTE_KEY));
        user.setLastName(request.getParameter(LAST_NAME_ATTRIBUTE_KEY));
        user.setPasswordHash(request.getParameter(PASSWORD_ATTRIBUTE_KEY));
        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();
        try {
            userService.registration(user);
            response.sendRedirect(UrlBuilder.buildUrl(CommandName.GOTOSIGNIN));
        } catch (ServiceException e) {
            UrlBuilder.sendRedirectToLastUrlWithMessage(request,
                    response, ExceptionMessageMapper.getKey(this, e));
        }
    }


}
