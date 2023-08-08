package com.lumentech.renthire.controller;

import com.lumentech.renthire.payload.PageResponse;
import com.lumentech.renthire.payload.PropertyForSaleDto;
import com.lumentech.renthire.service.PropertyForSaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/propertySale")
public class PropertyForSaleController {

    private final PropertyForSaleService propertySaleService;

    public PropertyForSaleController(PropertyForSaleService propertySaleService){
        this.propertySaleService = propertySaleService;
    }

    // http://localhost:8080/api/propertySale/create
    @PostMapping("/create")
    public ResponseEntity<PropertyForSaleDto> createPropertyForSale(@RequestBody PropertyForSaleDto dto){
        PropertyForSaleDto propSaleDto = propertySaleService.createNewPropertySale(dto);
        return new ResponseEntity<>(propSaleDto, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/propertySale/1/getDetail
    @GetMapping("/{id}/getDetail")
    public ResponseEntity<PropertyForSaleDto> getSaleDataById(@PathVariable("id") long id){
        PropertyForSaleDto propSaleDto = propertySaleService.getPropertySaleDetailById(id);
        return new ResponseEntity<>(propSaleDto, HttpStatus.OK);
    }

    // http://localhost:8080/api/propertySale?pageNo=1&pageSize=2&sortBy=propertyId&sortDir=asc
    @GetMapping
    public PageResponse getAllPropertySaleDetails(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "2", required = false) int pageSize,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "propertyId", required = false) String sortBy
    ){
        return propertySaleService.getAllPropertySale(pageNo, pageSize, sortBy, sortDir);
    }

    // http://localhost:8080/api/propertySale/1
    @PutMapping("/{id}")
    public ResponseEntity<PropertyForSaleDto> updatePropertySale(@PathVariable("id") long id, @RequestBody PropertyForSaleDto propSaleDto){
        PropertyForSaleDto dto = propertySaleService.updatePropertySaleDetails(id, propSaleDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // http://localhost:8080/api/propertySale/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProperty(@PathVariable("id") long id){
        propertySaleService.deletePropertySaleDetail(id);
        return new ResponseEntity<>("Property Sale Details Deleted Successfully!!", HttpStatus.NO_CONTENT);
    }
}
