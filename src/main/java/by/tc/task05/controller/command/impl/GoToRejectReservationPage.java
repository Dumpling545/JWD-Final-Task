package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.CommandName;

public class GoToRejectReservationPage extends GoToSimpleVerificationPage {
	private static final String FORM_HEADER_VALUE = "rejectReservation";
	private static final String BUTTON_TEXT_VALUE = "reject";
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
		return CommandName.GOTOHOTELMANAGEMENTPAGE;
	}
}
