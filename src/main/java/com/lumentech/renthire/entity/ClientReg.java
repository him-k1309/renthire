package com.lumentech.renthire.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Client_Reg")
public class ClientReg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clientId")
    private long clientId;

    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false)
    private String clientAddress;

    @Column(nullable = false)
    private long phone;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

//    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
//    @JoinColumn(name = "client_id")
//    private Sale sale;

    @OneToOne
    @JoinColumn(name = "sale_cid",referencedColumnName = "sale_id")
    private Sale sale;

}
