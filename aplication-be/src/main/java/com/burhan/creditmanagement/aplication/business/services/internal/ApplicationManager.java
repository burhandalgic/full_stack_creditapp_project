package com.burhan.creditmanagement.aplication.business.services.internal;
import com.burhan.creditmanagement.aplication.event.rabbitmq.EventRabbitApplicationRequestDto;
import com.burhan.creditmanagement.aplication.event.rabbitmq.EventRabbitEvalutionResponseDto;
import com.burhan.creditmanagement.aplication.business.dto.UpdateApplicationRequest;
import com.burhan.creditmanagement.aplication.business.services.external.customer.services.ClientCustomerService;
import com.burhan.creditmanagement.aplication.business.services.external.customer.dto.GetCustomerResponse;
import com.burhan.creditmanagement.aplication.exception.CustomerAndCreditTypeExistException;
import com.burhan.creditmanagement.aplication.dao.repository.ApplicationRepository;
import com.burhan.creditmanagement.aplication.business.dto.CreateApplicationRequest;
import com.burhan.creditmanagement.aplication.business.dto.GetApplicationResponse;
import com.burhan.creditmanagement.aplication.dao.entities.Application;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ApplicationManager implements ApplicationService {
    private ApplicationRepository applicationRepository;
    private RabbitTemplate rabbitTemplate;
    private ClientCustomerService clientCustomerService;
    private SendEmailService sendEmailService;

    @Override
    @RabbitListener(queues = "creditevaluation")
    public void listenEventAndUpdateStatus(EventRabbitEvalutionResponseDto eventEvalutionResponseDto) {
        Application application = applicationRepository
                .findById(eventEvalutionResponseDto
                        .getApplicationNo()).orElseThrow();
        GetCustomerResponse getCustomerResponse = clientCustomerService.getById(application.getCustomerNo());

        String mailAddress = getCustomerResponse.getMail();
        StringBuilder mailBody = new StringBuilder();
        StringBuilder mailsubject = new StringBuilder();
        mailsubject
                .append(application.getApplicationNo())
                .append(" numaralı Kredi Başvuru Sonucunuz!");
        mailBody
                .append("Sayın ")
                .append(getCustomerResponse.getName())
                .append(" ")
                .append(getCustomerResponse.getSurname())
                .append(",\n")
                .append(application.getApplicationNo())
                .append(" numaralı ");

        String logStatus;
        if (eventEvalutionResponseDto.isResult()){
            application.setStatus(2);
            mailBody.append("Krediniz Onaylanmıştır, kullanım için sizi en yakın şubemize bekleriz .\nKredi Tipi : ");
            logStatus="OK";
        }else {
            application.setStatus(3);
            mailBody.append("Kredini başvurunuz reddedilmiştir.\nKredi Tipi : ");
            logStatus="RED";
       }
        if(application.getCreditType()==1){
        mailBody.append("İhtiyaç Kredisi.\nKredi Miktarı : ");}
        if(application.getCreditType()==2){
            mailBody.append("Konut Kredisi.\nKredi Miktarı : ");}
        if(application.getCreditType()==3){
            mailBody.append("Araç Kredisi.\nKredi Miktarı : ");}
        mailBody.append(application.getCreditAmount());

        applicationRepository.save(application);

        try {
            sendEmailService.sendEmail(mailAddress,mailsubject.toString(),mailBody.toString(),logStatus);
        }catch (Exception e){
           log.info("mail hatası :" + e.getMessage());
        }

    }

    @Override
    public List<GetApplicationResponse> getApplicationList() {
        List<Application> applications = applicationRepository.findAll();
        List<GetApplicationResponse> returnApplication= new ArrayList<>();
        for (Application application:applications){
            returnApplication.add(application.toGetApplicationResponce());
        }
        return returnApplication;
    }

    @Override
    public GetApplicationResponse getApplicationByApplicationNo(long id) {
        Application application = applicationRepository.findById(id).orElseThrow();
        return application.toGetApplicationResponce();
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Application inquiryApplication(CreateApplicationRequest createApplicationRequest) {
        boolean exist = applicationRepository
                .existsByCustomerNoAndCreditType
                        (createApplicationRequest.getCustomerNo(),createApplicationRequest.getCreditType());
        if (exist) {
            throw new CustomerAndCreditTypeExistException("Bu müşterinin bu kredi tipinde başvurusu mevcut!", HttpStatus.UNPROCESSABLE_ENTITY.value());
        }else
        {
            Application application = applicationRepository.save(createApplicationRequest.toApplication());
            EventRabbitApplicationRequestDto eventDto = new EventRabbitApplicationRequestDto(createApplicationRequest.getCustomerNo(),createApplicationRequest.getCreditAmount(),application.getApplicationNo());
            rabbitTemplate.convertAndSend("eventexchange", "creditapplication", eventDto);
            return application;
        }

    }

    @Override
    public void deleteApplication(long id) {
        applicationRepository.deleteById(id);
    }
    @Override
    public void updateApplicationInfo(UpdateApplicationRequest updateApplicationRequest ) {
        Application application = applicationRepository.findById(updateApplicationRequest
                        .getApplicationNo())
                .orElseThrow();
        application.setStatus(updateApplicationRequest.getStatus());
        applicationRepository.save(application);
    }

}
