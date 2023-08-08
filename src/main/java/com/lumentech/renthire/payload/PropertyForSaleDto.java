package com.lumentech.renthire.payload;

import lombok.Data;

@Data
public class PropertyForSaleDto {

    private long propertyId;

    private int propertySuggestedPrice;

    private boolean propertyAvailable;
}
