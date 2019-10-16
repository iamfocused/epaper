package com.eorionsolution.iot.epaper.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class IntermediateData {
    private String screenIp;
    private String templateName;
    private String pictureName;
    private String jsonData;
    private Integer flag;
    private Integer errorLevel;
}
