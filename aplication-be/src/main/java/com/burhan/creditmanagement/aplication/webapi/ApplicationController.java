package com.burhan.creditmanagement.aplication.webapi;

import com.burhan.creditmanagement.aplication.business.services.internal.ApplicationService;
import com.burhan.creditmanagement.aplication.business.dto.CreateApplicationRequest;
import com.burhan.creditmanagement.aplication.business.dto.UpdateApplicationRequest;
import com.burhan.creditmanagement.aplication.business.dto.GetApplicationResponse;
import com.burhan.creditmanagement.aplication.dao.entities.Application;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/applications")
@AllArgsConstructor
public class ApplicationController {
    ApplicationService applicationService;
    @GetMapping()
    public List<GetApplicationResponse> getApplicationList(){
        return applicationService.getApplicationList();
    }

    @GetMapping("/{id}")
    public GetApplicationResponse getApplicationByApplicationNo (@PathVariable long id) {
        return  applicationService.getApplicationByApplicationNo(id);
    }

    @PostMapping()
    public Application inquiryApplication(@RequestBody @Valid CreateApplicationRequest createApplicationRequest )  {
        return applicationService.inquiryApplication(createApplicationRequest);
    }

    @PutMapping //update bu şekilde yapılır
    public void updateApplicationInfo(@RequestBody @Valid UpdateApplicationRequest updateApplicationRequest ){
        applicationService.updateApplicationInfo(updateApplicationRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteApplication( @PathVariable long id){
        applicationService.deleteApplication(id);
    }

}
