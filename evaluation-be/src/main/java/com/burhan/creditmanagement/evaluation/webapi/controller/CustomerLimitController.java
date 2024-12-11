package com.burhan.creditmanagement.evaluation.webapi.controller;

import com.burhan.creditmanagement.evaluation.business.abstracts.CustomerLimitService;
import com.burhan.creditmanagement.evaluation.webapi.request.CreateCustomerLimitRequest;
import com.burhan.creditmanagement.evaluation.webapi.response.GetCustomerLimitResponce;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customerLimits")
@AllArgsConstructor
public class CustomerLimitController {
    CustomerLimitService customerLimitService;

    @GetMapping()
    public List<GetCustomerLimitResponce> getAllCustomerLimit(){

        return customerLimitService.getAllCustomerLimit();
    }

    @GetMapping("/{id}")
    public GetCustomerLimitResponce getCustomerLimitById(@PathVariable long id)
    {
        return  customerLimitService.getCustomerLimitById(id);
    }

    @PostMapping()
    public void saveCustomerLimit (@RequestBody @Valid CreateCustomerLimitRequest createCustomerLimitRequest) {
        customerLimitService.saveCustomerLimit(createCustomerLimitRequest);
    }


    @DeleteMapping("/{id}")
    public void deleteCustomerLimit( @PathVariable long id){
        customerLimitService.deleteCustomerLimit(id);
    }

}
