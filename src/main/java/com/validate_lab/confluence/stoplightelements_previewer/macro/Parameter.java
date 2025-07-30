package com.validate_lab.confluence.stoplightelements_previewer.macro;

public enum Parameter {
	width(1),
	height(2),
	autoplay(3),
	muted(4),
	loop(5),
	preview(6),
	videolink(7);
	
	int value;
	Parameter(int p) {
		value = p;
	}
	
	public int getValue() {
		return value;
	}
	
	/**
	 * values() from 0
	 * @param i
	 * @return
	 */
	public static String getName(int i) {
		return values()[i-1].toString();
	}
}