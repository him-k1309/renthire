package com.lumentech.renthire.service;

import com.lumentech.renthire.payload.ClientRegDto;

import java.util.List;

public interface ClientRegService {
    ClientRegDto createClientReg(ClientRegDto clientRegDto);

    List<ClientRegDto> getAllClientDetails();

    ClientRegDto getClientDetailsById(long id);
}
