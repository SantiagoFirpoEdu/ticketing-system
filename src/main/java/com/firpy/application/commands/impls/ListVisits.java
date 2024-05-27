package com.firpy.application.commands.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.shell.Shell;
import com.firpy.model.Visit;
import com.firpy.repositories.impls.VisitDataAccess;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ListVisits extends Command
{
	public ListVisits(VisitDataAccess visitDataAccess)
	{
		super("list-visits", "Lists all visits.");
		this.visitDataAccess = visitDataAccess;
	}

	@Override
	public void run(@NotNull String[] args, @NotNull Shell shell)
	{
		List<Visit> all = visitDataAccess.findAllVisits();
		shell.println("Visits:");
		all.stream()
		   .map(ticket -> "%s%n".formatted(ticket.toString()))
		   .forEach(shell::println);
	}

	private final VisitDataAccess visitDataAccess;
}
