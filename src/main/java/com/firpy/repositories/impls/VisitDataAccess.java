package com.firpy.repositories.impls;

import com.firpy.model.*;
import com.firpy.repositories.CrudRepository;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.List;

public class VisitDataAccess
{
	public VisitDataAccess(CrudRepository<Visit, Long> visitRepository)
	{
		this.visitRepository = visitRepository;
	}

	public @NotNull Visit registerVisit(Ticket ticket, Attraction attraction)
	{
		Visit visit = new Visit(nextVisitId++, ticket, attraction);
		visitRepository.save(visit);
		return visit;
	}

	public @NotNull List<Visit> findAllVisits()
	{
		return visitRepository.findAll();
	}

	public @NotNull List<Visit> findVisitsByTicketId(TicketId ticketId)
	{
		return visitRepository.findManyByPredicate(visit -> visit.ticket().id().equals(ticketId));
	}

	public @NotNull List<Visit> findVisitsByVisitor(Visitor visitor)
	{
		return visitRepository.findManyByPredicate(visit -> visit.ticket().visitor().equals(visitor));
	}

	private long nextVisitId = 0;
	private final CrudRepository<Visit, Long> visitRepository;
}
