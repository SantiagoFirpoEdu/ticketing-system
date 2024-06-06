package com.firpy.application.commands;

import com.firpy.application.commands.arguments.ArgumentSchema;
import com.firpy.application.commands.exceptions.CommandException;
import com.firpy.application.commands.exceptions.CommandUsageException;
import com.firpy.application.shell.Shell;
import com.firpy.application.shell.ShellColors;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Command
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

	public void addArgumentSchema(@NotNull ArgumentSchema argumentSchema)
	{
		argumentSchema.setIndex(argumentSchemas.size());
		argumentSchemas.add(argumentSchema);
	}

	public void addArgumentSchemas(ArgumentSchema @NotNull ... argumentSchemas)
	{
		for (ArgumentSchema argumentSchema : argumentSchemas)
		{
			addArgumentSchema(argumentSchema);
		}
	}

	public @NotNull String prettyPrint()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(ShellColors.YELLOW)
			   .append(name)
			   .append(": ")
			   .append(ShellColors.RESET)
			   .append(description)
			   .append('\n')
			   .append(ShellColors.BLUE_BRIGHT)
			   .append("Usage: ")
			   .append(ShellColors.RESET)
			   .append(usage());

		if (!argumentSchemas.isEmpty())
		{
			builder.append('\n')
				   .append(ShellColors.CYAN_BOLD + "Arguments:\n" + ShellColors.RESET)
				   .append(argumentSchemas.stream().map(ArgumentSchema::prettyPrint).collect(Collectors.joining("\n")));
		}
		return builder.toString();
	}

	public String usage()
	{

        return "%s %s".formatted(name, argumentSchemas.stream().map(argumentSchema -> "<%s>".formatted(argumentSchema.getName())).collect(Collectors.joining(" ")));
	}

	public abstract void run(@NotNull String[] args, @NotNull Shell shell) throws CommandException, CommandUsageException;

	private final List<ArgumentSchema> argumentSchemas = new ArrayList<>();
	private final String name;
	private final String description;
}
