package com.lumentech.renthire.service;

import com.lumentech.renthire.payload.PageResponse;
import com.lumentech.renthire.payload.SaleDto;

import java.util.List;

public interface SaleService {
    SaleDto createSale(SaleDto saleDTO);

    SaleDto getSaleDetailById(long id);

    List<SaleDto> getAllSaleDetails();

    PageResponse getAllSaleDetailsByPagination(int pageNo, int pageSize, String sortBy, String sortDir);

    SaleDto updateSale(long id, SaleDto saleDto);

    void deleteSaleById(long id);
}
