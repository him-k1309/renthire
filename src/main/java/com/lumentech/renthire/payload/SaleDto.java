package com.lumentech.renthire.payload;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class SaleDto {

    private int saleId;
    private int agentId;
    private int propertyId;
    private LocalDate saleDate;

    private AgentDto agent;
}
