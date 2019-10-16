package com.eorionsolution.iot.epaper.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

@Component
@ConfigurationProperties(prefix="pdf")
public class PdfProperties {
    @Getter
    @Setter
    @NotEmpty
    private int dpi;

    @Getter
    @Setter
    @NotEmpty
    private String templatePath;
}
