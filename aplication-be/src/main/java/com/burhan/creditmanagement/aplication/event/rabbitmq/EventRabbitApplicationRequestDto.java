package com.burhan.creditmanagement.aplication.event.rabbitmq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRabbitApplicationRequestDto {
    private Long customerNo;
    private BigDecimal amount;
    private Long applicationNo;
}
