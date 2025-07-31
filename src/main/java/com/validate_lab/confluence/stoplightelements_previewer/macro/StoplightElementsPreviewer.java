package com.validate_lab.confluence.stoplightelements_previewer.macro;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.webresource.api.assembler.PageBuilderService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

@Scanned
public class StoplightElementsPreviewer implements Macro {
    private PageBuilderService pageBuilderService;

    @Autowired
    public StoplightElementsPreviewer(@ComponentImport PageBuilderService pageBuilderService) {
        this.pageBuilderService = pageBuilderService;
    }

    public String execute(Map<String, String> map, String s, ConversionContext conversionContext) throws MacroExecutionException {
        // load JS and CSS
        pageBuilderService.assembler().resources().requireWebResource("com.validate_lab.confluence.stoplightelements_previewer:resources");

        return "<elements-api apiDescriptionUrl=\"" + map.get("link") + "\"  router=\"hash\" />";
    }

    public BodyType getBodyType() {
        return BodyType.NONE;
    }

    public OutputType getOutputType() {
        return OutputType.BLOCK;
    }
}
