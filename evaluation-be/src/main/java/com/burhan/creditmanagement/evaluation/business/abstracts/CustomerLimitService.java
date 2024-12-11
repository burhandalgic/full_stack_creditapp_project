package com.burhan.creditmanagement.evaluation.business.abstracts;
import com.burhan.creditmanagement.evaluation.webapi.request.CreateCustomerLimitRequest;
import com.burhan.creditmanagement.evaluation.webapi.response.GetCustomerLimitResponce;
import com.burhan.creditmanagement.evaluation.event.EventApplicationRequestDto;

import java.util.List;

public interface CustomerLimitService {

    void evaluateApplication(EventApplicationRequestDto eventApplicationRequestDto);
    List<GetCustomerLimitResponce> getAllCustomerLimit();
    GetCustomerLimitResponce getCustomerLimitById(long id);
    void saveCustomerLimit(CreateCustomerLimitRequest createCustomerLimitRequest) ;
    void deleteCustomerLimit(long id);







}
