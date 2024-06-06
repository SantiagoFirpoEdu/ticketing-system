package com.firpy.model;

import com.firpy.repositories.Identifiable;
import org.jetbrains.annotations.NotNull;

public record Visit(long id, Ticket ticket, Attraction attraction) implements Identifiable<Long>
{
	@Override
	public @NotNull Long getId()
	{
		return id;
	}
}
