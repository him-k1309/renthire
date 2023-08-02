package com.lumentech.renthire.service;

import com.lumentech.renthire.payload.AgentDto;

public interface AgentService {
    AgentDto createNewAgent(AgentDto dto);

    AgentDto getAgentDetailById(long id);
}
