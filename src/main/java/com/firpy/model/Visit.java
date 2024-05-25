package com.firpy.model;

import com.firpy.repositories.Identifiable;

public record Visit(long id, Ticket ticket, Attraction attraction) implements Identifiable<Long>
{
	@Override
	public Long getId()
	{
		return id;
	}
}
