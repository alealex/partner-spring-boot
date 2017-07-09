package com.rest.partner.domain;

import javax.persistence.Embeddable;

@Embeddable
public class WorkingHours {
    private Long startHour;
    private Long endHour;

    public WorkingHours() {
    }

    public WorkingHours(Long startHour, Long endHour) {
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public Long getStartHour() {
        return startHour;
    }

    public void setStartHour(Long startHour) {
        this.startHour = startHour;
    }

    public Long getEndHour() {
        return endHour;
    }

    public void setEndHour(Long endHour) {
        this.endHour = endHour;
    }
}
