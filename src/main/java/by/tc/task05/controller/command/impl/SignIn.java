package by.tc.task05.controller.command.impl;

import java.io.IOException;
import java.util.Optional;
import by.tc.task05.controller.command.Command;
import by.tc.task05.controller.command.CommandName;
import by.tc.task05.controller.helper.ExceptionMessageMapper;
import by.tc.task05.controller.helper.UrlBuilder;
import by.tc.task05.entity.User;
import by.tc.task05.service.exception.CredentialValidationException;
import by.tc.task05.service.exception.ServiceException;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SignIn implements Command {
    private static final String USER_ID_ATTRIBUTE_KEY = "userId";
    private static final String EMAIL_PARAMETER_KEY = "email";
    private static final String PASSWORD_PARAMETER_KEY = "password";
    private static final String NO_SUCH_USER_MSG = "noSuchUser";

    @Override
    public void execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String email;
        String password;

        email = request.getParameter(EMAIL_PARAMETER_KEY);
        password = request.getParameter(PASSWORD_PARAMETER_KEY);

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();

        Optional<User> user = Optional.empty();
        try {
            user = userService.authorization(email, password);
            if (user.isEmpty()) {
                response.sendRedirect(UrlBuilder
                        .buildUrl(CommandName.GOTOSIGNIN, NO_SUCH_USER_MSG));
                return;
            }

            HttpSession session = request.getSession(true);
            session.setAttribute(USER_ID_ATTRIBUTE_KEY, user.get().getId());
            response.sendRedirect(
                    UrlBuilder.buildUrl(CommandName.GOTOSTARTERPAGE));

        } catch (ServiceException e) {
            UrlBuilder.sendRedirectToLastUrlWithMessage(request,
                    response, ExceptionMessageMapper.getKey(this, e));
        }
    }

}
