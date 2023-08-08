package com.lumentech.renthire.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "property_for_rent")
public class PropertyForRent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long propertyId;

    private int propertyRentPrice;

    private boolean propertyAvailable;

}

