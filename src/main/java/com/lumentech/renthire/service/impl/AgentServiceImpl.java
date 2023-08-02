package com.lumentech.renthire.service.impl;

import com.lumentech.renthire.entity.Agent;
import com.lumentech.renthire.exception.ResourceNotFoundException;
import com.lumentech.renthire.payload.AgentDto;
import com.lumentech.renthire.repository.AgentRepository;
import com.lumentech.renthire.service.AgentService;
import org.springframework.stereotype.Service;

@Service
public class AgentServiceImpl implements AgentService {

    private final AgentRepository agentRepo;

    public AgentServiceImpl(AgentRepository agentRepo){
        this.agentRepo = agentRepo;
    }
    @Override
    public AgentDto createNewAgent(AgentDto dto) {
        Agent agent = mapToEntity(dto);
        Agent newAgentSave = agentRepo.save(agent);
        AgentDto agentDto = mapToDto(newAgentSave);
        return agentDto;
    }

    @Override
    public AgentDto getAgentDetailById(long id) {
        Agent agent = agentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Agent","id",id)
        );
        return mapToDto(agent);
    }

    private AgentDto mapToDto(Agent agent) {
        AgentDto dto = new AgentDto();
        dto.setAgentId(agent.getAgentId());
        dto.setAgentName(agent.getAgentName());
        dto.setOffice(agent.getOffice());
        dto.setPhoneNo(agent.getPhoneNo());
        return dto;
    }

    private Agent mapToEntity(AgentDto dto) {
        Agent agent = new Agent();
        agent.setAgentName(dto.getAgentName());
        agent.setOffice(dto.getOffice());
        agent.setPhoneNo(dto.getPhoneNo());
        return agent;
    }
}
