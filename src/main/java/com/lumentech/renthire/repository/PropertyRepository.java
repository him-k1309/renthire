package com.lumentech.renthire.repository;

import com.lumentech.renthire.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
