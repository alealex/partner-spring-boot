package com.rest.partner.service;

import com.rest.partner.domain.Address;
import com.rest.partner.domain.ContactInfo;
import com.rest.partner.domain.Partner;
import com.rest.partner.domain.PartnerAddress;
import com.rest.partner.repository.AddressRepository;
import com.rest.partner.repository.PartnerAddressRepository;
import com.rest.partner.repository.PartnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import java.util.*;

@Service
@Validated
public class ReportServiceImpl implements ReportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportServiceImpl.class);
    private final AddressRepository addressRepository;
    private final PartnerRepository partnerRepository;
    private final PartnerAddressRepository partnerAddressRepository;

    @Inject
    public ReportServiceImpl(final AddressRepository addressRepository,
                             final PartnerRepository partnerRepository,
                             final PartnerAddressRepository partnerAddressRepository) {
        this.addressRepository = addressRepository;
        this.partnerRepository = partnerRepository;
        this.partnerAddressRepository = partnerAddressRepository;
    }

    @Override
    public String generateReport(final List<Partner> allPartners) {
        final StringBuilder sb = new StringBuilder();
        for (Partner partner : allPartners) {
            sb.append(String.format("%010d", partner.getPartnerId())).append(", ");
            sb.append(partner.getName()).append(", ");
            sb.append(partner.getPartnerType()).append(", ");
            sb.append(partner.getTaxNumber()).append(", ");

            generateAddressPart(sb, partner);
            generateContactInfoPart(sb, partner);


            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

    private void generateAddressPart(final StringBuilder sb, Partner next) {
        Map<Address, String> addressToType = new HashMap<>();
        //todo optimize loading whole table
//        Set<PartnerAddress> partnerAddressSet = next.getPartnerAddresses();
        List<PartnerAddress> partnerAddressSet = partnerAddressRepository.findAll();
        for (PartnerAddress partnerAddress : partnerAddressSet) {
            Address address = partnerAddress.getAddress();
            if (!partnerAddress.getPartner().getPartnerId().equals(next.getPartnerId())) {
                continue;
            }
            String typesText = addressToType.get(address);
            if (typesText == null) {
                typesText = partnerAddress.getAddressType().name();
            } else {
                typesText = typesText + "/ " + partnerAddress.getAddressType().name();
            }
            addressToType.put(address, typesText);
        }

        for (Map.Entry<Address, String> entry :addressToType.entrySet()) {
            Address address = entry.getKey();
            String typesText = entry.getValue();

            sb.append(typesText).append(", ");
            sb.append(address.getCountry()).append(", ");
            sb.append(address.getIndex()).append(", ");
            sb.append(address.getState()).append(", ");
            sb.append(address.getProvince()).append(", ");
            sb.append(address.getCity()).append(", ");
            sb.append(address.getStreetAddress()).append("; ");
        }

    }
    private void generateContactInfoPart(final StringBuilder sb, Partner partner) {
        ContactInfo contactInfo = partner.getContactInfo();
        if (contactInfo != null) {
            sb.append(contactInfo.getContactPerson()).append(", ");
            sb.append(contactInfo.getContactType()).append(", ");
            sb.append(contactInfo.getWorkingHours().getStartHour()).append("-").append(contactInfo.getWorkingHours().getEndHour()).append(", ");
            sb.append(contactInfo.getContactInfo()).append(", ");
        } else {
            sb.append(", , , ,");
        }

    }

}
