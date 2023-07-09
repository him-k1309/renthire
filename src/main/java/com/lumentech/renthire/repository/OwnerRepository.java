package com.lumentech.renthire.repository;

import com.lumentech.renthire.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
