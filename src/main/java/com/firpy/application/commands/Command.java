package com.firpy.application.commands;

import com.firpy.application.commands.arguments.ArgumentModel;
import com.firpy.application.commands.exceptions.CommandException;
import com.firpy.application.shell.PrettyPrintable;
import com.firpy.application.shell.Shell;
import com.firpy.application.shell.ShellColors;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Command implements PrettyPrintable
{
	protected Command(String name, String description)
	{
		this.name = name;
		this.description = description;
	}

	public String getName()
	{
		return name;
	}

	public void addArgumentModel(ArgumentModel argumentModel)
	{
		argumentModels.add(argumentModel);
	}

	public void addArgumentModels(ArgumentModel... argumentModels)
	{
		this.argumentModels.addAll(Arrays.asList(argumentModels));
	}

	public void parse(@NotNull String @NotNull [] args) throws CommandException
	{
		if (args.length != argumentModels.size())
		{
			throw new CommandException("Invalid number of arguments. Expected %d, got %d".formatted(argumentModels.size(), args.length));
		}

		for (int i = 0; i < args.length; i++)
		{
			ArgumentModel argumentModel = argumentModels.get(i);
			argumentModel.parse(args[i]);
		}
	}

	public String prettyPrint()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(ShellColors.YELLOW + name)
			   .append(": " + ShellColors.RESET)
			   .append(description)
			   .append('\n')
			   .append(ShellColors.BLUE_BRIGHT + "Usage: " + ShellColors.RESET)
			   .append(name)
			   .append(" ")
			   .append(argumentModels.stream().map(argumentModel -> "<%s>".formatted(argumentModel.getName())).collect(Collectors.joining(" ")));


		if (!argumentModels.isEmpty())
		{
			builder.append('\n')
				   .append('\n')
				   .append(ShellColors.CYAN_BOLD + "Arguments:\n" + ShellColors.RESET)
				   .append(argumentModels.stream().map(ArgumentModel::prettyPrint).collect(Collectors.joining("\n")));
		}
		return builder.toString();
	}

	public abstract void run(@NotNull String[] args, @NotNull Shell shell) throws CommandException;

	private final List<ArgumentModel> argumentModels = new ArrayList<>();
	private final String name;
	private final String description;
}
