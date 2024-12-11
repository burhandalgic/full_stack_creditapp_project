package com.burhan.creditmanagement.aplication.business.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetApplicationResponse {

    private Long applicationNo ;
    private Long customerNo;
    private Integer status;
    private Integer creditType;
    private BigDecimal creditAmount;

}
