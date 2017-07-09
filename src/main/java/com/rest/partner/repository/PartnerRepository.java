package com.rest.partner.repository;

import com.rest.partner.domain.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
    List<Partner> findByName(@Param("name") String name);
}
