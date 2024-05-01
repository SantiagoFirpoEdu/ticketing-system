package com.firpy.application.commands.arguments;

import com.firpy.application.commands.exceptions.CommandException;
import com.firpy.application.shell.PrettyPrintable;
import com.firpy.application.shell.ShellColors;

public abstract class ArgumentModel implements PrettyPrintable
{
	protected ArgumentModel(String name, String description)
	{
		this.name = name;
		this.description = description;
	}

	@Override
	public String prettyPrint()
	{
		return "%s%s:%s %s".formatted(ShellColors.GREEN, name, ShellColors.RESET, description);
	}

	public String getName()
	{
		return name;
	}

	public abstract Object parse(String arg) throws CommandException;

	private final String name;
	private final String description;
}
