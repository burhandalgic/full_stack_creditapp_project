package com.burhan.creditmanagement.evaluation.business.concrets;

import com.burhan.creditmanagement.evaluation.dao.entities.CustomerLimit;
import com.burhan.creditmanagement.evaluation.event.EventApplicationRequestDto;
import com.burhan.creditmanagement.evaluation.event.EventEvalutionResponseDto;
import com.burhan.creditmanagement.evaluation.dao.repository.CustomerLimitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerLimitManagerTest {

    @Mock
    private CustomerLimitRepository customerLimitRepository;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    CustomerLimitManager customerLimitManager;

    @Test
    void evaluateApplication_whenCustomerLimitPresent_thenConvertAndSend() {
        EventApplicationRequestDto eventApplicationRequestDto=new EventApplicationRequestDto(10000001L, BigDecimal.valueOf(60000),1L);
        CustomerLimit customerLimit=new CustomerLimit(1L,10000001L,BigDecimal.valueOf(50000));
        when(customerLimitRepository.findByCustomerNo(anyLong())).thenReturn(Optional.of(customerLimit));
        customerLimitManager.evaluateApplication(eventApplicationRequestDto);
        verify(rabbitTemplate,times(1)).convertAndSend(anyString(),anyString(), any(EventEvalutionResponseDto.class));

    }
    @Test
    void evaluateApplication_whenCustomerLimitNull_thenDontCallConvertAndSend() {
        EventApplicationRequestDto eventApplicationRequestDto=new EventApplicationRequestDto(10000001L, BigDecimal.valueOf(60000),1L);
        when(customerLimitRepository.findByCustomerNo(anyLong())).thenReturn(Optional.empty());
        customerLimitManager.evaluateApplication(eventApplicationRequestDto);
        verify(rabbitTemplate,times(0)).convertAndSend(anyString(),anyString(), any(EventEvalutionResponseDto.class));

    }
}