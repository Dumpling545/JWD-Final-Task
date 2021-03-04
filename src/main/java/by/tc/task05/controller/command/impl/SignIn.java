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
import jakarta.servlet.http.HttpSession;

public class SignIn implements Command {

    @Override
    public void execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String email;
        String password;

        email = request.getParameter("email");
        password = request.getParameter("password");

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();

        User user = null;
        try {
            user = userService.authorization(email, password);

            if (user == null) {
                response.sendRedirect(
                        "Controller?command=gotosigninpage&message=wrong2");
                return;
            }

            HttpSession session = request.getSession(true);
            session.setAttribute("email", email);
            response.sendRedirect("Controller?command=gotoindexpage");

        } catch (ServiceException e) {
            response.sendRedirect(
                    "Controller?command=gotosigninpage&message=wrong in catch");
        }
    }

}
