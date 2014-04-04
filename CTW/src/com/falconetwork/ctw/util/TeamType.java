package com.falconetwork.ctw.util;

public enum TeamType {
	GREEN("Green", "§aGreen"),
	YELLOW("Yellow", "§eYellow"),
	BLUE("Blue", "§bBlue"),
	RED("Red", "§cRed"),
	UNKNOWN("Unknown", "§7Unknown");
	
	private String localized, colored;
	private TeamType(String localized, String colored) {
		this.localized = localized;
		this.colored = colored;
	}
	
	public String getLocalized() {
		return localized;
	}
	
	public String colored() {
		return colored;
	}
	
}