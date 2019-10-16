package com.eorionsolution.iot.epaper.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QueryResult {
    @JsonProperty("donetasks")
    private String doneTask;
    @JsonProperty("taskowner_name")
    private String taskOwner;
    @JsonProperty("END_ACT_ID_")
    private String endActId;
    @JsonProperty("PROC_DEF_ID_")
    private String processDefinitionId;
    private String initiator;
    @JsonProperty("taskremaining")
    private String taskRemaingCount;
    @JsonProperty("PROC_INST_ID_")
    private String processInstanceId;
    @JsonProperty("START_TIME_")
    private String startTime;
    @JsonProperty("END_TIME_")
    private String endTime;
    @JsonProperty("taskrole")
    private String taskRole;
}
