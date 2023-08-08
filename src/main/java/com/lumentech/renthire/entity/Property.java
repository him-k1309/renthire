package com.lumentech.renthire.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "property")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long propertyId;

    @Column(nullable = false)
    private Instant propertyDateAdd;

    @Column(nullable = false)
    private String propertyAddress;

    @Column(nullable = false)
    private int propertyNumOfRooms;

    @Column(nullable = false)
    private int propertyNumOfBedroom;

    @Column(nullable = false)
    private int propertyNumOfBathroom;

    @Column(nullable = false)
    private int propertyNumOfGarage;

    @Column(nullable = false)
    private String propertyDescription;

}
