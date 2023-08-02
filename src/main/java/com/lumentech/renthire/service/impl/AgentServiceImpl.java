package com.lumentech.renthire.service.impl;

import com.lumentech.renthire.entity.Agent;
import com.lumentech.renthire.exception.ResourceNotFoundException;
import com.lumentech.renthire.payload.AgentDto;
import com.lumentech.renthire.payload.PageResponse;
import com.lumentech.renthire.repository.AgentRepository;
import com.lumentech.renthire.service.AgentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<AgentDto> getAllAgentDetails() {
        List<Agent> allAgent = agentRepo.findAll();
        return allAgent.stream().map(agent -> mapToDto(agent)).collect(Collectors.toList());
    }

    @Override
    public PageResponse getAllAgentPagination(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Agent> agents = agentRepo.findAll(pageable);

        List<Agent> listOfAgent = agents.getContent();
        List<AgentDto> listOfAgentDto = listOfAgent.stream()
                .map(agent -> mapToDto(agent))
                .collect(Collectors.toList());

        PageResponse pageResponse = new PageResponse();
        pageResponse.setAgentContent(listOfAgentDto);
        pageResponse.setPageNo(agents.getNumber());
        pageResponse.setPageSize(agents.getSize());
        pageResponse.setTotalPages(agents.getTotalPages());
        pageResponse.setTotalElements(agents.getTotalElements());
        pageResponse.setLast(agents.isLast());
        return pageResponse;
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
