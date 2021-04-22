package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.CommandName;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToDeleteRoomFeaturePage extends GoToSimpleVerificationPage {
    private static final String FORM_HEADER_VALUE = "deleteRoomFeatures";
    private static final CommandName COMMAND = CommandName.DELETEROOMFEATURE;
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
