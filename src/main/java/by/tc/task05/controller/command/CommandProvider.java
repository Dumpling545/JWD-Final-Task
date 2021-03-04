package by.tc.task05.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.tc.task05.controller.command.impl.*;

public class CommandProvider {
	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.GOTOSIGNIN, new GoToSignInPage());
		commands.put(CommandName.GOTOREGISTRATION, new GoToRegistrationPage());
		commands.put(CommandName.SAVENEWUSER, new SaveNewUser());
		commands.put(CommandName.GOTOINDEXPAGE, new GoToIndexPage());
		commands.put(CommandName.SIGNOUT, new SignOut());
	}


	public Command takeCommand(String name) {
		CommandName commandName;

		commandName = CommandName.valueOf(name.toUpperCase());

		return commands.get(commandName);
	}

}
