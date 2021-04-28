package by.tc.task05.controller;

import java.io.IOException;


import by.tc.task05.controller.command.Command;
import by.tc.task05.controller.command.CommandName;
import by.tc.task05.controller.command.CommandProvider;
import by.tc.task05.controller.helper.UrlHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Project's servlet. Delegates requests from {@link Controller#doGet(HttpServletRequest,
 * HttpServletResponse)} and {@link Controller#doPost(HttpServletRequest,
 * HttpServletResponse)} to {@link Command} implementations provided by {@link
 * CommandProvider}
 */
@MultipartConfig
public class Controller extends HttpServlet {
	private static final long serialVersionUID = -27708333102107886L;
	private final CommandProvider provider = new CommandProvider();
	private static String COMMAND_PARAMETER = "command";
	private static String NO_COMMAND_MSG = "noCommandInUrl";
	private static String LAST_URL_SESSION_ATTRIBUTE_KEY = "lastUrl";

	/**
	 * Keeps track of  url of last GET request in Session attribute and
	 * delegates request processing to {@link Controller#process(HttpServletRequest,
	 * HttpServletResponse)}
	 *
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		HttpSession session = req.getSession(true);
		session.setAttribute(LAST_URL_SESSION_ATTRIBUTE_KEY,
				req.getRequestURL().append('?').append(req.getQueryString())
						.toString());
		process(req, resp);
	}

	/**
	 * Delegates request processing to {@link Controller#process(HttpServletRequest,
	 * HttpServletResponse)}
	 *
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		process(req, resp);
	}

	/**
	 * Extracts COMMAND_PARAMETER from request, gets {@link Command}
	 * implementation associated with parameter's value via {@link
	 * CommandProvider} and delegates request processing to {@link
	 * Command#execute(HttpServletRequest, HttpServletResponse)} of provided
	 * command.
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void process(HttpServletRequest request,
	                     HttpServletResponse response)
			throws ServletException, IOException
	{
		String name;
		Command command;
		name = request.getParameter(COMMAND_PARAMETER);
		if (name != null) {
			command = provider.takeCommand(name);
			if (command != null) {
				command.execute(request, response);
			} else {
				response.sendRedirect(UrlHelper
						.buildUrl(CommandName.GOTOSTARTERPAGE, NO_COMMAND_MSG));
			}
		} else {
			response.sendRedirect(UrlHelper
					.buildUrl(CommandName.GOTOSTARTERPAGE, NO_COMMAND_MSG));
		}
	}
}
