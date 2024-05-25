package com.firpy.application;

import com.firpy.application.commands.impls.*;
import com.firpy.application.shell.Shell;
import com.firpy.model.*;
import com.firpy.repositories.CrudRepository;
import com.firpy.repositories.exceptions.CheckedIllegalArgumentException;
import com.firpy.repositories.exceptions.DailyTicketLimitReachedException;
import com.firpy.repositories.impls.TicketDataAccess;
import com.firpy.repositories.impls.VisitorDataAccess;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.Optional;

public class Main
{
    public static void main(String[] args)
    {
        CrudRepository<MinorVisitor, Long> minorVisitorRepository = new CrudRepository<>();
        CrudRepository<AdultVisitor, Long> visitorRepository = new CrudRepository<>();
        CrudRepository<Attraction, Long> attractionRepository = new CrudRepository<>();
        CrudRepository<Ticket, TicketId> ticketRepository = new CrudRepository<>();
        CrudRepository<Visit, Long> visitRepository = new CrudRepository<>();

        mockAttractionData(attractionRepository);

        VisitorDataAccess visitorDataAccess = new VisitorDataAccess(visitorRepository, minorVisitorRepository);
        mockVisitorData(visitorDataAccess);
	    try
	    {
		    mockMinorVisitorData(visitorDataAccess);
	    }
	    catch (CheckedIllegalArgumentException e)
	    {
            //Will never happen
	    }

        TicketDataAccess ticketDataAccess = new TicketDataAccess(ticketRepository);
	    try
	    {
		    mockTicketData(ticketDataAccess, visitorDataAccess);
	    }
	    catch (DailyTicketLimitReachedException e)
	    {
            //Will never happen
	    }

	    mockVisitData(visitRepository, ticketDataAccess, attractionRepository);

	    Shell shell = new Shell
        (
            new RegisterVisitorCommand(visitorDataAccess),
            new RegisterMinorVisitorCommand(visitorDataAccess),
            new ListVisitors(visitorDataAccess),
            new QueryEarningsForMonthCommand(ticketRepository),
            new QueryEarningsForYearCommand(ticketRepository),
            new ExitCommand(),
            new HelpCommand()
        );

        shell.help();
        shell.waitForInput();
    }

    private static void mockAttractionData(@NotNull CrudRepository<Attraction, Long> attractionRepository)
    {
        attractionRepository.save(new Attraction(0, "Montanha Russa"));
        attractionRepository.save(new Attraction(1, "Roda Gigante"));
        attractionRepository.save(new Attraction(2, "Barco Pirata"));
        attractionRepository.save(new Attraction(3, "Carro Bate Bate"));
    }

    private static void mockVisitorData(@NotNull VisitorDataAccess visitorDataAccess)
    {
        visitorDataAccess.registerAdultVisitor("Andreia Silveira", LocalDate.of(1990, 1, 1), "123456789");
        visitorDataAccess.registerAdultVisitor("Roberto Silveira", LocalDate.of(1995, 1, 1), "987654321");
    }

    private static void mockMinorVisitorData(@NotNull VisitorDataAccess visitorDataAccess) throws CheckedIllegalArgumentException
    {
        visitorDataAccess.registerMinorVisitor("Alice Silveira", LocalDate.of(2010, 1, 1), 0L);
        visitorDataAccess.registerMinorVisitor("Miguel Silveira", LocalDate.of(2015, 1, 1), 0L);
        visitorDataAccess.registerMinorVisitor("Enzo Silveira", LocalDate.of(2015, 1, 1), 1L);
    }

    private static void mockTicketData(TicketDataAccess ticketDataAccess, VisitorDataAccess visitorDataAccess) throws DailyTicketLimitReachedException
    {
        ticketDataAccess.registerTicket(LocalDate.now(), visitorDataAccess.findVisitorById(0).get());
        ticketDataAccess.registerTicket(LocalDate.now(), visitorDataAccess.findVisitorById(1).get());
        ticketDataAccess.registerTicket(LocalDate.now(), visitorDataAccess.findVisitorById(2).get());
        ticketDataAccess.registerTicket(LocalDate.now(), visitorDataAccess.findVisitorById(3).get());
        ticketDataAccess.registerTicket(LocalDate.now(), visitorDataAccess.findVisitorById(4).get());
    }

    private static void mockVisitData(@NotNull CrudRepository<Visit, Long> visitRepository, TicketDataAccess ticketDataAccess, CrudRepository<Attraction, Long> attractionRepository)
    {
	    Ticket ticket = ticketDataAccess.findById(new TicketId(LocalDate.now(), 0)).get();
	    Attraction attraction = attractionRepository.findById(0L).get();
	    visitRepository.save(new Visit(0, ticket, attraction));
	    visitRepository.save(new Visit(1, ticketDataAccess.findById(new TicketId(LocalDate.now(), 1)).get(), attraction));
	    visitRepository.save(new Visit(2, ticketDataAccess.findById(new TicketId(LocalDate.now(), 2)).get(), attraction));
	    visitRepository.save(new Visit(3, ticketDataAccess.findById(new TicketId(LocalDate.now(), 3)).get(), attraction));
	    visitRepository.save(new Visit(4, ticketDataAccess.findById(new TicketId(LocalDate.now(), 4)).get(), attraction));
    }
}