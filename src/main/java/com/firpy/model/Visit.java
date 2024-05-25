package com.firpy.model;

import com.firpy.repositories.Identifiable;

import java.time.LocalDate;

public record Visit(long id, long visitorId, TicketId ticketId, LocalDate date, Long attractionId) implements Identifiable<Long>
{
	@Override
	public Long getId()
	{
		return id;
	}
}
