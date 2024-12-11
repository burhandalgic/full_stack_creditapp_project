package com.burhan.creditmanagement.aplication.business.services.external.customer.client;
import com.burhan.creditmanagement.aplication.business.services.external.customer.dto.GetCustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer", url = "http://localhost:8082")
public interface CustomerServiceClient {
    @GetMapping("/customers/{id}")
    ResponseEntity<GetCustomerResponse> getById(@PathVariable("id") Long id);
}
