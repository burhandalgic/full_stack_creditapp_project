package com.burhan.customermanagement.customer.webapi.controller;

import com.burhan.customermanagement.customer.business.abstracts.CustomerService;
import com.burhan.customermanagement.customer.webapi.request.CreateCustomerRequest;
import com.burhan.customermanagement.customer.webapi.request.UpdateCustomerRequest;
import com.burhan.customermanagement.customer.webapi.response.GetCustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {
    CustomerService iCustomerService;


    @GetMapping()
    public List<GetCustomerResponse> getAll(){
        return iCustomerService.getAll();
    }

    @GetMapping("/{id}")
    public GetCustomerResponse getById (@PathVariable long id) {
        return  iCustomerService.getById(id);
    }

    @PostMapping()
    public void add (@RequestBody @Valid CreateCustomerRequest createCustomerRequest )  {
        iCustomerService.add(createCustomerRequest);
    }

    @PutMapping //update bu şekilde yapılır
    public void update(@RequestBody @Valid UpdateCustomerRequest updateCustomerRequest){
        iCustomerService.update(updateCustomerRequest);
    }

    @DeleteMapping("/{id}")
    public void delete ( @PathVariable long id){
        iCustomerService.delete(id);
    }

}
