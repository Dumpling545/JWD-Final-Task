package by.tc.task05.controller.command.impl;

import java.io.IOException;

import by.tc.task05.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SignOut implements Command {

    @Override
    public void execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session != null) {
            session.removeAttribute("userId");
        }
        response.sendRedirect(
                "Controller?command=gotostarterpage&message=logoutOk");

    }

}
