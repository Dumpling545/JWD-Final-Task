package by.tc.task05.controller.command.impl;

import java.io.IOException;
import java.util.Locale;

import by.tc.task05.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ChangeLanguage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cookie cookie = new Cookie("locale", (String) request.getAttribute("language"));
        response.addCookie(cookie);
        if (session != null) {
            response.sendRedirect((String) session.getAttribute("lastUrl"));
        } else {
            response.sendRedirect("Controller?command=gotostarterpage");
        }

    }

}
