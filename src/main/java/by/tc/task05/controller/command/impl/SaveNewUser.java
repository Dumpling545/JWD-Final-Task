package by.tc.task05.controller.command.impl;

import java.io.IOException;

import by.tc.task05.controller.command.Command;
import by.tc.task05.entity.User;
import by.tc.task05.service.ServiceException;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SaveNewUser implements Command {

    @Override
    public void execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setEmail(request.getParameter("email"));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setPasswordHash(request.getParameter("password"));
        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();
        try {
            userService.registration(user);
            response.sendRedirect("Controller?command=gotosigninpage");
        } catch (ServiceException e) {
            RequestDispatcher requestDispatcher =
                    request.getRequestDispatcher("/WEB-INF/jsp/signin.jsp");
            requestDispatcher.forward(request, response);
        }
    }

}
