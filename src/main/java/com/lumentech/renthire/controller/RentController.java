package com.lumentech.renthire.controller;

import com.lumentech.renthire.payload.PageResponse;
import com.lumentech.renthire.payload.RentDto;
import com.lumentech.renthire.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rent")
public class RentController {

    @Autowired
    private RentService rentService;

    // http://localhost:8080/api/rent/create
    @PostMapping("/create")
    public ResponseEntity<RentDto> createRent(@RequestBody RentDto rentDto){
        RentDto dto = rentService.createNewService(rentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/rent/1/getRent
    @GetMapping("/{id}/getRent")
    public ResponseEntity<RentDto> getRentById(@PathVariable("id") long id){
        RentDto rentDto = rentService.getRentDetailById(id);
        return new ResponseEntity<>(rentDto, HttpStatus.OK);
    }

    // http://localhost:8080/api/rent/getAllRent
    @GetMapping("/getAllRent")
    public ResponseEntity<List<RentDto>> getAllRent() {
        List<RentDto> dto = rentService.getAllRentDetails();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // http://localhost:8080/api/rent?pageNo=1&pageSize=2&sortBy=clientId&sortDir=asc
    @GetMapping
    public PageResponse getAllRentByPagination(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "2", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "clientId", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        return rentService.getAllRentDetailsByPagination(pageNo, pageSize, sortBy, sortDir);
    }

    // http://localhost:8080/api/rent/1
    @PutMapping("/{id}")
    public ResponseEntity<RentDto> updateRent(@PathVariable("id") long id, @RequestBody RentDto rentDto){
        RentDto dto = rentService.updateRent(id, rentDto);
        return ResponseEntity.ok(dto);
    }

    // http://localhost:8080/api/rent/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRent(@PathVariable("id") long id){
        rentService.deleteRentById(id);
        return new ResponseEntity<>("Rent Details Deleted Successfully!!", HttpStatus.NO_CONTENT);
    }
}
