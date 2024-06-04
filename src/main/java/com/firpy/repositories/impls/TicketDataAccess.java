package com.firpy.repositories.impls;

import com.firpy.model.Ticket;
import com.firpy.model.TicketId;
import com.firpy.model.Visitor;
import com.firpy.repositories.CrudRepository;
import com.firpy.repositories.exceptions.DailyTicketLimitReachedException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class TicketDataAccess
{

	public TicketDataAccess(CrudRepository<Ticket, TicketId> ticketRepository, VisitorDataAccess visitorDataAccess)
	{
		this.ticketRepository = ticketRepository;
		this.visitorDataAccess = visitorDataAccess;
	}

	public void registerTicket(LocalDate purchaseDate, Visitor visitor) throws DailyTicketLimitReachedException
	{
		long dailyId = dailyIdForDate.getOrDefault(purchaseDate, -1L) + 1;
		if (dailyId >= 500)
		{
			throw new DailyTicketLimitReachedException(purchaseDate);
		}

		dailyIdForDate.put(purchaseDate, dailyId);
		ticketRepository.save(new Ticket(new TicketId(purchaseDate, dailyId), visitor));
	}

	public void registerTicket(LocalDate purchaseDate, long visitorId) throws DailyTicketLimitReachedException
	{
		Visitor visitor = visitorDataAccess.findVisitorById(visitorId).orElseThrow();
		registerTicket(purchaseDate, visitor);

	}

	public List<Ticket> findAllTickets()
	{
		return ticketRepository.findAll();
	}

	public Optional<Ticket> findById(TicketId ticketId)
	{
		return ticketRepository.findById(ticketId);
	}

	private final CrudRepository<Ticket, TicketId> ticketRepository;
	private final HashMap<LocalDate, Long> dailyIdForDate = new HashMap<>();
	private final VisitorDataAccess visitorDataAccess;
}
