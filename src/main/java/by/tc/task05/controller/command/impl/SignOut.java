package by.tc.task05.controller.command.impl;

import java.io.IOException;

import by.tc.task05.controller.command.Command;
import by.tc.task05.controller.command.CommandName;
import by.tc.task05.controller.helper.UrlBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SignOut implements Command {
    private static final String USER_ID_ATTRIBUTE_KEY = "userId";
    private static final String LOGOUT_OK_MSG = "logoutOk";

    @Override
    public void execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session != null) {
            session.removeAttribute(USER_ID_ATTRIBUTE_KEY);
        }
        response.sendRedirect(UrlBuilder.buildUrl(CommandName.GOTOSTARTERPAGE,
                LOGOUT_OK_MSG));

    }

}
