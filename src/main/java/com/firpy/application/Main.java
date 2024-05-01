package com.firpy.application;

import com.firpy.application.shell.Shell;

public class Main
{
    public static void main(String[] args)
    {
        Shell shell = new Shell();
        shell.help();
        shell.waitForInput();
    }
}