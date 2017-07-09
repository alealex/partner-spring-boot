package com.rest.partner.domain;

/**
 * Contact type enum
 */
public enum ContactType {

    EMAIL(1),
    PHONE(2),
    FAX(3),
    IM(4);

    private final int ordinal;

    ContactType(int ordinal) {
        this.ordinal = ordinal;
    }

    public int getOrdinal() {
        return ordinal;
    }
}
