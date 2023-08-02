package com.lumentech.renthire.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Client_Id")
    private int cid;

    @Column(name = "Agent_Id")
    private int agentId;

    @Column(name = "Property_Id")
    private int propertyId;
    private String saleDate;

    @ManyToOne
    @JoinColumn(name = "agentid",referencedColumnName = "Agent_Id")
    private Agent agent;

}
   /*

    {
  "id": 1,
  "agent_id": 1,
  "property_id": 1,
  "saleDate": "2023-07-01",
  "agent": {
    "id": 3,
    "office": "Relience Office",
    "agentName": "John Doe",
    "phoneNum": 1234567890
  }
}
    */
