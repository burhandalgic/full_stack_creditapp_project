package com.burhan.creditmanagement.aplication.repository;

import com.burhan.creditmanagement.aplication.entities.MailLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MailLogRepository extends JpaRepository<MailLog,Long> {
    List<MailLog> findBySentTimeBetween(Date start, Date end);

}
