package com.burhan.creditmanagement.evaluation.dao.repository;
import com.burhan.creditmanagement.evaluation.dao.entities.CustomerLimit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerLimitRepository extends JpaRepository<CustomerLimit,Long> {
    boolean existsByCustomerNo(Long customerNo);
    Optional<CustomerLimit> findByCustomerNo(Long customerNo);


}
