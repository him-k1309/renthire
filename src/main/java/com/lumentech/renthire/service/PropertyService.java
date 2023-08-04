package com.lumentech.renthire.service;

import com.lumentech.renthire.payload.PropertyDto;

public interface PropertyService {
    public PropertyDto createNewProperty(PropertyDto dto);

    PropertyDto getPropertyDetailById(long id);
}
