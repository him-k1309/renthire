package com.lumentech.renthire.payload;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RentDto {

    private long clientId;
    private long agentId;
    private long propertyId;
    private LocalDate rentStartDate;
    private LocalDate rentEndDate;
    private int rentPrice;
    private long rentalAgreementId;
}
