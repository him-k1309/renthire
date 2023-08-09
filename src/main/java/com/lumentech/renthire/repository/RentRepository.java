package com.lumentech.renthire.repository;

import com.lumentech.renthire.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<Rent, Long> {
}
