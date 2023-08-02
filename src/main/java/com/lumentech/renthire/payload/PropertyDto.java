package com.lumentech.renthire.payload;

import lombok.Data;

import java.util.Date;
@Data
public class PropertyDto {

    private long propertyId;

    private Date propertyDateAdd;

    private String propertyAddress;

    private int propertyNumOfRooms;

    private int propertyNumOfBedroom;

    private int propertyNumOfBathroom;

    private int propertyNumOfGarage;

    private String propertyDescription;
}
