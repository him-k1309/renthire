package com.lumentech.renthire.controller;

import com.lumentech.renthire.payload.SaleDto;
import com.lumentech.renthire.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sale")
public class SaleController {

    @Autowired
    private SaleService saleService;

    // http://localhost:8080/api/sale/create
    @PostMapping("/create")
    public ResponseEntity<SaleDto> createSale(@RequestBody SaleDto saleDTO) {
        SaleDto DTO = saleService.createSale(saleDTO);
        return new ResponseEntity<>(DTO, HttpStatus.CREATED);
    }
}
