package com.lumentech.renthire.controller;

import com.lumentech.renthire.payload.PageResponse;
import com.lumentech.renthire.payload.PropertyDto;
import com.lumentech.renthire.payload.SaleDto;
import com.lumentech.renthire.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/property")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService){
        this.propertyService = propertyService;
    }

    // http://localhost:8080/api/property/create
    @PostMapping("/create")
    public ResponseEntity<PropertyDto> createProperty(@RequestBody PropertyDto dto){
        PropertyDto propDto = propertyService.createNewProperty(dto);
        return new ResponseEntity<>(propDto, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/property/1/getDetail
    @GetMapping("/{id}/getDetail")
    public ResponseEntity<PropertyDto> getSaleDataById(@PathVariable("id") long id){
        PropertyDto propDto = propertyService.getPropertyDetailById(id);
        return new ResponseEntity<>(propDto, HttpStatus.OK);
    }

    // http://localhost:8080/api/property?pageNo=1&pageSize=2&sortBy=propertyId&sortDir=asc
    @GetMapping
    public PageResponse getAllPropertyDetails(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "2", required = false) int pageSize,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "propertyId", required = false) String sortBy
    ){
        return propertyService.getAllProperty(pageNo, pageSize, sortBy, sortDir);
    }

    // http://localhost:8080/api/property/1
    @PutMapping("/{id}")
    public ResponseEntity<PropertyDto> updateProperty(@PathVariable("id") long id, @RequestBody PropertyDto propDto){
        PropertyDto dto = propertyService.updatePropertyDetails(id, propDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // http://localhost:8080/api/property/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProperty(@PathVariable("id") long id){
        propertyService.deletePropertyDetail(id);
        return new ResponseEntity<>("Property Details Deleted Successfully!!", HttpStatus.NO_CONTENT);
    }
}
