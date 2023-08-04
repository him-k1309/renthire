package com.lumentech.renthire.controller;

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
}
