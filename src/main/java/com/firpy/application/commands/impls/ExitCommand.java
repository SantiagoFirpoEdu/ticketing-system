package com.firpy.application.commands.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.shell.Shell;
import org.jetbrains.annotations.NotNull;

public class ExitCommand extends Command
{
	public ExitCommand()
	{
		super("exit", "Exits the shell");
	}

	@Override
	public void run(@NotNull String[] args, @NotNull Shell shell)
	{
		shell.exit();
		shell.println("Exiting...");
	}
}
