package com.lumentech.renthire.payload;

import lombok.Data;

@Data
public class PropertyForRentDto {

    private long propertyId;

    private int propertyRentPrice;

    private boolean propertyAvailable;
}
