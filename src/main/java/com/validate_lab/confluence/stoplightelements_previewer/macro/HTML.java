package com.validate_lab.confluence.stoplightelements_previewer.macro;

public enum HTML {
	videotag_open("<video"),
	preload(" preload=metadata"),
	width_open(" width=\""),
	width_close("px\""),
	height_open(" height=\""),
	height_close("px\""),
	autoplay(" autoplay"),
	muted(" muted"),
	loop(" loop"),
	poster_open(" poster=\""),
	poster_close("\""),
	style(" style=\"background-color:#000;\""),
    controls(" controls"),
    videotag_close(">"),
    source_open("<source src=\""),
    source_type("\" type=\"video/"),
    source_close("\">"),
	videotag_end("</video>");
	
	String value;
	HTML(String string) {
		value = string;
	}
	public String getValue() {
		return value;
	}

}
