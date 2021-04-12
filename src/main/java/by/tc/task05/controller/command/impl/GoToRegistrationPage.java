package by.tc.task05.controller.command.impl;

import java.io.IOException;

import by.tc.task05.controller.command.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToRegistrationPage implements Command {
    private static final String REGISTRATION_JSP_LOCATION =
            "/WEB-INF/jsp/registration.jsp";

    @Override
    public void execute(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher =
                request.getRequestDispatcher(REGISTRATION_JSP_LOCATION);
        requestDispatcher.forward(request, response);

    }

}
