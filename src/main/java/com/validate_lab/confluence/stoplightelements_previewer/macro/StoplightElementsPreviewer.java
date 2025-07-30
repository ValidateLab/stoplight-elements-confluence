package com.validate_lab.confluence.stoplightelements_previewer.macro;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.webresource.api.assembler.PageBuilderService;
import com.google.common.annotations.VisibleForTesting;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

@Scanned
public class StoplightElementsPreviewer implements Macro {
	private PageBuilderService pageBuilderService;
	private String[] video_formats = {"mp4","webm","ogg","ogv","ogm","avi","flv"};
	private String[] picture_formats = {"jpg","png"};
	private int STD_WIDTH = 400;
	private int MIN_SIZE = 50;
	private String STD_VIDEOCODEC ="mp4";
	private final int WIDTH = 1;
	private final int HEIGHT = 2;
	private final int AUTOPLAY = 3;
	private final int MUTED = 4;
	private final int LOOP = 5;
	private final int POSTER = 6;
	private final int VIDEOLINK = 7;
	
	@Autowired
    public StoplightElementsPreviewer(@ComponentImport PageBuilderService pageBuilderService) {
        this.pageBuilderService = pageBuilderService;
    }
	/**
     * Atlassian Confluence jump in method.
     */
	public String execute(Map<String, String> map, String s, ConversionContext conversionContext) throws MacroExecutionException {
    	try {
    		pageBuilderService.assembler().resources().requireWebResource("com.oneandone.sourcecenter.confluence.webvideo:webvideo-resources");
		} catch (NullPointerException e) {}
    	String videotag = HTML.videotag_open.value;
    	try {
        	//Add Width if available, add variable WIDTH if not
	        if (this.checkInput(Parameter.width.value,map.get(Parameter.width.toString()))) {
	        	videotag += this.getCodePart(Parameter.width.value, map.get(Parameter.width.toString()));
	        } else if (!this.checkInput(Parameter.height.value,map.get(Parameter.height.toString()))){
	        	videotag += this.getCodePart(Parameter.width.value, "" + STD_WIDTH);
	        }
	        //Manage Height, Autoplay, Muted, Loop, Preview
	        for (int i = Parameter.height.value; i <= Parameter.preview.value; i++) {
    			if (this.checkInput(i, map.get(Parameter.getName(i)))) {
    				videotag += this.getCodePart(i, map.get(Parameter.getName(i)));
    			}
    		}
	        //Add video source
	        videotag += HTML.style.value + HTML.controls.value + HTML.videotag_close.value;
	        if (!this.isEmpty(map.get(Parameter.videolink.toString()))) {
	        	videotag += this.getCodePart(Parameter.videolink.value, map.get(Parameter.videolink.toString()));
	        } else {
	        	return "<p>Please check video url</p><p>Supported formats: .mp4/.webm/.ogg/.ogv/.ogm/.avi/.flv</p>";
	        }
	        videotag += HTML.videotag_end.value;
	    } catch (Exception e) {
	    	return "<p>Input not readable!</p>";
	    }
        return videotag;
    }
    
	/**
    * This method checks fails and returns true if all checks passed
    * @param part
    * @return
    */
   private boolean checkSize(String url){
   	try {
   		if (Integer.parseInt(url) < MIN_SIZE) {
   			return false;
   		}
   	} catch (Exception e) {
   		return false;
   	}
   	return true;
   }
   
   /**
    * Checks input from textfields and checkboxes.
    * @param number of call in execute method.
    * @param part string from textfields and checkboxes.
    * @return false if check failed.
    */
   private boolean checkInput(int number, String part) {
	   if (this.isEmpty(part)) {
    		return false;
    	}
    	switch (number) {
    	case WIDTH: 									//
    	case HEIGHT: return this.checkSize(part);  		//same check
    	case AUTOPLAY:								//
    	case MUTED:									// same check
    	case LOOP: return part.equals("true");      //
    	case POSTER: return this.checkPoster(part); 
    	}
    	return false;
    }
    
   	/**
   	 * Checks preview picture in videotag.
   	 * @param part
   	 * @return
   	 */
    private boolean checkPoster(String url){
    	for(String format : picture_formats) {
    		if (this.getFormat(url).toLowerCase().equals(format)) {
        		return true;
        	}
    	}
    	return false;
    }
    
    /**
     * Is
     * @param url no defined or empty? 
     * @return false.
     */
    @VisibleForTesting
    public boolean isEmpty(String url){
    	return url == null || url.isEmpty();
    }
    
    /**
     * Add video sources. Given form this.sources
     * @param part
     * @return
     */
    private String getVideoSource(String url) {
    	return HTML.source_open.value + url + HTML.source_type.value + this.getCodecType(url) + HTML.source_close.value;
    }
    
    /**
     * Get format from
     * @param url and
     * @return the format.
     */
    private String getFormat(String url) {
    	return url.substring(url.lastIndexOf('.')+1,url.length());
    }
    
    /**
     * OGG is a special format and can have ending .ogv or .ogm
     * If we have no match, the method returns mp4 by default. This supports YouTube-Sources.
     * @param url
     * @return one of formats
     */
    private String getCodecType(String url) {
    	String estimate_format = STD_VIDEOCODEC;
    	if (this.getFormat(url).equals("ogv") || this.getFormat(url).equals("ogm")) {
    		return "ogg";
    	}
    	for (String format : video_formats) {
    		if (this.getFormat(url).toLowerCase().equals(format)) {
        		return format;
        	}
    		if (url.contains("."+format)) {
    			estimate_format = format;
    		}
    	}
    	return estimate_format;
    }
    
    /**
     * @return Name to Atlassian Confluence
     */
    public String getName()
    {
        return "WebVideo Macro";
    }
    
    /**
     * Simple attributes add to video-tag.
     * Check out enum class HTML.
     * @param number 
     * @param part
     * @return
     */
    private String getCodePart(int number, String part) {
    	switch (number) {
    	case WIDTH: return HTML.width_open.value + part + HTML.width_close.value;		
    	case HEIGHT: return HTML.height_open.value + part + HTML.height_close.value;	   
    	case AUTOPLAY: return HTML.autoplay.value;								
    	case MUTED:	return HTML.muted.value;								
    	case LOOP: return HTML.loop.value;									
    	case POSTER: return HTML.poster_open.value + part + HTML.poster_close.value;		
    	case VIDEOLINK: return this.getVideoSource(part);
    	}
    	return "";
    }
    
    /**
     * Atlassian method.
     */
    public BodyType getBodyType() { return BodyType.NONE; }
    
    /**
     * Atlassian method.
     */
    public OutputType getOutputType() { return OutputType.BLOCK; }
}
