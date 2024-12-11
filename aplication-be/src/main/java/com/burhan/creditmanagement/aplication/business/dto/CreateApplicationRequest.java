package com.burhan.creditmanagement.aplication.business.dto;

import com.burhan.creditmanagement.aplication.dao.entities.Application;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateApplicationRequest {

    @NotNull
    private Long customerNo;

    @NotNull
    @Min(value = 1, message = "Value must be greater than or equal to 1")
    @Max(value = 3, message = "Value must be less than or equal to 3")
    private Integer creditType;

    @NotNull
    private BigDecimal creditAmount;



    public Application toApplication () {
        return new Application (null,this.customerNo,1,this.creditType,this.creditAmount);
    }



}
