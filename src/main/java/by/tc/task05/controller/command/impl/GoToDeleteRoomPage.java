package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.CommandName;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToDeleteRoomPage extends GoToSimpleVerificationPage {
	private static final String FORM_HEADER_VALUE = "deleteRoom";
	private static final CommandName COMMAND = CommandName.DELETEROOM;

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
		return CommandName.GOTOHOTELMANAGEMENTPAGE;
	}
}
