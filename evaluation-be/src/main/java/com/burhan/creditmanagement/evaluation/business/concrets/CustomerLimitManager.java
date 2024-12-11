package com.burhan.creditmanagement.evaluation.business.concrets;
import com.burhan.creditmanagement.evaluation.business.abstracts.CustomerLimitService;
import com.burhan.creditmanagement.evaluation.webapi.request.CreateCustomerLimitRequest;
import com.burhan.creditmanagement.evaluation.webapi.response.GetCustomerLimitResponce;
import com.burhan.creditmanagement.evaluation.dao.entities.CustomerLimit;
import com.burhan.creditmanagement.evaluation.event.EventApplicationRequestDto;
import com.burhan.creditmanagement.evaluation.event.EventEvalutionResponseDto;
import com.burhan.creditmanagement.evaluation.dao.repository.CustomerLimitRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerLimitManager implements CustomerLimitService {
    private CustomerLimitRepository customerLimitRepository;
    private RabbitTemplate rabbitTemplate;

    @Override
    @RabbitListener(queues = "creditapplication")
    public void evaluateApplication(EventApplicationRequestDto eventApplicationRequestDto) {
        Optional<CustomerLimit> customerLimit = customerLimitRepository
                .findByCustomerNo(eventApplicationRequestDto.getCustomerNo());
        if(customerLimit.isPresent()) {
            EventEvalutionResponseDto eventEvalutionResponseDto = new EventEvalutionResponseDto();
            eventEvalutionResponseDto.setResult(customerLimit.get().getLimitt().compareTo(eventApplicationRequestDto.getAmount()) >= 0);
            eventEvalutionResponseDto.setCustomerNo(eventApplicationRequestDto.getCustomerNo());
            eventEvalutionResponseDto.setApplicationNo(eventApplicationRequestDto.getApplicationNo());
            rabbitTemplate.convertAndSend("eventexchange", "creditevaluation", eventEvalutionResponseDto);
        }
    }


    @Override
    public List<GetCustomerLimitResponce> getAllCustomerLimit() {
        List<CustomerLimit> customerLimits = customerLimitRepository.findAll();
        List<GetCustomerLimitResponce> returnApplication= new ArrayList<>();
        for (CustomerLimit customerLimit:customerLimits){
            returnApplication.add(customerLimit.toGetCustomerLimitResponce());
        }
        return returnApplication;
    }

    @Override
    public GetCustomerLimitResponce getCustomerLimitById(long id) {
        CustomerLimit customerLimit = customerLimitRepository.findById(id).orElseThrow();
        return customerLimit.toGetCustomerLimitResponce();
    }


    @Override
    public void saveCustomerLimit(CreateCustomerLimitRequest createCustomerLimitRequest) {
        boolean exist = customerLimitRepository
                .existsByCustomerNo
                        (createCustomerLimitRequest.getCustomerNo());
        if (!exist) {
            customerLimitRepository.save(createCustomerLimitRequest.toCustomerLimit());
        }
    }

    @Override
    public void deleteCustomerLimit(long id) {
        customerLimitRepository.deleteById(id);

    }


}
