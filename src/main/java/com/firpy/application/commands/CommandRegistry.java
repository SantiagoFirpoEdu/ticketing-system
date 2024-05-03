package com.firpy.application.commands;

import com.firpy.application.commands.exceptions.CommandException;
import com.firpy.application.commands.exceptions.CommandNotFoundException;
import com.firpy.application.commands.exceptions.CommandUsageException;
import com.firpy.application.shell.PrettyPrintable;
import com.firpy.application.shell.Shell;
import com.firpy.application.shell.ShellColors;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class CommandRegistry implements PrettyPrintable
{
	@Override
	public @NotNull String prettyPrint()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(ShellColors.YELLOW_BOLD + "Commands:\n" + ShellColors.RESET);
		for (Command command : commands.values())
		{
			builder.append(command.prettyPrint());
			builder.append('\n');
			builder.append('\n');
		}

		builder.delete(builder.length() - 2, builder.length());

		return builder.toString();
	}

	public void registerCommands(Command @NotNull ... commands)
	{
		for (Command command : commands)
		{
			registerCommand(command);
		}
	}

	public void registerCommand(@NotNull Command command)
	{
		commands.put(command.getName(), command);
	}

	public void run(String command, String[] args, @NotNull Shell shell) throws CommandNotFoundException, CommandException, CommandUsageException
	{
		if (!commands.containsKey(command))
		{
			throw new CommandNotFoundException(command);
		}

		commands.get(command).run(args, shell);
	}

	private final HashMap<String, Command> commands = new HashMap<>();
}
