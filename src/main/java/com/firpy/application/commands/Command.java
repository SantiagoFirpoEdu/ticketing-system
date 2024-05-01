package com.firpy.application.commands;

import com.firpy.application.commands.arguments.ArgumentSchema;
import com.firpy.application.commands.exceptions.CommandException;
import com.firpy.application.shell.PrettyPrintable;
import com.firpy.application.shell.Shell;
import com.firpy.application.shell.ShellColors;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
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

	public void addArgumentModel(@NotNull ArgumentSchema argumentSchema)
	{
		argumentSchema.setIndex(argumentSchemas.size());
		argumentSchemas.add(argumentSchema);
	}

	public void addArgumentModels(ArgumentSchema @NotNull ... argumentSchemas)
	{
		for (ArgumentSchema argumentSchema : argumentSchemas)
		{
			addArgumentModel(argumentSchema);
		}
	}

	public String prettyPrint()
	{
		StringBuilder builder = new StringBuilder();
        builder.append(ShellColors.YELLOW)
			   .append(name)
			   .append(": " + ShellColors.RESET)
			   .append(description)
			   .append('\n')
			   .append(ShellColors.BLUE_BRIGHT)
			   .append("Usage: ")
			   .append(ShellColors.RESET)
			   .append(name)
			   .append(" ")
			   .append(argumentSchemas.stream().map(argumentSchema -> "<%s>".formatted(argumentSchema.getName())).collect(Collectors.joining(" ")));


		if (!argumentSchemas.isEmpty())
		{
			builder.append('\n')
				   .append(ShellColors.CYAN_BOLD + "Arguments:\n" + ShellColors.RESET)
				   .append(argumentSchemas.stream().map(ArgumentSchema::prettyPrint).collect(Collectors.joining("\n")));
		}
		return builder.toString();
	}

	public abstract void run(@NotNull String[] args, @NotNull Shell shell) throws CommandException;

	private final List<ArgumentSchema> argumentSchemas = new ArrayList<>();
	private final String name;
	private final String description;
}
