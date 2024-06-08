package com.firpy.application.commands.impls;

import com.firpy.application.UserDataAccess;
import com.firpy.application.commands.Command;
import com.firpy.application.commands.arguments.impls.StringArgumentSchema;
import com.firpy.application.commands.exceptions.CommandUsageException;
import com.firpy.application.shell.Shell;
import org.jetbrains.annotations.NotNull;

public class SetCurrentUserCommand extends Command
{
	public SetCurrentUserCommand(UserDataAccess userDataAccess)
	{
		super("set-current-user", "Sets the current user.");
		this.userDataAccess = userDataAccess;
		addArgumentSchema(userArgument);
	}

	@Override
	public void run(@NotNull String @NotNull [] args, @NotNull Shell shell) throws CommandUsageException
	{
		String user = userArgument.parse(args);
		userDataAccess.setCurrentUserName(user);
		shell.println("Current user set to: %s".formatted(user));
	}

	private final StringArgumentSchema userArgument = new StringArgumentSchema("user", "The user to set as the current user.", this);
	private final UserDataAccess userDataAccess;
}
