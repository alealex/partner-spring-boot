package com.rest.partner.controller;

import com.rest.partner.domain.Address;
import com.rest.partner.domain.AddressType;
import com.rest.partner.domain.PartnerAddressDTO;
import com.rest.partner.service.AddressService;
import com.rest.partner.service.exception.AppLogicException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@RestController
public class AddressController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressController.class);
    private final AddressService addressService;

    @Inject
    public AddressController(final AddressService addressService) {
        this.addressService = addressService;
    }

    @RequestMapping(value = "/addresses", method = RequestMethod.GET)
    public List<Address> getAddresses() {
        LOGGER.debug("Received request to get all addresses");
        return addressService.getList();
    }

    @RequestMapping(value = "/partners/{id}/addresses", method = RequestMethod.GET)
    public List<Address> getPartnerAddresses(@PathVariable("id") final Long id) {
        LOGGER.debug("Received request to get all partner's {} addresses", id);
        return addressService.getPartnerAddresses(id);
    }

    @RequestMapping(value = "/addresseslinks", method = RequestMethod.GET)
    public List<PartnerAddressDTO> getPartnerAddressesLink() {
        LOGGER.debug("Received request to get all links between partners and addresses");
        return addressService.getPartnerAddressesLink();
    }

    @RequestMapping(value = "/partners/{id}/addresses/{addressType}", method = RequestMethod.POST)
    public void createPartnerAddresses(@PathVariable("id") final Long id,
                                       @PathVariable("addressType") final AddressType addressType,
                                       @RequestBody @Valid final Address address) {
        LOGGER.debug("Received request to create partner's {} addresses", id);
        addressService.storePartnerAddress(id, addressType, null, address);
    }

    @RequestMapping(value = "/partners/{id}/addresses/{addressType}", method = RequestMethod.PUT)
    public void updatePartnerAddresses(@PathVariable("id") final Long id,
                                       @PathVariable("addressType") final AddressType addressType,
                                       @RequestBody @Valid final Address address) {
        LOGGER.debug("Received request to create partner's {} addresses", id);
        addressService.storePartnerAddress(id, addressType, null, address);
    }

    @RequestMapping(value = "/partners/{id}/addresses/{addressType}/{addressId}", method = RequestMethod.PUT)
    public void updatePartnerAddresses(@PathVariable("id") final Long id,
                                       @PathVariable("addressType") final AddressType addressType,
                                       @PathVariable("addressId") final Long addressId,
                                       @RequestBody @Valid final Address address) {
        LOGGER.debug("Received request to create partner's {} addresses", id);
        addressService.storePartnerAddress(id, addressType, addressId, address);
    }

    @RequestMapping(value = "/partners/{id}/addresses/{addressType}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deletePartnerAddresses(@PathVariable("id") final Long id,
                                       @PathVariable("addressType") final AddressType addressType) {
        if (AddressType.REGISTERED.equals(addressType) || AddressType.REAL.equals(addressType)) {
            throw new AppLogicException("Forbidden to remove REGISTERED or REAL addresses");
        } else {
            throw new AppLogicException("Specify parameter addressId");
        }
    }

    @RequestMapping(value = "/partners/{id}/addresses/{addressType}/{addressId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deletePartnerAddresses(@PathVariable("id") final Long id,
                                       @PathVariable("addressType") final AddressType addressType,
                                       @PathVariable("addressId") final Long addressId) {
        LOGGER.debug("Received request to delete partner's {} addresses", id);
        addressService.deletePartnerAddress(id, addressType, addressId, false);
    }

    @RequestMapping(value = "/partners/{id}/addresses/{addressType}", method = RequestMethod.GET)
    public List<Address> getPartnerAddresses(@PathVariable("id") final Long id,
                                             @PathVariable("addressType") final AddressType addressType) {
        LOGGER.debug("Received request to get partner's {} addresses", id);
        return addressService.getPartnerAddresses(id, addressType);
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleAppLogicException(AppLogicException e) {
        return e.getMessage();
    }

}
