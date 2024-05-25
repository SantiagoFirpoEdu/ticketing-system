package com.firpy.repositories.impls;

import com.firpy.model.Ticket;
import com.firpy.model.TicketId;
import com.firpy.model.Visitor;
import com.firpy.repositories.CrudRepository;
import com.firpy.repositories.exceptions.DailyTicketLimitReachedException;

import java.time.LocalDate;
import java.util.Optional;

public class TicketDataAccess
{
	public TicketDataAccess(CrudRepository<Ticket, TicketId> ticketRepository)
	{
		this.ticketRepository = ticketRepository;
	}

	public void registerTicket(LocalDate purchaseDate, Visitor visitor) throws DailyTicketLimitReachedException
	{
		if (lastPurchaseDate.isEmpty() || !lastPurchaseDate.get().equals(purchaseDate))
		{
			dailyId = 0;
		}
		else if (dailyId == 500)
		{
			throw new DailyTicketLimitReachedException(purchaseDate);
		}
		ticketRepository.save(new Ticket(new TicketId(purchaseDate, dailyId++), visitor));
		lastPurchaseDate = Optional.of(purchaseDate);
	}

	public Optional<Ticket> findTicketByIdAndDate(long dailyId, LocalDate date)
	{
		return ticketRepository.findById(new TicketId(date, dailyId));
	}

	private final CrudRepository<Ticket, TicketId> ticketRepository;

	private long dailyId = 0;
	private Optional<LocalDate> lastPurchaseDate = Optional.empty();
}
