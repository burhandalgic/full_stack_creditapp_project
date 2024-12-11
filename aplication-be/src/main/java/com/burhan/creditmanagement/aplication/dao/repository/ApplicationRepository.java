package com.burhan.creditmanagement.aplication.dao.repository;
import com.burhan.creditmanagement.aplication.dao.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application,Long> {
    boolean existsByCustomerNoAndCreditType(Long customerNo, Integer creditType);

}
