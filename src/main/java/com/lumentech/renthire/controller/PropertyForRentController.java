package com.lumentech.renthire.controller;

import com.lumentech.renthire.payload.PageResponse;
import com.lumentech.renthire.payload.PropertyForRentDto;
import com.lumentech.renthire.service.PropertyForRentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/propertyRent")
public class PropertyForRentController {

    private final PropertyForRentService propertyRentService;

    public PropertyForRentController(PropertyForRentService propertyRentService){
        this.propertyRentService = propertyRentService;
    }

    // http://localhost:8080/api/propertyRent/create
    @PostMapping("/create")
    public ResponseEntity<PropertyForRentDto> createPropertyForRent(@RequestBody PropertyForRentDto dto){
        PropertyForRentDto propRentDto = propertyRentService.createNewPropertyRent(dto);
        return new ResponseEntity<>(propRentDto, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/propertyRent/1/getDetail
    @GetMapping("/{id}/getDetail")
    public ResponseEntity<PropertyForRentDto> getRentDataById(@PathVariable("id") long id){
        PropertyForRentDto propRentDto = propertyRentService.getPropertyRentDetailById(id);
        return new ResponseEntity<>(propRentDto, HttpStatus.OK);
    }

    // http://localhost:8080/api/propertyRent?pageNo=1&pageSize=2&sortBy=propertyId&sortDir=asc
    @GetMapping
    public PageResponse getAllPropertyRentDetails(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "2", required = false) int pageSize,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "propertyId", required = false) String sortBy
    ){
        return propertyRentService.getAllPropertyRent(pageNo, pageSize, sortBy, sortDir);
    }

    // http://localhost:8080/api/propertyRent/1
    @PutMapping("/{id}")
    public ResponseEntity<PropertyForRentDto> updatePropertyRent(@PathVariable("id") long id, @RequestBody PropertyForRentDto propRentDto){
        PropertyForRentDto dto = propertyRentService.updatePropertyRentDetails(id, propRentDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // http://localhost:8080/api/propertyRent/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePropertyRent(@PathVariable("id") long id){
        propertyRentService.deletePropertyRentDetail(id);
        return new ResponseEntity<>("Property Rent Details Deleted Successfully!!", HttpStatus.NO_CONTENT);
    }
}

