package by.tc.task05.controller.helper;

import by.tc.task05.controller.command.CommandName;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlHelper {
    private static final String BASE_NAME = "Controller";
    private static final String COMMAND_ATTRIBUTE_KEY = "command";
    private static final String MESSAGE_ATTRIBUTE_KEY = "message";
    private static final String MESSAGE_REGEX =
            "(?<=[\\&\\?]message\\s?=)\\s*.*?(?=\\&|$)";
    private static final String LAST_URL_ATTRIBUTE_KEY = "lastUrl";
    private static final String RETURN_URL_PARAMETER_KEY = "returnurl";

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

    public static String buildUrl(CommandName command, String message,
                                  Map<String, Object> params) {
        StringBuilder sb = createUrlStringBuilder(command);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            sb.append('&');
            sb.append(entry.getKey()).append('=').append(entry.getValue());
        }
        return buildUrl(sb, message);
    }

    public static String buildUrl(String base, String message) {
        return buildUrl(new StringBuilder(base), message);
    }

    public static void sendRedirectToReturnUrl(HttpServletRequest request, HttpServletResponse response,
                                               String fallBackUrl)
            throws IOException {
        String url = request.getParameter(RETURN_URL_PARAMETER_KEY);
        if(url != null && !url.isBlank()){
            response.sendRedirect(url);
        } else {
            response.sendRedirect(fallBackUrl);
        }
    }
    public static void sendRedirectToLastUrlWithMessage(
            HttpServletRequest request, HttpServletResponse response,
            String message) throws IOException {
        HttpSession session = request.getSession();
        if (session != null) {
            Object attr = session.getAttribute(LAST_URL_ATTRIBUTE_KEY);
            if (attr != null) {
                String lastUrl = (String) attr;
                Matcher matcher =
                        Pattern.compile(MESSAGE_REGEX).matcher(lastUrl);
                String newUrl;
                if (matcher.find()) {
                    newUrl = matcher.replaceFirst(message);
                } else {
                    newUrl = buildUrl(lastUrl, message);
                }
                response.sendRedirect(newUrl);
            } else {
                response.sendRedirect(
                        UrlHelper.buildUrl(CommandName.GOTOSTARTERPAGE));
            }
        } else {
            response.sendRedirect(
                    UrlHelper.buildUrl(CommandName.GOTOSTARTERPAGE));
        }
    }
}
