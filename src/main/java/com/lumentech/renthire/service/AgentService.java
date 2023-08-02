package com.lumentech.renthire.service;

import com.lumentech.renthire.payload.AgentDto;
import com.lumentech.renthire.payload.PageResponse;

import java.util.List;

public interface AgentService {
    AgentDto createNewAgent(AgentDto dto);

    AgentDto getAgentDetailById(long id);

    List<AgentDto> getAllAgentDetails();

    PageResponse getAllAgentPagination(int pageNo, int pageSize, String sortBy, String sortDir);
}
