package com.lumentech.renthire.service;

import com.lumentech.renthire.payload.PageResponse;
import com.lumentech.renthire.payload.PropertyDto;

public interface PropertyService {
    public PropertyDto createNewProperty(PropertyDto dto);

    PropertyDto getPropertyDetailById(long id);

    PageResponse getAllProperty(int pageNo, int pageSize, String sortBy, String sortDir);

    PropertyDto updatePropertyDetails(long id, PropertyDto propDto);

    void deletePropertyDetail(long id);
}
