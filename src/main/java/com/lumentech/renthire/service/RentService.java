package com.lumentech.renthire.service;

import com.lumentech.renthire.payload.PageResponse;
import com.lumentech.renthire.payload.RentDto;

import java.util.List;

public interface RentService {
    RentDto createNewService(RentDto rentDto);

    RentDto getRentDetailById(long id);

    List<RentDto> getAllRentDetails();

    PageResponse getAllRentDetailsByPagination(int pageNo, int pageSize, String sortBy, String sortDir);

    RentDto updateRent(long id, RentDto rentDto);

    void deleteRentById(long id);
}
