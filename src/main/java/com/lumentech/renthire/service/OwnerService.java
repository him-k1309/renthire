package com.lumentech.renthire.service;

import com.lumentech.renthire.payload.OwnerDto;
import com.lumentech.renthire.payload.PageResponse;

import java.util.List;

public interface OwnerService {
    OwnerDto createOwner(OwnerDto dto);

    OwnerDto findOwnerById(long id);

    OwnerDto updateOwner(OwnerDto dto, long id);

    void deleteOwnerDetail(long id);

    List<OwnerDto> findAllOwner();

    PageResponse getAllOwner(int pageNo, int pageSize, String sortBy, String sortDir);
}
