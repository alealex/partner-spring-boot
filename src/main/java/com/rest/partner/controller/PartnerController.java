package com.rest.partner.controller;

import com.rest.partner.domain.*;
import com.rest.partner.service.AddressService;
import com.rest.partner.service.PartnerService;
import com.rest.partner.service.ReportService;
import com.rest.partner.service.exception.AppLogicException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@RestController
public class PartnerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PartnerController.class);
    private final PartnerService partnerService;
    private final AddressService addressService;
    private final ReportService reportService;


    @Inject
    public PartnerController(final PartnerService partnerService,
                             final AddressService addressService,
                             final ReportService reportService) {
        this.partnerService = partnerService;
        this.addressService = addressService;
        this.reportService = reportService;
    }

    @RequestMapping(value = "/partners", method = RequestMethod.POST)
    public Partner createPartner(@RequestBody @Valid final Partner partner) {
        LOGGER.debug("Received request to create the {}", partner);
        final Partner partnerSaved = partnerService.create(partner);

        // todo create empty mandatory REGISTERED and REAL addresses (if needed???)
//        final Address addressReg = new Address();
//        addressReg.setCountry("Predefined REGISTERED address");
//        addressService.storePartnerAddress(partnerSaved.getPartnerId(), AddressType.REGISTERED, null, addressReg);

//        final Address addressReal = new Address();
//        addressReal.setCountry("Predefined REAL address");
//        addressService.storePartnerAddress(partnerSaved.getPartnerId(), AddressType.REAL, null, addressReal);



        return partnerSaved;
    }

    @RequestMapping(value = "/partners/{id}", method = RequestMethod.GET)
    public Partner readPartner(@PathVariable("id") final Long id) {
        LOGGER.debug("Received request to read partner with id {}", id);
        return partnerService.read(id);
    }

    @RequestMapping(value = "/partners/{id}", method = RequestMethod.PUT)
    public Partner updatePartner(@PathVariable("id") final Long id,
                                 @RequestBody @Valid final Partner partner) {
        LOGGER.debug("Received request to update the {}", partner);
        return partnerService.update(id, partner);
    }

    @RequestMapping(value = "/partners/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") final Long id) {
        LOGGER.debug("Received request to delete the partner with id {}", id);
        partnerService.delete(id);
    }

    @RequestMapping(value = "/partners", method = RequestMethod.GET)
    public List<Partner> getPartners() {
        LOGGER.debug("Received request to get all partners");
        return partnerService.getList();
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String generateReport() {
        LOGGER.debug("Received request to get report");
        return reportService.generateReport(partnerService.getList());
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleAppLogicException(AppLogicException e) {
        return e.getMessage();
    }

}
