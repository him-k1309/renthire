package com.lumentech.renthire.controller;

import com.lumentech.renthire.payload.ClientRegDto;
import com.lumentech.renthire.service.ClientRegService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientReg")
public class ClientRegController {

    private ClientRegService service;

    public ClientRegController(ClientRegService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ClientRegDto> createClientReg(@RequestBody ClientRegDto dto){
        return new ResponseEntity<>(service.createClientReg(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ClientRegDto> getAllClientRegDetails(){
        return service.getAllClientDetails();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientRegDto> getClientDetailById(@PathVariable(name="id") long id){
        ClientRegDto dto = service.getClientDetailsById(id);
        return ResponseEntity.ok(dto);
    }
}
