package com.lumentech.renthire.service;

import com.lumentech.renthire.payload.PageResponse;
import com.lumentech.renthire.payload.PropertyForRentDto;

public interface PropertyForRentService {
    PropertyForRentDto createNewPropertyRent(PropertyForRentDto dto);

    PropertyForRentDto getPropertyRentDetailById(long id);

    PageResponse getAllPropertyRent(int pageNo, int pageSize, String sortBy, String sortDir);

    PropertyForRentDto updatePropertyRentDetails(long id, PropertyForRentDto propRentDto);

    void deletePropertyRentDetail(long id);
}
