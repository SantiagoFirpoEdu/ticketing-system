package com.firpy.application.commands.arguments.impls;

import com.firpy.application.commands.arguments.ArgumentSchema;
import com.firpy.application.commands.exceptions.CommandException;

public class IntArgumentSchema extends ArgumentSchema
{
	public IntArgumentSchema(String name, String description)
	{
		super(name, description);
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
