package com.lumentech.renthire.service;

import com.lumentech.renthire.payload.ClientRegDto;
import com.lumentech.renthire.payload.PageResponse;

public interface ClientRegService {
    ClientRegDto createClientReg(ClientRegDto clientRegDto);

    PageResponse getAllClientDetails(int pageNo, int pageSize, String sortBy, String sortDir);

    ClientRegDto getClientDetailsById(long id);

    ClientRegDto updateClientDetails(ClientRegDto dto, long id);

    void deleteClientDetail(long id);
}
