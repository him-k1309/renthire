package com.lumentech.renthire.controller;

import com.lumentech.renthire.payload.AgentDto;
import com.lumentech.renthire.service.AgentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agent")
public class AgentController {

    private final AgentService agentService;

    public AgentController(AgentService agentService){
        this.agentService = agentService;
    }

    // http://localhost:8080/api/agent
    @PostMapping
    public ResponseEntity<AgentDto> createAgent(@RequestBody AgentDto dto){
        AgentDto agentDto = agentService.createNewAgent(dto);
        return new ResponseEntity<>(agentDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/getDetail")
    public ResponseEntity<AgentDto> getAgentDetailByID(@PathVariable(name = "id") long id){
        AgentDto dto = agentService.getAgentDetailById(id);
        return ResponseEntity.ok(dto);
    }
}
