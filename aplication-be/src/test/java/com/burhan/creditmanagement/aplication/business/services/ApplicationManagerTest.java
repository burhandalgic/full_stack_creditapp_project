package com.burhan.creditmanagement.aplication.business.services;

import com.burhan.creditmanagement.aplication.business.dto.GetApplicationResponse;
import com.burhan.creditmanagement.aplication.business.services.internal.ApplicationManager;
import com.burhan.creditmanagement.aplication.business.services.internal.SendEmailService;
import com.burhan.creditmanagement.aplication.business.services.external.customer.services.ClientCustomerService;
import com.burhan.creditmanagement.aplication.dao.entities.Application;
import com.burhan.creditmanagement.aplication.dao.repository.ApplicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationManagerTest {

    @Mock
    ApplicationRepository applicationRepository;

    @Mock
    private ClientCustomerService clientCustomerService;

    @Mock
    private SendEmailService sendEmailService;


    @InjectMocks
    ApplicationManager applicationManager;



    @Test
    void getAll_whenApplicationListAvailable_ThenReturnResponse() {
        List<Application> applicationList = new ArrayList<>();
        Application application = new Application();
        application.setApplicationNo(1L);
        applicationList.add(application);
        when(applicationRepository.findAll()).thenReturn(applicationList);
        List<GetApplicationResponse> response = applicationManager.getApplicationList();
        assertFalse(CollectionUtils.isEmpty(response));
        assertEquals(response.get(0).getApplicationNo(),application.getApplicationNo());
    }

    @Test
    void getAll_whenApplicationListEmpty_ThenReturnResponse() {
        when(applicationRepository.findAll()).thenReturn(new ArrayList<>());
        List<GetApplicationResponse> response = applicationManager.getApplicationList();
        assertTrue(CollectionUtils.isEmpty(response));
    }

    @Test
    void getById_whenInvalidId_ThenReturnException() {
        when(applicationRepository.findById(1L)).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class, ()-> applicationManager.getApplicationByApplicationNo(1L));
    }

    @Test
    void getById_whenValidId_ThenReturnResponse() {
        Application application = new Application();
        application.setCustomerNo(1L);
        application.setStatus(2);
        when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));
        GetApplicationResponse applicationResponce = applicationManager.getApplicationByApplicationNo(1L);
        assertEquals(applicationResponce.getStatus(),application.getStatus());
    }



}