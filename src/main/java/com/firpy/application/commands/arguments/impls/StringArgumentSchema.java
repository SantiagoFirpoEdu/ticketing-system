package com.firpy.application.commands.arguments.impls;

import com.firpy.application.commands.arguments.ArgumentSchema;
import com.firpy.application.commands.exceptions.CommandException;

public class StringArgumentSchema extends ArgumentSchema
{
	public StringArgumentSchema(String name, String description)
	{
		super(name, description);
	}

	@Override
	public String parse(String[] args) throws CommandException
	{
		checkSize(args);
		return args[getIndex()];
	}
}
