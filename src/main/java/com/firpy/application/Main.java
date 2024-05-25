package com.firpy.application;

import com.firpy.application.commands.impls.*;
import com.firpy.application.shell.Shell;
import com.firpy.model.*;
import com.firpy.repositories.CrudRepository;
import com.firpy.repositories.impls.VisitorDataAccess;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

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
        mockVisitData(visitRepository);

        VisitorDataAccess visitorDataAccess = new VisitorDataAccess(visitorRepository, minorVisitorRepository);
        Shell shell = new Shell
        (
            new RegisterVisitorCommand(visitorDataAccess),
            new RegisterMinorVisitorCommand(visitorDataAccess),
            new ListVisitors(visitorDataAccess),
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

    private static void mockVisitData(@NotNull CrudRepository<Visit, Long> visitRepository)
    {
        visitRepository.save(new Visit(0, 0, new TicketId(LocalDate.now(), 0), LocalDate.now(), 0L));
        visitRepository.save(new Visit(1, 0, new TicketId(LocalDate.now(), 0), LocalDate.now(), 0L));
        visitRepository.save(new Visit(2, 0, new TicketId(LocalDate.now(), 0), LocalDate.now(), 1L));
        visitRepository.save(new Visit(3, 0, new TicketId(LocalDate.now(), 0), LocalDate.now(), 1L));
        visitRepository.save(new Visit(4, 0, new TicketId(LocalDate.now(), 0), LocalDate.now(), 1L));
        visitRepository.save(new Visit(5, 0, new TicketId(LocalDate.now(), 0), LocalDate.now(), 2L));
    }
}