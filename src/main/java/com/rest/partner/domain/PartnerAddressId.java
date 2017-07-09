package com.rest.partner.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PartnerAddressId implements Serializable {

    private Partner partner;
    private Address address;
    private AddressType addressType;

    @ManyToOne(cascade = CascadeType.ALL)
    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartnerAddressId that = (PartnerAddressId) o;
        return Objects.equals(partner.getPartnerId(), that.partner.getPartnerId())
                && Objects.equals(address.getAddressId(), that.address.getAddressId())
                && Objects.equals(addressType, addressType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partner.getPartnerId(), address.getAddressId(), addressType);
    }
}

