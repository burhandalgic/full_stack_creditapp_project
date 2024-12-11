package com.burhan.customermanagement.customer.business.concrets;

import com.burhan.customermanagement.customer.business.abstracts.CustomerService;
import com.burhan.customermanagement.customer.webapi.request.CreateCustomerRequest;
import com.burhan.customermanagement.customer.webapi.request.UpdateCustomerRequest;
import com.burhan.customermanagement.customer.webapi.response.GetCustomerResponse;
import com.burhan.customermanagement.customer.dao.entities.Customer;
import com.burhan.customermanagement.customer.exception.CustomException;
import com.burhan.customermanagement.customer.dao.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {
    private CustomerRepository iCustomerRepository;
    @Override
    public List<GetCustomerResponse> getAll() {
        List<Customer> customers = iCustomerRepository.findAll();
        List<GetCustomerResponse> returnCustomers= new ArrayList<>();
        for (Customer customer:customers){
            returnCustomers.add(customer.toGetCustomerResponce());
        }
        return returnCustomers;
    }

    @Override
    public GetCustomerResponse getById(long id) {
        Customer customer = iCustomerRepository.findById(id)
                .orElseThrow(() -> new CustomException(id + " numaralı müşteri bulunamadı!", 404 ));
        return customer.toGetCustomerResponce();
    }


    @Override
    public void add (CreateCustomerRequest createCustomerRequest) {
        boolean exist = iCustomerRepository.existsByTcNo(createCustomerRequest.getTcNo());
        if (exist) {
            throw new CustomException(" Bu tc numarasında müşteri mevcut !", 409);
        }else
        {
            iCustomerRepository.save(createCustomerRequest.toCustomer());
        }
    }

    @Override
    public void delete(long id) {
        iCustomerRepository.deleteById(id);

    }
    @Override
    public void update(UpdateCustomerRequest updateCustomerRequest) {
        Customer customernew = updateCustomerRequest.toCustomer();
        Customer customer = iCustomerRepository.findById(updateCustomerRequest.getCustomerNo()).orElseThrow();
        customernew.setAddresses(customer.getAddresses());
        iCustomerRepository.save(customernew);
    }

}
