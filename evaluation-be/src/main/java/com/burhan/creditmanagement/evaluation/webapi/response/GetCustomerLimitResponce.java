package com.burhan.creditmanagement.evaluation.webapi.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomerLimitResponce {

    private Long id ;
    private Long customerNo;
    private BigDecimal limit;


}
