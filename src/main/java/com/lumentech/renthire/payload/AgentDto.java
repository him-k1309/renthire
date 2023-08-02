package com.lumentech.renthire.payload;

import lombok.Data;

@Data
public class AgentDto {

    private long agentId;
    private String office;
    private String agentName;
    private long phoneNo;

}
