package com.firpy.application.commands.arguments.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.commands.arguments.ArgumentSchema;
import com.firpy.application.commands.exceptions.CommandException;

public class IntArgumentSchema extends ArgumentSchema
{
	public IntArgumentSchema(String name, String description, Command command)
	{
		super(name, description, command);
	}

	@Override
	public Integer parse(String[] args) throws CommandException
	{
		checkSize(args);
		String arg = args[getIndex()];
		try
		{
			return Integer.parseInt(arg);
		}
		catch (NumberFormatException e)
		{
			throw new CommandException("Invalid integer: %s".formatted(arg));
		}
	}

}
