package com.burhan.creditmanagement.evaluation.webapi.request;
import com.burhan.creditmanagement.evaluation.dao.entities.CustomerLimit;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerLimitRequest {

    @NotNull
    private Long customerNo;

    @NotNull
    private BigDecimal limit;



    public CustomerLimit toCustomerLimit () {
        return new CustomerLimit (null,this.customerNo,this.limit);
    }



}
