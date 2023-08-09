package com.lumentech.renthire.controller;

import com.lumentech.renthire.payload.OwnerDto;
import com.lumentech.renthire.payload.PageResponse;
import com.lumentech.renthire.repository.OwnerRepository;
import com.lumentech.renthire.service.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owner")
public class OwnerController {

    private final OwnerService ownerService;
    private final OwnerRepository ownerRepository;

    public OwnerController(OwnerRepository ownerRepository, OwnerService ownerService){
        this.ownerRepository = ownerRepository;
        this.ownerService = ownerService;
    }

    // http://localhost:8080/api/owner
    @PostMapping
    public ResponseEntity<OwnerDto> createOwner(@RequestBody OwnerDto dto){
        OwnerDto ownerDto = ownerService.createOwner(dto);
        return new ResponseEntity<>(ownerDto, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/owner/getAllOwner
    @GetMapping("/getAllOwner")
    public ResponseEntity<List<OwnerDto>> getAllOwner(){
        List<OwnerDto> dto = ownerService.findAllOwner();
        return ResponseEntity.ok(dto);
    }

    // http://localhost:8080/api/owner/5
    @GetMapping("/{id}")
    public ResponseEntity<OwnerDto> getOwnerById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(ownerService.findOwnerById(id));
    }

    // http://localhost:8080/api/owner?pageNo=1&pageSize=2&sortBy=ownerId&sortDir=asc
    @GetMapping
    public PageResponse getAllOwnerDetails(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "2", required = false) int pageSize,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "ownerId", required = false) String sortBy
    ){
        return ownerService.getAllOwner(pageNo, pageSize, sortBy, sortDir);
    }

    // http://localhost:8080/api/owner/5
    @PutMapping("/{id}")
    public ResponseEntity<OwnerDto> updateOwner(@RequestBody OwnerDto dto,
                                                @PathVariable(name = "id") long id){
        return ResponseEntity.ok(ownerService.updateOwner(dto, id));
    }

    // http://localhost:8080/api/owner/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOwner(@PathVariable(name = "id") long id){
        ownerService.deleteOwnerDetail(id);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }
}
