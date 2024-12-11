package com.burhan.creditmanagement.aplication.aspect;

import com.burhan.creditmanagement.aplication.dao.entities.MailLog;
import com.burhan.creditmanagement.aplication.dao.repository.MailLogRepository;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import java.util.Date;

@Aspect
@Component
@AllArgsConstructor
public class EmailLogAspect {

    private MailLogRepository mailLogRepository;

    @AfterReturning(value = "execution(void com.burhan.creditmanagement.aplication.business.services.internal.SendEmailManager.sendEmail(String, String, String, String))")
    public void logEmailDetails(JoinPoint joinPoint) {
        // Gönderilen email adresini ve içeriğini metod parametrelerinden alıyoruz
        String emailParameter = (String) joinPoint.getArgs()[0]; // Email adresi
        String emailTextParameter = (String) joinPoint.getArgs()[2];  // Email içeriği
        String logStatusParameter = (String) joinPoint.getArgs()[3];        // Emailin durumu ("OK" veya "RED")

        // Veritabanına log kaydını oluşturuyoruz
        MailLog mailLog = new MailLog();
        mailLog.setEmail(emailParameter);
        mailLog.setEmailText(emailTextParameter);
        mailLog.setLogStatus(logStatusParameter);
        mailLog.setSentTime(new Date());
        mailLogRepository.save(mailLog);

    }


}

