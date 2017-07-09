package com.rest.partner.service;

import com.rest.partner.domain.*;
import com.rest.partner.repository.AddressRepository;
import com.rest.partner.repository.PartnerAddressRepository;
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
import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class AddressServiceImpl implements AddressService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressServiceImpl.class);
    private final AddressRepository addressRepository;
    private final PartnerRepository partnerRepository;
    private final PartnerAddressRepository partnerAddressRepository;

    @Inject
    public AddressServiceImpl(final AddressRepository addressRepository,
                              final PartnerRepository partnerRepository,
                              final PartnerAddressRepository partnerAddressRepository) {
        this.addressRepository = addressRepository;
        this.partnerRepository = partnerRepository;
        this.partnerAddressRepository = partnerAddressRepository;
    }

    @Override
    @Transactional
    public Address create(@NotNull @Valid final Address address) {
        LOGGER.debug("Creating {}", address);
        Address existingAddress = addressRepository.findByParams(address.getCountry(), address.getIndex(), address.getState(),
                address.getProvince(), address.getCity(), address.getStreetAddress());
        if (existingAddress != null) {
            return existingAddress;
        }
        return addressRepository.save(address);
    }

    @Override
    @Transactional(readOnly = true)
    public Address read(@NotNull final Long addressId) {
        LOGGER.debug("Reading address with Id {}", addressId);
        return addressRepository.findOne(addressId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Address> getList() {
        LOGGER.debug("Retrieving the list of all addresss");
        return addressRepository.findAll();
    }

    @Override
    @Transactional
    public void storePartnerAddress(@NotNull final Long partnerId, @NotNull final AddressType addressType, Long addressId, @NotNull final Address address) {
        Partner partner = partnerRepository.findOne(partnerId);
        if (partner == null) {
            throw new AppLogicException("Partner is not found");
        }

        // Real and Registered addresses are single, we always update it data (don't add links to a new addresses)
        if (AddressType.REGISTERED.equals(addressType) || AddressType.REAL.equals(addressType)) {
            PartnerAddress link = partner.getPartnerAddress(addressType, null);
            if (link != null) {
                addressId = link.getAddress().getAddressId();
            }
        }
        if (addressId != null) {
            deletePartnerAddress(partnerId, addressType, addressId, true);
        }

        final Address createdAddress = this.create(address);
        PartnerAddress partnerAddress = partner.addAddress(createdAddress, addressType);;
        partnerAddressRepository.save(partnerAddress);
    }

    @Override
    @Transactional
    public void deletePartnerAddress(@NotNull Long partnerId, @NotNull AddressType addressType, @NotNull Long addressId, boolean force) {
        if (!force && (AddressType.REGISTERED.equals(addressType) || AddressType.REAL.equals(addressType))) {
            throw new AppLogicException("Forbidden to remove REGISTERED or REAL addresses");
        } else {
            final Partner partner = partnerRepository.findOne(partnerId);
            final Address address = addressRepository.findOne(addressId);

            PartnerAddressId primaryKey = new PartnerAddressId();
            primaryKey.setPartner(partner);
            primaryKey.setAddress(address);
            primaryKey.setAddressType(addressType);

            PartnerAddress link = partnerAddressRepository.findOne(primaryKey);
            // check for existing valid link between 'partnerId' and 'addressId' with type 'addressType'
            // user can't delete existing link if 'addressType' is REGISTERED or REAL
            if (link != null && (force || !(AddressType.REGISTERED.equals(link.getAddressType()) || AddressType.REAL.equals(link.getAddressType())))) {
                partnerAddressRepository.delete(link);
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PartnerAddressDTO> getPartnerAddressesLink() {
        List<PartnerAddressDTO> result = new ArrayList<>();
        List<PartnerAddress> all = partnerAddressRepository.findAll();
        for (PartnerAddress next : all) {
            result.add(new PartnerAddressDTO(next.getAddressType(), next.getPartner().getPartnerId(), next.getAddress().getAddressId()));
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Address> getPartnerAddresses(Long partnerId) {
        Partner partner = partnerRepository.findOne(partnerId);

        if (partner != null) {
            return partner.getAddresses();
        } else {
            throw new AppLogicException("Partner is not found");
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<Address> getPartnerAddresses(Long partnerId, AddressType addressType) {
        Partner partner = partnerRepository.findOne(partnerId);

        if (partner != null) {
            return partner.getAddresses(addressType);
        } else {
            throw new AppLogicException("Partner is not found");
        }

    }


}
