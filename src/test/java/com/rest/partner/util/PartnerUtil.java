package com.rest.partner.util;

import com.rest.partner.domain.Partner;

import java.util.ArrayList;
import java.util.List;

public class PartnerUtil {

    private static final Long ID = (long) 123;
    private static final String NAME = "name";

    private PartnerUtil() {
    }

    public static Partner createPartner() {
        return new Partner(ID, NAME);
    }

    public static List<Partner> createPartnerList(int howMany) {
        List<Partner> partners = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            partners.add(new Partner(ID + i, NAME));
        }
        return partners;
    }

}
