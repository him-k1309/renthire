package com.lumentech.renthire.controller;

import com.lumentech.renthire.payload.ClientRegDto;
import com.lumentech.renthire.payload.PageResponse;
import com.lumentech.renthire.service.ClientRegService;
import com.lumentech.renthire.util.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientReg")
public class ClientRegController {

    private final ClientRegService clientRegService;
    private final EmailService emailService;

    public ClientRegController(ClientRegService clientRegService, EmailService emailService){
        this.emailService = emailService;
        this.clientRegService = clientRegService;
    }

    @PostMapping
    public ResponseEntity<ClientRegDto> createClientReg(@RequestBody ClientRegDto dto){
        ClientRegDto clientDto = clientRegService.createClientReg(dto);
        emailService.sendRegistrationEmail(clientDto.getEmail(), clientDto.getClientName());
        return new ResponseEntity<>(clientDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageResponse> getAllClientRegDetails(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){

        PageResponse clientDetails = clientRegService.getAllClientDetails(pageNo, pageSize, sortBy, sortDir);
        return ResponseEntity.ok(clientDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientRegDto> getClientDetailById(@PathVariable(name="id") long id){
        ClientRegDto dto = clientRegService.getClientDetailsById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientRegDto> updateClientReg(@RequestBody ClientRegDto dto,
                                                        @PathVariable(name="id") long id){
        ClientRegDto clientRegDto = clientRegService.updateClientDetails(dto,id);
        return ResponseEntity.ok(clientRegDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClientReg(@PathVariable(name="id") long id){
        clientRegService.deleteClientDetail(id);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }
}
