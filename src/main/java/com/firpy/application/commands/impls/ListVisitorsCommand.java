package com.firpy.application.commands.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.shell.Shell;
import com.firpy.model.Visitor;
import com.firpy.repositories.impls.VisitorDataAccess;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ListVisitorsCommand extends Command
{
	public ListVisitorsCommand(VisitorDataAccess visitorDataAccess)
	{
		super("list-visitors", "Lists all visitors.");
		this.visitorDataAccess = visitorDataAccess;
	}

	@Override
	public void run(@NotNull String[] args, @NotNull Shell shell)
	{
		List<Visitor> all = visitorDataAccess.findAllVisitors();
		shell.println("Visitors:");
		all.stream()
		   .map(visitor -> "%s%n".formatted(visitor.toString()))
		   .forEach(shell::println);
	}

	private final VisitorDataAccess visitorDataAccess;
}
