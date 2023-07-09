package com.lumentech.renthire.controller;

import com.lumentech.renthire.payload.OwnerDto;
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

    @PostMapping
    public ResponseEntity<OwnerDto> createOwner(@RequestBody OwnerDto dto){
        OwnerDto ownerDto = ownerService.createOwner(dto);
        return new ResponseEntity<>(ownerDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OwnerDto>> getAllOwner(){
        List<OwnerDto> dto = ownerService.findAllOwner();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerDto> getOwnerById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(ownerService.findOwnerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerDto> updateOwner(@RequestBody OwnerDto dto,
                                                @PathVariable(name = "id") long id){
        return ResponseEntity.ok(ownerService.updateOwner(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOwner(@PathVariable(name = "id") long id){
        ownerService.deleteOwnerDetail(id);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }
}
