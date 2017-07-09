package com.rest.partner.repository;

import com.rest.partner.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("select a from Address a where a.country = :country and a.index = :index and a.state = :state and a.province = :province and a.city = :city and a.streetAddress =:streetAddress")
    Address findByParams(@Param("country") String country,
                         @Param("index") String index,
                         @Param("state") String state,
                         @Param("province") String province,
                         @Param("city") String city,
                         @Param("streetAddress") String streetAddress);



}
