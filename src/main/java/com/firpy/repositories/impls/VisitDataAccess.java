package com.firpy.repositories.impls;

import com.firpy.model.Attraction;
import com.firpy.model.Ticket;
import com.firpy.model.TicketId;
import com.firpy.model.Visit;
import com.firpy.repositories.CrudRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class VisitDataAccess
{
	public VisitDataAccess(CrudRepository<Visit, Long> visitRepository)
	{
		this.visitRepository = visitRepository;
	}

	public Visit registerVisit(Ticket ticket, Attraction attraction)
	{
		Visit visit = new Visit(nextVisitId++, ticket, attraction);
		visitRepository.save(visit);
		return visit;
	}

	public List<Visit> findAllVisits()
	{
		return visitRepository.findAll();
	}

	public List<Visit> findVisitsByTicketId(TicketId ticketId)
	{
		return visitRepository.findAll();
	}

	private long nextVisitId = 0;
	private final CrudRepository<Visit, Long> visitRepository;
}
