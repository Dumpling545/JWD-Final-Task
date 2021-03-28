package by.tc.task05.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.tc.task05.controller.command.Command;
import by.tc.task05.controller.command.CommandName;
import by.tc.task05.controller.command.CommandProvider;
import by.tc.task05.controller.helper.UrlBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CommandProvider provider = new CommandProvider();
    private static String COMMAND_PARAMETER = "command";
    private static String NO_COMMAND_MSG = "noCommandInUrl";
    private static String LAST_URL_SESSION_ATTRIBUTE_KEY = "lastUrl";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        session.setAttribute(LAST_URL_SESSION_ATTRIBUTE_KEY,
                req.getRequestURL().append('?').append(req.getQueryString()).toString());
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String name;
        Command command;
        name = request.getParameter(COMMAND_PARAMETER);
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.WARNING, name);
        if (name != null) {
            command = provider.takeCommand(name);
            if (command != null)
                command.execute(request, response);
            else {
                logger.log(Level.WARNING, "command == null");
                response.sendRedirect(
                        UrlBuilder.buildUrl(CommandName.GOTOSTARTERPAGE, NO_COMMAND_MSG));
            }
        } else {
            logger.log(Level.WARNING, "name == null");
            response.sendRedirect(
                    UrlBuilder.buildUrl(CommandName.GOTOSTARTERPAGE, NO_COMMAND_MSG));
        }
    }
}
