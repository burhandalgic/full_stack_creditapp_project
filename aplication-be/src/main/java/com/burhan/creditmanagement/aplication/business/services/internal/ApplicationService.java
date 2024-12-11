package com.burhan.creditmanagement.aplication.business.services.internal;

import com.burhan.creditmanagement.aplication.event.rabbitmq.EventRabbitEvalutionResponseDto;
import com.burhan.creditmanagement.aplication.business.dto.CreateApplicationRequest;
import com.burhan.creditmanagement.aplication.business.dto.UpdateApplicationRequest;
import com.burhan.creditmanagement.aplication.business.dto.GetApplicationResponse;
import com.burhan.creditmanagement.aplication.dao.entities.Application;


import java.util.List;

public interface ApplicationService {

    void listenEventAndUpdateStatus(EventRabbitEvalutionResponseDto eventEvalutionResponseDto);
    List<GetApplicationResponse> getApplicationList();
    GetApplicationResponse getApplicationByApplicationNo(long id);
    Application inquiryApplication(CreateApplicationRequest createApplicationRequest);
    void deleteApplication(long id);
    void updateApplicationInfo(UpdateApplicationRequest updateApplicationRequest);






}
