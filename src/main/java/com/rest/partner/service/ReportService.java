package com.rest.partner.service;

import com.rest.partner.domain.Partner;

import java.util.List;

public interface ReportService {

    String generateReport(List<Partner> allPartners);


}
