package com.lumentech.renthire.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse {
    private List<ClientRegDto> clientContent;
    private List<SaleDto> saleContent;
    private List<AgentDto> agentContent;
    private List<PropertyDto> propContent;
    private List<PropertyForSaleDto> propForSaleContent;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
