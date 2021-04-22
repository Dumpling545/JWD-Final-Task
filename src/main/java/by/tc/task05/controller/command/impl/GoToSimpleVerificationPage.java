package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.CommandName;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public abstract class GoToSimpleVerificationPage
		extends AuthorizedUserCommand
{
	private static final String PASSWORD_VERIFICATION_ROOM_JSP_LOCATION =
			"/WEB-INF/jsp/verificationPage.jsp";
	private static final String FORM_HEADER_KEY = "header";
	private static final String BUTTON_TEXT_KEY = "buttonTextBundleKey";
	private static final String COMMAND_KEY = "command";
	private static final String PASS_CONFIRM_KEY = "passwordConfirmation";
	private static final String DANGEROUS_KEY = "dangerous";

	@Override
	public void executeAuthorized(int userId, HttpServletRequest request,
	                              HttpServletResponse response)
			throws ServiceException, ServletException, IOException
	{
		RequestDispatcher requestDispatcher =
				request.getRequestDispatcher(
						PASSWORD_VERIFICATION_ROOM_JSP_LOCATION);
		request.setAttribute(FORM_HEADER_KEY, getFormHeaderValue());
		request.setAttribute(COMMAND_KEY, getCommandValue().name());
		request.setAttribute(PASS_CONFIRM_KEY, isPasswordVerified());
		request.setAttribute(BUTTON_TEXT_KEY, getButtonTextValue());
		request.setAttribute(DANGEROUS_KEY, getDangerousValue());
		requestDispatcher.forward(request, response);
	}

	public abstract String getFormHeaderValue();

	public boolean getDangerousValue() {
		return true;
	}

	public abstract CommandName getCommandValue();

	public boolean isPasswordVerified() {
		return true;
	}

	public String getButtonTextValue() {
		return null;
	}
}
