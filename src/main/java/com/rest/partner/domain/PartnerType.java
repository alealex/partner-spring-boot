package com.rest.partner.domain;

/**
 * Partner type enum
 */
public enum PartnerType {

    COMPANY(1),
    SOLE_PROPRIETORSHIP(2),
    NATURAL_PERSON(3);

    private final int ordinal;

    PartnerType(int ordinal) {
        this.ordinal = ordinal;
    }

    public int getOrdinal() {
        return ordinal;
    }
}
