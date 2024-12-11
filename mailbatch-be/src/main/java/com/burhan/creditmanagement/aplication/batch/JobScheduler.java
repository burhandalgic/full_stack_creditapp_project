package com.burhan.creditmanagement.aplication.batch;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor

public class JobScheduler {
    private final JobLauncher jobLauncher;
    private final MailStatisticJob mailStatisticJob;

    @Scheduled(cron = "0 0/60 * * * ?") // her saat başı çalışacak şekilde cron ifadesi
    public void runJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters jobParameters = new JobParameters();  // Boş parametreleri ekliyoruz
        jobLauncher.run(mailStatisticJob.mailLogJob(), jobParameters);
    }
}