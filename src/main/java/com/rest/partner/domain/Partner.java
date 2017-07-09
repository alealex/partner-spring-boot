package com.rest.partner.domain;

import com.google.common.base.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long partnerId;

    @NotNull
    @Size(max = 64)
    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private PartnerType partnerType;


    @Size(max = 32)
    @Column(name = "propertyForm", nullable = true)
    private String propertyForm;

    @Column(name = "taxNumber", nullable = true)
    private Long taxNumber;

    @OneToMany(mappedBy = "primaryKey.partner", cascade = CascadeType.ALL)
    private Set<PartnerAddress> partnerAddresses = new HashSet<>();

    @OneToOne(optional=true)
    private ContactInfo contactInfo;

    public Partner() {
    }

    public Partner(final Long partnerId, final String name) {
        this.partnerId = partnerId;
        this.name = name;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("partnerId", partnerId)
                .add("name", name)
                .add("partnerType", partnerType)
                .add("propertyForm", propertyForm)
                .add("taxNumber", taxNumber)
                .toString();
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PartnerType getPartnerType() {
        return partnerType;
    }

    public void setPartnerType(PartnerType partnerType) {
        this.partnerType = partnerType;
    }

    public String getPropertyForm() {
        return propertyForm;
    }

    public void setPropertyForm(String propertyForm) {
        this.propertyForm = propertyForm;
    }

    public Long getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(Long taxNumber) {
        this.taxNumber = taxNumber;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<Address> getAddresses() {
        List<Address> result = new ArrayList<>();
        for (PartnerAddress next : partnerAddresses) {
            result.add(next.getAddress());

        }
        return result;
    }

    public List<Address> getAddresses(AddressType addressType) {
        List<Address> result = new ArrayList<>();
        for (PartnerAddress next : partnerAddresses) {
            if (next.getAddressType().equals(addressType)) {
                result.add(next.getAddress());
            }

        }
        return result;
    }

    public PartnerAddress getPartnerAddress(AddressType addressType, Long addressId) {
        for (PartnerAddress next : partnerAddresses) {
            if (next.getAddressType().equals(addressType) && (addressId == null || next.getAddress().getAddressId().equals(addressId))) {
                return next;
            }
        }
        return null;
    }


/*
    public Set<PartnerAddress> getPartnerAddresses() {
        return partnerAddresses;
    }

    public void setPartnerAddresses(Set<PartnerAddress> partnerAddresses) {
        this.partnerAddresses = partnerAddresses;
    }
*/

    public PartnerAddress addAddress(Address address, AddressType addressType) {
        PartnerAddress association = new PartnerAddress();
        association.setPartner(this);
        association.setAddress(address);
        association.setAddressType(addressType);
        return association;
    }
}
