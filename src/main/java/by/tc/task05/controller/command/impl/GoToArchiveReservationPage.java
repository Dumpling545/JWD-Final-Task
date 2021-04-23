package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.CommandName;

public class GoToArchiveReservationPage extends GoToSimpleVerificationPage {
	private static final String FORM_HEADER_VALUE = "archiveReservation";
	private static final String BUTTON_TEXT_VALUE = "archive";
	private static final CommandName COMMAND = CommandName.ARCHIVERESERVATION;
	private static final boolean DANGEROUS_VALUE = false;

	@Override
	public String getFormHeaderValue() {
		return FORM_HEADER_VALUE;
	}

	@Override
	public boolean getDangerousValue() {
		return DANGEROUS_VALUE;
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
