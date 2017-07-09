package com.rest.partner.service;

import com.rest.partner.domain.ContactInfo;
import com.rest.partner.domain.Partner;
import com.rest.partner.repository.ContactInfoRepository;
import com.rest.partner.repository.PartnerRepository;
import com.rest.partner.service.exception.AppLogicException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Validated
public class PartnerServiceImpl implements PartnerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartnerServiceImpl.class);
    private final PartnerRepository partnerRepository;
    private final ContactInfoRepository contactInfoRepository;

    @Inject
    public PartnerServiceImpl(final PartnerRepository partnerRepository,
                              final ContactInfoRepository contactInfoRepository) {
        this.partnerRepository = partnerRepository;
        this.contactInfoRepository = contactInfoRepository;

    }

    @Override
    @Transactional
    public Partner create(@NotNull @Valid final Partner partner) {
        LOGGER.debug("Creating {}", partner);
        List<Partner> existingPartners = partnerRepository.findByName(partner.getName());
        if (!existingPartners.isEmpty()) {
            throw new AppLogicException(
                    String.format("There already exists a partner with name=%s", partner.getName()));
        }

// todo temp code for creating ContactInfo
//        final ContactInfo contactInfo = new ContactInfo();
//        contactInfo.setContactPerson("Alex");
//        contactInfo.setContactType(ContactType.EMAIL);
//        contactInfo.setWorkingHours((long) 8, (long) 16);
//        contactInfo.setContactInfo("alex.alekhin@gmail.com");
//        contactInfoRepository.save(contactInfo);
//        partner.setContactInfo(contactInfo);

        return partnerRepository.save(partner);
    }

    @Override
    @Transactional(readOnly = true)
    public Partner read(@NotNull final Long partnerId) {
        LOGGER.debug("Reading partner with Id {}", partnerId);
        return partnerRepository.findOne(partnerId);
    }

    @Override
    @Transactional
    public Partner update(@NotNull final Long partnerId, @NotNull @Valid final Partner partner) {
        LOGGER.debug("Updating {}", partner);
        Partner existing = partnerRepository.findOne(partnerId);
        if (existing == null) {
            throw new AppLogicException(
                    String.format("Partner does not exist with id=%s", partnerId));
        }
        partner.setPartnerId(partnerId); // it's necessary to think about clever merging two entities (existing in db and client one)
        return partnerRepository.save(partner);
    }

    @Override
    @Transactional
    public ContactInfo updateContactInfo(Long partnerId, ContactInfo contactInfo) {
        LOGGER.debug("Updating {}", contactInfo);
        Partner existing = partnerRepository.findOne(partnerId);
        if (existing == null) {
            throw new AppLogicException(
                    String.format("Partner does not exist with id=%s", partnerId));
        }

        contactInfoRepository.save(contactInfo);
        existing.setContactInfo(contactInfo);

        return partnerRepository.save(existing).getContactInfo();
    }

    @Override
    @Transactional
    public void deleteContactInfo(Long partnerId) {
        LOGGER.debug("Deleting contact info for partner {}", partnerId);
        Partner existing = partnerRepository.findOne(partnerId);
        if (existing == null) {
            throw new AppLogicException(
                    String.format("Partner does not exist with id=%s", partnerId));
        }
        existing.setContactInfo(null);

        partnerRepository.save(existing);
    }


    @Override
    @Transactional
    public void delete(@NotNull @Valid final Long partnerId) {
        LOGGER.debug("Deleting partner with id {}", partnerId);
        Partner existing = partnerRepository.findOne(partnerId);
        if (existing == null) {
            throw new AppLogicException(
                    String.format("Partner does not exist with id=%s", partnerId));
        }
        partnerRepository.delete(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Partner> getList() {
        LOGGER.debug("Retrieving the list of all partners");
        return partnerRepository.findAll();
    }

}
