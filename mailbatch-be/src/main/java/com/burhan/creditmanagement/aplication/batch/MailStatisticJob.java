package com.burhan.creditmanagement.aplication.batch;

import com.burhan.creditmanagement.aplication.entities.MailLog;
import com.burhan.creditmanagement.aplication.entities.MailLogStatistics;
import com.burhan.creditmanagement.aplication.repository.MailLogRepository;
import com.burhan.creditmanagement.aplication.repository.MailLogStatisticsRepository;
import lombok.Setter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

@Configuration
public class MailStatisticJob {
    private MailLogRepository mailLogRepository;
    private MailLogStatisticsRepository mailLogStatisticsRepository;
    private JobRepository jobRepository;
    private PlatformTransactionManager transactionManager;
    @Setter
    private DataSource postgresDataSource;
    private List<MailLog> mailLogList;
    private Integer okCount=0;
    private Integer redCount=0;

    public MailStatisticJob(MailLogRepository mailLogRepository, MailLogStatisticsRepository mailLogStatisticsRepository, JobRepository jobRepository, PlatformTransactionManager transactionManager, DataSource postgresDataSource) {
        this.mailLogRepository = mailLogRepository;
        this.mailLogStatisticsRepository = mailLogStatisticsRepository;
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.postgresDataSource = postgresDataSource;
    }

    @Bean
    public Job mailLogJob() {
        return new JobBuilder("mailLogJob",jobRepository)
                .start(mailLogStep())
                .next(mailLogStatistic())
                .build();
    }

    private Date getStartOfHour() {
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        java.time.LocalDateTime previousHourStart = now.minusHours(1).withMinute(0).withSecond(0).withNano(0);
        return java.sql.Timestamp.valueOf(previousHourStart);
    }

    private Date getEndOfHour() {
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        java.time.LocalDateTime previousHourEnd = now.minusHours(1).withMinute(59).withSecond(59).withNano(999999999);
        return java.sql.Timestamp.valueOf(previousHourEnd);
    }
    @Bean
    public Step mailLogStep() {
       return new StepBuilder("mailLogStep", jobRepository)
               .tasklet(getOkRedCount(),transactionManager)
               .allowStartIfComplete(true)
               .build();
   }
    @Bean
    public Tasklet getOkRedCount(){
        return (stepContribution,chunkContext) -> {
            mailLogList = mailLogRepository.findBySentTimeBetween(getStartOfHour(),getEndOfHour());
            if(mailLogList != null) {
                for (MailLog mailLLog:mailLogList) {
                    okCount += mailLLog.getLogStatus().equals("OK") ? 1 : 0;
                    redCount += mailLLog.getLogStatus().equals("RED") ? 1 : 0;
                }
            }
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Step mailLogStatistic() {
        return new StepBuilder("mailLogStep", jobRepository)
                .tasklet(mailLogStatisticTasklet(),transactionManager)
                .allowStartIfComplete(true)
                .build();
    }
    @Bean
    public Tasklet mailLogStatisticTasklet(){
        return (stepContribution,chunkContext) -> {
            MailLogStatistics statistics = new MailLogStatistics();
            statistics.setOkCount(this.okCount);
            statistics.setRedCount(this.redCount);
            statistics.setRecordTime(new Date());
            mailLogStatisticsRepository.save(statistics);
            okCount=0;
            redCount=0;
            return RepeatStatus.FINISHED;
        };
    }
}