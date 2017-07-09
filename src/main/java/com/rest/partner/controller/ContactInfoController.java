package com.rest.partner.controller;

import com.rest.partner.domain.ContactInfo;
import com.rest.partner.service.PartnerService;
import com.rest.partner.service.exception.AppLogicException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;

@RestController
public class ContactInfoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContactInfoController.class);
    private final PartnerService partnerService;

    @Inject
    public ContactInfoController(final PartnerService partnerService) {
        this.partnerService = partnerService;
    }


    @RequestMapping(value = "/partners/{id}/contactinfo", method = RequestMethod.GET)
    public ContactInfo getPartnerContactInfo(@PathVariable("id") final Long id) {
        LOGGER.debug("Received request to get partner's {} contact info", id);
        return partnerService.read(id).getContactInfo();
    }

    @RequestMapping(value = "/partners/{id}/contactinfo", method = RequestMethod.POST)
    public void createPartnerContactInfo(@PathVariable("id") final Long id,
                                         @RequestBody @Valid final ContactInfo contactInfo) {
        LOGGER.debug("Received request to create partner's {} contact info", id);
        partnerService.updateContactInfo(id, contactInfo);
    }

    @RequestMapping(value = "/partners/{id}/contactinfo", method = RequestMethod.PUT)
    public void updatePartnerContactInfo(@PathVariable("id") final Long id,
                                         @RequestBody @Valid final ContactInfo contactInfo) {
        LOGGER.debug("Received request to update partner's {} contact info", id);
        partnerService.updateContactInfo(id, contactInfo);
    }

    @RequestMapping(value = "/partners/{id}/contactinfo", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deletePartnerContactInfo(@PathVariable("id") final Long id) {
        LOGGER.debug("Received request to delete partner's {} contact info", id);
        partnerService.deleteContactInfo(id);
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleAppLogicException(AppLogicException e) {
        return e.getMessage();
    }

}
