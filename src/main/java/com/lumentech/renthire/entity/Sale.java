package com.lumentech.renthire.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Client_Id")
    private int cid;

    @Column(name = "Agent_ID")
    private int agentId;

    @Column(name = "Property_Id")
    private int propertyId;
    private LocalDate saleDate;

    @ManyToOne
    @JoinColumn(name = "agentid", referencedColumnName = "Agent_ID")
    private Agent agent;

}
   /*

    {
  "cid": 1,
  "agentId": 1,
  "propertyId": 1,
  "saleDate": "2023-07-01",
  "agent": {
    "agentId": 3,
    "office": "Relience Office",
    "agentName": "John Doe",
    "phoneNo": 1234567890
  }
}
    */
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
