package com.firpy.application.commands;

public class StringArgumentModel extends ArgumentModel
{
	public StringArgumentModel(String name, String description)
	{
		super(name, description);
	}

	@Override
	public String parse(String arg)
	{
		return arg;
	}
}
