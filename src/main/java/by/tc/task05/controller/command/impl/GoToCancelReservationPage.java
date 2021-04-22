package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.CommandName;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToCancelReservationPage extends GoToSimpleVerificationPage {
	private static final String FORM_HEADER_VALUE = "cancelReservation";
	private static final String BUTTON_TEXT_VALUE = "cancel";
	private static final CommandName COMMAND = CommandName.ARCHIVERESERVATION;

	@Override
	public String getFormHeaderValue() {
		return FORM_HEADER_VALUE;
	}

	@Override
	public CommandName getCommandValue() {
		return COMMAND;
	}

	@Override
	public String getButtonTextValue() {
		return BUTTON_TEXT_VALUE;
	}

	@Override
	public CommandName getExceptionRedirectCommand() {
		return CommandName.GOTOUSERRESERVATIONSPAGE;
	}
}
