package com.burhan.customermanagement.customer.webapi.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomerResponse {

    private long customerNo;

    private String name;

    private String surname;

    private String tcNo;

    private String mail;


}
