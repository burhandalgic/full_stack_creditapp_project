package com.burhan.creditmanagement.creditapp_bff.business.customer.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomerResponce {

    private long customerNo;

    private String name;

    private String surname;

    private String tcNo;

    private String mail;


}
