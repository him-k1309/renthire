package com.lumentech.renthire.payload;

import lombok.Data;

@Data
public class SaleDto {

    private int cid;
    private int Agent_id;
    private int Property_id;
    private String SaleDate;

    private AgentDto agent;
}
