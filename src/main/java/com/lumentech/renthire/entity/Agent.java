package com.lumentech.renthire.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "Agent")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Agent_Id")
    private long agentId;
    private String office;
    private String agentName;
    private long phoneNo;

    @OneToMany(mappedBy = "agent", fetch = FetchType.LAZY)
    private List<Sale> sales= new ArrayList<>();

}
