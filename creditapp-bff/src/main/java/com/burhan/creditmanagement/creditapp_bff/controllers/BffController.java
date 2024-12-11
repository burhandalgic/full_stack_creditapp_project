package com.burhan.creditmanagement.creditapp_bff.controllers;
import com.burhan.creditmanagement.creditapp_bff.business.application.service.BffApplicationService;
import com.burhan.creditmanagement.creditapp_bff.business.application.dto.Application;
import com.burhan.creditmanagement.creditapp_bff.business.application.dto.CreateApplicationRequest;
import com.burhan.creditmanagement.creditapp_bff.business.customer.service.BffCustomerService;
import com.burhan.creditmanagement.creditapp_bff.business.customer.dto.GetCustomerResponce;
import com.burhan.creditmanagement.creditapp_bff.customexeption.CustomApplicationException;
import com.burhan.creditmanagement.creditapp_bff.customexeption.CustomCustomerException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bff")
@AllArgsConstructor
public class BffController {
    BffCustomerService bffCustomerService;
    BffApplicationService bffApplicationService;

    @GetMapping("/customers/{id}")
    public GetCustomerResponce getById(@PathVariable long id) {
        try {
            return bffCustomerService.getById(id);
        } catch (Exception ex) {
            throw new CustomCustomerException(id + " numaralı müşteri bulunamadı !", 404);
        }
    }


    @PostMapping("/applications")
    public Application addApplication(@RequestBody @Valid CreateApplicationRequest createApplicationRequest) {
        try {
            return bffApplicationService.addApplication(createApplicationRequest);
        } catch (Exception ex) {
                throw new CustomApplicationException(" Bu müşterinin bu kredi tipinde başvurusu mevcut !", 422);
            }
        }



    }


