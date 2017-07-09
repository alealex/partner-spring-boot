package com.rest.partner.service;

import com.rest.partner.repository.ContactInfoRepository;
import com.rest.partner.repository.PartnerRepository;
import com.rest.partner.service.exception.AppLogicException;
import com.rest.partner.util.PartnerUtil;
import com.rest.partner.domain.Partner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PartnerServiceImplTest {

    @Mock
    private PartnerRepository partnerRepository;
    @Mock
    private ContactInfoRepository contactInfoRepository;

    private PartnerService partnerService;

    @Before
    public void setUp() throws Exception {
        partnerService = new PartnerServiceImpl(partnerRepository, contactInfoRepository);
    }

    @Test
    public void shouldSaveNewPartner_GivenThereDoesNotExistOneWithTheSameName_ThenTheSavedPartnerShouldBeReturned() throws Exception {
        final Partner savedPartner = stubRepositoryToReturnPartnerOnSave();
        final Partner partner = PartnerUtil.createPartner();
        final Partner returnedPartner = partnerService.create(partner);
        // verify repository was called with partner
        verify(partnerRepository, times(1)).save(partner);
        assertEquals("Returned partner should come from the repository", savedPartner, returnedPartner);
    }

    private Partner stubRepositoryToReturnPartnerOnSave() {
        Partner partner = PartnerUtil.createPartner();
        when(partnerRepository.save(any(Partner.class))).thenReturn(partner);
        return partner;
    }

    @Test
    public void shouldSaveNewPartner_GivenThereExistsOneWithTheSameName_ThenTheExceptionShouldBeThrown() throws Exception {
        stubRepositoryToReturnExistingPartner();
        try {
            partnerService.create(PartnerUtil.createPartner());
            fail("Expected exception");
        } catch (AppLogicException ignored) {
        }
        verify(partnerRepository, never()).save(any(Partner.class));
    }

    private void stubRepositoryToReturnExistingPartner() {
        final Partner partner = PartnerUtil.createPartner();
        final List<Partner> existingPartners = new ArrayList<>();
        existingPartners.add(partner);
        when(partnerRepository.findByName(partner.getName())).thenReturn(existingPartners);
    }

    @Test
    public void shouldListAllPartners_GivenThereExistSome_ThenTheCollectionShouldBeReturned() throws Exception {
        stubRepositoryToReturnExistingPartners(10);
        Collection<Partner> list = partnerService.getList();
        assertNotNull(list);
        assertEquals(10, list.size());
        verify(partnerRepository, times(1)).findAll();
    }

    private void stubRepositoryToReturnExistingPartners(int howMany) {
        when(partnerRepository.findAll()).thenReturn(PartnerUtil.createPartnerList(howMany));
    }

    @Test
    public void shouldListAllPartners_GivenThereNoneExist_ThenTheEmptyCollectionShouldBeReturned() throws Exception {
        stubRepositoryToReturnExistingPartners(0);
        Collection<Partner> list = partnerService.getList();
        assertNotNull(list);
        assertTrue(list.isEmpty());
        verify(partnerRepository, times(1)).findAll();
    }

}
