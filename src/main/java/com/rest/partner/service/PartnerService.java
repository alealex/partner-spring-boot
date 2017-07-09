package com.rest.partner.service;

import com.rest.partner.domain.ContactInfo;
import com.rest.partner.domain.Partner;

import java.util.List;

public interface PartnerService {

    Partner create(Partner partner);

    Partner read(Long id);

    Partner update(Long id, Partner partner);

    ContactInfo updateContactInfo(Long id, ContactInfo contactInfo);
    void deleteContactInfo(Long id);

    void delete(Long id);


    List<Partner> getList();



}
