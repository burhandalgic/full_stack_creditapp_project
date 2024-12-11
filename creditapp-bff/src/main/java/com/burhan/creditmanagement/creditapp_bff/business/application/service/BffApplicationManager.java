package com.burhan.creditmanagement.creditapp_bff.business.application.service;
import com.burhan.creditmanagement.creditapp_bff.business.application.client.ApplicationServiceClient;
import com.burhan.creditmanagement.creditapp_bff.business.application.dto.Application;
import com.burhan.creditmanagement.creditapp_bff.business.application.dto.CreateApplicationRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BffApplicationManager implements BffApplicationService {

    private ApplicationServiceClient applicationServiceClient;

    @Override
    public Application addApplication(CreateApplicationRequest createApplicationRequest){
        return applicationServiceClient.addApplication(createApplicationRequest);
    }
}
