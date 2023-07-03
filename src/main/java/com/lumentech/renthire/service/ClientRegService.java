package com.lumentech.renthire.service;

import com.lumentech.renthire.payload.ClientRegDto;

import java.util.List;

public interface ClientRegService {
    ClientRegDto createClientReg(ClientRegDto clientRegDto);

    List<ClientRegDto> getAllClientDetails(int pageNo, int pageSize, String sortBy, String sortDir);

    ClientRegDto getClientDetailsById(long id);

    ClientRegDto updateClientDetails(ClientRegDto dto, long id);

    void deleteClientDetail(long id);
}
