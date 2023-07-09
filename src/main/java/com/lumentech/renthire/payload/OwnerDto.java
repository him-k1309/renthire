package com.lumentech.renthire.payload;

import lombok.Data;

@Data
public class OwnerDto {

    private long ownerId;
    private String ownerName;
    private long phone;
}
