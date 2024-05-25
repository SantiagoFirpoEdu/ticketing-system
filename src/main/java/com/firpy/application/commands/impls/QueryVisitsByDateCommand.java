package com.firpy.application.commands.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.commands.arguments.impls.LocalDateArgumentSchema;
import com.firpy.application.commands.exceptions.CommandException;
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
	public QueryVisitsByDateCommand(CrudRepository<Visit, Long> visitRepository, CrudRepository<Attraction, Long> attractionRepository)
	{
		super("query-visits-by-date", "Query visits by a date.");
		this.visitRepository = visitRepository;
		this.attractionRepository = attractionRepository;
		addArgumentSchemas(filterByDateArgument);
	}

	@Override
	public void run(@NotNull String[] args, @NotNull Shell shell) throws CommandException, CommandUsageException
	{
		LocalDate parsedDate = filterByDateArgument.parse(args);
		List<Visit> filtered = visitRepository.findManyByPredicate(visit -> visit.date().isEqual(parsedDate));

		TreeMap<Long, Integer> attractionVisitCount = new TreeMap<>();

		for (Visit visit : filtered)
		{
			if (attractionVisitCount.containsKey(visit.attractionId()))
			{
				attractionVisitCount.put(visit.attractionId(), attractionVisitCount.get(visit.attractionId()) + 1);
			}
			else
			{
				attractionVisitCount.put(visit.attractionId(), 1);
			}
		}

		List<Attraction> attractionsSortedByVisitCount = attractionVisitCount.keySet()
																			 .stream()
																			 .sorted(Comparator.comparing(attractionVisitCount::get)
																			 .reversed()).map(attractionRepository::findById)
																			 .map(Optional::get)
																			 .toList();
		shell.println("Result:");
		for (Attraction attraction : attractionsSortedByVisitCount)
		{
			shell.println("%s: %d visits".formatted(attraction.name(), attractionVisitCount.get(attraction.id())));
		}
	}

	private final CrudRepository<Visit, Long> visitRepository;
	private final CrudRepository<Attraction, Long> attractionRepository;

	private final LocalDateArgumentSchema filterByDateArgument = new LocalDateArgumentSchema("filter-by-date", "The date used to filter visits", this);
}
