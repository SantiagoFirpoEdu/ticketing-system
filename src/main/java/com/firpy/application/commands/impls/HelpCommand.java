package com.firpy.application.commands.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.shell.Shell;
import org.jetbrains.annotations.NotNull;

public class HelpCommand extends Command
{
	public HelpCommand()
	{
		super("help", "Prints this help message.");
	}

	@Override
	public void run(@NotNull String[] args, @NotNull Shell shell)
	{
		shell.println();
		shell.help();
	}
}
