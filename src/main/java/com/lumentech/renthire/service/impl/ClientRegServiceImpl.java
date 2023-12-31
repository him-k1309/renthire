package com.lumentech.renthire.service.impl;

import com.lumentech.renthire.entity.ClientReg;
import com.lumentech.renthire.entity.Gender;
import com.lumentech.renthire.entity.Sale;
import com.lumentech.renthire.exception.ResourceNotFoundException;
import com.lumentech.renthire.payload.ClientRegDto;
import com.lumentech.renthire.payload.PageResponse;
import com.lumentech.renthire.payload.SaleDto;
import com.lumentech.renthire.repository.ClientRegRepository;
import com.lumentech.renthire.repository.SaleRepository;
import com.lumentech.renthire.service.ClientRegService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientRegServiceImpl implements ClientRegService {

    private final ClientRegRepository clientRegRepository;
    private final SaleRepository saleRepo;

    private ModelMapper mapper;

    public ClientRegServiceImpl(ClientRegRepository clientRegRepository, ModelMapper mapper, SaleRepository saleRepo){
        this.clientRegRepository = clientRegRepository;
        this.mapper = mapper;
        this.saleRepo = saleRepo;
    }
    @Override
    public ClientRegDto createClientReg(ClientRegDto clientRegDto) {
        long saleId = clientRegDto.getSale().getSaleId();
        Optional<Sale> sale = saleRepo.findById(saleId);
        if(!sale.isPresent()){
            throw new ResourceNotFoundException("Sale", "saleId", saleId);
        }
        Sale saleDetail = sale.get();
        ClientReg clientReg = mapToEntity(clientRegDto);
        clientReg.setSale(saleDetail);
        ClientReg newClient = clientRegRepository.save(clientReg);
        ClientRegDto dto = mapToDto(newClient);
        return dto;
    }

    @Override
    public PageResponse getAllClientDetails(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? 
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<ClientReg> clientRegs = clientRegRepository.findAll(pageable);

        List<ClientReg> listOfClient = clientRegs.getContent();
        List<ClientRegDto> listOfClientDto = listOfClient.stream().map(clientReg
                -> mapToDto(clientReg)).collect(Collectors.toList());

        PageResponse pageResponse = new PageResponse();
        pageResponse.setClientContent(listOfClientDto);
        pageResponse.setPageNo(clientRegs.getNumber());
        pageResponse.setPageSize(clientRegs.getSize());
        pageResponse.setTotalPages(clientRegs.getTotalPages());
        pageResponse.setTotalElements(clientRegs.getTotalElements());
        pageResponse.setLast(clientRegs.isLast());
        return pageResponse;

//        List<ClientReg> all = clientRegRepository.findAll();
//        return all.stream().map(clientReg -> mapToDto(clientReg)).collect(Collectors.toList());
    }

    @Override
    public ClientRegDto getClientDetailsById(long id) {
        ClientReg clientReg = clientRegRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("ClientReg", "id", id)
        );
        return mapToDto(clientReg);
    }

    @Override
    public ClientRegDto updateClientDetails(ClientRegDto dto, long id) {
        ClientReg clientReg = clientRegRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("ClientReg", "id", id)
        );
        clientReg.setClientAddress(dto.getClientAddress());
        clientReg.setPhone(dto.getPhone());
        ClientReg update = clientRegRepository.save(clientReg);
        return mapToDto(update);
    }

    @Override
    public void deleteClientDetail(long id) {
        ClientReg clientReg = clientRegRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("ClientReg", "id", id)
        );
        clientRegRepository.delete(clientReg);
    }

    private ClientReg mapToEntity(ClientRegDto clientRegDto) {
        ClientReg clientReg = mapper.map(clientRegDto, ClientReg.class);
        return clientReg;
    }

    private ClientRegDto mapToDto(ClientReg newClient) {
        ClientRegDto clientDto = mapper.map(newClient, ClientRegDto.class);
        return clientDto;
    }

//    public ClientRegDto mapToDto(ClientReg newClient) {
//        ClientRegDto dto = new ClientRegDto();
//        dto.setClientId(newClient.getClientId());
//        dto.setClientName(newClient.getClientName());
//        dto.setClientAddress(newClient.getClientAddress());
//        dto.setPhone(newClient.getPhone());
//        dto.setEmail(newClient.getEmail());
//        dto.setGender(String.valueOf(newClient.getGender()));
//        return dto;
//    }
//
//    public ClientReg mapToEntity(ClientRegDto clientRegDto) {
//        ClientReg clientReg = new ClientReg();
//        clientReg.setClientName(clientRegDto.getClientName());
//        clientReg.setClientAddress(clientRegDto.getClientAddress());
//        clientReg.setPhone(clientRegDto.getPhone());
//        clientReg.setEmail(clientRegDto.getEmail());
//        clientReg.setGender(Gender.valueOf(clientRegDto.getGender()));
//        return clientReg;
//    }
}
