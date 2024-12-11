package com.burhan.creditmanagement.evaluation.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventApplicationRequestDto {
    private Long customerNo;
    private BigDecimal amount;
    private Long applicationNo;
}
