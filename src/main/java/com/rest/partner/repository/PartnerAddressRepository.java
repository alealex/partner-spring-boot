package com.rest.partner.repository;

import com.rest.partner.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PartnerAddressRepository extends JpaRepository<PartnerAddress, PartnerAddressId> {
}
