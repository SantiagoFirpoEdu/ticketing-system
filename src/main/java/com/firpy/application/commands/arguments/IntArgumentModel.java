package com.firpy.application.commands.arguments;

import com.firpy.application.commands.exceptions.CommandException;

public class IntArgumentModel extends ArgumentModel
{
	public IntArgumentModel(String name, String description, boolean required)
	{
		super(name, description);
	}

	@Override
	public Integer parse(String arg) throws CommandException
	{
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
