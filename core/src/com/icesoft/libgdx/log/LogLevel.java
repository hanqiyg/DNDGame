package com.icesoft.libgdx.log;


import com.badlogic.gdx.graphics.Color;

/** Specifies the 'level' of a log entry. The level affects the color of the entry in the console and is also displayed next to
 * the entry when the log entries are printed to a file with {@link Console#printLogToFile(String)}.
 *
 * @author StrongJoshua */
public enum LogLevel {
	/** The default log level. Prints in white to the console and has no special indicator in the log file.<br>
	 * Intentional Use: debugging. */
	INFO(new Color(1, 1, 1, 1), "INFO"),
	/** Use to print errors. Prints in red to the console and has the '<i>ERROR</i>' marking in the log file.<br>
	 * Intentional Use: printing internal console errors; debugging. */
	DOWNGRADE(new Color(217f / 255f, 0, 0, 1), "DOWNGRADE"),
	/** Prints in green. Use to print success notifications of events. Intentional Use: Print successful execution of console
	 * commands (if needed). */
	UPGRADE(new Color(0, 217f / 255f, 0, 1), "UPGRADE"),
	/** Prints in white with {@literal "> "} prepended to the command. Has that prepended text as the indicator in the log file.
	 * Intentional Use: To be used by the console, alone. */
	SYSTEM(new Color(1, 1, 1, 1), "SYSTEM");

	private Color color;
	private String identifier;

	LogLevel (Color c, String identity) {
		this.color = c;
		identifier = identity;
	}

	public Color getColor () {
		return color;
	}

	public String getIdentifier () {
		return identifier;
	}
}
