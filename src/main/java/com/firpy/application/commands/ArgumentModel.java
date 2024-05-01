package com.firpy.application.commands;

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
		return "%s: %s".formatted(name, description);
	}

	public String getName()
	{
		return name;
	}

	public abstract Object parse(String arg) throws CommandException;

	private final String name;
	private final String description;
}
