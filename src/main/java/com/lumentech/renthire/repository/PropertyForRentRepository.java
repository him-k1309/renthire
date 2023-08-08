package com.lumentech.renthire.repository;

import com.lumentech.renthire.entity.PropertyForRent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyForRentRepository extends JpaRepository<PropertyForRent, Long> {
}
