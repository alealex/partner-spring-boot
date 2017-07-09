package com.rest.partner.repository;

import com.rest.partner.domain.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactInfoRepository extends JpaRepository<ContactInfo, Long> {


}
