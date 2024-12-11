package com.burhan.creditmanagement.creditapp_bff.business.customer.client;
import com.burhan.creditmanagement.creditapp_bff.business.customer.dto.GetCustomerResponce;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customerService", url = "http://localhost:8082")
public interface CustomerServiceClient {
    @GetMapping("/customers/{id}")
    ResponseEntity<GetCustomerResponce> getById(@PathVariable("id") Long id);
}
