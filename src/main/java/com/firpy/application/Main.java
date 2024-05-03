package com.firpy.application;

import com.firpy.application.shell.Shell;
import com.firpy.model.*;
import com.firpy.repositories.CrudRepository;
import com.firpy.repositories.impls.VisitorDataAccess;

public class Main
{
    public static void main(String[] args)
    {
        CrudRepository<MinorVisitor, Long> minorVisitorRepository = new CrudRepository<>();
        CrudRepository<AdultVisitor, Long> visitorRepository = new CrudRepository<>();
        CrudRepository<Attraction, Long> attractionRepository = new CrudRepository<>();
        CrudRepository<Ticket, TicketId> ticketRepository = new CrudRepository<>();

        VisitorDataAccess visitorDataAccess = new VisitorDataAccess(visitorRepository, minorVisitorRepository);
        Shell shell = new Shell
        (
            visitorDataAccess
        );
        shell.help();
        shell.waitForInput();
    }
}