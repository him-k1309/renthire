package com.lumentech.renthire.payload;

import lombok.Data;

@Data
public class ClientRegDto {

    private long clientId;

    private String clientName;

    private String clientAddress;

    private long phone;

    private String email;

    private String gender;
}
