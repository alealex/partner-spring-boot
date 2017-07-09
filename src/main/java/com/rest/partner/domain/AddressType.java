package com.rest.partner.domain;

/**
 * Address type enum
 */
public enum AddressType {

    REGISTERED(1),
    REAL(2),
    LOGISTIC(3),
    CORRESPONDENCE(4);

    private final int ordinal;

    AddressType(int ordinal) {
        this.ordinal = ordinal;
    }

    public int getOrdinal() {
        return ordinal;
    }
}
