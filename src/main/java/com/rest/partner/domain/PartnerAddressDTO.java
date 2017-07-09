package com.rest.partner.domain;

import javax.validation.constraints.NotNull;

/**
 * DTO represents link Partner - Address
 */
public class PartnerAddressDTO {
    @NotNull
    private AddressType addressType;
    private Long partnerId;
    private Long addressId;

    public PartnerAddressDTO(AddressType addressType, Long partnerId, Long addressId) {
        this.addressType = addressType;
        this.partnerId = partnerId;
        this.addressId = addressId;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
}
