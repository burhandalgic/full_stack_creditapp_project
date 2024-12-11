package com.burhan.creditmanagement.aplication.event.rabbitmq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRabbitEvalutionResponseDto {
    private Long customerNo;
    private boolean result;
    private Long applicationNo;
}
