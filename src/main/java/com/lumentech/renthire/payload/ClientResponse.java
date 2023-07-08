package com.lumentech.renthire.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {
    private List<ClientRegDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int tatalPages;
    private boolean last;
}
