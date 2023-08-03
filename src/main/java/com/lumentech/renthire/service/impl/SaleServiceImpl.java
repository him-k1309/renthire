package com.lumentech.renthire.service.impl;

import com.lumentech.renthire.entity.Agent;
import com.lumentech.renthire.entity.Sale;
import com.lumentech.renthire.exception.ResourceNotFoundException;
import com.lumentech.renthire.payload.SaleDto;
import com.lumentech.renthire.repository.AgentRepository;
import com.lumentech.renthire.repository.SaleRepository;
import com.lumentech.renthire.service.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

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

    private Sale mapToEntity(SaleDto saleDTO) {
        Sale sale = mapper.map(saleDTO, Sale.class);
        return sale;
    }

    private SaleDto mapToDto(Sale sale) {
        SaleDto saleDTO = mapper.map(sale, SaleDto.class);
        return saleDTO;
    }
}
