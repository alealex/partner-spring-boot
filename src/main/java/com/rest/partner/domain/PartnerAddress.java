package com.rest.partner.domain;

import com.google.common.base.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.address",
                joinColumns = @JoinColumn(name = "addressId")),
        @AssociationOverride(name = "primaryKey.partner",
                joinColumns = @JoinColumn(name = "partnerId"))
//        ,
//        @AssociationOverride(name = "primaryKey.addressType",
//                joinColumns = @JoinColumn(name = "address_type", insertable = false, updatable = false))
})
public class PartnerAddress {
    
    private PartnerAddressId primaryKey = new PartnerAddressId();

    @EmbeddedId
    public PartnerAddressId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(PartnerAddressId primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Transient
    public Partner getPartner() {
        return getPrimaryKey().getPartner();
    }

    public void setPartner(Partner partner) {
        getPrimaryKey().setPartner(partner);
    }

    @Transient
    public Address getAddress() {
        return getPrimaryKey().getAddress();
    }

    public void setAddress(Address address) {
        getPrimaryKey().setAddress(address);
    }

    @Transient
    public AddressType getAddressType() {
        return getPrimaryKey().getAddressType();
    }

    public void setAddressType(AddressType addressType) {
        this.primaryKey.setAddressType(addressType);
    }
}
