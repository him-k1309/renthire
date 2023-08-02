package com.lumentech.renthire.controller;

import com.lumentech.renthire.payload.AgentDto;
import com.lumentech.renthire.payload.PageResponse;
import com.lumentech.renthire.service.AgentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // http://localhost:8080/api/agent/5/getDetail
    @GetMapping("/{id}/getDetail")
    public ResponseEntity<AgentDto> getAgentDetailByID(@PathVariable(name = "id") long id){
        AgentDto dto = agentService.getAgentDetailById(id);
        return ResponseEntity.ok(dto);
    }

    // http://localhost:8080/api/agent/getAllAgent
    @GetMapping("/getAllAgent")
    public ResponseEntity<List<AgentDto>> getAllAgentDetails(){
        List<AgentDto> agentDto = agentService.getAllAgentDetails();
        return new ResponseEntity<>(agentDto, HttpStatus.OK);
    }

    // http://localhost:8080/api/agent?pageNo=0&pageSize=2&sortBy=id&sortDir=asc
    @GetMapping
    public ResponseEntity<PageResponse> getAllAgentPagination(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        PageResponse agentDetails = agentService.getAllAgentPagination(pageNo, pageSize, sortBy, sortDir);
        return ResponseEntity.ok(agentDetails);
    }
}
