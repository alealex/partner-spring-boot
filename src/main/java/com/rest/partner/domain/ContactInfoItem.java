package com.rest.partner.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// todo make contact info with several item types
@Entity
public class ContactInfoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long contactInfoItemId;

    @NotNull
    private ContactType contactType;


}
