package com.burhan.customermanagement.customer.webapi.request;

import com.burhan.customermanagement.customer.dao.entities.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerRequest {

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String surname;

    @NotBlank
    @NotNull
    private String tcNo;

    @NotBlank
    @NotNull
    @Email(message = "Geçersiz mail adresi!")
    private String mail;


    public Customer toCustomer () {
        return new Customer (null,this.name,this.surname,this.tcNo,this.mail,null);
    }



}
