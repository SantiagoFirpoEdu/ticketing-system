package com.firpy.application;

import com.firpy.application.commands.impls.*;
import com.firpy.application.shell.Shell;
import com.firpy.model.*;
import com.firpy.repositories.CrudRepository;
import com.firpy.repositories.CrudValueRepository;
import com.firpy.repositories.impls.VisitorDataAccess;

public class Main
{
    public static void main(String[] args)
    {
        CrudRepository<MinorVisitor, Long> minorVisitorRepository = new CrudRepository<>();
        CrudRepository<AdultVisitor, Long> visitorRepository = new CrudRepository<>();
        CrudRepository<Attraction, Long> attractionRepository = new CrudRepository<>();
        CrudRepository<Ticket, TicketId> ticketRepository = new CrudRepository<>();
        CrudValueRepository<Visit> visitRepository = new CrudValueRepository<>();


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
}