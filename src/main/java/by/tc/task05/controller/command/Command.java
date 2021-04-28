package by.tc.task05.controller.command;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Contract of class to process particular http request and produce
 * corresponding http response with {@link Command#execute(HttpServletRequest,
 * HttpServletResponse)}.{@link by.tc.task05.controller.command.CommandProvider}
 * is responsible for assigning requests to {@link Command} non-abstract
 * implementations.
 */
public interface Command {
	/**
	 * Method that process http request delegated to {@link Command} by {@link
	 * by.tc.task05.controller.Controller} via {@link by.tc.task05.controller.command.CommandProvider}
	 *
	 * @param request
	 * 		http request from {@link by.tc.task05.controller.Controller}
	 * @param response
	 * 		http response from {@link by.tc.task05.controller.Controller}
	 * @throws ServletException
	 * @throws IOException
	 */
	void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

}
