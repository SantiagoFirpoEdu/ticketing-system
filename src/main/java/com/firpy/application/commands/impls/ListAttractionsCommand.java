package com.firpy.application.commands.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.shell.Shell;
import com.firpy.model.Attraction;
import com.firpy.repositories.CrudRepository;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ListAttractionsCommand extends Command
{
	public ListAttractionsCommand(CrudRepository<Attraction, Long> attractionRepository)
	{
		super("list-attractions", "Lists all attractions.");
		this.attractionRepository = attractionRepository;
	}

	@Override
	public void run(@NotNull String[] args, @NotNull Shell shell)
	{
		List<Attraction> all = attractionRepository.findAll();
		shell.println("Attractions:");
		all.stream()
		   .map(attraction -> "%s%n".formatted(attraction.toString()))
		   .forEach(shell::println);
	}

	private final CrudRepository<Attraction, Long> attractionRepository;
}
