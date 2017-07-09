package com.rest.partner.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class ContactInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long contactInfoId;

    @NotNull
    @Size(max = 32)
    private String contactPerson;

    @NotNull
    private ContactType contactType;

    private WorkingHours workingHours;

    @Size(max = 128)
    private String contactInfo;

    @OneToOne(fetch=FetchType.LAZY, mappedBy="contactInfo")
    private Partner partner;

    public ContactInfo() {
    }

    public ContactInfo(String contactPerson, ContactType contactType, WorkingHours workingHours, String contactInfo) {
        this.contactPerson = contactPerson;
        this.contactType = contactType;
        this.workingHours = workingHours;
        this.contactInfo = contactInfo;
    }

    public Long getContactInfoId() {
        return contactInfoId;
    }

    public void setContactInfoId(Long contactInfoId) {
        this.contactInfoId = contactInfoId;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="startHour", column=@Column(nullable=true)),
            @AttributeOverride(name="endHour", column=@Column(nullable=true))
    })
    public WorkingHours getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(WorkingHours workingHours) {
        this.workingHours = workingHours;
    }

    public void setWorkingHours(Long startHour, Long endHour) {
        if (this.workingHours == null) {
            this.workingHours = new WorkingHours();
        }
        this.workingHours.setStartHour(startHour);
        this.workingHours.setEndHour(endHour);
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }


}
