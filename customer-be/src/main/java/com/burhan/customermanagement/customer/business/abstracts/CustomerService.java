package com.burhan.customermanagement.customer.business.abstracts;

import com.burhan.customermanagement.customer.webapi.request.CreateCustomerRequest;
import com.burhan.customermanagement.customer.webapi.request.UpdateCustomerRequest;
import com.burhan.customermanagement.customer.webapi.response.GetCustomerResponse;

import java.util.List;

public interface CustomerService {
    List<GetCustomerResponse> getAll ();
    GetCustomerResponse getById(long id);
    void add (CreateCustomerRequest createCustomerRequest) ;
    void delete (long id);
    void update (UpdateCustomerRequest updateCustomerRequest);






}
