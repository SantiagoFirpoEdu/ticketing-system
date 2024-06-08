package com.firpy.application.commands.impls;

import com.firpy.application.UserDataAccess;
import com.firpy.application.commands.Command;
import com.firpy.application.shell.Shell;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class GetCurrentUserCommand extends Command
{
	public GetCurrentUserCommand(UserDataAccess userDataAccess)
	{
		super("get-current-user", "Prints the current user.");
		this.userDataAccess = userDataAccess;
	}

	@Override
	public void run(@NotNull String[] args, @NotNull Shell shell)
	{
		Optional<String> currentUser = userDataAccess.getCurrentUserName();
		if (currentUser.isPresent())
		{
			shell.println("Current user: %s".formatted(currentUser.get()));
		}
		else
		{
			shell.println("No current user.");
		}
	}

	private final UserDataAccess userDataAccess;
}
