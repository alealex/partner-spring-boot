package com.rest.partner.service;

import com.rest.partner.domain.Address;
import com.rest.partner.domain.AddressType;
import com.rest.partner.domain.PartnerAddressDTO;

import java.util.List;

public interface AddressService {

    Address create(Address address);
    Address read(Long id);
    List<Address> getList();

    void storePartnerAddress(Long partnerId, AddressType addressType, Long addressId,  Address address);
    void deletePartnerAddress(Long partnerId, AddressType addressType, Long addressId, boolean force);
    List<PartnerAddressDTO> getPartnerAddressesLink();

    List<Address> getPartnerAddresses(Long partnerId);
    List<Address> getPartnerAddresses(Long partnerId, AddressType addressType);



}
