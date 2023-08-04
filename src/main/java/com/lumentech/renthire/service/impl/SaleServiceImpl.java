package com.lumentech.renthire.service.impl;

import com.lumentech.renthire.entity.Agent;
import com.lumentech.renthire.entity.Sale;
import com.lumentech.renthire.exception.ResourceNotFoundException;
import com.lumentech.renthire.payload.PageResponse;
import com.lumentech.renthire.payload.SaleDto;
import com.lumentech.renthire.repository.AgentRepository;
import com.lumentech.renthire.repository.SaleRepository;
import com.lumentech.renthire.service.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private AgentRepository agentRepo;

    @Autowired
    private SaleRepository saleRepo;

    private ModelMapper mapper;
    public SaleServiceImpl(ModelMapper mapper){
        this.mapper=mapper;
    }

    @Override
    public SaleDto createSale(SaleDto saleDTO) {
        long agentId = saleDTO.getAgentId();
        // Check if the provided Agent ID exists in the Agent table
        Optional<Agent> agent = agentRepo.findById(agentId);
        if (!agent.isPresent()){
            throw new ResourceNotFoundException("Agent", "agent_Id", agentId);
        }
        // If the Agent exists, proceed with creating the Sale entity
        Agent agentDetail = agent.get();
        Sale sale = mapToEntity(saleDTO);
        sale.setAgent(agentDetail); // Set the existing Agent to the Sale entity
        Sale saveSale = saleRepo.save(sale);
        SaleDto dto = mapToDto(saveSale);
        return dto;
    }

    @Override
    public SaleDto getSaleDetailById(long id) {
        Sale sale = saleRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Sale", "id", id)
        );
        SaleDto saleDto = mapToDto(sale);
        return saleDto;
    }

    @Override
    public List<SaleDto> getAllSaleDetails() {
        List<Sale> allSale = saleRepo.findAll();
        List<SaleDto> saleDto = allSale.stream().map(sale -> mapToDto(sale)).collect(Collectors.toList());
        return saleDto;
    }

    @Override
    public PageResponse getAllSaleDetailsByPagination(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Sale> salePage = saleRepo.findAll(pageable);

        List<Sale> content = salePage.getContent();
        List<SaleDto> contentList = content.stream()
                .map(sale -> mapToDto(sale))
                .collect(Collectors.toList());

        PageResponse pageResponse = new PageResponse();
        pageResponse.setSaleContent(contentList);
        pageResponse.setPageNo(salePage.getNumber());
        pageResponse.setPageSize(salePage.getSize());
        pageResponse.setTotalPages(salePage.getSize());
        pageResponse.setTotalPages(salePage.getTotalPages());
        pageResponse.setTotalElements(salePage.getTotalElements());
        pageResponse.setLast(salePage.isLast());
        return pageResponse;
    }

    @Override
    public SaleDto updateSale(long id, SaleDto saleDto) {
        Sale sale = saleRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Sale", "id", id)
        );
        sale.setSaleDate(saleDto.getSaleDate());
        Sale update = saleRepo.save(sale);
        return mapToDto(update);
    }

    @Override
    public void deleteSaleById(long id) {
        Sale sale = saleRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Sale", "id", id)
        );
        saleRepo.deleteById(id);
    }

    private Sale mapToEntity(SaleDto saleDTO) {
        Sale sale = mapper.map(saleDTO, Sale.class);
        return sale;
    }

    private SaleDto mapToDto(Sale sale) {
        SaleDto saleDTO = mapper.map(sale, SaleDto.class);
        return saleDTO;
    }
}
