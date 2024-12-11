package com.burhan.creditmanagement.evaluation.webapi.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerLimitRequest {


    @NotNull
    private Long customerNo;

    @NotNull
    private BigDecimal limit;




}
