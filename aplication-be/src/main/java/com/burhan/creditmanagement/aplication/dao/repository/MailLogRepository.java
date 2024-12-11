package com.burhan.creditmanagement.aplication.dao.repository;

import com.burhan.creditmanagement.aplication.dao.entities.MailLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailLogRepository extends JpaRepository<MailLog,Long> {
}
