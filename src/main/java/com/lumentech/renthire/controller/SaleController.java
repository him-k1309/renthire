package com.lumentech.renthire.controller;

import com.lumentech.renthire.payload.PageResponse;
import com.lumentech.renthire.payload.SaleDto;
import com.lumentech.renthire.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // http://localhost:8080/api/sale/1/getData
    @GetMapping("/{id}/getData")
    public ResponseEntity<SaleDto> getSaleDataById(@PathVariable("id") long id){
        SaleDto saleDto = saleService.getSaleDetailById(id);
        return new ResponseEntity<>(saleDto, HttpStatus.OK);
    }

    // http://localhost:8080/api/sale/getAllSale
    @GetMapping("/getAllSale")
    public ResponseEntity<List<SaleDto>> getAllSale() {
        List<SaleDto> dto = saleService.getAllSaleDetails();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // http://localhost:8080/api/sale?pageNo=1&pageSize=2&sortBy=saleId&sortDir=asc
    @GetMapping
    public PageResponse getAllSaleByPagination(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "2", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        return saleService.getAllSaleDetailsByPagination(pageNo, pageSize, sortBy, sortDir);
    }

    // http://localhost:8080/api/sale/1
    @PutMapping("/{id}")
    public ResponseEntity<SaleDto> updateSale(@PathVariable("id") long id, @RequestBody SaleDto saleDto){
        SaleDto dto = saleService.updateSale(id, saleDto);
        return ResponseEntity.ok(dto);
    }

    // http://localhost:8080/api/sale/1
    @DeleteMapping("/{cid}")
    public ResponseEntity<String> deleteSale(@PathVariable("cid") int id){
        saleService.deleteSaleById(id);
        return new ResponseEntity<>("Sale Details Deleted Successfully!!", HttpStatus.NO_CONTENT);
    }

}
