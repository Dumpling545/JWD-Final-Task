package by.tc.task05.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.tc.task05.controller.command.impl.*;
import com.google.common.base.Enums;
import com.google.common.base.Optional;

import javax.annotation.Nullable;

public class CommandProvider {
	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.GOTOSIGNIN, new GoToSignInPage());
		commands.put(CommandName.GOTOREGISTRATION, new GoToRegistrationPage());
		commands.put(CommandName.SAVENEWUSER, new SaveNewUser());
		commands.put(CommandName.GOTOSEARCHPAGE, new GoToSearchPage());
		commands.put(CommandName.GOTOSTARTERPAGE, new GoToStarterPage());
		commands.put(CommandName.SIGNIN, new SignIn());
		commands.put(CommandName.SIGNOUT, new SignOut());
		commands.put(CommandName.CHANGELANGUAGE, new ChangeLanguage());
		commands.put(CommandName.ACCOUNT, new Account());
		commands.put(CommandName.GOTOEDITUSERINFOPAGE, new GoToEditUserInfoPage());
		commands.put(CommandName.EDITUSERINFO, new EditUserInfo());
	}

	@Nullable
	public Command takeCommand(String name) {
		Optional<CommandName> commandName;
		commandName = Enums.getIfPresent(CommandName.class, name.toUpperCase());
		if(commandName.isPresent()){
			return commands.get(commandName.get());
		} else {
			return null;
		}
	}

}
