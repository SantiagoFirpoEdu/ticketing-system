package com.firpy.application.commands.arguments.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.commands.arguments.ArgumentSchema;
import com.firpy.application.commands.exceptions.CommandUsageException;
import org.jetbrains.annotations.NotNull;

public class StringArgumentSchema extends ArgumentSchema
{
	public StringArgumentSchema(String name, String description, Command command)
	{
		super(name, description, command);
	}

	public String parse(String @NotNull [] args) throws CommandUsageException
	{
		checkSize(args);
		return args[getIndex()];
	}
}
