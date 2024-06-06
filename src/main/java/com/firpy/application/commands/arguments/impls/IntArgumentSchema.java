package com.firpy.application.commands.arguments.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.commands.arguments.ArgumentSchema;
import com.firpy.application.commands.exceptions.CommandUsageException;
import org.jetbrains.annotations.NotNull;

public class IntArgumentSchema extends ArgumentSchema
{
	public IntArgumentSchema(String name, String description, Command command)
	{
		super(name, description, command);
	}

	public @NotNull Integer parse(String @NotNull [] args) throws CommandUsageException
	{
		checkSize(args);
		String arg = args[getIndex()];
		try
		{
			return Integer.parseInt(arg);
		}
		catch (NumberFormatException e)
		{
			throw new CommandUsageException("Invalid integer: %s".formatted(arg), getCommand());
		}
	}

}
