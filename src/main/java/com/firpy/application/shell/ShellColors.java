package com.firpy.application.shell;

//From https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
public class ShellColors
{
	// Reset
	public static final String RESET = "\033[0m";  // Text Reset

	public static final String RED = "\033[0;31m";     // RED
	public static final String GREEN = "\033[0;32m";   // GREEN
	public static final String YELLOW = "\033[0;33m";  // YELLOW

	// Bold
	public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
	public static final String CYAN_BOLD = "\033[1;36m";   // CYAN

	// High Intensity
	public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE

	private ShellColors() {}
}
