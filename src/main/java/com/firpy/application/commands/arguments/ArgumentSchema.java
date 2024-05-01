package com.firpy.application.commands.arguments;

import com.firpy.application.commands.exceptions.CommandException;
import com.firpy.application.shell.PrettyPrintable;
import com.firpy.application.shell.ShellColors;

public abstract class ArgumentSchema implements PrettyPrintable
{
	protected ArgumentSchema(String name, String description)
	{
		this.name = name;
		this.description = description;
	}

	protected void checkSize(String[] args) throws CommandException
	{
		if (getIndex() >= args.length)
		{
			throw new CommandException("Missing argument: %s".formatted(getName()));
		}
	}

	protected int getIndex()
	{
		return index;
	}

	public void setIndex(int index)
	{
		this.index = index;
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

	public abstract Object parse(String[] args) throws CommandException;

	private final String name;
	private final String description;

	private int index;
}
