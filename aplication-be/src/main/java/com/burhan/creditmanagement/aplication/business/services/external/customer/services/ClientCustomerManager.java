package com.burhan.creditmanagement.aplication.business.services.external.customer.services;
import com.burhan.creditmanagement.aplication.business.services.external.customer.client.CustomerServiceClient;
import com.burhan.creditmanagement.aplication.business.services.external.customer.dto.GetCustomerResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientCustomerManager implements ClientCustomerService {

    private CustomerServiceClient iCustomerServiceClient;

    @Override
    public GetCustomerResponse getById(long id) {
       return iCustomerServiceClient.getById(id).getBody();
    }


}
