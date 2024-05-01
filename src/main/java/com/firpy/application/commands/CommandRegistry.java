package com.firpy.application.commands;

import java.util.HashMap;

public class CommandRegistry implements PrettyPrintable
{
	@Override
	public String prettyPrint()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Commands:\n");
		for (Command command : commands.values())
		{
			builder.append(command.prettyPrint());
			builder.append("\n");
			builder.append("\n");
			builder.append("\n");
		}

		builder.delete(builder.length() - 2, builder.length());

		return builder.toString();
	}

	public void registerCommand(Command command)
	{
		commands.put(command.getName(), command);
	}

	public void run(String command, String[] args, Shell shell) throws CommandNotFoundException, CommandException
	{
		if (!commands.containsKey(command))
		{
			throw new CommandNotFoundException(command);
		}

		commands.get(command).run(args, shell);
	}

	private final HashMap<String, Command> commands = new HashMap<>();
}
