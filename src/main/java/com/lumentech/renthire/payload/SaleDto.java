package com.lumentech.renthire.payload;

import lombok.Data;

import java.util.Date;

@Data
public class SaleDto {

    private int cid;
    private int Agent_id;
    private int Property_id;
    private Date SaleDate;

    private AgentDto agent;
}
