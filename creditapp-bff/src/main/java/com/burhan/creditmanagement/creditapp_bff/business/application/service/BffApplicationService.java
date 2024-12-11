package com.burhan.creditmanagement.creditapp_bff.business.application.service;

import com.burhan.creditmanagement.creditapp_bff.business.application.dto.Application;
import com.burhan.creditmanagement.creditapp_bff.business.application.dto.CreateApplicationRequest;

public interface BffApplicationService {

    Application addApplication (CreateApplicationRequest createApplicationRequest);

}
