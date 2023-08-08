package com.lumentech.renthire.service;

import com.lumentech.renthire.payload.PageResponse;
import com.lumentech.renthire.payload.PropertyForSaleDto;

public interface PropertyForSaleService {
    PropertyForSaleDto createNewPropertySale(PropertyForSaleDto dto);

    PropertyForSaleDto getPropertySaleDetailById(long id);

    PageResponse getAllPropertySale(int pageNo, int pageSize, String sortBy, String sortDir);

    PropertyForSaleDto updatePropertySaleDetails(long id, PropertyForSaleDto propSaleDto);

    void deletePropertySaleDetail(long id);
}
