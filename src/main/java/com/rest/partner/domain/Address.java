package com.rest.partner.domain;

import com.google.common.base.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;

    @NotNull
    @Size(max = 32)
    private String country;

    @NotNull
    @Size(max = 8)
    private String index;

    @NotNull
    @Size(max = 32)
    private String state;

    @NotNull
    @Size(max = 32)
    private String province;

    @NotNull
    @Size(max = 64)
    private String city;

    @NotNull
    @Size(max = 128)
    private String streetAddress;

    @OneToMany(mappedBy = "primaryKey.address", cascade = CascadeType.ALL)
    private Set<PartnerAddress> partnerAddresses = new HashSet<>();


    public Address() {
        this.country = "";
        this.index = "";
        this.state = "";
        this.province = "";
        this.city = "";
        this.streetAddress = "";
    }

    public Address(Long addressId, String country, String index, String state,
                   String province, String city, String streetAddress) {
        this.addressId = addressId;
        this.country = country;
        this.index = index;
        this.state = state;
        this.province = province;
        this.city = city;
        this.streetAddress = streetAddress;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("addressId", addressId)
                .add("country", country)
                .add("index", index)
                .add("state", state)
                .add("province", province)
                .add("city", city)
                .add("streetAddress", streetAddress)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return java.util.Objects.equals(addressId, address.addressId) &&
                java.util.Objects.equals(country, address.country) &&
                java.util.Objects.equals(index, address.index) &&
                java.util.Objects.equals(state, address.state) &&
                java.util.Objects.equals(province, address.province) &&
                java.util.Objects.equals(city, address.city) &&
                java.util.Objects.equals(streetAddress, address.streetAddress);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(addressId, country, index, state, province, city, streetAddress);
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
}
