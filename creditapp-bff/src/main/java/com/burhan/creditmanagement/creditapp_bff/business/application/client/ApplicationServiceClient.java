package com.burhan.creditmanagement.creditapp_bff.business.application.client;
import com.burhan.creditmanagement.creditapp_bff.business.application.dto.Application;
import com.burhan.creditmanagement.creditapp_bff.business.application.dto.CreateApplicationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "applicationService", url = "http://localhost:8083")
public interface ApplicationServiceClient {
    @PostMapping("/applications")
    Application addApplication (CreateApplicationRequest createApplicationRequest) ;

}
