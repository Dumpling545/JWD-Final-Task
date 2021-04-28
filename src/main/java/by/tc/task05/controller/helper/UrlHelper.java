package by.tc.task05.controller.helper;

import by.tc.task05.controller.command.CommandName;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that provides static methods to build redirect urls
 */
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

	/**
	 * Builds url to server with 'command' parameter associated with given
	 * {@link CommandName}
	 *
	 * @param command
	 * 		name of command that should be added to url
	 * @return url to server with specified command parameter
	 */
	public static String buildUrl(CommandName command) {

		return createUrlStringBuilder(command).toString();
	}

	private static String buildUrl(StringBuilder base, String message) {
		base.append('&');
		base.append(MESSAGE_ATTRIBUTE_KEY).append('=').append(message);
		return base.toString();
	}

	/**
	 * Builds url to server with provided 'message' parameter and with 'command'
	 * parameter associated with given {@link CommandName}
	 *
	 * @param command
	 * 		name of command that should be added to url
	 * @param message
	 * 		message that should  be added to url
	 * @return url to server with specified command and message parameters
	 */
	public static String buildUrl(CommandName command, String message) {
		StringBuilder sb = createUrlStringBuilder(command);
		return buildUrl(sb, message);
	}

	/**
	 * Builds url to server with provided 'message' parameter, 'command'
	 * parameter associated with given {@link CommandName}, and any other
	 * parameters provided in {@link Map}
	 *
	 * @param command
	 * 		name of command that should be added to url
	 * @param message
	 * 		message that should be added to url
	 * @param params
	 * 		other parameters that should be added to url, where {@link Map.Entry}
	 * 		key is parameter's name and {@link Map.Entry} value is parameter's
	 * 		value
	 * @return url to server with specified parameters
	 */
	public static String buildUrl(CommandName command, String message,
	                              Map<String, Object> params)
	{
		StringBuilder sb = createUrlStringBuilder(command);
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			sb.append('&');
			sb.append(entry.getKey()).append('=').append(entry.getValue());
		}
		return buildUrl(sb, message);
	}

	/**
	 * Builds url from specified base url with 'command' parameter associated
	 * with given {@link CommandName}
	 *
	 * @param base
	 * 		base url
	 * @param message
	 * 		message that should be added to url
	 * @return base url with added specified command parameter
	 */
	public static String buildUrl(String base, String message) {
		return buildUrl(new StringBuilder(base), message);
	}

	/**
	 * Sends redirect with provided response to the value of 'return_url'
	 * parameter in provided request, and if there is no such parameter, sends
	 * redirect with provided response to fallback url
	 *
	 * @param request
	 * 		request with possible 'return_url' parameter
	 * @param response
	 * 		response to given request
	 * @param fallBackUrl
	 * 		fallback url
	 * @throws IOException
	 */
	public static void sendRedirectToReturnUrl(HttpServletRequest request,
	                                           HttpServletResponse response,
	                                           String fallBackUrl)
			throws IOException
	{
		String url = request.getParameter(RETURN_URL_PARAMETER_KEY);
		if (url != null && !url.isBlank()) {
			response.sendRedirect(url);
		} else {
			response.sendRedirect(fallBackUrl);
		}
	}

	/**
	 * Sends redirect with provided response to the value of 'lastUrl' Session
	 * attribute with added 'message' parameter, and if there is no such
	 * parameter, sends redirect with provided response to fallback url
	 *
	 * @param request
	 * 		request with possible 'return_url' parameter
	 * @param response
	 * 		response to given request
	 * @param message
	 * 		value of 'message' parameter to be added
	 * @throws IOException
	 */
	public static void sendRedirectToLastUrlWithMessage(
			HttpServletRequest request, HttpServletResponse response,
			String message) throws IOException
	{
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
