package com.rest.partner.controller;

import com.rest.partner.domain.Partner;
import com.rest.partner.service.AddressService;
import com.rest.partner.service.PartnerService;
import com.rest.partner.service.ReportService;
import com.rest.partner.util.PartnerUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PartnerControllerTest {

    @Mock
    private PartnerService partnerService;
    @Mock
    private AddressService addressService;
    @Mock
    private ReportService reportService;

    private PartnerController partnerController;

    @Before
    public void setUp() throws Exception {
        partnerController = new PartnerController(partnerService, addressService, reportService);
    }

    @Test
    public void shouldCreatePartner() throws Exception {
        final Partner savedPartner = stubServiceToReturnStoredPartner();
        final Partner partner = PartnerUtil.createPartner();
        Partner returnedPartner = partnerController.createPartner(partner);
        // verify partner was passed to PartnerService
        verify(partnerService, times(1)).create(partner);
        assertEquals("Returned partner should come from the service", savedPartner, returnedPartner);
    }

    private Partner stubServiceToReturnStoredPartner() {
        final Partner partner = PartnerUtil.createPartner();
        when(partnerService.create(any(Partner.class))).thenReturn(partner);
        return partner;
    }


    @Test
    public void shouldListAllPartners() throws Exception {
        stubServiceToReturnExistingPartners(10);
        Collection<Partner> partners = partnerController.getPartners();
        assertNotNull(partners);
        assertEquals(10, partners.size());
        // verify partner was passed to PartnerService
        verify(partnerService, times(1)).getList();
    }

    private void stubServiceToReturnExistingPartners(int howMany) {
        when(partnerService.getList()).thenReturn(PartnerUtil.createPartnerList(howMany));
    }

}
