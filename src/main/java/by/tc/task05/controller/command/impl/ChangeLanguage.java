package by.tc.task05.controller.command.impl;

import java.io.IOException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.tc.task05.controller.command.Command;
import by.tc.task05.controller.command.CommandName;
import by.tc.task05.controller.helper.UrlBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ChangeLanguage implements Command {
    private static final String LOCALE_COOKIE_KEY = "lang";
    private static final String LANGUAGE_PARAMETER_KEY = "language";
    private static final String LAST_URL_ATTRIBUTE_KEY = "lastUrl";

    @Override
    public void execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cookie lang = new Cookie(LOCALE_COOKIE_KEY, request.getParameter(LANGUAGE_PARAMETER_KEY));
        response.addCookie(lang);
        if (session != null) {
            Object attr =session.getAttribute(LAST_URL_ATTRIBUTE_KEY);
            if(attr != null){
                response.sendRedirect((String) attr);
            } else {
                response.sendRedirect(UrlBuilder.buildUrl(CommandName.GOTOSTARTERPAGE));
            }
        } else {
            response.sendRedirect(UrlBuilder.buildUrl(CommandName.GOTOSTARTERPAGE));
        }

    }

}
