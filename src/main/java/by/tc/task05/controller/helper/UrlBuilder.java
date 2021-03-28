package by.tc.task05.controller.helper;

import by.tc.task05.controller.command.CommandName;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UrlBuilder {
    private static final String BASE_NAME = "Controller";
    private static final String COMMAND_ATTRIBUTE_KEY = "command";
    private static final String MESSAGE_ATTRIBUTE_KEY = "message";
    private static final String LAST_URL_ATTRIBUTE_KEY = "lastUrl";

    private static StringBuilder createUrlStringBuilder(CommandName command) {
        StringBuilder sb = new StringBuilder(BASE_NAME);
        sb.append('?');
        sb.append(COMMAND_ATTRIBUTE_KEY).append('=')
                .append(command.name().toLowerCase());
        return sb;
    }

    public static String buildUrl(CommandName command) {

        return createUrlStringBuilder(command).toString();
    }

    private static String buildUrl(StringBuilder base, String message) {
        base.append('&');
        base.append(MESSAGE_ATTRIBUTE_KEY).append('=').append(message);
        return base.toString();
    }
    public static String buildUrl(CommandName command, String message) {
        StringBuilder sb = createUrlStringBuilder(command);
        return buildUrl(sb, message);
    }
    private static String buildUrl(String base, String message) {
        return buildUrl(new StringBuilder(base), message);
    }
    public static void sendRedirectToLastUrlWithMessage(HttpServletRequest request,
                                                          HttpServletResponse response,
                                                          String message) throws IOException {
        HttpSession session = request.getSession();
        if (session != null) {
            Object attr = session.getAttribute(LAST_URL_ATTRIBUTE_KEY);
            if(attr != null){
                String lastUrl = (String) attr;
                Logger logger = Logger.getAnonymousLogger();
                String newUrl = buildUrl(lastUrl, message);
                response.sendRedirect(newUrl);
            } else {
                response.sendRedirect(UrlBuilder.buildUrl(CommandName.GOTOSTARTERPAGE));
            }
        } else {
            response.sendRedirect(UrlBuilder.buildUrl(CommandName.GOTOSTARTERPAGE));
        }
    }
}
