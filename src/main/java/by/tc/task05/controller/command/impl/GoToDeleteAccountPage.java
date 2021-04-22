package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.CommandName;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToDeleteAccountPage extends GoToSimpleVerificationPage {
	private static final String FORM_HEADER_VALUE = "deleteAccount";
	private static final CommandName COMMAND = CommandName.DELETEACCOUNT;

	@Override
	public String getFormHeaderValue() {
		return FORM_HEADER_VALUE;
	}

	@Override
	public CommandName getCommandValue() {
		return COMMAND;
	}
	@Override
	public CommandName getExceptionRedirectCommand() {
		return CommandName.ACCOUNT;
	}

}
