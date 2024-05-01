package com.firpy.application;

import com.firpy.application.shell.Shell;
import com.firpy.model.MinorVisitor;
import com.firpy.model.Visitor;
import com.firpy.repositories.CrudRepository;

public class Main
{
    public static void main(String[] args)
    {
        CrudRepository<MinorVisitor, Long> minorVisitorRepository = new CrudRepository<>();
        CrudRepository<Visitor, Long> visitorRepository = new CrudRepository<>();
        Shell shell = new Shell(minorVisitorRepository, visitorRepository);
        shell.help();
        shell.waitForInput();
    }
}