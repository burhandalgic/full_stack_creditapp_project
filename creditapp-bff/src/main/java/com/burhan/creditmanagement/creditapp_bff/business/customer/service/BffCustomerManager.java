package com.burhan.creditmanagement.creditapp_bff.business.customer.service;
import com.burhan.creditmanagement.creditapp_bff.business.customer.client.CustomerServiceClient;
import com.burhan.creditmanagement.creditapp_bff.business.customer.dto.GetCustomerResponce;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BffCustomerManager implements BffCustomerService {

    private CustomerServiceClient iCustomerServiceClient;

    @Override
    public GetCustomerResponce getById(long id) {
        return iCustomerServiceClient.getById(id).getBody();

    }


}
