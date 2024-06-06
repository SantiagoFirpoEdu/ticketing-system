package com.firpy.application.commands.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.commands.arguments.impls.LocalDateArgumentSchema;
import com.firpy.application.commands.exceptions.CommandUsageException;
import com.firpy.application.shell.Shell;
import com.firpy.model.Attraction;
import com.firpy.model.Visit;
import com.firpy.repositories.CrudRepository;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.*;

public class QueryVisitsByDateCommand extends Command
{
	public QueryVisitsByDateCommand(CrudRepository<Visit, Long> visitRepository)
	{
		super("query-visits-by-date", "Query visits by a date.");
		this.visitRepository = visitRepository;
		addArgumentSchemas(filterByDateArgument);
	}

	@Override
	public void run(@NotNull String @NotNull [] args, @NotNull Shell shell) throws CommandUsageException
	{
		LocalDate parsedDate = filterByDateArgument.parse(args);
		List<Visit> filtered = visitRepository.findManyByPredicate(visit -> visit.ticket().id().purchaseDate().isEqual(parsedDate));

		HashMap<Attraction, Integer> attractionVisitCount = new HashMap<>();

		for (Visit visit : filtered)
		{
			int visitCount = attractionVisitCount.getOrDefault(visit.attraction(), 0) + 1;
			attractionVisitCount.put(visit.attraction(), visitCount);
		}

		@SuppressWarnings("SuspiciousMethodCalls")
		List<Attraction> attractionsSortedByVisitCount = attractionVisitCount.keySet()
																			 .stream()
																			 .sorted(Comparator.comparing(attractionVisitCount::get)
																			 .reversed())
																			 .toList();
		shell.println("Result:");
		for (Attraction attraction : attractionsSortedByVisitCount)
		{
			shell.println("%s: %d visits".formatted(attraction.name(), attractionVisitCount.get(attraction)));
		}
	}

	private final CrudRepository<Visit, Long> visitRepository;
	private final LocalDateArgumentSchema filterByDateArgument = new LocalDateArgumentSchema("filter-by-date", "The date used to filter visits", this);
}
