package com.lumentech.renthire.payload;

import lombok.Data;

import java.time.Instant;

@Data
public class PropertyDto {

    private long propertyId;

    private Instant propertyDateAdd;

    private String propertyAddress;

    private int propertyNumOfRooms;

    private int propertyNumOfBedroom;

    private int propertyNumOfBathroom;

    private int propertyNumOfGarage;

    private String propertyDescription;
}
