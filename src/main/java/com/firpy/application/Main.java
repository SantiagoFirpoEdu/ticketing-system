package com.firpy.application;

import com.firpy.application.shell.Shell;
import com.firpy.model.*;
import com.firpy.repositories.CrudRepository;
import com.firpy.repositories.impls.MinorVisitorDataAccess;

public class Main
{
    public static void main(String[] args)
    {
        CrudRepository<MinorVisitor, Long> minorVisitorRepository = new CrudRepository<>();
        CrudRepository<Visitor, Long> visitorRepository = new CrudRepository<>();
        CrudRepository<Attraction, Long> attractionRepository = new CrudRepository<>();
        CrudRepository<Ticket, TicketId> ticketRepository = new CrudRepository<>();

        MinorVisitorDataAccess minorVisitorDataAccess = new MinorVisitorDataAccess(visitorRepository, minorVisitorRepository);
        Shell shell = new Shell
        (
            minorVisitorDataAccess,
            visitorRepository
        );
        shell.help();
        shell.waitForInput();
    }
}